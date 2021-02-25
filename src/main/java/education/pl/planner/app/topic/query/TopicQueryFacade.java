package education.pl.planner.app.topic.query;

import education.pl.planner.app.user.query.UserQueryDto;
import education.pl.planner.app.user.query.UserQueryRepository;
import education.pl.planner.app.exception.NotFoundException;
import education.pl.planner.core.security.JwtUtils;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TopicQueryFacade {

    private final TopicQueryRepository topicQuery;
    private final UserQueryRepository userQuery;
    private final JwtUtils jwtUtils;

    public List<TopicQueryDto> getAllTopicsForUser(String token) {
        UserQueryDto user = userQuery.findByUsername(usernameFrom(token))
                .orElseThrow(() -> new NotFoundException("User not found"));
        return topicQuery.findAllByUser(user);
    }

    public TopicQueryDto getByIdAndUser(int id, String token) {
        UserQueryDto user = userQuery.findByUsername(usernameFrom(token))
                .orElseThrow(() -> new NotFoundException("User not found"));

        return topicQuery.findByIdAndUser(id, user)
                .orElseThrow(() -> new NotFoundException("Topic not found"));
    }

    private String usernameFrom(String token) {
        return jwtUtils.getUsernameFromJwtToken(token);
    }
}
