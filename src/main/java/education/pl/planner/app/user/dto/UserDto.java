package education.pl.planner.app.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private String username;
    private String password;
    private String role;

}
