package education.pl.planner.app.subtopic.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubtopicQueryRepository extends JpaRepository<SubtopicQueryDto, Integer> {

    List<SubtopicQueryDto> findAllByTopicId(Integer topicId);

    int countByTopicIdAndCompleted(Integer id, boolean completed);
    int countByTopicId(Integer id);
}
