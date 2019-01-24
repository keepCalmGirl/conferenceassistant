package ifit.cluster.cassistant.service;


public interface TopicService {
    Topic getTopic(Long topicId);
    Integer incrementRate(Long topicId);
    boolean checkEmail(String email);
}
