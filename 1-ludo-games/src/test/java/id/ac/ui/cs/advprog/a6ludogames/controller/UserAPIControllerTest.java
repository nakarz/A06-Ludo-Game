package id.ac.ui.cs.advprog.a6ludogames.controller;

import id.ac.ui.cs.advprog.a6ludogames.model.User;
import id.ac.ui.cs.advprog.a6ludogames.security.PlayersUserDetails;
import id.ac.ui.cs.advprog.a6ludogames.security.UserValidator;
import id.ac.ui.cs.advprog.a6ludogames.service.PlayersUserDetailsService;
import id.ac.ui.cs.advprog.a6ludogames.service.SecurityService;
import id.ac.ui.cs.advprog.a6ludogames.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserAPIController.class)
class UserAPIControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PlayersUserDetailsService userDetailsService;
    @MockBean
    private UserService userService;
    @MockBean
    private SecurityService securityService;
    @MockBean
    private UserValidator userValidator;
    
    private User user;
    private PlayersUserDetails userDetails;
    
    @BeforeEach
    public void setUp() {
        user = new User("Bhisma", "12345678", "USER");
        user.setPasswordConfirm("12345678");
        userDetails = new PlayersUserDetails(user);
    }
    
    @Test
    void userDetails() throws Exception{
        when(securityService.findLoggedInUserDetails()).thenReturn(userDetails);
        when(userService.findByUsername(user.getUsername())).thenReturn(user);
        
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect((handler().methodName("userDetails")))
                .andExpect(jsonPath("$.username").value(user.getUsername()));
    }
    
    @Test
    void register() throws Exception {
        when(userService.findByUsername(user.getUsername())).thenThrow(new UsernameNotFoundException(""));
        mockMvc.perform(post("/api/user/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", user.getUsername())
                .param("password", user.getPassword())
                .param("passwordConfirm", user.getPasswordConfirm())
                .sessionAttr("user", new User()))
                .andExpect(status().is3xxRedirection());
    }
    

}