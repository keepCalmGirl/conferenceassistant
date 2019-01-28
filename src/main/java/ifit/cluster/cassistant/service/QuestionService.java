package ifit.cluster.cassistant.service;

import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Status;

public interface QuestionService {

    Question saveQuestion(Question question);
    Integer incrementRate(Long questionId);
    Integer decrementRate(Long questionId);
    Boolean checkEmail(Long questionId, String email);
    Status updateStatus(Long questionId, Status status);
    Question getQuestion(Long questionId);

}
