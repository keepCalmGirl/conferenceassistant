package ifit.cluster.cassistant.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.List;

@Entity
public class Conference {
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
}
