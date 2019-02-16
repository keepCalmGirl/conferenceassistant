package ifit.cluster.cassistant.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Question implements Comparable<Question> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Topic topic;
    private Status status = Status.NEW;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "question_like",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likes = new HashSet<>();

    public Question() {
    }

    public Question(String email, String text, Topic topic) {
        this.email = email;
        this.text = text;
        this.topic = topic;
    }

    public Question(String email, String text, Topic topic, Status status, Set<User> likes) {
        this.email = email;
        this.text = text;
        this.topic = topic;
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

    public boolean hasLike(String email) {
        return likes.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public int compareTo(Question o) {
        return o.likes.size()-likes.size();
    }
}
