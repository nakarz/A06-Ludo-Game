package id.ac.ui.cs.advprog.ludogameengine.repository;

import id.ac.ui.cs.advprog.ludogameengine.model.Dice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiceRepository  extends JpaRepository<Dice, Integer> {
    Dice findById(int id);
}
