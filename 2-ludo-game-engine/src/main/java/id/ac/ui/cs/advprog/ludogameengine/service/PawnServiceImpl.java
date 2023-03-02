package id.ac.ui.cs.advprog.ludogameengine.service;

import id.ac.ui.cs.advprog.ludogameengine.model.Pawn;
import id.ac.ui.cs.advprog.ludogameengine.repository.PawnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PawnServiceImpl implements PawnService {

    @Autowired
    PawnRepository pawnRepository;

    @Override
    public Pawn findById(int id) {
        return pawnRepository.findById(id);
    }

    @Override
    public boolean checkRolledDice(int roll) {
        return roll == 6;
    }

    @Override
    public Pawn updateLastPosition(int position, int id) {
        var pawn = pawnRepository.findById(id);

        pawn.setLastPosition(position);
        pawnRepository.save(pawn);
        return pawn;
    }
}
