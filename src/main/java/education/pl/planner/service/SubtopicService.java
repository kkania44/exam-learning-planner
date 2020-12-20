package education.pl.planner.service;

import education.pl.planner.domain.Subtopic;
import education.pl.planner.domain.SubtopicRepository;
import education.pl.planner.domain.Topic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SubtopicService {

    private SubtopicRepository subtopicRepository;
    private TopicService topicService;


    public List<Subtopic> getAllSubtopicsForTopic(String title) {
        List<Topic> topicsByName = topicService.getTopicByTitle(title);
        if (topicsByName.isEmpty()) {
            return new ArrayList<>();
        }
        Topic topicToSearch = topicsByName.get(0);
        return subtopicRepository.findAllByTopic(topicToSearch);
    }

    public void add(Subtopic subtopic) {
        subtopicRepository.save(subtopic);
    }

}
