package mahajan.prateek.intuit.oms.repository;

import mahajan.prateek.intuit.oms.repository.model.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by: pramahajan on 11/11/20 4:09 AM GMT+05:30
 */
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
