package education.pl.planner.rest;

import education.pl.planner.domain.Subtopic;
import education.pl.planner.service.SubtopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                new ResponseEntity<>(subtopics, HttpStatus.OK);
    }

    @PostMapping("/topic/{topicId}")
    ResponseEntity<Subtopic> add(@RequestBody String subtopicTitle, @PathVariable("topicId") Integer topicId) {
        return new ResponseEntity<>(subtopicService.add(subtopicTitle, topicId), HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<Subtopic> update(@RequestBody Subtopic subtopic) {
        return new ResponseEntity<>(subtopicService.update(subtopic), HttpStatus.OK);
    }

}
