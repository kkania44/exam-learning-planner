package education.pl.planner.app.subtopic;

import education.pl.planner.app.subtopic.dto.SubtopicDto;
import education.pl.planner.app.topic.query.TopicQueryDto;
import education.pl.planner.app.topic.query.TopicQueryRepository;
import education.pl.planner.app.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Slf4j
public class SubtopicFacade {

    private final SubtopicRepository subtopicRepository;
    private final TopicQueryRepository topicQuery;

    private final String NOT_FOUND_MESSAGE = "Subtopic not found id = %d";

    public Subtopic add(String subtopicTitle, Integer topicId) {
        TopicQueryDto topic = topicQuery.findById(topicId)
                .orElseThrow(() -> new NotFoundException("Topic not found"));
        return subtopicRepository.save(new Subtopic(topic, subtopicTitle));
    }

    @Transactional
    public Subtopic update(SubtopicDto updatedSubtopic) {
        Subtopic subtopicToUpdate = subtopicRepository.findById(updatedSubtopic.getId())
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE, updatedSubtopic.getId())));
        subtopicToUpdate.rename(updatedSubtopic.getTitle());
        if (updatedSubtopic.isCompleted()) {
            subtopicToUpdate.markAsCompleted();
        }
        return subtopicToUpdate;
    }

    public void delete(Integer id) {
        subtopicRepository.deleteById(id);
    }

}
