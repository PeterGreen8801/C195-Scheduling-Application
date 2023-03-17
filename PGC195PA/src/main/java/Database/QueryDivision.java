package Database;

import Model.Country;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The QueryDivision class is used to perform SQL operations on Division objects.*/
public class QueryDivision {

    /**
     * Retrieves an ObservableList of Division objects
     * @return ObservableList of Divisions
     * @throws SQLException for SQL related errors*/
    public static ObservableList<Division> getDivision() throws SQLException
    {
        ObservableList<Division> divisions = FXCollections.observableArrayList();

        String query = "SELECT * FROM first_level_divisions;";

        QueryDatabase.setStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        try
        {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next())
            {
                Division division = new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("COUNTRY_ID")
                );
                divisions.add(division);
            }
            return divisions;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a Division by the Division Name
     * @param division String variable for Division Name
     * @return Division Object
     * @throws SQLException for SQL related errors*/
    public static Division getID(String division) throws SQLException
    {
        String query = "SELECT * FROM first_level_divisions WHERE Division=?";

        QueryDatabase.setStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setString(1, division);

        try
        {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next())
            {
                Division newDiv = new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("COUNTRY_ID")
                );
                return newDiv;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves an ObservableList of Divisions by country
     * @param country String variable for country
     * @return ObservableList of Divisions
     * @throws SQLException for SQL related errors*/
    public static ObservableList<Division> getDivisionByCountry(String country) throws SQLException
    {
        Country newCountry = QueryCountry.getCountryID(country);

        ObservableList<Division> divisions = FXCollections.observableArrayList();

        String query = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID=?;";

        QueryDatabase.setStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setInt(1, newCountry.getCountryID());

        try
        {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next())
            {
                Division newDiv = new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("COUNTRY_ID")
                );
                divisions.add(newDiv);
            }
            return divisions;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

}
