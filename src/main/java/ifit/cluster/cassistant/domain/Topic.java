package ifit.cluster.cassistant.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer rate = 0;
    private String speaker;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conference_id", nullable = false)
    @JsonBackReference
    private Conference conference;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dateTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    @JsonManagedReference
    private List<Question> questions = new ArrayList<>();

    public Topic(Conference conference, String name, String summary, String speaker, Date dateTime, Integer rate) {
        this.conference = conference;
        this.name = name;
        this.summary = summary;
        this.speaker = speaker;
        this.dateTime = dateTime;
        this.rate = rate;
    }
}
