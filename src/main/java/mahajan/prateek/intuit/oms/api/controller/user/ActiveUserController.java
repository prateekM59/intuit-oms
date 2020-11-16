package mahajan.prateek.intuit.oms.api.controller.user;

import mahajan.prateek.intuit.oms.api.model.user.UpsertUserRequest;
import mahajan.prateek.intuit.oms.exception.OMSBusinessException;
import mahajan.prateek.intuit.oms.repository.model.User;
import mahajan.prateek.intuit.oms.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by: pramahajan on 11/17/20 12:40 AM GMT+05:30
 */
@RestController
@RequestMapping(path = "/users/active")
public class ActiveUserController {

    private final UserService userService;
    private final static Logger LOGGER = LoggerFactory.getLogger(ActiveUserController.class);

    @Inject
    public ActiveUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void markActive(@RequestBody UpsertUserRequest upsertUserRequest) {
        LOGGER.info("Marking user active " + upsertUserRequest);
        userService.markActive(upsertUserRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteActive() {
        LOGGER.info("Deleting active user");
        userService.deleteActive();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public User getActive() throws OMSBusinessException {
        LOGGER.info("GET active user");
        return userService.getActive();
    }
}
