package id.ac.ui.cs.advprog.a6ludogames.service;

import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotDisconnectFromLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotConnectToLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.model.GameStatus;
import id.ac.ui.cs.advprog.a6ludogames.model.Lobby;
import id.ac.ui.cs.advprog.a6ludogames.model.User;
import id.ac.ui.cs.advprog.a6ludogames.repository.LobbyRepository;
import id.ac.ui.cs.advprog.a6ludogames.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LobbyServiceImpl implements LobbyService {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    LobbyRepository lobbyRepository;
    
    @Override
    public Lobby createLobby(User player) throws CannotConnectToLobbyException {
        var lobby = new Lobby();
        lobby.setGameStatus(GameStatus.WAITS_FOR_PLAYER);
 
        return connectToLobby(player, lobby);
    }
    
    @Override
    public Lobby connectToLobby(User player, Lobby lobby) throws CannotConnectToLobbyException {
        if (lobby.canAddPlayer().equals(Boolean.TRUE)) {
            
            // if exist, remove player from their current lobby
            var playerPreviousLobby = player.getCurrentGame();
            if (playerPreviousLobby != null) {
                playerPreviousLobby.removePlayer(player);
                lobbyRepository.save(playerPreviousLobby);
            }
            
            player.setCurrentGame(lobby);
            lobby.addPlayer(player);
    
            lobbyRepository.save(lobby);
            userRepository.save(player);
            return lobby;
        }
        else {
            throw new CannotConnectToLobbyException();
        }
    }
    
    @Override
    public User disconnectFromLobby(User player) throws CannotDisconnectFromLobbyException {
        try {
            var lobby = player.getCurrentGame();
            if (lobby != null) {
                lobby.removePlayer(player);
                player.setCurrentGame(null);
                lobbyRepository.save(lobby);
                userRepository.save(player);
            }
            return player;
        }
        catch (Exception e) {
            throw new CannotDisconnectFromLobbyException();
        }
    }
    
    @Override
    public List<Lobby> getListLobby() {
        return lobbyRepository.findAll();
    }
    
    @Override
    public Lobby findById(int id) {
        return lobbyRepository.findById(id);
    }
    
    @Override
    public Lobby getUserLobby(User user) {
        return user.getCurrentGame();
    }
    
}
