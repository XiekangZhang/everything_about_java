package de.xiekang.db;

import de.xiekang.model.User;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * @author XZ
 */
public class DBUtils {
    private DBConnection connection;

    public DBUtils(DBConnection connection) {
        this.connection = connection;
    }

    public void createTable(String table, HashMap<String, String>... colInfos) {
        System.out.println("Trying creating table " + table);
        String query = this.createTableQuery(table, colInfos);
        executeQuery(query);
    }

    private String createTableQuery(String table, HashMap<String, String>... colInfos) {
        StringBuilder query = new StringBuilder();
        int colIndex = 1;
        query.append("CREATE TABLE " + table + " (");
        for (HashMap<String, String> col : colInfos) {
            if (col.size() != 1) {
                throw new RuntimeException("Please separate column information");
            }
            String key = col.keySet().toArray()[col.keySet().size() - 1].toString();
            String value = col.get(key);
            System.out.println("col: " + key + " col info: " + value);
            if (colIndex != colInfos.length) {
                query.append(key + " " + value + ", ");
            } else {
                query.append(key + " " + value + "); ");
            }
            colIndex++;
        }
        return query.toString();
    }

    public void registerUser(String table, User user) {
        String query = this.registerUserQuery(table, user);
        executeQuery(query);
    }

    private void executeQuery(String query) {
        System.out.println(query);
        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.connection.DBClose();
        }
    }

    private String registerUserQuery(String table, User user) {
        StringBuilder query = new StringBuilder();
        List<String> colNames = Arrays.stream(user.getClass().getDeclaredFields()).
                map(Field::getName).
                filter(name -> !name.equalsIgnoreCase("id")).toList();
        String colNamesStr = String.join(", ", colNames);

        List<String> values = colNames.stream().map(
                name -> {
                    Field field = null;
                    try {
                        field = user.getClass().getDeclaredField(name);
                        field.setAccessible(true);
                        return field.get(user).toString();
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).toList();

        String valStr = "'" + String.join("', '", values) + "'";

        query.append("INSERT INTO \"" + table + "\" (");
        query.append(colNamesStr);
        query.append(") VALUES (");
        query.append(valStr + ");");
        return query.toString();
    }

    public void showUsers(String table) {
        String query = showUsersQuery(table);

        System.out.println(query);
        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            /*while (resultSet.next()) {
                System.out.println("Email: "+ resultSet.getString("email"));
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.connection.DBClose();
        }
    }

    private String showUsersQuery(String table) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM \"" + table + "\"; ");
        return stringBuilder.toString();
    }
}
