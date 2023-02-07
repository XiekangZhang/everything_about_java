package de.xiekang.web_tutorial.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    @Test
    void DBConnectionTest1() {
        DBConnection dbConnection = new DBConnection("postgresql", "localhost",
                "5432", "postgres", "123456789");
    }

}