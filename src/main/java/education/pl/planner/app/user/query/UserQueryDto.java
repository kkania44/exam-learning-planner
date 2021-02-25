package education.pl.planner.app.user.query;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@Getter
public class UserQueryDto {

    @Id
    private int id;
    private String username;
    private String password;
    private String role;

}
