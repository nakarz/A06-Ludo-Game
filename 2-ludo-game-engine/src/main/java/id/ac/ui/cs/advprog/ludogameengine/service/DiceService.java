package id.ac.ui.cs.advprog.ludogameengine.service;

import id.ac.ui.cs.advprog.ludogameengine.model.Dice;

public interface DiceService{

    Dice findById(int id);
    int numberRoll(int count);
    Dice playerChance(int id);
}