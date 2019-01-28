package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.Conference;
import ifit.cluster.cassistant.domain.Topic;
import ifit.cluster.cassistant.repository.ConferenceRepository;
import ifit.cluster.cassistant.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @GetMapping("/topics/{id}")
    public String getTopic(@PathVariable("id") Long topicId, Model model){
        Topic topic = topicService.getTopic(topicId);
        model.addAttribute("topic", topic);
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

    @GetMapping("/topic")
    public String topicForm(Model model) {

        model.addAttribute("topic", new Topic());
//        model.addAttribute("conference", conferenceRepository.findById(2L));
        return "topic_form";
    }

    @PostMapping("/topic")
    public String topicSubmit(@ModelAttribute Topic topic, Model model) {
//        need to fix !!! I don`t know how to pass conference to Topic object
        Conference conference = new Conference("Confer2", "Java10");
        conferenceRepository.save(conference);
        topic.setConference(conference);
//        need to fix!!! Maybe set set default value "0" to rate
        topic.setRate(0);

        topicService.saveTopic(topic);
        model.addAttribute("topic", topic);
        return "redirect:/topics/"+topic.getId();
    }
}