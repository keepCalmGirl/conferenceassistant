package ifit.cluster.cassistant.service;

import ifit.cluster.cassistant.domain.Conference;
import ifit.cluster.cassistant.domain.Topic;
import ifit.cluster.cassistant.repository.ConferenceRepository;
import ifit.cluster.cassistant.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Override
    public Topic getTopic(Long topicId) {
        return topicRepository.findById(topicId).get();
    }

    @Override
    public Integer incrementRate(Long topicId) {

        topicRepository.incrementRate(topicId);
        return topicRepository.findById(topicId).get().getRate();
    }

    @Override
    public Integer decrementRate(Long topicId) {
        topicRepository.decrementRate(topicId);
        return topicRepository.findById(topicId).get().getRate();
    }

    @Override
    public Topic saveTopic(Topic topic, Long conferenceId) {
        topic.setConference(conferenceRepository.findById(conferenceId).get());
        return topicRepository.save(topic);
    }

    @Override
    public boolean checkEmail(String email) {
        return false;
    }

}
