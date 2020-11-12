package mahajan.prateek.intuit.oms.service;

import mahajan.prateek.intuit.oms.api.model.UpsertUserRequest;
import mahajan.prateek.intuit.oms.repository.UserRepository;
import mahajan.prateek.intuit.oms.repository.model.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by: pramahajan on 11/13/20 2:55 AM GMT+05:30
 */

@Service
public class UserService {

    private final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer createUser(UpsertUserRequest upsertUserRequest) {
        User user = new User(upsertUserRequest.getName(), upsertUserRequest.getEmail(), upsertUserRequest.getPhone(), upsertUserRequest.getPhone(), upsertUserRequest.getType());
        return userRepository.save(user).getId();
    }
}
