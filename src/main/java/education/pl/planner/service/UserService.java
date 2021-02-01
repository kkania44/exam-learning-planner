package education.pl.planner.service;

import education.pl.planner.domain.User;
import education.pl.planner.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public void add(User user) {
        if (user.getPassword().isBlank() || user.getUsername().isBlank()) {
            throw new RuntimeException("Invalid user's data");
        }
        userRepository.save(new User(
                user.getUsername(),
                encoder.encode(user.getPassword()),
                user.getRole()));
    }
}
