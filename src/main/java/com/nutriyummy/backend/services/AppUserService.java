package com.nutriyummy.backend.services;

import com.nutriyummy.backend.domain.AppUser;

public interface AppUserService {
    AppUser getAppUser(String username) ;

    AppUser saveAppUser(AppUser appUser);
}
