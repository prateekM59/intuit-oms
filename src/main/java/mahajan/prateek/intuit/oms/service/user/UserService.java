package mahajan.prateek.intuit.oms.service.user;

import mahajan.prateek.intuit.oms.api.model.user.UpsertUserRequest;
import mahajan.prateek.intuit.oms.repository.user.IUserRepository;
import mahajan.prateek.intuit.oms.repository.model.User;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by: pramahajan on 11/13/20 2:55 AM GMT+05:30
 */

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Inject
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Integer createUser(UpsertUserRequest upsertUserRequest) {
        User user = new User(upsertUserRequest.getName(), upsertUserRequest.getEmail(), upsertUserRequest.getPhone(), upsertUserRequest.getPhone(), upsertUserRequest.getType());
        return userRepository.save(user).getId();
    }

    @Override
    public User getUser(Integer userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with userId " + userId));
    }

    @Override
    public void updateUser(UpsertUserRequest upsertUserRequest, Integer userId) {
        User user = getUpdatedUser(upsertUserRequest, userId);
        userRepository.save(user);
    }

    private User getUpdatedUser(UpsertUserRequest upsertUserRequest, Integer userId) {
        User user = getUser(userId);
        user.setEmail(upsertUserRequest.getEmail());
        user.setPhone(upsertUserRequest.getPhone());
        user.setType(upsertUserRequest.getType());
        user.setName(upsertUserRequest.getName());
        user.setAddress(upsertUserRequest.getAddress());
        return user;
    }
}
