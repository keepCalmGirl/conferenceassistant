package ifit.cluster.cassistant.serviceImpl;

import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Status;
import ifit.cluster.cassistant.repository.QuestionRepository;
import ifit.cluster.cassistant.repository.TopicRepository;
import ifit.cluster.cassistant.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Optional<Question> getQuestion(Long questionId) {
        return questionRepository.findById(questionId);
    }

    @Override
    public Question saveQuestion(Question question){
        return questionRepository.save(question);
    }

    @Override
    public void incrementRate(Long questionId){
        questionRepository.incrementRate(questionId);
    }

    @Override
    public void decrementRate(Long questionId) {
        questionRepository.decrementRate(questionId);
    }

    @Override
    public Boolean checkEmail(Long questionId, String email){
        return false;
    }
}
