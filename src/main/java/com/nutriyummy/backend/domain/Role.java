package com.nutriyummy.backend.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Create role model table
 * to use it to give every application user a role
 */
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name ;

   @ManyToMany(mappedBy = "roles")
   private Set<AppUser> appUsers = new HashSet<>();


    public Role(){}

    public Role(String name){
        this.name = name ;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setName(String name) {
        this.name = name;
    }


}
