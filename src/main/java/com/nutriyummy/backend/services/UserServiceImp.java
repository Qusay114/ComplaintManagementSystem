package com.nutriyummy.backend.services;

import com.nutriyummy.backend.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * we implemented UserDetailsService to override loadUserByUsername
 * so , we will load the appUser account using the username ;
 */
@Service
public class UserServiceImp implements UserDetailsService {

    @Autowired
    private AppUserService appUserService ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserService.getAppUser(username);

        if (appUser == null)
            throw new UsernameNotFoundException(username + " NOT FOUND!");
        return appUser ;
    }
}
