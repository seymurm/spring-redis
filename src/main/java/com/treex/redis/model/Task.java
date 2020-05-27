package com.treex.redis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by seymurmanafov on 2020. 05. 25..
 */
@Entity
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User user;

    private String name;

    private String descriptiion;

    private Date deadline;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptiion() {
        return descriptiion;
    }

    public void setDescriptiion(String descriptiion) {
        this.descriptiion = descriptiion;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
