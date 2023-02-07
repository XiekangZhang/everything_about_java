package de.xiekang.web_tutorial.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilsTest {

    @Test
    void DBConnectionTest2() {
        DBUtils dbUtils = new DBUtils(new DBConnection("postgresql",
                "localhost", "5432", "postgres", "123456789"));
    }

}