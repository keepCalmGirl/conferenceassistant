package ifit.cluster.cassistant.domain;

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
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private Role role = Role.USER;
    private String password;
    private boolean enabled = true;
    private String photo;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "likes")
    private Set<Question> likedQuestions = new HashSet<>();

    public User(String email, String firstName, String lastName, Role role, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.password = password;
    }
}
