package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.service.TopicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.server.PathParam;

@Controller
public class TopicController {

    @Autowired
    private TopicServiceImpl topicService;

    @GetMapping("/topic/{id}")
    public String getTopic(@PathParam("id") Long topicID, Model model){
        topicService.getTopic(topicID);
        return "topic";
    }



}
