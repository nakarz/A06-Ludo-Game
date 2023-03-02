package id.ac.ui.cs.advprog.ludogameengine.controller;

import id.ac.ui.cs.advprog.ludogameengine.model.Dice;
import id.ac.ui.cs.advprog.ludogameengine.model.Player;
import id.ac.ui.cs.advprog.ludogameengine.repository.DiceRepository;
import id.ac.ui.cs.advprog.ludogameengine.service.DiceService;
import id.ac.ui.cs.advprog.ludogameengine.service.RollDiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dice")
public class DiceController {

    @Autowired
    DiceRepository diceRepository;

    DiceService diceService;

    RollDiceService rollDiceService;

    @GetMapping("/")
    public List<Dice> allDiceData(){
        return diceRepository.findAll();
    }

    @PostMapping("/")
    public Dice createDiceData(@RequestParam("color_name") String colorName, @RequestParam("flag") boolean flag,
    @RequestParam("count") int count, @RequestParam("is_six") int isSix, @RequestParam("chance") boolean chance){
        var dice = new Dice();
        dice.setColorName(colorName);
        dice.setFlag(flag);
        dice.setCount(count);
        dice.setIsSix(isSix);
        dice.setChance(chance);

        return diceRepository.save(dice);
    }

    @PutMapping("/{id}")
    public Dice updateDiceData(@PathVariable("id") int id, @RequestParam("color_name") String colorName, @RequestParam("flag") boolean flag,
    @RequestParam("count") int count, @RequestParam("is_six") int isSix, @RequestParam("chance") boolean chance){
        var dice = new Dice();
        dice.setId(id);
        dice.setColorName(colorName);
        dice.setFlag(flag);
        dice.setCount(count);
        dice.setIsSix(isSix);
        dice.setChance(chance);

        return diceRepository.save(dice);
    }

    @DeleteMapping("/{id}")
    public void deleteDiceData(@PathVariable("id") int id){
        var dice = new Dice();
        dice.setId(id);
        diceRepository.delete(dice);
    }

    @GetMapping("/{id}")
    public Optional<Dice> findPlayerData(@PathVariable("id") int id){
        return Optional.ofNullable(diceRepository.findById(id));
    }

    @GetMapping("/save-number")
    public void saveNumberRolledDice(@RequestParam("count") int count){
        var dice = new Dice();
        diceRepository.save(dice);
    }

    @GetMapping("/roll")
    public Dice rollChance(@RequestParam("color_name") String colorName, @RequestParam("flag") boolean flag,
                           @RequestParam("count") int count, @RequestParam("is_six") int isSix, @RequestParam("chance") boolean chance) {
        var dice = new Dice();
        var redPlayer = new Player("Red", false, 0, 0, true);
        var bluePlayer = new Player("Blue", false, 0, 0, false);
        var yellowPlayer = new Player("Yellow", false, 0, 0, false);
        var greenPlayer = new Player("Green", false, 0, 0, false);

        if (redPlayer.chance) {
            rollDiceService.redFunction();
        }
        if (bluePlayer.chance) {
            rollDiceService.blueFunction();
        }
        if (greenPlayer.chance) {
            rollDiceService.greenFunction();
        }
        if (yellowPlayer.chance) {
            rollDiceService.yellowFunction();
        }
        return diceRepository.save(dice);
    }
}
