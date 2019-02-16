package ifit.cluster.cassistant.service;

import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Status;

import java.util.Optional;

public interface QuestionService {
    Optional<Question> getQuestion(Long questionId);
    Question saveQuestion(Question question);
    Boolean checkEmail(Long questionId, String email);
}