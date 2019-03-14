package ifit.cluster.cassistant.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conference {
    @Id
    @GeneratedValue
    private Long id_hash;
    private String name;
    private String info;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "conference")
    @JsonManagedReference
    private List<Topic> topic;

    public Conference(String name, String info) {
        this.name = name;
        this.info = info;
    }
}
