package education.pl.planner.app.topic.query;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/topics")
@AllArgsConstructor
class TopicQueryController {

    private final TopicQueryFacade topicFacade;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<List<TopicQueryDto>> getAllTopicsForUser(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(topicFacade.getAllTopicsForUser(tokenValue(token)), OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<TopicQueryDto> getById(@RequestHeader("Authorization") String token, @PathVariable("id") int id) {
        return new ResponseEntity<>(topicFacade.getByIdAndUser(id, tokenValue(token)), OK);
    }

    private String tokenValue(String token) {
        return token.substring(7);
    }

}
