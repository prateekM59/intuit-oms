package mahajan.prateek.intuit.oms.repository.order;

import mahajan.prateek.intuit.oms.api.model.order.SearchOrderRequest;
import mahajan.prateek.intuit.oms.repository.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by: pramahajan on 11/11/20 4:09 AM GMT+05:30
 */
public interface IOrderRepository extends JpaRepository<Order, Integer> {

    List<Order> searchOrders(SearchOrderRequest searchOrderRequest);

}
