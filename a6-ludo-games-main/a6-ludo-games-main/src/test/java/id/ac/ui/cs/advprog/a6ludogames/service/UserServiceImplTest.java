package id.ac.ui.cs.advprog.a6ludogames.service;

import id.ac.ui.cs.advprog.a6ludogames.model.User;
import id.ac.ui.cs.advprog.a6ludogames.repository.UserRepository;
import id.ac.ui.cs.advprog.a6ludogames.security.PlayersUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @InjectMocks
    private UserServiceImpl userService;
    
    private User user;
    private PlayersUserDetails playersUserDetails;
    
    @BeforeEach
    public void setUp() {
        user = new User("user", "password", "USER");
        playersUserDetails = new PlayersUserDetails(user);
    }
    
    @Test
    void register() {
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(userService.register(user), user);
    }
    
    @Test
    void findByUsername() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        assertEquals(userService.findByUsername(user.getUsername()), user);
    }
    
    @Test
    void findByUserDetails() {
        when(userRepository.findByUsername(playersUserDetails.getUsername())).thenReturn(Optional.of(user));
        assertEquals(userService.findByUserDetails(playersUserDetails), user);
    }
}