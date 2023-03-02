package id.ac.ui.cs.advprog.ludogameengine.service;

import id.ac.ui.cs.advprog.ludogameengine.model.Player;

public interface RollDiceService {
    double redFunction();
    double blueFunction();
    double greenFunction();
    double yellowFunction();
    void throwDice(Player color);
    void rollDice();
}
