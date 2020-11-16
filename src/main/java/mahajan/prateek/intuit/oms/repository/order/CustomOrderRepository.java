package mahajan.prateek.intuit.oms.repository.order;

import mahajan.prateek.intuit.oms.api.model.order.SearchOrderRequest;
import mahajan.prateek.intuit.oms.repository.model.Order;

import java.util.List;

/**
 * Created by: pramahajan on 11/14/20 6:54 PM GMT+05:30
 */
public interface CustomOrderRepository {
    List<Order> searchOrders(SearchOrderRequest searchOrderRequest);
}
