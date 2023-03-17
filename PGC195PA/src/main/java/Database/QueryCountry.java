package Database;

import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The QueryCountry class is used to perform SQL operations on Country objects.*/
public class QueryCountry {

    /**
     * Retrieves a list of country objects
     * @return ObservableList of country objects
     * @throws SQLException for SQL related errors*/
    public static ObservableList<Country> getCountry() throws SQLException
    {
        ObservableList<Country> countries = FXCollections.observableArrayList();

        String query = "SELECT * FROM countries;";

        QueryDatabase.setStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        try
        {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next())
            {
                Country newCountry = new Country(
                        resultSet.getInt("Country_ID"),
                        resultSet.getString("Country")
                );
                countries.add(newCountry);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a Country by the country parameter
     * @param country String variable for the country's name
     * @return Country
     * @throws SQLException for SQL related errors*/
    public static Country getCountryID(String country) throws SQLException
    {
        String query = "SELECT * FROM countries WHERE Country=?";

        QueryDatabase.setStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setString(1, country);

        try
        {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next())
            {
                Country newCountry = new Country(
                        resultSet.getInt("Country_ID"),
                        resultSet.getString("Country")
                );
                return newCountry;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
