package id.ac.ui.cs.advprog.a6ludogames.service;

import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotDisconnectFromLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotConnectToLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.model.Lobby;
import id.ac.ui.cs.advprog.a6ludogames.model.User;
import id.ac.ui.cs.advprog.a6ludogames.repository.LobbyRepository;
import id.ac.ui.cs.advprog.a6ludogames.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.TransientDataAccessResourceException;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class LobbyServiceImplTest {
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private LobbyRepository lobbyRepository;
    
    @InjectMocks
    private LobbyServiceImpl lobbyService;
    
    private User user;
    private Lobby lobby;
    
    @BeforeEach
    public void setUp() {
        user = new User("user", "password", "USER");
        lobby = new Lobby();
        lobby.setId(1);
    }
    
    @Test
    void createLobby() throws CannotConnectToLobbyException {
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(lobbyRepository.save(any(Lobby.class))).thenReturn(lobby);
        
        Lobby getLobby = lobbyService.createLobby(user);
        List<User> listUser = new LinkedList<>();
        listUser.add(user);
        assertEquals(getLobby.getPlayers(), listUser);
    }
    
    @Test
    void connectToLobbyPreviousNullTest() throws CannotConnectToLobbyException {
        when(userRepository.save(user)).thenReturn(user);
        when(lobbyRepository.save(lobby)).thenReturn(lobby);
        Lobby getLobby = lobbyService.connectToLobby(user, lobby);
        List<User> listUser = new LinkedList<>();
        listUser.add(user);
        assertEquals(getLobby.getPlayers(), listUser);
    }
    
    @Test
    void connectToLobbyPreviousNotNullTest() throws CannotConnectToLobbyException {
        when(userRepository.save(user)).thenReturn(user);
        when(lobbyRepository.save(lobby)).thenReturn(lobby);
        Lobby lobby2 = new Lobby();
        lobby2.setId(2);
        when(lobbyRepository.save(lobby2)).thenReturn(lobby2);
        user.setCurrentGame(lobby2);
        Lobby getLobby = lobbyService.connectToLobby(user, lobby);
        List<User> listUser = new LinkedList<>();
        listUser.add(user);
        assertEquals(getLobby.getPlayers(), listUser);
    }
    
    @Test
    void connectToLobbyIsFullTest() throws CannotConnectToLobbyException {
        Exception exception = null;
        try {
            lobby.addPlayer(new User());
            lobby.addPlayer(new User());
            lobby.addPlayer(new User());
            lobby.addPlayer(new User());
            Lobby getLobby = lobbyService.connectToLobby(user, lobby);
        }
        catch (CannotConnectToLobbyException e) {
            exception = e;
        }
        assertNotNull(exception);
    }
    
    @Test
    void disconnectFromLobbyTest() throws CannotDisconnectFromLobbyException {
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(lobbyRepository.save(any(Lobby.class))).thenReturn(lobby);
        user.setCurrentGame(lobby);
        List<User> listUser = new LinkedList<>();
        listUser.add(user);
        lobby.setPlayers(listUser);
    
        assertNull(lobbyService.disconnectFromLobby(user).getCurrentGame());
    }
    
    @Test
    void disconnectFromLobbyCurrentNullTest() throws CannotDisconnectFromLobbyException {
        List<User> listUser = new LinkedList<>();
        listUser.add(user);
        lobby.setPlayers(listUser);
        
        assertNull(lobbyService.disconnectFromLobby(user).getCurrentGame());
    }
    
    @Test
    void disconnectFromLobbyExceptionTest() throws CannotDisconnectFromLobbyException {
        Exception exception = null;
        try {
            when(lobbyRepository.save(any(Lobby.class))).thenThrow(new TransientDataAccessResourceException("oops"));
            user.setCurrentGame(lobby);
            List<User> listUser = new LinkedList<>();
            listUser.add(user);
            lobby.setPlayers(listUser);
            lobbyService.disconnectFromLobby(user);
        }
        catch (CannotDisconnectFromLobbyException e) {
            exception = e;
        }
        assertNotNull(exception);
    }
    
    @Test
    void getListLobby() {
        List<Lobby> listLobby = lobbyRepository.findAll();
        lenient().when(lobbyService.getListLobby()).thenReturn(listLobby);
        List listLobbyRes = lobbyService.getListLobby();
        Assertions.assertIterableEquals(listLobby, listLobbyRes);
    }
    
    @Test
    void findById() {
        when(lobbyRepository.findById(lobby.getId())).thenReturn(lobby);
        assertEquals(lobbyService.findById(lobby.getId()), lobby);
    }
    
    @Test
    void getUserLobby() throws CannotConnectToLobbyException {
        lobbyService.connectToLobby(user, lobby);
        assertEquals(lobbyService.getUserLobby(user), lobby);
    }
}