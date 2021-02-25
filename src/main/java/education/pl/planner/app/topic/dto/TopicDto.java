package education.pl.planner.app.topic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {

    private int id;
    private String title;
    private int daysForLearning;
    @Builder.Default
    private boolean completed = false;

    public TopicDto(String title, int daysForLearning) {
        this.title = title;
        this.daysForLearning = daysForLearning;
    }
}


