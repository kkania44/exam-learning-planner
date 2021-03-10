package education.pl.planner.app.subtopic;

import com.fasterxml.jackson.annotation.JsonBackReference;
import education.pl.planner.app.topic.query.TopicQueryDto;
import education.pl.planner.app.shared.TopicManager;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "SUBTOPICS")
class Subtopic implements TopicManager {

    @Id
    @GeneratedValue(generator = "sequence_generator")
    @GenericGenerator(
            name = "sequence_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "subtopic_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_value", value = "1")
            }
    )
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @JsonBackReference
    private TopicQueryDto topic;
    @NotEmpty
    private String title;
    private boolean completed = false;

    public Subtopic(TopicQueryDto topic, String title) {
        this.topic = topic;
        this.title = title;
    }

    @Override
    public void rename(String newTitle) {
        if (!newTitle.isBlank()) {
            this.title = newTitle;
        }
    }

    @Override
    public void markAsCompleted() {
        this.completed = true;
    }
}
