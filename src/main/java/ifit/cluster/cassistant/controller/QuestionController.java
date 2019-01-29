package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.Conference;
import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Topic;
import ifit.cluster.cassistant.repository.QuestionRepository;
import ifit.cluster.cassistant.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @PostMapping("/topic/questions/{id}/like")
    public String incrementRate(@PathVariable("id") Long questionId){
        questionService.incrementRate(questionId);
        return "redirect:/topic/questions/"+questionId;
    }

    @PostMapping("/topic/questions/{id}/dislike")
    public String decrementRate(@PathVariable("id") Long questionId){
        questionService.decrementRate(questionId);
        return "redirect:/topic/questions/"+questionId;
    }




}
