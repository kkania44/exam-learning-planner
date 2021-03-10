package education.pl.planner.app.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class UserConfig {

    @Bean
    public UserFacade userFacade(UserRepository repository) {
        return new UserFacade(repository);
    }
}
