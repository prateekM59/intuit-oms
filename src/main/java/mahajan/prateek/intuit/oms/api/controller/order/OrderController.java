package mahajan.prateek.intuit.oms.api.controller.order;

import mahajan.prateek.intuit.oms.api.model.order.CreateOrderRequest;
import mahajan.prateek.intuit.oms.api.model.order.SearchOrderRequest;
import mahajan.prateek.intuit.oms.repository.model.Order;
import mahajan.prateek.intuit.oms.service.order.IOrderService;
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
import java.util.List;

/**
 * Created by: pramahajan on 11/14/20 3:44 AM GMT+05:30
 */

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final IOrderService orderService;

    @Inject
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return orderService.createOrder(createOrderRequest);
    }

    @GetMapping(path = "/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrder(@PathVariable Integer orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> searchOrders(@RequestParam Integer userId,
            @RequestParam String email, @RequestParam String phone,
            @RequestParam String product, @RequestParam String dateSearchType) {

        SearchOrderRequest searchOrderRequest = searchOrderRequest(userId, email, phone, product, dateSearchType);
        return orderService.searchOrders(searchOrderRequest);
    }

    private SearchOrderRequest searchOrderRequest(Integer userId,
            String email, String phone,
             String product, String dateSearchType) {

        return SearchOrderRequest.builder()
                .withEmail(email)
                .withDateSearchType(Enum.valueOf( SearchOrderRequest.DateSearchType.class, dateSearchType))
                .withPhone(phone)
                .withProduct(product)
                .withUserId(userId)
                .withEmail(email)
                .build();
    }

}
