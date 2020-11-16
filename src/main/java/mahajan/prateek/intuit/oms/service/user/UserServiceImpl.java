package mahajan.prateek.intuit.oms.service.user;

import mahajan.prateek.intuit.oms.api.model.user.UpsertUserRequest;
import mahajan.prateek.intuit.oms.exception.OMSBusinessException;
import mahajan.prateek.intuit.oms.repository.model.ActiveUser;
import mahajan.prateek.intuit.oms.repository.user.ActiveUserRepository;
import mahajan.prateek.intuit.oms.repository.user.UserRepository;
import mahajan.prateek.intuit.oms.repository.model.User;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by: pramahajan on 11/13/20 2:55 AM GMT+05:30
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ActiveUserRepository activeUserRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ActiveUserRepository activeUserRepository) {
        this.userRepository = userRepository;
        this.activeUserRepository = activeUserRepository;
    }

    @Override
    public Integer createUser(UpsertUserRequest upsertUserRequest) {
        User user = new User(upsertUserRequest.getName(), upsertUserRequest.getEmail(), upsertUserRequest.getPhone(), upsertUserRequest.getAddress(), upsertUserRequest.getType());
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

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void markActive(UpsertUserRequest upsertUserRequest) {
        String email = upsertUserRequest.getEmail();
        User user = findByEmail(email);
        Integer userId = null;
        if (user == null) {
            userId = createUser(upsertUserRequest);
        } else {
            userId = user.getId();
        }

        markActive(userId);
    }

    @Override
    public User getActive() throws OMSBusinessException {
        ActiveUser activeUser = activeUserRepository.findByActive(true);
        if (activeUser == null) {
            throw new OMSBusinessException("No active user");
        }

        return getUser(activeUser.getUserId());
    }

    @Override
    public void deleteActive() {
        ActiveUser activeUser = activeUserRepository.findByActive(true);
        activeUserRepository.delete(activeUser);
    }

    private void markActive(Integer userId) {
        ActiveUser activeUser = activeUserRepository.findByActive(true);
        if (activeUser == null) {
            activeUser = new ActiveUser();
        }
        activeUser.setUserId(userId);
        activeUser.setActive(true);

        activeUserRepository.save(activeUser);
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
