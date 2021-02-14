package education.pl.planner.rest;

import education.pl.planner.domain.Subtopic;
import education.pl.planner.service.SubtopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

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
        return subtopics.isEmpty() ? new ResponseEntity<>("No subtopics found", NOT_FOUND) :
                new ResponseEntity<>(subtopics, OK);
    }

    @GetMapping("/topic/{id}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<List<Subtopic>> getAllSubtopicsForTopic(@PathVariable("id") Integer topicId) {
        List<Subtopic> subtopics = subtopicService.getAllSubtopicsForTopic(topicId);
        return new ResponseEntity<>(subtopics, OK);
    }

    @GetMapping("/topic/{id}/progress")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<String> getProgress(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(subtopicService.getProgressInTopic(id), OK);
    }

    @PostMapping("/topic/{topicId}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Subtopic> add(@RequestBody String subtopicTitle, @PathVariable("topicId") Integer topicId) {
        return new ResponseEntity<>(subtopicService.add(subtopicTitle, topicId), CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Subtopic> update(@RequestBody Subtopic subtopic) {
        return new ResponseEntity<>(subtopicService.update(subtopic), OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        subtopicService.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

}
