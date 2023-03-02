package id.ac.ui.cs.advprog.ludogameengine.controller;

import id.ac.ui.cs.advprog.ludogameengine.model.Color;
import id.ac.ui.cs.advprog.ludogameengine.model.Pawn;
import id.ac.ui.cs.advprog.ludogameengine.repository.PawnRepository;
import id.ac.ui.cs.advprog.ludogameengine.service.PawnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pawn")
public class PawnController {

    @Autowired
    private PawnRepository pawnRepository;

    @Autowired
    private PawnService pawnService;

    @GetMapping("/")
    public List<Pawn> allPawnData() {
        return  pawnRepository.findAll();
    }

    @PostMapping("/")
    public Pawn createPawnData(@RequestParam("username") String username, @RequestParam("color") Color color,
    @RequestParam("last_position") int lastPosition, @RequestParam("current_player") boolean currentPlayer){
        var pawn = new Pawn();
        pawn.setUsername(username);
        pawn.setColor(color);
        pawn.setLastPosition(lastPosition);
        pawn.setCurrentPlayer(currentPlayer);

        return pawnRepository.save(pawn);
    }

    @PutMapping("/{id}")
    public Pawn updatePawnData(@PathVariable("id") int id, @RequestParam("username") String username, @RequestParam("color") Color color,
    @RequestParam("last_position") int lastPosition, @RequestParam("current_player") boolean currentPlayer){
        var pawn = new Pawn();
        pawn.setId(id);
        pawn.setUsername(username);
        pawn.setColor(color);
        pawn.setLastPosition(lastPosition);
        pawn.setCurrentPlayer(currentPlayer);

        return pawnRepository.save(pawn);
    }

    @DeleteMapping("/{id}")
    public void deletePawnData(@PathVariable("id") int id){
        var pawn = new Pawn();
        pawn.setId(id);
        pawnRepository.delete(pawn);
    }

    @GetMapping("/{id}")
    public Optional<Pawn> findPawnData(@PathVariable("id") int id){
        return Optional.ofNullable(pawnRepository.findById(id));
    }

    @GetMapping("/update-position")
    public Optional<Pawn> saveLastPawnPosition(@PathVariable("id") int id, int lastPositon) {
        return Optional.ofNullable(pawnService.updateLastPosition(lastPositon,id));
    }
}
