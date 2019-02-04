package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.Conference;
import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Status;
import ifit.cluster.cassistant.domain.Topic;
import ifit.cluster.cassistant.repository.QuestionRepository;
import ifit.cluster.cassistant.service.QuestionService;
import ifit.cluster.cassistant.service.TopicService;
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

    @Autowired
    TopicService topicService;

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
        question.setRate(0);
        question.setTopic(topicService.getTopic(topicId));
        question.setStatus(Status.NEW);

        questionService.saveQuestion(question, topicId);
        model.addAttribute("topic", topicId);
        return "redirect:/topics/" + topicId;
    }

    @PostMapping("/question/{questionId}/{status}")
    public String changeQuestionStatus(@PathVariable("questionId") Long questionId
            , @PathVariable("status") Status status){
        questionService.updateStatus(questionId, status);
        return "redirect:/topics/" + questionService.getQuestion(questionId).getTopic().getId();
    }




}
