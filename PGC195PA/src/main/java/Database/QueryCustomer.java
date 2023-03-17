package Database;

import Model.Customer;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The QueryCustomer class is used to perform SQL operations on Customer objects.*/
public class QueryCustomer {

    /**
     * Retrieves a list of Customer objects
     * @return ObservableList of customers
     * @throws SQLException for SQL related errors*/
    public static ObservableList<Customer> getCustomers() throws SQLException
    {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        String selection = "SELECT * FROM customers AS c INNER JOIN first_level_divisions AS d ON c.Division_ID = d.Division_ID INNER JOIN countries AS co ON co.Country_ID=d.COUNTRY_ID;";

        QueryDatabase.setStatement(JDBC.getConnection(), selection);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        try
        {
            preparedStatement.execute();
            ResultSet results = preparedStatement.getResultSet();

            while(results.next())
            {
                Customer addedCustomer = new Customer(
                        results.getInt("Customer_ID"),
                        results.getString("Customer_Name"),
                        results.getString("Address"),
                        results.getString("Postal_Code"),
                        results.getString("Phone"),
                        results.getString("Division"),
                        results.getString("Country"),
                        results.getInt("Division_ID")
                );
                customers.add(addedCustomer);
            }

            return customers;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


    /**
     * Creates a new customer
     * @param name String variable for customer name
     * @param address String variable for customer address
     * @param postalCode String variable for customer postal code
     * @param phone String variable for customer phone number
     * @param division String variable for division
     * @return Boolean true if a customer is created and false if else
     * @throws SQLException for SQL related errors*/
    public static boolean addCustomer(String name, String address, String postalCode, String phone, String division) throws SQLException
    {
        Division createDivision =QueryDivision.getID(division);

        String insert = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?)";

        QueryDatabase.setStatement(JDBC.getConnection(),insert);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setString(1,name);
        preparedStatement.setString(2,address);
        preparedStatement.setString(3,postalCode);
        preparedStatement.setString(4,phone);
        preparedStatement.setInt(5,createDivision.getDivisionID());

        try
        {
            preparedStatement.execute();

            if(preparedStatement.getUpdateCount() > 0)
            {
                System.out.println("Rows affected: " + preparedStatement.getUpdateCount());
            }
            else
            {
                System.out.println("There are no changes.");
            }
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Updates the information pertaining to a selected customer
     * @param customerID Int variable for customerID
     * @param name String variable for customer name
     * @param address String variable for customer address
     * @param postalCode String variable for customer postal code
     * @param phone String variable for customer phone number
     * @param division String variable for division
     * @return Boolean true if updated correctly and false if not
     * @throws SQLException for SQL related errors*/
    public static boolean updateInformation(int customerID, String name, String address, String postalCode, String phone, String division) throws SQLException
    {
        Division createDivision = QueryDivision.getID(division);

        String insert = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID=?";

        QueryDatabase.setStatement(JDBC.getConnection(), insert);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postalCode);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5, createDivision.getDivisionID());
        preparedStatement.setInt(6, customerID);

        try
        {
            preparedStatement.execute();
            if(preparedStatement.getUpdateCount() > 0)
            {
                System.out.println("Rows affected: " + preparedStatement.getUpdateCount());
            }
            else
            {
                System.out.println("There are no changes.");
            }
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }

    /**
     * Deletes a desired customer
     * @param customerID Int variable for the customer's id
     * @return Boolean true if deleted and false if not
     * @throws SQLException for SQL related errors*/
    public static boolean delete(int customerID) throws SQLException
    {
        String insert = "DELETE from customers WHERE Customer_Id=?";

        QueryDatabase.setStatement(JDBC.getConnection(), insert);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setInt(1, customerID);

        try
        {
            preparedStatement.execute();

            if(preparedStatement.getUpdateCount() > 0)
            {
                System.out.println("Rows affected: " + preparedStatement.getUpdateCount());
            }
            else
            {
                System.out.println("There are no changes.");
            }
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }


}
