package education.pl.planner.service;

import education.pl.planner.domain.Topic;
import education.pl.planner.domain.TopicRepository;
import education.pl.planner.domain.User;
import education.pl.planner.domain.UserRepository;
import education.pl.planner.exception.NotFoundException;
import education.pl.planner.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TopicService {

    private TopicRepository topicRepository;
    private UserRepository userRepository;
    private JwtUtils jwtUtils;

    private final String NOT_FOUND_MESSAGE = "Topic not found id = %d";

    public List<Topic> getAllTopicsForUser(String token) {
        User user = userRepository.findByUsername(usernameFrom(token))
                .orElseThrow(() -> new NotFoundException("User not found"));
        return topicRepository.findAllByUser(user);
    }

    public List<Topic> getTopicByTitle(String title) {
        return topicRepository.findAllByTitle(title);
    }

    public Topic getTopicById(Integer id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE, id)));
    }

    public Topic add(Topic topic, String token) {
        User user = userRepository.findByUsername(usernameFrom(token))
                .orElseThrow(() -> new NotFoundException("User not found"));
        topic.setUser(user);
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

    private String usernameFrom(String token) {
        return jwtUtils.getUsernameFromJwtToken(token);
    }
}
