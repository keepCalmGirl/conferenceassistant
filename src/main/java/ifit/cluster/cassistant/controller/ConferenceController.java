package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @GetMapping("/{id}")
    public String getConference(@PathVariable("id") Long conferenceId, Model model){
        model.addAttribute("conference", conferenceService.getConference(conferenceId));
        return "topic";
    }

    @GetMapping("/")
    public String getConferences(Model model){
        model.addAttribute("conferences", conferenceService.getAllConferences());
        return "conferences";
    }
}
