package education.pl.planner.app.subtopic.dto;

import education.pl.planner.app.topic.dto.TopicDto;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SubtopicDto {

    private int id;
    private String title;
    @Setter
    private boolean completed = false;


}
