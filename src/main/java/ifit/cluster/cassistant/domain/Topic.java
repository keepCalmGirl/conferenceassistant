package ifit.cluster.cassistant.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Entity
public class Topic {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conference_id", nullable = false)
    @JsonBackReference
    private Conference conf_ID;

    private String name;
    private String summary;
    private String speaker;
    private Date dateTime;
    private Integer rate;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Question> questions;

    public Topic(Conference conf_ID, String name, String summary, String speaker, Date dateTime, Integer rate, List<Question> questions) {
        this.conf_ID = conf_ID;
        this.name = name;
        this.summary = summary;
        this.speaker = speaker;
        this.dateTime = dateTime;
        this.rate = rate;
        this.questions = questions;
    }

    public Topic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conference getConf_ID() {
        return conf_ID;
    }

    public void setConf_ID(Conference conf_ID) {
        this.conf_ID = conf_ID;
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
