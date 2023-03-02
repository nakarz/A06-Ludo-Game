package id.ac.ui.cs.advprog.a6ludogames.service;

import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotConnectToLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotDisconnectFromLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.model.Lobby;
import id.ac.ui.cs.advprog.a6ludogames.model.User;

import java.util.List;

public interface FriendService {
    public User addFriend(User user1, User user2);// follow
    public User unFollowFriend(User user1, User user2);// follow
    public Iterable<User> listFriend();
    public Lobby joinFriendLobby(User friend, Lobby lobby) throws CannotConnectToLobbyException;
    public Lobby unjoinFriendLobby(User friend, Lobby lobby) throws CannotDisconnectFromLobbyException;
    public List<Lobby> listFriendLobbyLocation();
}
