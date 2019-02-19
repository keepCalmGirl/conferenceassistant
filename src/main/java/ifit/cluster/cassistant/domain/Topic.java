package ifit.cluster.cassistant.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Topic implements Comparable<Topic> {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conference_id", nullable = false)
    @JsonBackReference
    private Conference conference;

    private String name;
    private String summary;
    private String speaker;
    private String image;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dateTime;

    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinTable(
            name = "topic_like",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    @JsonManagedReference
    private List<Question> questions;

    public Topic(Conference conference, String name, String summary, String speaker, String image, Date dateTime, Set<User> likes, List<Question> questions) {
        this.conference = conference;
        this.name = name;
        this.summary = summary;
        this.speaker = speaker;
        this.image = image;
        this.dateTime = dateTime;
        this.likes = likes;
        this.questions = questions;
    }

    public Topic(Conference conference, String name, String summary, String speaker, Date dateTime) {
        this.conference = conference;
        this.name = name;
        this.summary = summary;
        this.speaker = speaker;
        this.dateTime = dateTime;
    }

    public Topic() {
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public boolean hasLike(String email){
        return likes.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public int compareTo(Topic t){
        return t.likes.size()-likes.size();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int likesSize(){
        return likes.size();
    }
}

