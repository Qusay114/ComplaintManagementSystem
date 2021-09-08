package com.nutriyummy.backend.services;

import com.nutriyummy.backend.domain.Role;
import com.nutriyummy.backend.infrastructure.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    private RoleRepository roleRepository ;

    @Override
    public void saveRole(Role userRole) {
        roleRepository.save(userRole);
    }
}
