package ifit.cluster.cassistant.service;

import ifit.cluster.cassistant.domain.Question;

import java.util.Optional;

public interface QuestionService {
    Optional<Question> getQuestion(Long questionId);
    @SuppressWarnings("UnusedReturnValue")
    Question saveQuestion(Question question);
    Boolean checkEmail(Long questionId, String email);
}