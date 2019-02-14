package ifit.cluster.cassistant.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "question_like",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> likedQuestions = new HashSet<>();

    public User(String email, String phone, String firstName, String lastName, Role role, String password, boolean enabled, Set<Question> likedQuestions) {
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.password = password;
        this.enabled = enabled;
        this.likedQuestions = likedQuestions;
    }

    public User() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Question> getLikedQuestions() {
        return likedQuestions;
    }

    public void setLikedQuestions(Set<Question> likedQuestions) {
        this.likedQuestions = likedQuestions;
    }
}
