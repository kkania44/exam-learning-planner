package education.pl.planner.service;

import education.pl.planner.domain.Topic;
import education.pl.planner.domain.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public void save(Topic topic) {
        topicRepository.save(topic);
    }

}
