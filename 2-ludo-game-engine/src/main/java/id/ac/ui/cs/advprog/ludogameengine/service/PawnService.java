package id.ac.ui.cs.advprog.ludogameengine.service;

import id.ac.ui.cs.advprog.ludogameengine.model.Pawn;

public interface PawnService {

    Pawn findById(int id);
    boolean checkRolledDice(int roll);
    Pawn updateLastPosition(int position, int id);
}
