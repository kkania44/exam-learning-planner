package education.pl.planner.app.topic;

import com.fasterxml.jackson.annotation.*;
import education.pl.planner.app.subtopic.query.SubtopicQueryDto;
import education.pl.planner.app.user.query.UserQueryDto;
import education.pl.planner.app.shared.TopicManager;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TOPICS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"subtopics"})
class Topic implements TopicManager {

    @Id
    @GeneratedValue(generator = "sequence_generator")
    @GenericGenerator(
            name = "sequence_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "topic_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_value", value = "1")
            }
    )
    private int id;
    @NotEmpty
    private String title;
    @OneToMany(mappedBy = "topic", orphanRemoval = true)
    @JsonManagedReference
    @Builder.Default
    private Set<SubtopicQueryDto> subtopics = new HashSet<>();
    private int daysForLearning;
    @Builder.Default
    private boolean completed = false;
    private LocalDate startedOn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @Setter
    private UserQueryDto user;

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
