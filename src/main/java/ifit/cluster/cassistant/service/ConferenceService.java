package ifit.cluster.cassistant.service;

import ifit.cluster.cassistant.domain.Conference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConferenceService {


    Conference addConference(Conference conference);
    void deleteConference(Long id);
    Conference getConferenceById(Long id);
    List<Conference> getAll();
    void updateConference(Conference conference);
}
