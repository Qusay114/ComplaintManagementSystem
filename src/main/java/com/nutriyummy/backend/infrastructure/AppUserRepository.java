package com.nutriyummy.backend.infrastructure;

import com.nutriyummy.backend.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser , Long> {
    AppUser findAppUserByUsername(String username) ;
}
