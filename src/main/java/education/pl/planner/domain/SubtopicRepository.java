package education.pl.planner.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubtopicRepository extends JpaRepository<Subtopic, Integer> {

    List<Subtopic> findAllByTopic(Topic topic);

    int countByTopicIdAndCompleted(Integer id, boolean completed);
    int countByTopicId(Integer id);

}
