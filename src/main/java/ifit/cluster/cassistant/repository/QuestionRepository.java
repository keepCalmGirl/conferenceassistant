package ifit.cluster.cassistant.repository;

import ifit.cluster.cassistant.domain.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {

}
