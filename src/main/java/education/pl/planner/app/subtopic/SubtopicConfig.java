package education.pl.planner.app.subtopic;

import education.pl.planner.app.topic.query.TopicQueryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SubtopicConfig {

    @Bean
    SubtopicFacade subtopicFacade(SubtopicRepository subtopicRepository, TopicQueryRepository topicQuery) {
        return new SubtopicFacade(subtopicRepository, topicQuery);
    }

}
