package com.database.service;

import com.database.UserPrincipalDto;
import com.database.client.authorization.AuthorizationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private AuthorizationClient authorizationClient;

    @Autowired
    public UserPrincipalDetailsService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    @Override
    public UserPrincipalDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return authorizationClient.getUserByUsername(username);
    }
}
