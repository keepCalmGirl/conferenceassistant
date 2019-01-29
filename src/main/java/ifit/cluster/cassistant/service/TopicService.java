package ifit.cluster.cassistant.service;

import ifit.cluster.cassistant.domain.Topic;

public interface TopicService {
    Topic getTopic(Long topicId);
    Integer incrementRate(Long topicId);
    Integer decrementRate(Long topicId);
    boolean checkEmail(String email);
    Topic saveTopic(Topic topic, Long conferenceId);
}
