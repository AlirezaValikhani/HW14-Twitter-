package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Twit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 280)
    private String twit;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "twit")
    private Set<Comment> comments;

    public Twit(Integer id, String twit, User user, Set<Comment> comments) {
        this.id = id;
        this.twit = twit;
        this.user = user;
        this.comments = comments;
    }

    public Twit() {
    }
}
