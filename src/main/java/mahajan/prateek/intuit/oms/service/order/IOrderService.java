package mahajan.prateek.intuit.oms.service.order;

import mahajan.prateek.intuit.oms.api.model.order.CreateOrderRequest;
import mahajan.prateek.intuit.oms.api.model.order.SearchOrderRequest;
import mahajan.prateek.intuit.oms.repository.model.Order;

import java.util.List;

/**
 * Created by: pramahajan on 11/14/20 1:58 AM GMT+05:30
 */
public interface IOrderService {
    Integer createOrder(CreateOrderRequest createOrderRequest);

    Order getOrder(Integer orderId);

    List<Order> searchOrders(SearchOrderRequest searchOrderRequest);
}
