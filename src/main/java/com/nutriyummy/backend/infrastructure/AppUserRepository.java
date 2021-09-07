package com.nutriyummy.backend.infrastructure;

import com.nutriyummy.backend.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<Long , AppUser> {
    AppUser findAppUserByUsername(String username) ;
}
