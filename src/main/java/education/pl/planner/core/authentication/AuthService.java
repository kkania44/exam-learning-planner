package education.pl.planner.core.authentication;

import education.pl.planner.app.user.UserFacade;
import education.pl.planner.app.user.dto.Role;
import education.pl.planner.app.user.dto.UserDto;
import education.pl.planner.app.user.query.UserQueryRepository;
import education.pl.planner.core.authentication.request.AuthRequest;
import education.pl.planner.core.authentication.response.JwtResponse;
import education.pl.planner.core.authentication.response.MessageResponse;
import education.pl.planner.core.security.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
class AuthService {

    private final UserFacade userFacade;
    private final UserQueryRepository userQuery;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;

    public MessageResponse registerUser(AuthRequest authRequest) {
        if (existsByUsername(authRequest.getUsername())) {
            return new MessageResponse(String.format("User %s already exists", authRequest.getUsername()), true);
        }

        UserDto user = new UserDto(authRequest.getUsername(), encoder.encode(authRequest.getPassword()), Role.USER.getName());
        userFacade.add(user);
        log.info("Registration of user type {}", user.getRole());

        return new MessageResponse("Registration successful", false);
    }

    public JwtResponse authenticateUser(AuthRequest authRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return new JwtResponse(jwt, authRequest.getUsername());
    }

    private boolean existsByUsername(String username) {
        return userQuery.findByUsername(username).isPresent();
    }


}
