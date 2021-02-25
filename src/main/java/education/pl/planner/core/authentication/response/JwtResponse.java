package education.pl.planner.core.authentication.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponse {

    private String token;
    private String username;

}
