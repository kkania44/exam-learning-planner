package education.pl.planner.service;

import education.pl.planner.domain.Subtopic;
import education.pl.planner.domain.SubtopicRepository;
import education.pl.planner.domain.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtopicService {

    private SubtopicRepository subtopicRepository;

    public SubtopicService(SubtopicRepository subtopicRepository) {
        this.subtopicRepository = subtopicRepository;
    }

    public List<Subtopic> getAllSubtopicsFor(Topic topic) {
        return subtopicRepository.findAllByTopic(topic);
    }
    
}
