package mahajan.prateek.intuit.oms.api.controller;

import mahajan.prateek.intuit.oms.api.model.UpsertUserRequest;
import mahajan.prateek.intuit.oms.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by: pramahajan on 11/13/20 3:09 AM GMT+05:30
 */

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Integer createUser(@RequestBody UpsertUserRequest upsertUserRequest) {
        return userService.createUser(upsertUserRequest);
    }

}
