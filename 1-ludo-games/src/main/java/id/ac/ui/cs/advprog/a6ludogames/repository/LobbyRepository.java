package id.ac.ui.cs.advprog.a6ludogames.repository;

import id.ac.ui.cs.advprog.a6ludogames.model.GameStatus;
import id.ac.ui.cs.advprog.a6ludogames.model.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LobbyRepository extends JpaRepository<Lobby, Integer> {
    Lobby findById(int id);
    List<Lobby> findByGameStatus(GameStatus object);
}
