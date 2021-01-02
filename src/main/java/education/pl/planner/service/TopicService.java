package education.pl.planner.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import education.pl.planner.domain.Topic;
import education.pl.planner.domain.TopicRepository;
import education.pl.planner.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicService {

    private TopicRepository topicRepository;
    private final String NOT_FOUND_MESSAGE = "Topic not found id = %d";

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public List<Topic> getTopicByTitle(String title) {
        return topicRepository.findAllByTitle(title);
    }

    public Topic getTopicById(Integer id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE, id)));
    }

    public Topic add(Topic topic) {
        return topicRepository.save(topic);
    }

    @Transactional
    public Topic update(Topic updatedTopic) {
        Topic topicToUpdate = topicRepository.findById(updatedTopic.getId())
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE, updatedTopic.getId())));
        topicToUpdate.rename(updatedTopic.getTitle());
        topicToUpdate.setDaysForLearning(updatedTopic.getDaysForLearning());
        return topicToUpdate;
    }

    @Transactional
    public void startTopic(Integer topicId) {
        Topic topicToStart = topicRepository.findById(topicId)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE, topicId)));
        topicToStart.start();
    }

    public void deleteById(Integer id) {
        topicRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE, id)));
        topicRepository.deleteById(id);
    }
}
