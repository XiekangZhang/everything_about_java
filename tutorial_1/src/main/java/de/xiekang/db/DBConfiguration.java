package de.xiekang.db;

/**
 * @author XZ
 */

public class DBConfiguration {
    // info: pass your db information
    private String jdbcURL;
    private String user;
    private String password;

    public DBConfiguration(String TYPE,
                           String SERVER,
                           String PORT,
                           String USER,
                           String PASSWORD) {
        this.user = USER;
        this.password = PASSWORD;
        this.jdbcURL = String.format("jdbc:%s://%s:%s/", TYPE, SERVER, PORT);
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
