package com.nutriyummy.backend.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

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

    //relation between appuser and complaint where the complaintList will be mapped by appUser in Complaint table
    @OneToMany(mappedBy = "appUser")
    private List<Complaint> complaintList ;


    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(
            name = "appuser_role",
            joinColumns = @JoinColumn(name = "appuser_id") ,
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

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

    public List<Complaint> getComplaintList() {
        return complaintList;
    }

    public Set<Role> getRoleList() {
        return roles;
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

    public void addNewRole(Role role){
        this.roles.add(role);
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (Role role : this.roles)
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        return simpleGrantedAuthorities ;
    }



}
