package ifit.cluster.cassistant.serviceImpl;

import ifit.cluster.cassistant.domain.Conference;
import ifit.cluster.cassistant.repository.ConferenceRepository;
import ifit.cluster.cassistant.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConferenceServiceImpl implements ConferenceService {

    private final ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceServiceImpl(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @Override
    public Optional<Conference> getConference(Long conferenceId) {
        return conferenceRepository.findById(conferenceId);
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
