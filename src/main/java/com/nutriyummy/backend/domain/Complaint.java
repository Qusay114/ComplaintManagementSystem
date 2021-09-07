package com.nutriyummy.backend.domain;

import javax.persistence.*;

/**
 * Create complaint model table
 */
@Entity
public class Complaint {

    //this id for each application user , so we can get a specific application user
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;


    /**
     * table fields
     */
    private String content ;
    private String status ;

    //create a relation between complaint and appuser
    @ManyToOne
    private AppUser appUser ;

    public Complaint(){}

    public Complaint(String content){
        this.content = content ;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
