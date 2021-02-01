package education.pl.planner.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponse {

    private final String type = "Bearer";
    private String token;
    private String username;

}
