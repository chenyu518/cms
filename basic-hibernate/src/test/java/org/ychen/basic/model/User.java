package org.ychen.basic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by cy on 16/12/1.
 */
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String username;

    public User() {
    }

    public User(int id,String username) {
        super();
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
