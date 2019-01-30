package ifit.cluster.cassistant.service;

import ifit.cluster.cassistant.domain.Conference;
import ifit.cluster.cassistant.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    ConferenceRepository conferenceRepository;

    @Override
    public Conference getConference(Long conferenceId) {

        return conferenceRepository.findById(conferenceId).get();
    }

    @Override
    public List<Conference> getAllConferences() {
        return (List<Conference>) conferenceRepository.findAll();
    }

    @Override
    public Conference saveConference(Conference conference) {
        return conferenceRepository.save(conference);
    }
}
