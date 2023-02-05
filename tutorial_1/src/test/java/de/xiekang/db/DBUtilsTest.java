package de.xiekang.db;

import de.xiekang.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilsTest {
    public DBUtils dbUtils;
    @BeforeEach
    void setUp() {
        dbUtils = new DBUtils(new DBConnection("postgresql", "localhost", "5432",
                "postgres", "123456789"));
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void createTable() {
    }

    @Test
    void registerUser(){
        dbUtils.registerUser("user", new User("bbbb@gmail.com", "123456"));
    }

    @Test
    void showUsers() {
        dbUtils.showUsers("user");
    }
}