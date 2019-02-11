package ifit.cluster.cassistant.service;

import ifit.cluster.cassistant.domain.Conference;

import java.util.List;
import java.util.Optional;

public interface ConferenceService {
    Optional<Conference> getConference(Long conferenceId);
    List<Conference> getAllConferences();
    Conference saveConference(Conference conference);
}
