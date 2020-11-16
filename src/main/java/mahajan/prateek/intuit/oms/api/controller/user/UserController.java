package mahajan.prateek.intuit.oms.api.controller.user;

import mahajan.prateek.intuit.oms.api.model.user.UpsertUserRequest;
import mahajan.prateek.intuit.oms.repository.model.User;
import mahajan.prateek.intuit.oms.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by: pramahajan on 11/13/20 3:09 AM GMT+05:30
 */

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createUser(@RequestBody UpsertUserRequest upsertUserRequest) {
        LOGGER.info("Create user " + upsertUserRequest);
        return userService.createUser(upsertUserRequest);
    }

    @GetMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable Integer userId) {
        LOGGER.info("GET user " + userId);
        return userService.getUser(userId);
    }

    @PutMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@RequestBody UpsertUserRequest upsertUserRequest, @PathVariable Integer userId) {
        LOGGER.info("PUT user " + userId);
        userService.updateUser(upsertUserRequest, userId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public User findByEmail(@RequestParam String email) {
        LOGGER.info("GET user by email " + email);
        return userService.findByEmail(email);
    }

}
