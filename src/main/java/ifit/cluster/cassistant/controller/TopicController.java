package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.Status;
import ifit.cluster.cassistant.domain.Topic;
import ifit.cluster.cassistant.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/topics/{id}")
    public String getTopic(@PathVariable("id") Long topicId, Model model){
        Topic topic = topicService.getTopic(topicId);
        model.addAttribute("topic", topic);
        model.addAttribute("questionStatuses", Status.values());
        return "topic";
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
        topicService.saveTopic(topic, conferenceId);
        return "redirect:/topics/"+topic.getId();
    }

}