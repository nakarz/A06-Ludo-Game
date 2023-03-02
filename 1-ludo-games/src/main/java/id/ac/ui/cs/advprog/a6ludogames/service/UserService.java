package id.ac.ui.cs.advprog.a6ludogames.service;

import id.ac.ui.cs.advprog.a6ludogames.model.User;
import id.ac.ui.cs.advprog.a6ludogames.security.PlayersUserDetails;

public interface UserService {
    
    User findByUsername(String username);
    
    User register(User user);
    
    User findByUserDetails(PlayersUserDetails playersUserDetails);
}
