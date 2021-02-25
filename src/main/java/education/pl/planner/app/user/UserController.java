package education.pl.planner.app.user;

import education.pl.planner.app.user.dto.UserDto;
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
class UserController {

    private final UserFacade userFacade;

    @PostMapping("/new")
    ResponseEntity<String> add(@RequestBody UserDto user) {
        userFacade.add(user);
        return new ResponseEntity<>("User added: " +user.getUsername(), HttpStatus.CREATED);
    }

}
