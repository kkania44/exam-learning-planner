package education.pl.planner.service;

import education.pl.planner.domain.Subtopic;
import education.pl.planner.domain.SubtopicRepository;
import education.pl.planner.domain.Topic;
import education.pl.planner.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SubtopicService {

    private SubtopicRepository subtopicRepository;
    private TopicService topicService;

    private final String NOT_FOUND_MESSAGE = "Topic not found id = %d";


    public List<Subtopic> getAllSubtopicsForTopic(String title) {
        List<Topic> topicsByName = topicService.getTopicByTitle(title);
        if (topicsByName.isEmpty()) {
            return new ArrayList<>();
        }
        Topic topicToSearch = topicsByName.get(0);
        return subtopicRepository.findAllByTopic(topicToSearch);
    }

    public List<Subtopic> getAllSubtopicsForTopic(Integer id) {
        Topic topicById = topicService.getTopicById(id);
        return subtopicRepository.findAllByTopic(topicById);
    }

    public String getProgressInTopic(Integer id) {
        int allSubtopicsCount = subtopicRepository.countByTopicId(id);
        int completedSubtopicsCount = subtopicRepository.countByTopicIdAndCompleted(id, true);
        return completedSubtopicsCount + "/" + allSubtopicsCount;
    }

    public Subtopic add(String subtopicTitle, Integer topicId) {
        Topic topic = topicService.getTopicById(topicId);
        return subtopicRepository.save(new Subtopic(topic, subtopicTitle));
    }

    @Transactional
    public Subtopic update(Subtopic updatedSubtopic) {
        Subtopic subtopicToUpdate = subtopicRepository.findById(updatedSubtopic.getId())
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE, updatedSubtopic.getId())));
        subtopicToUpdate.rename(updatedSubtopic.getTitle());
        if (updatedSubtopic.isCompleted()) {
            subtopicToUpdate.markAsCompleted();
        }
        return subtopicToUpdate;
    }

    public void delete(Integer id) {
        subtopicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE, id)));
        subtopicRepository.deleteById(id);
    }

}
