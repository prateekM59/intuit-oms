package mahajan.prateek.intuit.oms.api.controller.order;

import mahajan.prateek.intuit.oms.api.controller.user.UserController;
import mahajan.prateek.intuit.oms.api.model.order.CreateOrderRequest;
import mahajan.prateek.intuit.oms.api.model.order.SearchOrderRequest;
import mahajan.prateek.intuit.oms.exception.OMSBusinessException;
import mahajan.prateek.intuit.oms.repository.model.Order;
import mahajan.prateek.intuit.oms.service.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by: pramahajan on 11/14/20 3:44 AM GMT+05:30
 */

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final OrderService orderService;
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Inject
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createOrder(@RequestBody CreateOrderRequest createOrderRequest) throws OMSBusinessException {
        LOGGER.info("Create order: " + createOrderRequest);
        return orderService.createOrder(createOrderRequest);
    }

    @GetMapping(path = "/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrder(@PathVariable Integer orderId) {
        LOGGER.info("GET order: " + orderId);
        return orderService.getOrder(orderId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> searchOrders(@RequestParam Integer userId,
            @RequestParam(required = false) String email, @RequestParam(required = false) String phone,
            @RequestParam(required = false) String product, @RequestParam(required = false) Integer dateSearchType) {
        SearchOrderRequest searchOrderRequest = searchOrderRequest(userId, email, phone, product, dateSearchType);
        LOGGER.info("Search orders:" + searchOrderRequest);
        return orderService.searchOrders(searchOrderRequest);
    }

    private SearchOrderRequest searchOrderRequest(Integer userId,
            String email, String phone,
            String product, Integer dateSearchType) {

        SearchOrderRequest.DateSearchType  dateSearchType1= SearchOrderRequest.DateSearchType.LAST_15_MINS;

        if (dateSearchType == 1) {
            dateSearchType1 = SearchOrderRequest.DateSearchType.OLDER_THAN_15_MINS;
        }

        return SearchOrderRequest.builder()
                .withEmail(email)
                .withDateSearchType(dateSearchType1)
                .withPhone(phone)
                .withProduct(product)
                .withUserId(userId)
                .withEmail(email)
                .build();
    }

}
