package mahajan.prateek.intuit.oms.repository;

import mahajan.prateek.intuit.oms.repository.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by: pramahajan on 11/11/20 4:07 AM GMT+05:30
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
