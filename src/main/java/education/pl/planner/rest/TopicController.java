package education.pl.planner.rest;

import education.pl.planner.domain.Topic;
import education.pl.planner.exception.NotFoundException;
import education.pl.planner.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/topics")
@AllArgsConstructor
public class TopicController {

    private TopicService topicService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<List<Topic>> getAllTopicsForUser(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(topicService.getAllTopicsForUser(tokenValue(token)), OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Topic> getById(@PathVariable("id") int id) {
        return new ResponseEntity<>(topicService.getTopicById(id), OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Topic> add(@RequestBody Topic topic, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(topicService.add(topic, tokenValue(token)), CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<?> update(@RequestBody Topic topic) {
        return new ResponseEntity<>(topicService.update(topic), OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<HttpStatus> startTopic(@PathVariable("id") int id) {
        topicService.startTopic(id);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        topicService.deleteById(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private String tokenValue(String token) {
        return token.substring(7);
    }
}
