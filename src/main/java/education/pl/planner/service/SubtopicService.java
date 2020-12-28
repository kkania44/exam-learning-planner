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

    public Subtopic add(String subtopicTitle, Integer topicId) {
        Topic topic = topicService.getTopicById(topicId);
        return subtopicRepository.save(new Subtopic(topic, subtopicTitle));
    }

    @Transactional
    public Subtopic update(Subtopic updatedSubtopic) {
        Subtopic subtopicToUpdate = subtopicRepository.findById(updatedSubtopic.getId())
                .orElseThrow(() -> new NotFoundException("Subtopic not found id = " + updatedSubtopic.getId()));
        subtopicToUpdate.rename(updatedSubtopic.getTitle());
        return subtopicToUpdate;
    }

    public void delete(Integer id) {
        subtopicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subtopic not found id = " +id));
        subtopicRepository.deleteById(id);
    }

}
