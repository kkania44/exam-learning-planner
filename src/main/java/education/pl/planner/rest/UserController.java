package education.pl.planner.rest;

import education.pl.planner.domain.User;
import education.pl.planner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    ResponseEntity<String> add(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>("User added: " +user.getUsername(), HttpStatus.CREATED);
    }

}
