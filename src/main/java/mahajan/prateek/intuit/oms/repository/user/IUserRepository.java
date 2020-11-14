package mahajan.prateek.intuit.oms.repository.user;

import mahajan.prateek.intuit.oms.repository.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by: pramahajan on 11/11/20 4:07 AM GMT+05:30
 */
public interface IUserRepository extends CrudRepository<User, Integer> {
}
