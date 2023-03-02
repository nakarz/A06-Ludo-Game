package id.ac.ui.cs.advprog.a6ludogames.service;

import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotConnectToLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.exceptions.CannotDisconnectFromLobbyException;
import id.ac.ui.cs.advprog.a6ludogames.model.*;
import id.ac.ui.cs.advprog.a6ludogames.repository.LobbyRepository;
import id.ac.ui.cs.advprog.a6ludogames.repository.UserFollowRepository;
import id.ac.ui.cs.advprog.a6ludogames.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    LobbyRepository lobbyRepository;

    @Autowired
    UserFollowRepository userFollowRepository;
    
    @Autowired
    LobbyService lobbyService;

    @Override
    public List<User> listFriend() { return userRepository.findAll();}

    @Override
    public Lobby joinFriendLobby(User friend, Lobby lobby) throws CannotConnectToLobbyException {
        return lobbyService.connectToLobby(friend, lobby);
    }

    @Override
    public Lobby unjoinFriendLobby(User friend, Lobby lobby) throws CannotDisconnectFromLobbyException {
        lobbyService.disconnectFromLobby(friend);
        return lobby;
    }

    @Override
    public List<Lobby> listFriendLobbyLocation() {
        return lobbyRepository.findByGameStatus(GameStatus.WAITS_FOR_PLAYER);
    }

    //User 1 mau following user 2 maka user 1 akan menjadi followersnya user 2
    @Override
    public User addFriend(User user1, User user2){
        var checkUser =userFollowRepository.findOneByFollowingAndFollowersAndStatus(user1,user2, true);
        if(checkUser == null){
            var uf = new UserFollow();
            uf.setFollowing(user1);
            uf.setFollowers(user2);
            userFollowRepository.save(uf);
            return userRepository.findById(user1.getId()).get();
        }else if(!checkUser.isStatus()){
            checkUser.setStatus(true);
            userFollowRepository.save(checkUser);
            return userRepository.findById(checkUser.getId()).get();
        }
        return user1;
    }

    @Override
    public User unFollowFriend(User user1, User user2){
       var checkUser =userFollowRepository.findOneByFollowingAndFollowersAndStatus(user1,user2, true);
       if(checkUser != null){
            checkUser.setStatus(false);
            userFollowRepository.save(checkUser);
            return user1;
        }
        return user1;
    }
}
