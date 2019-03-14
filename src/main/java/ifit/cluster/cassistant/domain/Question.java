package ifit.cluster.cassistant.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Question(String email, String text, Topic topic) {
        this.email = email;
        this.text = text;
        this.topic = topic;
    }

    public boolean hasLike(String email) {
        return likes.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public int compareTo(Question o) {
        return o.likes.size()-likes.size();
    }
}
