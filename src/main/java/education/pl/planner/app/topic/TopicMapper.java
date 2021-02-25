package education.pl.planner.app.topic;

import education.pl.planner.app.topic.dto.TopicDto;

class TopicMapper {

    Topic mapToEntity(TopicDto dto) {
        return Topic.builder()
                .title(dto.getTitle())
                .daysForLearning(dto.getDaysForLearning())
                .build();
    }

}
