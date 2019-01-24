package ifit.cluster.cassistant.repository;

import ifit.cluster.cassistant.domain.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {

    @Query("UPDATE topic SET rate = rate+1 WHERE topic.id =(:topicId)")
    void incrementRate(@Param("topicId") Long topicId);
}
