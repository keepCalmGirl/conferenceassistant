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

    @PostMapping("/topics/{topicId}/questions/{questionId}/like")
    public String incrementRate(@PathVariable("topicId") Long topicId
            ,@PathVariable("questionId") Long questionId){
        questionService.incrementRate(questionId);
        return "redirect:/topics/"+topicId;
    }

    @PostMapping(value = "/topics/{topicId}/questions/like-new",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String like(@PathVariable("topicId") Long topicId
            ,@RequestBody Question question){
////        questionService.incrementRate(questionId);
//        System.out.println(questionService.getQuestion(question.getId()).get().getText());
        Question q = questionService.getQuestion(question.getId()).get();
        String email = question.getLikes().stream().findFirst().get().getEmail();
        User u = userService.getUserByEmail(email).get();

        System.out.println(q.getText() + " " + u.getLastName()+ " " + q.getLikes().size());

        u.getLikedQuestions().add(q);
        q.getLikes().add(u);
        questionService.saveQuestion(q);
        System.out.println(questionService.getQuestion(q.getId()).get().getLikes().size());
        System.out.println(questionService.getQuestion(q.getId()).get().getLikes().stream().findFirst().get().getFirstName());
//        System.out.println(userService.getUserByEmail(likes.stream().findFirst().get().getEmail()).get().getFirstName());
//        System.out.println(userService.getUserByEmail(question.getLikes().size()));
//        userService.saveUser()
//        userService
//                .getUserByEmail(question
//                        .getLikes()
//                        .stream()
//                        .findFirst()
//                        .map(User::getEmail)
//                        .get())
//                .ifPresent(user -> questionService
//                        .getQuestion(question
//                                .getId())
//                        .ifPresent(q -> q
//                                .getLikes()
//                                .add(user)));
        return "redirect:/topics/"+topicId;
    }

    @PostMapping("/topics/{topicId}/questions/{questionId}/dislike")
    public String decrementRate(@PathVariable("topicId") Long topicId
            ,@PathVariable("questionId") Long questionId){
        questionService.decrementRate(questionId);
        return "redirect:/topics/"+topicId;
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
