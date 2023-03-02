package id.ac.ui.cs.advprog.a6ludogames.model;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="lobby")
@Data
public class Lobby {
    @Id
    @Column(name="id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @OneToMany(
            mappedBy ="currentGame",
            cascade = CascadeType.ALL
    )
    private List<User> players = new LinkedList<>();
    
    
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;
    
    public Boolean canAddPlayer() {
        return players.size() < 4;
    }
    
    public void addPlayer(User user) {
        if (canAddPlayer().equals(Boolean.TRUE)) players.add(user);
        this.setGameStatus(GameStatus.WAITS_FOR_PLAYER);
    }
    
    public void removePlayer(User player) {
        if (!players.isEmpty()) players.remove(player);
        if (players.isEmpty()) this.setGameStatus(GameStatus.CLOSED);
    }
}
