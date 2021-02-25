package education.pl.planner.app.subtopic.query;

import com.fasterxml.jackson.annotation.JsonBackReference;
import education.pl.planner.app.topic.query.TopicQueryDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "SUBTOPICS")
@NoArgsConstructor
@Getter
public class SubtopicQueryDto {

    @Id
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @JsonBackReference
    private TopicQueryDto topic;
    @NotEmpty
    private String title;
    private boolean completed = false;

}
