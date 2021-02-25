package education.pl.planner.app.subtopic.dto;

import education.pl.planner.app.topic.dto.TopicDto;
import lombok.Setter;

public class SubtopicDto {

    private int id;
    private TopicDto topic;
    private String title;
    @Setter
    private boolean completed = false;

    public SubtopicDto(TopicDto topic, String title) {
        this.topic = topic;
        this.title = title;
    }

}