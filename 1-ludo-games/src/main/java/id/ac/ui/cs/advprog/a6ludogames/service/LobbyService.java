package id.ac.ui.cs.advprog.a6ludogames.service;

import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotDisconnectFromLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotConnectToLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.model.Lobby;
import id.ac.ui.cs.advprog.a6ludogames.model.User;


public interface LobbyService {
    Lobby createLobby(User player) throws CannotConnectToLobbyException;
    Lobby connectToLobby(User player, Lobby lobby) throws CannotConnectToLobbyException;
    Iterable<Lobby> getListLobby();
    Lobby findById(int id);
    User disconnectFromLobby(User user) throws CannotDisconnectFromLobbyException;
    Lobby getUserLobby(User user);
}
