package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.Conference;
import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Status;
import ifit.cluster.cassistant.domain.Topic;
import ifit.cluster.cassistant.service.ConferenceService;
import ifit.cluster.cassistant.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TopicController {

    private final TopicService topicService;
    private final ConferenceService conferenceService;

    @Autowired
    public TopicController(TopicService topicService, ConferenceService conferenceService) {
        this.topicService = topicService;
        this.conferenceService = conferenceService;
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

    @PostMapping("/topics/{id}/like")
    public String incrementRate(@PathVariable("id") Long topicId){
        topicService.incrementRate(topicId);
        return "redirect:/topics/"+topicId;
    }

    @PostMapping("/topics/{id}/dislike")
    public String decrementRate(@PathVariable("id") Long topicId){
        topicService.decrementRate(topicId);
        return "redirect:/topics/"+topicId;
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