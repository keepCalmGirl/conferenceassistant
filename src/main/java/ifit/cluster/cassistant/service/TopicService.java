package ifit.cluster.cassistant.service;


import ifit.cluster.cassistant.domain.Topic;

public interface TopicService {
    Topic getTopic(Long topicId);
    Integer incrementRate(Long topicId);
    boolean checkEmail(String email);
}
