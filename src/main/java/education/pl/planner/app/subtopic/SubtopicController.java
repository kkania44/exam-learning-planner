package education.pl.planner.app.subtopic;

import education.pl.planner.app.subtopic.dto.SubtopicDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/subtopics")
@AllArgsConstructor
class SubtopicController {

    private SubtopicFacade subtopicFacade;

    @PostMapping("/topic/{topicId}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Subtopic> add(@RequestBody String subtopicTitle, @PathVariable("topicId") Integer topicId) {
        return new ResponseEntity<>(subtopicFacade.add(subtopicTitle, topicId), CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Subtopic> update(@RequestBody SubtopicDto subtopic) {
        return new ResponseEntity<>(subtopicFacade.update(subtopic), OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        subtopicFacade.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

}
