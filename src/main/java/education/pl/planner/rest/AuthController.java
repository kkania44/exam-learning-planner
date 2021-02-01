package education.pl.planner.rest;

import education.pl.planner.payload.request.AuthRequest;
import education.pl.planner.payload.response.JwtResponse;
import education.pl.planner.payload.response.MessageResponse;
import education.pl.planner.service.AuthService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody AuthRequest authRequest) {
        MessageResponse response = service.registerUser(authRequest);

        return response.isError() ? ResponseEntity.badRequest().body(response) : ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(service.authenticateUser(authRequest));
    }

}
