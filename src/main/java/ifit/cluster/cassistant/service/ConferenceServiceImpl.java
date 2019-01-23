package ifit.cluster.cassistant.service;

import ifit.cluster.cassistant.domain.Conference;
import ifit.cluster.cassistant.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConferenceServiceImpl  implements ConferenceService{

    @Autowired
    private ConferenceRepository conferenceRepository;

    public Conference addConference(Conference conference) {
        return conferenceRepository.save(conference);
    }

    public void deleteConference(Long id) {
        conferenceRepository.deleteById(id);
    }

    public List<Conference> getAll() {
        List<Conference> list = new ArrayList<>();
        conferenceRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    public void updateConference(Conference conference){
        conferenceRepository.save(conference);
    }

    public  Conference getConferenceById(Long id){
        return conferenceRepository.findById(id).get();
    }

}
