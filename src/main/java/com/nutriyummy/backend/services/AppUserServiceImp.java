package com.nutriyummy.backend.services;

import com.nutriyummy.backend.domain.AppUser;
import com.nutriyummy.backend.infrastructure.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * this is implementation for the appUser service
 * to write the methods that service needs
 */
@Service
public class AppUserServiceImp implements AppUserService {

    //we inject the AppUserRepository
    @Autowired
    private AppUserRepository appUserRepository ;

    /**
     *
     * @param username
     * @return the application user account that has this username
     */
    @Override
    public AppUser getAppUser(String username) {
        return appUserRepository.findAppUserByUsername(username);
    }
}
