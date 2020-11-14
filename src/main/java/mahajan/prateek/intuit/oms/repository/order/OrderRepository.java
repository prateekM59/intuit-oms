package mahajan.prateek.intuit.oms.repository.order;

import mahajan.prateek.intuit.oms.api.model.order.SearchOrderRequest;
import mahajan.prateek.intuit.oms.repository.model.Order;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by: pramahajan on 11/14/20 5:36 AM GMT+05:30
 */
@Repository
public class OrderRepository implements IOrderRepository {
    @Override
    public List<Order> searchOrders(SearchOrderRequest searchOrderRequest) {
        return null;
    }
}
