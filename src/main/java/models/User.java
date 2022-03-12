package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    @Column(length = 10)
    private String nationalCode;
    @Column(length = 11)
    private String phoneNumber;
    @OneToMany(mappedBy = "user")
    private Set<Twit> twit;
    @OneToMany(mappedBy = "user")
    private Set<Comment> comment;

    public User(Long id, String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber, Set<Twit> twit, Set<Comment> comment) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
        this.twit = twit;
        this.comment = comment;
    }

    public User() {
    }
}
