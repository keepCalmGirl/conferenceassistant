package ifit.cluster.cassistant.Service;

import ifit.cluster.cassistant.Service.QuestionService;
import ifit.cluster.cassistant.repository.QuestionRepository;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;

public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public void incrementRate(){}
    public Boolean checkEmail(String email){}


}
