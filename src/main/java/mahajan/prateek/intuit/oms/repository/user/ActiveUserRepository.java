package mahajan.prateek.intuit.oms.repository.user;

import mahajan.prateek.intuit.oms.repository.model.ActiveUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by: pramahajan on 11/16/20 11:50 PM GMT+05:30
 */
public interface ActiveUserRepository extends CrudRepository<ActiveUser, Integer> {
    ActiveUser findByActive(Boolean active);
}
