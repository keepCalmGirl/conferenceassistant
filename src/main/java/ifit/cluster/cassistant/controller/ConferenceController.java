package ifit.cluster.cassistant.controller;

import ifit.cluster.cassistant.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;



}
