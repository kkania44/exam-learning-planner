package education.pl.planner.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Entity
@Table(name = "SUBTOPICS")
public class Subtopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @NotEmpty
    private String title;
    private boolean completed = false;

    public Subtopic(Topic topic, String title) {
        this.topic = topic;
        this.title = title;
    }

}
