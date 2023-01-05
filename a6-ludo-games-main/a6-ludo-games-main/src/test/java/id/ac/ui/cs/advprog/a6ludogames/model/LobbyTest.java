package id.ac.ui.cs.advprog.a6ludogames.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LobbyTest {
    
    @Test
    void addPlayerEmptyTest() {
        Lobby lobby = new Lobby();
        lobby.addPlayer(new User());
        lobby.addPlayer(new User());
        lobby.addPlayer(new User());
    
        assertEquals(3, lobby.getPlayers().size());
    }
    
    @Test
    void addPlayerFullTest() {
        Lobby lobby = new Lobby();
        lobby.addPlayer(new User());
        lobby.addPlayer(new User());
        lobby.addPlayer(new User());
        lobby.addPlayer(new User());
        lobby.addPlayer(new User());
    
        assertEquals(4, lobby.getPlayers().size());
    }
    
    @Test
    void removePlayerSuccess() {
        Lobby lobby = new Lobby();
        User user = new User();
        lobby.addPlayer(user);
        lobby.addPlayer(new User());
        lobby.removePlayer(user);
    
        assertEquals(1, lobby.getPlayers().size());
    }
    
    @Test
    void removePlayerFail() {
        Lobby lobby = new Lobby();
        User user = new User();
        lobby.removePlayer(user);
        
        assertEquals(0, lobby.getPlayers().size());
    }
}