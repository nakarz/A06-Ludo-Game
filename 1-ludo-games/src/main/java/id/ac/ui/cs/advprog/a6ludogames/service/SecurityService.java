package id.ac.ui.cs.advprog.a6ludogames.service;

import id.ac.ui.cs.advprog.a6ludogames.security.PlayersUserDetails;

public interface SecurityService {
    PlayersUserDetails findLoggedInUserDetails();

    void autoLogin(String username, String password);
}
