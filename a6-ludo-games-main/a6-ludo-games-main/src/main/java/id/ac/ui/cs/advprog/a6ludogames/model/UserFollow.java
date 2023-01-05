package id.ac.ui.cs.advprog.a6ludogames.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "userfollow")
@Data
@Where(clause="status  = true")
public class UserFollow {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JsonIgnore
    private User following;

    @ManyToOne
    private User followers;

    boolean status = true;
}
