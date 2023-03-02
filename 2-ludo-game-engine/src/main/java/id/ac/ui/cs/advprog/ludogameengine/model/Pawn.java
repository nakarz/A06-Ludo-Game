package id.ac.ui.cs.advprog.ludogameengine.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="pawn")
@Data
@Setter
@Getter
@NoArgsConstructor
public class Pawn {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "color")
    private Color color;

    @Column(name = "last_position")
    private int lastPosition;

    @Column(name = "current_player")
    private boolean currentPlayer;

}
