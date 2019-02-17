package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.*;
import ifit.cluster.cassistant.service.ConferenceService;
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
public class TopicController {

    private final TopicService topicService;
    private final ConferenceService conferenceService;
    private final UserService userService;

    @Autowired
    public TopicController(TopicService topicService, ConferenceService conferenceService, UserService userService) {
        this.topicService = topicService;
        this.conferenceService = conferenceService;
        this.userService = userService;
    }

    @GetMapping("/topics/{id}")
    public String getTopic(@PathVariable("id") Long topicId, Model model){
        Optional<Topic> optionalTopic = topicService.getTopic(topicId);
        if (optionalTopic.isPresent()){
            model.addAttribute("topic", optionalTopic.get());
            model.addAttribute("questionStatuses", Status.values());
            model.addAttribute("question", new Question());
            return "topic";
        }
        return "404";
    }

    @PostMapping(value = "/like/topic",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void like(@RequestBody Topic topic){
        userService
                .getUserByEmail(topic
                .getLikes()
                .stream()
                .findFirst()
                .map(User::getEmail)
                .get())
        .ifPresent(user -> topicService
                .getTopic(topic
                        .getId())
                .ifPresent(t ->{
                    Set<User> likes = t.getLikes();
                    if (likes.contains(user)){
                        likes.remove(user);
                    }else {
                        likes.add(user);
                    }
                        topicService.saveTopic(t);
                }));
    }

    @GetMapping("/{conferenceId}/topic")
    public String topicForm(@PathVariable("conferenceId") Long conferenceId ,Model model) {
        model.addAttribute("topic", new Topic());
        model.addAttribute("conferenceId" , conferenceId);
        return "topic_form";
    }

    @PostMapping("/{conferenceId}/topic")
    public String topicSubmit(@PathVariable("conferenceId") Long conferenceId
            ,@ModelAttribute Topic topic) {
        Optional<Conference> conferenceOptional = conferenceService.getConference(conferenceId);
        if (conferenceOptional.isPresent()){
            topic.setConference(conferenceOptional.get());
            topicService.saveTopic(topic);
            return "redirect:/topics/"+topic.getId();
        }
        return "404";
    }
}