package ifit.cluster.cassistant.repository;

import ifit.cluster.cassistant.domain.Conference;
import org.springframework.data.repository.CrudRepository;

public interface ConferenceRepository extends CrudRepository<Conference, Long> {
}
