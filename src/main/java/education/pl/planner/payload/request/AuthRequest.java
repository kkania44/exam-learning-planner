package education.pl.planner.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AuthRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
