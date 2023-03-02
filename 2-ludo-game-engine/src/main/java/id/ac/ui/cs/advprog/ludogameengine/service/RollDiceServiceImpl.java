package id.ac.ui.cs.advprog.ludogameengine.service;

import id.ac.ui.cs.advprog.ludogameengine.model.Player;
import org.springframework.stereotype.Service;

@Service
public class RollDiceServiceImpl implements RollDiceService {

    Player redPlayer = new Player("Red", false, 0, 0, true);
    Player bluePlayer = new Player("Blue", false, 0, 0, false);
    Player yellowPlayer = new Player("Yellow", false, 0, 0, false);
    Player greenPlayer = new Player("Green", false, 0, 0, false);

    @Override
    public double redFunction() {
        if (redPlayer.chance) {
            throwDice(redPlayer);
        } else {
            bluePlayer.chance = true;
        }
        return redPlayer.count;
    }

    @Override
    public double blueFunction() {
        if (bluePlayer.chance) {
            throwDice(bluePlayer);
        } else {
            greenPlayer.setChance(true);
        }
        return bluePlayer.count;
    }

   @Override
    public double greenFunction() {
        if (greenPlayer.chance) {
            throwDice(greenPlayer);
        } else {
            yellowPlayer.setChance(true);
        }
        return greenPlayer.count;
    }

    @Override
    public double yellowFunction() {
        if (yellowPlayer.chance) {
            throwDice(yellowPlayer);
        } else {
            redPlayer.setChance(true);
        }
        return yellowPlayer.count;
    }

    @Override
    public void throwDice(Player color){
        var diceNumber = Math.ceil(Math.random() * Math.ceil(6));
        if(diceNumber == 6){
            color.setFlag(true);
            color.setChance(true);
            color.isSix++;
        } else {
            color.setChance(false);
        }

        if (color.count < 52){
            if (color.isFlag()){
                if((diceNumber == 6) && (color.isSix == 1)){
                    color.count = 0;
                }else {
                    color.count += diceNumber;
                }
            }
        } else {
            var checkWinner = color.count + diceNumber;
            if (checkWinner < 57){
                color.count += diceNumber;
            }
        }
    }

    @Override
    public void rollDice() {
        if (redPlayer.chance) {
            redFunction();
        }
        if (bluePlayer.chance) {
            blueFunction();
        }
        if (greenPlayer.chance) {
            greenFunction();
        }
        if (yellowPlayer.chance) {
            yellowFunction();
        }
    }
}