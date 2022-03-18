package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @ManyToOne
    private Twit twit;
    @ManyToOne
    private User user;

    public Comment(Long id, String comment, Twit twit, User user) {
        this.id = id;
        this.comment = comment;
        this.twit = twit;
        this.user = user;
    }

    public Comment(String comment, Twit twit, User user) {
        this.comment = comment;
        this.twit = twit;
        this.user = user;
    }

    public Comment(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public Comment() {
    }

    public Comment(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "-------------------\nID : " + id + "\nComment : " + comment + "\n-------------------\n";
    }
}
