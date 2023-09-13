package de.xiekang.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class ContactPerson {
    private String firstName;
    private String lastName;
    private String phone;

    public ContactPerson() {
    }

    public ContactPerson(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
}
