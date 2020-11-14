package mahajan.prateek.intuit.oms.service.order;

import mahajan.prateek.intuit.oms.api.model.order.CreateOrderRequest;
import mahajan.prateek.intuit.oms.api.model.order.SearchOrderRequest;
import mahajan.prateek.intuit.oms.repository.model.Order;
import mahajan.prateek.intuit.oms.repository.model.User;
import mahajan.prateek.intuit.oms.repository.order.IOrderRepository;
import mahajan.prateek.intuit.oms.service.user.IUserService;
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
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IUserService userService;

    @Inject
    public OrderService(IOrderRepository orderRepository, IUserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Override
    public Integer createOrder(CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        order.setProduct(createOrderRequest.getProduct());
        Integer quantity = createOrderRequest.getQuantity();
        order.setPrice(quantity*createOrderRequest.getProduct().getPrice());
        order.setQuantity(quantity);

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
        return null;
    }
}
