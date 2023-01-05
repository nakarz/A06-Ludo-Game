package id.ac.ui.cs.advprog.a6ludogames.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "players_user")
@Data
@NoArgsConstructor
public class User {
    
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column
    private String username;
    @Column
    private String password;
    @Transient
    private String passwordConfirm;
    @Column
    private boolean active;
    @Column
    private String roles;
    
    @ManyToOne
    @JsonIgnore
    private Lobby currentGame;

    @OneToMany(mappedBy = "following" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserFollow> followingUser;

    public User(String userName, String password, String roles) {
        this.username = userName;
        this.password = password;
        this.active = true;
        this.roles = roles;
    }
}