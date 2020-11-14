package mahajan.prateek.intuit.oms.service.user;

import mahajan.prateek.intuit.oms.api.model.user.UpsertUserRequest;
import mahajan.prateek.intuit.oms.repository.model.User;

/**
 * Created by: pramahajan on 11/14/20 1:53 AM GMT+05:30
 */
public interface IUserService {
    Integer createUser(UpsertUserRequest upsertUserRequest);

    User getUser(Integer userId);

    void updateUser(UpsertUserRequest upsertUserRequest, Integer userId);
}
