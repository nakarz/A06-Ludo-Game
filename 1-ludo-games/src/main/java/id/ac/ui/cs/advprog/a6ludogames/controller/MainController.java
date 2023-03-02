package id.ac.ui.cs.advprog.a6ludogames.controller;

import id.ac.ui.cs.advprog.a6ludogames.model.Lobby;
import id.ac.ui.cs.advprog.a6ludogames.model.User;
import id.ac.ui.cs.advprog.a6ludogames.security.PlayersUserDetails;
import id.ac.ui.cs.advprog.a6ludogames.service.FriendService;
import id.ac.ui.cs.advprog.a6ludogames.service.LobbyService;
import id.ac.ui.cs.advprog.a6ludogames.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    
    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private LobbyService lobbyService;

    @Autowired
    private FriendService friendService;
    
    @GetMapping("/")
    public String home() {
        PlayersUserDetails playersUserDetails = securityService.findLoggedInUserDetails();
        if (playersUserDetails == null)
            return "/home";
        else return  "/login_home";
    }
    
    @GetMapping("/register")
    public String registration(Model model) {
        var userDto = new User();
        model.addAttribute("user", userDto);
        return "/register";
    }
    
    
    @GetMapping("/lobby")
    public String lobby(Model model) {
        Iterable<Lobby> lobbyList = lobbyService.getListLobby();
        model.addAttribute("lobbyList", lobbyList);
        return "/lobby";
    }

    @GetMapping("/friendlist")
    public String friendlist(Model model) {
        Iterable<User> friendList = friendService.listFriend();
        model.addAttribute("friendList", friendList);
        return "/friendlist";
    }
}
