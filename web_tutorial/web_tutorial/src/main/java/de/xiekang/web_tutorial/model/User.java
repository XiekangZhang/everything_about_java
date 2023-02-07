package de.xiekang.web_tutorial.model;

import java.sql.Timestamp;

/**
 * @author XZ
 * This is the model of user, which corresponds what to be saved in the db.
 */
public class User {
    // info: corresponds to the columns
    //private int id;
    private String email;
    private String password;


    // for bean
    public User() {
    }

    public User(String email, String password) {
        //this.id = id;
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
