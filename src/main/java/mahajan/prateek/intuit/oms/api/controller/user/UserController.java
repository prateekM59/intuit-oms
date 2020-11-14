package mahajan.prateek.intuit.oms.api.controller.user;

import mahajan.prateek.intuit.oms.api.model.user.UpsertUserRequest;
import mahajan.prateek.intuit.oms.repository.model.User;
import mahajan.prateek.intuit.oms.service.user.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by: pramahajan on 11/13/20 3:09 AM GMT+05:30
 */

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final IUserService userService;

    @Inject
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createUser(@RequestBody UpsertUserRequest upsertUserRequest) {
        return userService.createUser(upsertUserRequest);
    }

    @GetMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable Integer userId) {
        return userService.getUser(userId);
    }

    @PutMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@RequestBody UpsertUserRequest upsertUserRequest, @PathVariable Integer userId) {
        userService.updateUser(upsertUserRequest, userId);
    }

}
