package ifit.cluster.cassistant.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Topic {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conference_id", nullable = false)
    @JsonBackReference
    private Conference conference;

    private String name;
    @Column(columnDefinition = "TEXT")
    private String summary;
    private String speaker;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dateTime;
    private Integer rate = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    @JsonManagedReference
    private List<Question> questions = new ArrayList<>();

    public Topic(Conference conference, String name, String summary, String speaker, Date dateTime, Integer rate, List<Question> questions
    ) {
        this.conference = conference;
        this.name = name;
        this.summary = summary;
        this.speaker = speaker;
        this.dateTime = dateTime;
        this.rate = rate;
        this.questions = questions;
    }

    public Topic(Conference conference, String name, String summary, String speaker, Date dateTime, Integer rate) {
        this.conference = conference;
        this.name = name;
        this.summary = summary;
        this.speaker = speaker;
        this.dateTime = dateTime;
        this.rate = rate;
    }

    public Topic() {
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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
