package ifit.cluster.cassistant.repository;

import ifit.cluster.cassistant.domain.Question;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE question SET question.rate = question.rate + 1 WHERE question.id = :questionId", nativeQuery = true)
    void incrementRate(@Param("questionId") Long questionId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE question SET question.rate = question.rate - 1 WHERE question.id = :questionId", nativeQuery = true)
    void decrementRate(@Param("questionId") Long questionId);

}
