package education.pl.planner.app.user;

import education.pl.planner.app.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public void add(UserDto userDto) {
        if (userDto.getPassword().isBlank() || userDto.getUsername().isBlank()) {
            throw new RuntimeException("Invalid user's data");
        }

        User user = new User(userDto.getUsername(), encoder.encode(userDto.getPassword()), userDto.getRole());

        userRepository.save(user);
    }

}
