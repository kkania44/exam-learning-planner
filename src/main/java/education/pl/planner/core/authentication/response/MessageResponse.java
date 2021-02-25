package education.pl.planner.core.authentication.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MessageResponse {

    private String message;
    private boolean error;

}
