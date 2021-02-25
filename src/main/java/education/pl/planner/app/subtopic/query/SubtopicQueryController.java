package education.pl.planner.app.subtopic.query;

import education.pl.planner.app.topic.query.TopicQueryDto;
import education.pl.planner.app.topic.query.TopicQueryRepository;
import education.pl.planner.app.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/subtopics")
@AllArgsConstructor
public class SubtopicQueryController {

    private final TopicQueryRepository topicQuery;
    private final SubtopicQueryRepository subtopicQuery;

    @GetMapping("/topic/{id}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<List<SubtopicQueryDto>> getAllSubtopicsForTopic(@PathVariable("id") Integer topicId) {
        TopicQueryDto topic = topicQuery.findById(topicId)
                .orElseThrow(() -> new NotFoundException("Topic not found"));
        List<SubtopicQueryDto> subtopics = subtopicQuery.findAllByTopicId(topic.getId());
        return new ResponseEntity<>(subtopics, OK);
    }

}
