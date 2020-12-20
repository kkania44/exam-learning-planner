package education.pl.planner.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "TOPICS")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    private String title;
    @OneToMany(mappedBy = "topic")
    private Set<Subtopic> subtopics = new HashSet<>();
    private int daysForLearning;
    private boolean completed = false;

    public Topic(String title, int daysForLearning) {
        this.title = title;
        this.daysForLearning = daysForLearning;
    }
}
