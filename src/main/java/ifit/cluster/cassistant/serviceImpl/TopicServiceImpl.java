package ifit.cluster.cassistant.serviceImpl;

import ifit.cluster.cassistant.domain.Conference;
import ifit.cluster.cassistant.domain.Topic;
import ifit.cluster.cassistant.repository.ConferenceRepository;
import ifit.cluster.cassistant.repository.TopicRepository;
import ifit.cluster.cassistant.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Optional<Topic> getTopic(Long topicId) {
        return topicRepository.findById(topicId);
    }

    @Override
    public void incrementRate(Long topicId) {
        topicRepository.incrementRate(topicId);
    }

    @Override
    public void decrementRate(Long topicId) {
        topicRepository.decrementRate(topicId);
    }

    @Override
    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public List<Topic> getAllOrderBy() {
        return topicRepository.getAllOrderById();
    }

    @Override
    public boolean checkEmail(String email) {
        return false;
    }


}
