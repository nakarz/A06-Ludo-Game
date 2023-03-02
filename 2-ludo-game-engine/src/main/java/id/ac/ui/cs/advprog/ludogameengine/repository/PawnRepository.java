package id.ac.ui.cs.advprog.ludogameengine.repository;

import id.ac.ui.cs.advprog.ludogameengine.model.Pawn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PawnRepository extends JpaRepository<Pawn, Integer> {
    Pawn findById(int id);
}
