package com.Geekster.BasicInstagramDesign.Services;

import com.Geekster.BasicInstagramDesign.Models.AuthenticationToken;
import com.Geekster.BasicInstagramDesign.Models.User;
import com.Geekster.BasicInstagramDesign.Repositories.IAuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    IAuthenticationTokenRepository authenticationTokenRepository;
    public void saveToken(AuthenticationToken token) {
        authenticationTokenRepository.save(token);
    }

    public AuthenticationToken getToken(User user) {
        return authenticationTokenRepository.findByUser(user);
    }
}
