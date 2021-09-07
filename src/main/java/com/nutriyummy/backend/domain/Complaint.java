package com.nutriyummy.backend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
