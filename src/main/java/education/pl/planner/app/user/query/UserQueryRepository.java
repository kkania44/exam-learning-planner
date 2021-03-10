package education.pl.planner.app.user.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQueryRepository extends JpaRepository<UserQueryDto, Integer> {

    Optional<UserQueryDto> findByUsername(String username);

}
