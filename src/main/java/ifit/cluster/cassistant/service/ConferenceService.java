package ifit.cluster.cassistant.service;

import ifit.cluster.cassistant.domain.Conference;

import java.util.List;

public interface ConferenceService {
    Conference getConference(Long conferenceId);
    List<Conference> getAllConferences();
}
