package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.Conference;
import ifit.cluster.cassistant.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @GetMapping("/{id}")
    public String getConference(@PathVariable("id") Long conferenceId, Model model){
        model.addAttribute("conference", conferenceService.getConference(conferenceId));
        return "conference";
    }

    @GetMapping("/")
    public String getConferences(Model model){
        model.addAttribute("conferences", conferenceService.getAllConferences());
        return "conferences";
    }

    @GetMapping("/conference")
    public String conferenceForm(Model model){
        model.addAttribute("conference", new Conference());
        return "conference_form";
    }

    @PostMapping("/conference")
    public String conferenceSubmit(@ModelAttribute Conference conference , Model model){
        conferenceService.saveConference(conference);
        //model.addAttribute("conference" , conference);
        return "redirect:/"+conference.getId_hash();
    }
}

