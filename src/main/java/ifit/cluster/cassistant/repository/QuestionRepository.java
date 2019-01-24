package ifit.cluster.cassistant.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Object, Long> {

}
