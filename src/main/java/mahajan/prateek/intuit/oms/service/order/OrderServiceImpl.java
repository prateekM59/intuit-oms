package mahajan.prateek.intuit.oms.service.order;

import mahajan.prateek.intuit.oms.api.model.order.CreateOrderRequest;
import mahajan.prateek.intuit.oms.api.model.order.SearchOrderRequest;
import mahajan.prateek.intuit.oms.exception.OMSBusinessException;
import mahajan.prateek.intuit.oms.repository.model.Order;
import mahajan.prateek.intuit.oms.repository.model.Product;
import mahajan.prateek.intuit.oms.repository.model.User;
import mahajan.prateek.intuit.oms.repository.order.OrderRepository;
import mahajan.prateek.intuit.oms.service.user.UserService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by: pramahajan on 11/14/20 3:23 AM GMT+05:30
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    @Inject
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Override
    public Integer createOrder(CreateOrderRequest createOrderRequest) throws OMSBusinessException {
        Order order = new Order();

        if (!EnumUtils.isValidEnum(Product.class, createOrderRequest.getProduct())) {
            throw new OMSBusinessException("No such product: " + createOrderRequest.getProduct());
        }

        Product product = Enum.valueOf(Product.class, createOrderRequest.getProduct());
        Integer quantity = createOrderRequest.getQuantity();

        order.setProduct(product.name());
        order.setQuantity(quantity);
        order.setPrice(quantity*product.getPrice());

        ZonedDateTime utcTimeNow = ZonedDateTime.now(ZoneId.of("UTC"));
        order.setDate(Timestamp.from(utcTimeNow.toInstant()));

        populateUserInfo(order, createOrderRequest.getUserId());

        return orderRepository.save(order).getId();
    }

    private void populateUserInfo(Order order, Integer userId) {
        User user = userService.getUser(userId);
        order.setUserId(user.getId());
        order.setAddress(user.getAddress());
        order.setEmail(user.getEmail());
        order.setPhone(user.getPhone());

    }

    @Override
    public Order getOrder(Integer orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException("Order not found with orderId " + orderId));
    }

    @Override
    public List<Order> searchOrders(SearchOrderRequest searchOrderRequest) {
        return orderRepository.searchOrders(searchOrderRequest);
    }
}
