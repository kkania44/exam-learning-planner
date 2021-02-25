package education.pl.planner.app.topic;

import education.pl.planner.app.topic.query.TopicQueryFacade;
import education.pl.planner.app.topic.query.TopicQueryRepository;
import education.pl.planner.app.user.query.UserQueryRepository;
import education.pl.planner.core.security.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TopicConfig {

    @Bean
    TopicFacade topicFacade(TopicRepository topicRepository, UserQueryRepository userRepository, JwtUtils jwtUtils) {
        return new TopicFacade(topicRepository, userRepository, jwtUtils);
    }

    @Bean
    public TopicQueryFacade topicQueryFacade(TopicQueryRepository topicQuery,
                                             UserQueryRepository userQuery,
                                             JwtUtils jwtUtils) {
        return new TopicQueryFacade(topicQuery,userQuery, jwtUtils);
    }

}
