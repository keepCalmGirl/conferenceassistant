package ifit.cluster.cassistant.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Topic topic;
    private Integer rate = 0;
    private Status status = Status.NEW;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "likedQuestions")
    private Set<User> likes = new HashSet<>();

    public Question() {
    }

    public Question(String email, String text) {
        this.email = email;
        this.text = text;
    }

    public Question(String email, String text, Topic topic, Integer rate, Status status, Set<User> likes) {
        this.email = email;
        this.text = text;
        this.topic = topic;
        this.rate = rate;
        this.status = status;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }
}
