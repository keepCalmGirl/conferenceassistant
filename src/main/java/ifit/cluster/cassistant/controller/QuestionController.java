package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Topic;
import ifit.cluster.cassistant.domain.User;
import ifit.cluster.cassistant.service.QuestionService;
import ifit.cluster.cassistant.service.TopicService;
import ifit.cluster.cassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
public class QuestionController {

    private final QuestionService questionService;
    private final TopicService topicService;
    private final UserService userService;

    @Autowired
    public QuestionController(QuestionService questionService, TopicService topicService, UserService userService) {
        this.questionService = questionService;
        this.topicService = topicService;
        this.userService = userService;
    }

    @PostMapping(value = "/like/question",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void like(@RequestBody Question question){
        userService
                .getUserByEmail(question
                        .getLikes()
                        .stream()
                        .findFirst()
                        .map(User::getEmail)
                        .get())
                .ifPresent(user -> questionService
                        .getQuestion(question
                                .getId())
                        .ifPresent(q -> {
                            Set<User> likes = q.getLikes();
                            if (likes.contains(user)){
                                likes.remove(user);
                            } else {
                                likes.add(user);
                            };
                            questionService.saveQuestion(q);
                }));
    }

    @PostMapping("/topics/{topicId}/question")
    public String questionSubmit(@PathVariable("topicId") Long topicId, @ModelAttribute Question question, Model model) {
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
