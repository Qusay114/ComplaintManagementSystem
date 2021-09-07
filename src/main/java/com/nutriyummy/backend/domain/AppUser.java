package com.nutriyummy.backend.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

/**
 * Create application user model table
 * implement UserDetails interface for security
 */
@Entity
public class AppUser implements UserDetails {

    //this id for each application user , so we can get a specific application user
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    /**
     * table fields
     */
    private String firstName ;
    private String lastName ;
    private String username ;
    private String password ;

    public AppUser(){}

    public AppUser(String firstName , String lastName){
        this.firstName = firstName ;
        this.lastName = lastName ;
    }

    public AppUser(String firstName , String lastName , String username , String password){
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.username = username ;
        this.password = password ;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}
