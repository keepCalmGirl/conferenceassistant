package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Status;
import ifit.cluster.cassistant.domain.Topic;
import ifit.cluster.cassistant.service.QuestionService;
import ifit.cluster.cassistant.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class QuestionController {

    private final QuestionService questionService;
    private final TopicService topicService;

    @Autowired
    public QuestionController(QuestionService questionService, TopicService topicService) {
        this.questionService = questionService;
        this.topicService = topicService;
    }

    @PostMapping("/topics/{topicId}/questions/{questionId}/like")
    public String incrementRate(@PathVariable("topicId") Long topicId
            ,@PathVariable("questionId") Long questionId){
        questionService.incrementRate(questionId);
        return "redirect:/topics/"+topicId;
    }

    @PostMapping("/topics/{topicId}/questions/{questionId}/dislike")
    public String decrementRate(@PathVariable("topicId") Long topicId
            ,@PathVariable("questionId") Long questionId){
        questionService.decrementRate(questionId);
        return "redirect:/topics/"+topicId;
    }

    @GetMapping("/topics/{topicId}/question")
    public String questionForm(@PathVariable("topicId") Long topicId, Model model) {
        model.addAttribute("question", new Question());
        model.addAttribute("topicId", topicId);
        return "question_form";
    }

    @PostMapping("/topics/{id}/question")
    public String questionSubmit(@PathVariable("id") Long topicId, @ModelAttribute Question question, Model model) {
        Optional<Topic> topicOptional = topicService.getTopic(topicId);
        if (topicOptional.isPresent()){
            question.setTopic(topicOptional.get());
            questionService.saveQuestion(question);
            model.addAttribute("topic", topicId);
            return "redirect:/topics/" + topicId;
        }
        return "404";
    }

//    @PostMapping("/question/{questionId}/{status}")
//    public void changeQuestionStatus(@PathVariable("questionId") Long questionId
//            , @PathVariable("status") Status status){
//        System.out.println(status);
////        Optional<Question> question = questionService.getQuestion(questionId);
////        question.ifPresent(question1 -> question1.setStatus(status));
////        System.out.println(status);
////        Question question = questionService.getQuestion(questionId)
//
//
////        questionService.updateStatus(questionId, status);
////        return "redirect:/topics/" + q.getTopic().getId();
//    }

    @PostMapping(value = "/question")
    public @ResponseBody String changeStatus(){
        String hahahha = "HAHAHHA";
        System.out.println(hahahha);
        return hahahha;
    }
}
