package ifit.cluster.cassistant.Service;

import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Status;

public interface QuestionService {

    Question saveQuestion(Question question);
    void incrementRate(Long question_id);
    Boolean checkEmail(Long question_id, String email);
    Status updateStatus(Long question_id, Status status);

}
