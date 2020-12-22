package education.pl.planner.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "SUBTOPICS")
public class Subtopic implements TopicManager{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @JsonBackReference
    private Topic topic;
    @NotEmpty
    private String title;
    private boolean completed = false;

    public Subtopic(Topic topic, String title) {
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
        if (!completed) {
            this.completed = true;
        }
    }
}
