package id.ac.ui.cs.advprog.a6ludogames.controller;

import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotConnectToLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotDisconnectFromLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.model.Lobby;
import id.ac.ui.cs.advprog.a6ludogames.model.User;
import id.ac.ui.cs.advprog.a6ludogames.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/lobby")
public class LobbyAPIController {
    @Autowired
    private LobbyService lobbyService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SecurityService securityService;
    
    @GetMapping(produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Iterable<Lobby>> getListLobby() {
        return ResponseEntity.ok(lobbyService.getListLobby());
    }
    
    @PostMapping(path="/new",produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Lobby> createNewLobby() {
        try {
            var user = userService.findByUserDetails(securityService.findLoggedInUserDetails());
            var lobby = lobbyService.createLobby(user);
            return ResponseEntity.ok(lobby);
        }
        catch (CannotConnectToLobbyException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping(path="/join/{id}",produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Lobby> joinLobby(@PathVariable(value = "id") int id) throws CannotConnectToLobbyException{
        try {
            var user = userService.findByUserDetails(securityService.findLoggedInUserDetails());
            var lobby = lobbyService.findById(id);
            return ResponseEntity.ok(lobbyService.connectToLobby(user, lobby));
        }
        catch (CannotConnectToLobbyException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping(path = "/disconnect", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<User> disconnectFromLobby() {
        try {
            var user = userService.findByUserDetails(securityService.findLoggedInUserDetails());
    
            return ResponseEntity.ok(lobbyService.disconnectFromLobby(user));
        }
        catch (CannotDisconnectFromLobbyException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
