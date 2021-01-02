package education.pl.planner.rest;

import education.pl.planner.domain.Topic;
import education.pl.planner.exception.NotFoundException;
import education.pl.planner.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    ResponseEntity<List<Topic>> getAllTopics() {
        return new ResponseEntity<>(topicService.getAllTopics(), OK);
    }

    @PostMapping
    ResponseEntity<Topic> add(@RequestBody Topic topic) {
        return new ResponseEntity<>(topicService.add(topic), CREATED);
    }

    @PutMapping
    ResponseEntity<?> update(@RequestBody Topic topic) {
        return new ResponseEntity<>(topicService.update(topic), OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<HttpStatus> startTopic(@PathVariable("id") Integer id) {
        topicService.startTopic(id);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        topicService.deleteById(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

}
