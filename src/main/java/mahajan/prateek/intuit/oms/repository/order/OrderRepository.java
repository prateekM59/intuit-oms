package mahajan.prateek.intuit.oms.repository.order;

import mahajan.prateek.intuit.oms.repository.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by: pramahajan on 11/11/20 4:09 AM GMT+05:30
 */
public interface OrderRepository extends JpaRepository<Order, Integer>, CustomOrderRepository {

}
