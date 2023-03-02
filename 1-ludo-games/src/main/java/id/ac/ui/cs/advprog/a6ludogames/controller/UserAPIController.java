package id.ac.ui.cs.advprog.a6ludogames.controller;

import id.ac.ui.cs.advprog.a6ludogames.model.User;
import id.ac.ui.cs.advprog.a6ludogames.security.PlayersUserDetails;
import id.ac.ui.cs.advprog.a6ludogames.security.UserValidator;
import id.ac.ui.cs.advprog.a6ludogames.service.SecurityService;
import id.ac.ui.cs.advprog.a6ludogames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
public class UserAPIController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserValidator userValidator;
    
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<PlayersUserDetails> userDetails() {
        var playersUserDetails = securityService.findLoggedInUserDetails();
        return ResponseEntity.ok(playersUserDetails);
    }
    
    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        
        if (bindingResult.hasErrors()) {
            return "/register";
        }
        userService.register(user);
        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());
        
        return "redirect:/";
    }
    

}
