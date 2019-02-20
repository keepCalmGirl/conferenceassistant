package ifit.cluster.cassistant.service;

import ifit.cluster.cassistant.domain.Topic;
import java.util.Optional;

public interface TopicService {
    Optional<Topic> getTopic(Long topicId);
    void incrementRate(Long topicId);
    void decrementRate(Long topicId);
    boolean checkEmail(String email);
    Topic saveTopic(Topic topic);
}
