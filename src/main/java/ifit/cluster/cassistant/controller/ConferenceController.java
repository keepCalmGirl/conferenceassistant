package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.domain.Conference;
import ifit.cluster.cassistant.domain.Topic;
import ifit.cluster.cassistant.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Optional;

@Controller
public class ConferenceController {

    private final ConferenceService conferenceService;
    private static boolean sortStatus;

    @Autowired
    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping("/{id}")
    public String getConference(@PathVariable("id") Long conferenceId, Model model){
        Optional<Conference> conferenceOptional = conferenceService.getConference(conferenceId);
        if (conferenceOptional.isPresent()){
            model.addAttribute("conference", sort(conferenceOptional.get()));
            System.out.println(sortStatus);
            return "conference";
        }
        return "404";
    }

    @PostMapping("/{id}/sort/{sortStatus}")
   public String sortTopics(@PathVariable("id") Long ConferenceId , @PathVariable("sortStatus") String sort){
        if (sort.contains("like")){
        sortStatus=true;}
        else if (sort.contains("timeline")){sortStatus=false;}
        return "redirect:/"+ConferenceId;
    }

    private Conference sort(Conference conference){
        if (sortStatus){
        conference.getTopic().sort(Comparator.comparing(Topic::likesSize).reversed());
            System.out.println("like");
        }else {conference.getTopic().sort(Comparator.comparing(Topic::getId).reversed());
            System.out.println("timeline");
        }
        return conference;
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

