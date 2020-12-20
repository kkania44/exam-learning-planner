package education.pl.planner.rest;

import education.pl.planner.domain.Topic;
import education.pl.planner.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    ResponseEntity<List<Topic>> getAllTopics() {
        return new ResponseEntity<List<Topic>>(topicService.getAllTopics(), HttpStatus.OK);
    }

}
