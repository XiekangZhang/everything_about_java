package de.xiekang.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    @Test
    public void DBconnectionTest() {
        DBConnection dbConnection = new DBConnection("postgresql", "localhost",
                "5432", "postgres", "123456789");
        dbConnection.DBClose();
    }
}