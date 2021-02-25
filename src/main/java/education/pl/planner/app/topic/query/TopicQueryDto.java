package education.pl.planner.app.topic.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import education.pl.planner.app.subtopic.query.SubtopicQueryDto;
import education.pl.planner.app.user.query.UserQueryDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TOPICS")
@NoArgsConstructor
@Getter
public class TopicQueryDto {

    @Id
    private int id;
    @NotEmpty
    private String title;
    @OneToMany(mappedBy = "topic", orphanRemoval = true)
    @JsonManagedReference
    private Set<SubtopicQueryDto> subtopics = new HashSet<>();
    private int daysForLearning;
    private boolean completed = false;
    private LocalDate startedOn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserQueryDto user;

}
