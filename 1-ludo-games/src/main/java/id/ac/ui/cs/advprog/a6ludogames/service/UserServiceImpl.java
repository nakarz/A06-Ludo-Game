package id.ac.ui.cs.advprog.a6ludogames.service;

import id.ac.ui.cs.advprog.a6ludogames.model.User;
import id.ac.ui.cs.advprog.a6ludogames.repository.UserRepository;
import id.ac.ui.cs.advprog.a6ludogames.security.PlayersUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        
        user.orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " is not found."));
        
        return user.get();
    }
    
    @Override
    public User register(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles("USER");
        return userRepository.save(user);
    }
    
    @Override
    public User findByUserDetails(PlayersUserDetails playersUserDetails) {
        return findByUsername(playersUserDetails.getUsername());
    }
}
