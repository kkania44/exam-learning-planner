package education.pl.planner.app.topic;

import education.pl.planner.app.topic.dto.TopicDto;
import education.pl.planner.app.user.query.UserQueryDto;
import education.pl.planner.app.user.query.UserQueryRepository;
import education.pl.planner.app.exception.NotFoundException;
import education.pl.planner.core.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class TopicFacade {

    private final TopicRepository topicRepository;
    private final UserQueryRepository userRepository;
    private final JwtUtils jwtUtils;

    private final TopicMapper mapper = new TopicMapper();

    private static final String NOT_FOUND_MESSAGE = "Topic not found id = %d";

    public Topic add(TopicDto topicDto, String token) {
        Topic topic = mapper.mapToEntity(topicDto);

        UserQueryDto user = userRepository.findByUsername(usernameFrom(token))
                .orElseThrow(() -> new NotFoundException("User not found"));
        topic.setUser(user);
        return topicRepository.save(topic);
    }

    @Transactional
    public Topic update(TopicDto updatedTopic) {
        Topic topicToUpdate = topicRepository.findById(updatedTopic.getId())
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE, updatedTopic.getId())));
        topicToUpdate.rename(updatedTopic.getTitle());
        topicToUpdate.setDaysForLearning(updatedTopic.getDaysForLearning());

        if (updatedTopic.isCompleted()) {
            topicToUpdate.markAsCompleted();
        }
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
