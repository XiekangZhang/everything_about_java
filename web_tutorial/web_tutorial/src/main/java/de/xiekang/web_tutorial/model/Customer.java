package de.xiekang.web_tutorial.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer implements Serializable {

    private String firstName;
    private String lastName;
    private String city;
    private static final Logger LOGGER = Logger.getLogger(Customer.class.getName());

    public Customer() {
        LOGGER.log(Level.INFO, "Customers constructor called");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
