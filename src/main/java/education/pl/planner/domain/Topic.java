package education.pl.planner.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"subtopics"})
@Table(name = "TOPICS")
public class Topic implements TopicManager{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    private String title;
    @OneToMany(mappedBy = "topic", orphanRemoval = true)
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

    @Override
    public void rename(String newTitle) {
        if (!title.isBlank()) {
            this.title = newTitle;
        }
    }

    @Override
    public void markAsCompleted() {
        if (areAllSubtopicsCompleted()) {
            this.completed = true;
        }
    }

    public boolean areAllSubtopicsCompleted() {
        return subtopics.stream()
                .allMatch(subtopic -> subtopic.isCompleted());
    }

    public void setDaysForLearning(int days) {
        if (days > 0) {
            this.daysForLearning = days;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id == topic.id &&
                title.equals(topic.title);
    }

}
