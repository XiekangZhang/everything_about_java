package de.xiekang.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.logging.Logger;

@Entity
public class User {
    private static Logger logger = Logger.getLogger(User.class.getName());

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "varchar(50) default 'John'", nullable = false, unique = true)
    private String userName;

    private String firstName;

    @Column(length = 5)
    @Size(min = 3, max = 5)
    private String lastName;

    @Transient
    private String fullName;

    public User() {
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        User.logger = logger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @PrePersist
    public void logNewUserAttempt() {
        logger.info("New user " + this.userName + " is being persisted.");
    }

    @PostPersist
    public void logNewUserAdded() {
        logger.info("New user " + this.userName + " has been persisted.");
    }

    @PreRemove
    public void logUserRemovalAttempt() {
        logger.info("User " + this.userName + " is being removed.");
    }

    @PostRemove
    public void logUserRemoval() {
        logger.info("User " + this.userName + " has been removed.");
    }

    @PreUpdate
    public void logUserUpdateAttempt() {
        logger.info("User " + this.userName + " is being updated.");
    }

    @PostUpdate
    public void logUserUpdate() {
        logger.info("User " + this.userName + " has been updated.");
    }

    @PostLoad
    public void logUserLoad() {
        fullName = firstName + " " + lastName;
    }
}
