package ifit.cluster.cassistant.service;

import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Status;

import java.util.Optional;

public interface QuestionService {
    Optional<Question> getQuestion(Long questionId);
    Question saveQuestion(Question question);
    void incrementRate(Long questionId);
    void decrementRate(Long questionId);
    Boolean checkEmail(Long questionId, String email);
}