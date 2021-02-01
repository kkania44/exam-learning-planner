package education.pl.planner.service;

import education.pl.planner.domain.Role;
import education.pl.planner.domain.User;
import education.pl.planner.domain.UserRepository;
import education.pl.planner.payload.request.AuthRequest;
import education.pl.planner.payload.response.JwtResponse;
import education.pl.planner.payload.response.MessageResponse;
import education.pl.planner.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;

    public MessageResponse registerUser(AuthRequest authRequest) {
        if (existsByUsername(authRequest.getUsername())) {
            return new MessageResponse(String.format("User %s already exists", authRequest.getUsername()), true);
        }

        User user = new User(authRequest.getUsername(), encoder.encode(authRequest.getPassword()), Role.USER.getName());
        userRepository.save(user);
        logger.info("Registration of user type {}", user.getRole());

        return new MessageResponse("Registration successful", false);
    }

    public JwtResponse authenticateUser(AuthRequest authRequest) {
        logger.info(authRequest.toString());
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return new JwtResponse(jwt, authRequest.getUsername());
    }

    private boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }


}
