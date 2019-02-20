package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.Conference;
import ifit.cluster.cassistant.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class ConferenceController {

    private final ConferenceService conferenceService;

    @Autowired
    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping("/{id}")
    public String getConference(@PathVariable("id") Long conferenceId, Model model){
        Optional<Conference> conferenceOptional = conferenceService.getConference(conferenceId);
        if (conferenceOptional.isPresent()){
            conferenceOptional.get().sortByLikes();
            model.addAttribute("conference", conferenceOptional.get());
            return "conference";
        }
        return "404";
    }

    @GetMapping("/sort/{id}")
    public String getSortConference(@PathVariable("id") Long conferenceId, Model model){
        Optional<Conference> conferenceOptional = conferenceService.getConference(conferenceId);
        if (conferenceOptional.isPresent()){
            conferenceOptional.get().sortById();
            model.addAttribute("conference", conferenceOptional.get());
            return "conference";
        }
        return "404";
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
    public String conferenceSubmit(@ModelAttribute Conference conference){
        conferenceService.saveConference(conference);
        return "redirect:/"+conference.getId_hash();
    }
}

