package education.pl.planner.rest;

import education.pl.planner.domain.Subtopic;
import education.pl.planner.domain.Topic;
import education.pl.planner.service.SubtopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subtopics")
public class SubtopicController {

    private SubtopicService subtopicService;

    public SubtopicController(SubtopicService subtopicService) {
        this.subtopicService = subtopicService;
    }

    @GetMapping
    ResponseEntity<?> getAllSubtopicsFor(@RequestParam("topic") String topic) {
        List<Subtopic> subtopics = subtopicService.getAllSubtopicsForTopic(topic);
        return subtopics.isEmpty() ? new ResponseEntity<>("No subtopics found", HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(subtopics, HttpStatus.OK);}

}
