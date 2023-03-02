package id.ac.ui.cs.advprog.a6ludogames.controller;
import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotConnectToLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotDisconnectFromLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.controller.request.RequestUserFollow;
import id.ac.ui.cs.advprog.a6ludogames.model.Lobby;
import id.ac.ui.cs.advprog.a6ludogames.model.User;
import id.ac.ui.cs.advprog.a6ludogames.repository.LobbyRepository;
import id.ac.ui.cs.advprog.a6ludogames.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/friend")
public class FriendAPIController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @Autowired
    private LobbyRepository lobbyRepository;

    @Autowired
    private TemplateResponse templateResponse;

    @PostMapping(path = "/addFriend", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<User> addNewFriend(@RequestBody()RequestUserFollow obj) {
        var user = userService.findByUsername(obj.getUsernameFollowing());
        var user2 = userService.findByUsername(obj.getUsernameFollowers());
        User data=  friendService.addFriend(user, user2);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(path = "/deleteFriend", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<User> deleteFriend(@RequestBody()RequestUserFollow obj) {
        var user = userService.findByUsername(obj.getUsernameFollowing());
        var user2 = userService.findByUsername(obj.getUsernameFollowers());
        User data=  friendService.unFollowFriend(user, user2);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Iterable<User>> getFriends() {
        return ResponseEntity.ok(friendService.listFriend());
    }

    @GetMapping(path = "/listFriendLobby", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<List<Lobby>> listFriendLobby() {
        return new ResponseEntity<>(friendService.listFriendLobbyLocation(), HttpStatus.OK);
    }

    @PostMapping(path = "/joinFriendLobby/{username}/{lobbyId}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Lobby> joinFriendLobby(@PathVariable(value = "username") String username,
                                               @PathVariable(value = "lobbyId") Integer lobbyId) throws CannotConnectToLobbyException {

        var userCheck = userService.findByUsername(username);

        Optional<Lobby> lobbyCheck = lobbyRepository.findById(lobbyId);

        var lobbyResult = friendService.joinFriendLobby(userCheck, lobbyCheck.get());

        return new ResponseEntity<>(lobbyResult, HttpStatus.OK);
    }

    @PostMapping(path = "/unjoinFriendLobby/{username}/{lobbyId}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Lobby> unjoinFriendLobby(@PathVariable(value = "username") String username,
                                               @PathVariable(value = "lobbyId") Integer lobbyId) throws CannotDisconnectFromLobbyException {

        var userCheck = userService.findByUsername(username);

        Optional<Lobby> lobbyCheck = lobbyRepository.findById(lobbyId);

        var lobbyResult = friendService.unjoinFriendLobby(userCheck, lobbyCheck.get());
        return new ResponseEntity<>(lobbyResult, HttpStatus.OK);
    }

}
