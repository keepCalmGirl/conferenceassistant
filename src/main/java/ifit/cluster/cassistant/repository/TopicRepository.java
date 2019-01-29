package ifit.cluster.cassistant.repository;

import ifit.cluster.cassistant.domain.Topic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE topic SET topic.rate = topic.rate + 1 WHERE topic.id = :topicId", nativeQuery = true)
    void incrementRate(@Param("topicId") Long topicId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE topic SET topic.rate = topic.rate - 1 WHERE topic.id = :topicId", nativeQuery = true)
    void decrementRate(@Param("topicId") Long topicId);
}
