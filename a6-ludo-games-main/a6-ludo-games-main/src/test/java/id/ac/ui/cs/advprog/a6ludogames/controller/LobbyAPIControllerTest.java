package id.ac.ui.cs.advprog.a6ludogames.controller;

import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotConnectToLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotDisconnectFromLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.model.GameStatus;
import id.ac.ui.cs.advprog.a6ludogames.model.Lobby;
import id.ac.ui.cs.advprog.a6ludogames.model.User;
import id.ac.ui.cs.advprog.a6ludogames.security.PlayersUserDetails;
import id.ac.ui.cs.advprog.a6ludogames.service.LobbyService;
import id.ac.ui.cs.advprog.a6ludogames.service.PlayersUserDetailsService;
import id.ac.ui.cs.advprog.a6ludogames.service.SecurityService;
import id.ac.ui.cs.advprog.a6ludogames.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LobbyAPIController.class)
class LobbyAPIControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private LobbyService lobbyService;
    @MockBean
    private UserService userService;
    @MockBean
    private SecurityService securityService;
    @MockBean
    private PlayersUserDetailsService userDetailsService;

    private User user;
    private PlayersUserDetails userDetails;
    private Lobby lobby;

    @BeforeEach
    public void setUp() {
        user = new User("Bhisma", "12345678", "USER");
        user.setPasswordConfirm("12345678");
        userDetails = new PlayersUserDetails(user);
        lobby = new Lobby();
        lobby.setId(1);
        lobby.setGameStatus(GameStatus.WAITS_FOR_PLAYER);
    }

    @Test
    void getListLobby() throws Exception {
        List<Lobby> lobbyList = new LinkedList<>();
        lobbyList.add(lobby);
        when(lobbyService.getListLobby()).thenReturn(lobbyList);
        mockMvc.perform(get("/api/lobby").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("getListLobby"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].gameStatus").value("WAITS_FOR_PLAYER"));
    }

    @Test
    void createNewLobby() throws Exception {
        user.setCurrentGame(lobby);
        lobby.addPlayer(user);

        when(securityService.findLoggedInUserDetails()).thenReturn(userDetails);
        when(userService.findByUserDetails(userDetails)).thenReturn(user);
        when(lobbyService.createLobby(user)).thenReturn(lobby);
        mockMvc.perform(post("/api/lobby/new").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("createNewLobby"))
                .andExpect(jsonPath("$.players[0].username").value("Bhisma"));
    }

    @Test
    void createNewLobbyFailTest() throws Exception {
        user.setCurrentGame(lobby);
        lobby.addPlayer(user);
        
        when(securityService.findLoggedInUserDetails()).thenReturn(userDetails);
        when(userService.findByUserDetails(userDetails)).thenReturn(user);
        when(lobbyService.createLobby(user)).thenThrow(new CannotConnectToLobbyException());
        
        mockMvc.perform(post("/api/lobby/new").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(handler().methodName("createNewLobby"));
    }
    
    @Test
    void joinLobbyTest() throws Exception{
        lobby.addPlayer(user);

        when(securityService.findLoggedInUserDetails()).thenReturn(userDetails);
        when(userService.findByUserDetails(userDetails)).thenReturn(user);
        when(lobbyService.findById(1)).thenReturn(lobby);
        when(lobbyService.connectToLobby(user, lobby)).thenReturn(lobby);
        mockMvc.perform(post("/api/lobby/join/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("joinLobby"))
                .andExpect(jsonPath("$.players[0].username").value("Bhisma"));
    }

    @Test
    void joinLobbyFailTest() throws Exception{
        lobby.addPlayer(user);
        
        when(securityService.findLoggedInUserDetails()).thenReturn(userDetails);
        when(userService.findByUserDetails(userDetails)).thenReturn(user);
        when(lobbyService.findById(1)).thenReturn(lobby);
        when(lobbyService.connectToLobby(user, lobby)).thenThrow(new CannotConnectToLobbyException());
        
        mockMvc.perform(post("/api/lobby/join/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(handler().methodName("joinLobby"));
    }
    
    @Test
    void disconnectFromLobby() throws Exception {
        when(securityService.findLoggedInUserDetails()).thenReturn(userDetails);
        when(userService.findByUserDetails(userDetails)).thenReturn(user);
        when(lobbyService.disconnectFromLobby(user)).thenReturn(user);
        mockMvc.perform(post("/api/lobby/disconnect").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("disconnectFromLobby"))
                .andExpect(jsonPath("$.username").value("Bhisma"));
    }

    @Test
    void disconnectFromLobbyFailTest() throws Exception {
        when(securityService.findLoggedInUserDetails()).thenReturn(userDetails);
        when(userService.findByUserDetails(userDetails)).thenReturn(user);
        when(lobbyService.disconnectFromLobby(user)).thenThrow(new CannotDisconnectFromLobbyException());
        
        mockMvc.perform(post("/api/lobby/disconnect").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(handler().methodName("disconnectFromLobby"));
    }
}