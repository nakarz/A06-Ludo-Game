package id.ac.ui.cs.advprog.ludogameengine.service;

import id.ac.ui.cs.advprog.ludogameengine.model.Dice;
import id.ac.ui.cs.advprog.ludogameengine.repository.DiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class DiceServiceImpl implements DiceService{

    @Autowired
    DiceRepository diceRepository;

    @Override
    public Dice findById(int id) {
        return diceRepository.findById(id);
    }

    @Override
    public int numberRoll(int count) {
        return count;
    }

    @Override
    public Dice playerChance(int id) {
        var numberRolled =  new Random().nextInt(6)+1;
        var dice = diceRepository.findById(id);
        dice.setChance(numberRolled == 6);
        return dice;
    }
}