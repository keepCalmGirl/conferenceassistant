package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Topic;
import ifit.cluster.cassistant.service.QuestionService;
import ifit.cluster.cassistant.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/cqs",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void changeStatus(@RequestBody Question question){
        Optional<Question> questionOptional = questionService.getQuestion(question.getId());
        questionOptional.ifPresent(q -> q.setStatus(question.getStatus()));
        questionOptional.ifPresent(questionService::saveQuestion);
    }
}
