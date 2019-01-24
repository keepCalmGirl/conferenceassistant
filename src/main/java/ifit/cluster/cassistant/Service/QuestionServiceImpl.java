package ifit.cluster.cassistant.Service;


import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Status;
import ifit.cluster.cassistant.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question saveQuestion(Question question){
        return questionRepository.save(question);
    }

    @Override
    public void incrementRate(Long question_id){
     Question question = questionRepository.findById(question_id).get();
     Integer rate = question.getRate();
     question.setRate(rate++);
    }

    @Override
    public Status updateStatus(Long question_id, Status status) {
      questionRepository.findById(question_id).get().setStatus(status);
      return questionRepository.findById(question_id).get().getStatus();
    }

    @Override
    public Boolean checkEmail(Long question_id, String email){
          return false;
    }


}
