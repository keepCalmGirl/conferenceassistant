package ifit.cluster.cassistant.service;

import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Status;
import ifit.cluster.cassistant.repository.QuestionRepository;
import ifit.cluster.cassistant.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Question getQuestion(Long questionId) {
        return questionRepository.findById(questionId).get();
    }

    @Override
    public Question saveQuestion(Question question, Long topicId){
        question.setTopic(topicRepository.findById(topicId).get());
        return questionRepository.save(question);
    }

    @Override
    public Integer incrementRate(Long questionId){

        questionRepository.incrementRate(questionId);
        return questionRepository.findById(questionId).get().getRate();
    }

    @Override
    public Integer decrementRate(Long questionId) {
        questionRepository.decrementRate(questionId);
        return questionRepository.findById(questionId).get().getRate();
    }

    @Override
    public Status updateStatus(Long questionId, Status status) {
        Question question = questionRepository.findById(questionId).get();
        question.setStatus(status);
        questionRepository.save(question);
        return questionRepository.findById(questionId).get().getStatus();
    }

    @Override
    public Boolean checkEmail(Long questionId, String email){
        return false;
    }




}
