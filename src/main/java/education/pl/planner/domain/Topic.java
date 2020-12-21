package education.pl.planner.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@ToString
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "TOPICS")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    private String title;
    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Subtopic> subtopics = new HashSet<>();
    private int daysForLearning;
    private boolean completed = false;
    private LocalDate startedOn;

    public Topic(String title, int daysForLearning) {
        this.title = title;
        this.daysForLearning = daysForLearning;
    }

    public void start() {
        this.startedOn = LocalDate.now();
    }

    public void rename(String newTitle) {
        if (!title.isBlank()) {
            this.title = newTitle;
        }
    }

    public void setDaysForLearning(int days) {
        if (days > 0) {
            this.daysForLearning = days;
        }
    }
}
