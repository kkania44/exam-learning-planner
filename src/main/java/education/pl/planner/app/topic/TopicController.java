package education.pl.planner.app.topic;

import education.pl.planner.app.topic.dto.TopicDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/topics")
@AllArgsConstructor
class TopicController {

    private final TopicFacade topicFacade;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Topic> add(@RequestBody TopicDto topic, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(topicFacade.add(topic, tokenValue(token)), CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<?> update(@RequestBody TopicDto topic) {
        return new ResponseEntity<>(topicFacade.update(topic), OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<HttpStatus> startTopic(@PathVariable("id") int id) {
        topicFacade.startTopic(id);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        topicFacade.deleteById(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private String tokenValue(String token) {
        return token.substring(7);
    }

}
