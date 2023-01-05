package id.ac.ui.cs.advprog.a6ludogames.controller;

import id.ac.ui.cs.advprog.a6ludogames.model.User;
import id.ac.ui.cs.advprog.a6ludogames.security.PlayersUserDetails;
import id.ac.ui.cs.advprog.a6ludogames.service.FriendService;
import id.ac.ui.cs.advprog.a6ludogames.service.LobbyService;
import id.ac.ui.cs.advprog.a6ludogames.service.PlayersUserDetailsService;
import id.ac.ui.cs.advprog.a6ludogames.service.SecurityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MainController.class)
//@SpringBootTest(classes = MainController.class)
//@AutoConfigureMockMvc
class MainControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PlayersUserDetailsService playersUserDetailsService;
    
    @MockBean
    private SecurityService securityService;
    
    @MockBean
    private LobbyService lobbyService;
    
    @MockBean
    private FriendService friendService;
    
    private PlayersUserDetails userDetails;
    
    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    void homeLoggedOutTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect((handler().methodName("home")))
                .andExpect(view().name("/home"));
    }
    
    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    void homeLoggedInTest() throws Exception {
        User user = new User("Bhisma", "12345678", "USER");
        user.setPasswordConfirm("12345678");
        userDetails = new PlayersUserDetails(user);
        when(securityService.findLoggedInUserDetails()).thenReturn(userDetails);
        
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect((handler().methodName("home")))
                .andExpect(view().name("/login_home"));
    }
    
    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    void registrationTest() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(view().name("/register"));
    }
    
    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    void lobbyTest() throws Exception {
        when(lobbyService.getListLobby()).thenReturn(new LinkedList<>());
        mockMvc.perform(get("/lobby"))
                .andExpect(view().name("/lobby"));
    }
}