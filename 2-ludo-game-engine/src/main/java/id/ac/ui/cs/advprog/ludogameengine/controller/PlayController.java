package id.ac.ui.cs.advprog.ludogameengine.controller;

import id.ac.ui.cs.advprog.ludogameengine.service.PawnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PlayController {
    @Autowired
    PawnService pawnService;

    @GetMapping("/play")
    public String playGame() {
        return "index";
    }
}
