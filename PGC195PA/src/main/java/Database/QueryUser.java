package Database;

import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The QueryUser class is used to perform SQL operations on User objects.*/
public class QueryUser {

    /**
     * Checks to see if the username and password are correct
     * @param username String variable for the username
     * @param password String variable for the password
     * @return Boolean that returns true if valid and false if invalid
     * @throws SQLException for SQL related errors*/
    public static boolean validateLogin(String username, String password) throws SQLException
    {
        String selection = "SELECT * FROM users WHERE User_Name=? AND Password=?";

        QueryDatabase.setStatement(JDBC.getConnection(), selection);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        try
        {
            preparedStatement.execute();
            ResultSet result = preparedStatement.getResultSet();
            return (result.next());
        }
        catch (Exception e)
        {
            System.out.println("Error Message: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves a list of all Users
     * @return ObservableList of Users
     * @throws SQLException for SQL related errors*/
    public static ObservableList<User> getUser() throws SQLException
    {
        ObservableList<User> users = FXCollections.observableArrayList();

        String selection = "SELECT * FROM users;";

        QueryDatabase.setStatement(JDBC.getConnection(), selection);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        try
        {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next())
            {
                User newUser = new User(
                        resultSet.getInt("User_ID"),
                        resultSet.getString("User_Name"),
                        resultSet.getString("Password")
                );
                users.add(newUser);
            }
            return users;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
