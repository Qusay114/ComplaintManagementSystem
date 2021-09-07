package com.nutriyummy.backend.domain;

import javax.persistence.*;

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

    public void setName(String name) {
        this.name = name;
    }
}
