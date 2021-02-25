package education.pl.planner.app.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TopicRepository extends JpaRepository<Topic, Integer> {

}
