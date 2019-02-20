package ifit.cluster.cassistant.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

@Entity
public class Conference implements Comparable<Conference> {
    @Id
    @GeneratedValue
    private Long id_hash;
    private String name;
    private String info;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "conference")
    @JsonManagedReference
    private List<Topic> topic;

    public Conference() {
    }

    public Conference(String name, String info, List<Topic> topic) {
        this.name = name;
        this.info = info;
        this.topic = topic;
    }

    public Conference(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public Long getId_hash() {
        return id_hash;
    }

    public void setId_hash(Long id_hash) {
        this.id_hash = id_hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Topic> getTopic() {
        return topic;
    }

    public void setTopic(List<Topic> topic) {
        this.topic = topic;
    }

    @Override
    public int compareTo(Conference o) {
        return o.topic.size()-topic.size();
    }

    public void sortById(){
            topic.sort(Comparator.comparing(Topic::getId).reversed());
    }

    public void sortByLikes(){topic.sort(Comparator.comparing(Topic::likesSize).reversed());}
}
