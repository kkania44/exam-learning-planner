package education.pl.planner.app.topic.query;

import education.pl.planner.app.user.query.UserQueryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicQueryRepository extends JpaRepository<TopicQueryDto, Integer> {

    List<TopicQueryDto> findAllByUser(UserQueryDto user);

    Optional<TopicQueryDto> findByIdAndUser(int id, UserQueryDto user);
}
