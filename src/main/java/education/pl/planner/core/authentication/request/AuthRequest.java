package education.pl.planner.core.authentication.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
