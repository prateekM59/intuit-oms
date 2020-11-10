package mahajan.prateek.intuit.oms;

import mahajan.prateek.intuit.oms.repository.UserRepository;
import mahajan.prateek.intuit.oms.repository.model.User;
import mahajan.prateek.intuit.oms.repository.model.UsersType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by: pramahajan on 11/7/20 2:50 PM GMT+05:30
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final UserRepository userRepository;

    public Application(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("RUNNING CRAZY MAHAJAN");
        userRepository.save(new User("user1", "abc@gmail.com", "97777", "1/881 Delhi", UsersType.NORMAL));
    }


}