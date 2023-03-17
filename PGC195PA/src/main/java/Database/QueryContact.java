package Database;

import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The QueryContact class is used to perform SQL operations on Contact objects.*/
public class QueryContact {

    /**
     * Retrieves a list of contacts
     * @return ObservableList of contact objects
     * @throws SQLException for SQL related errors*/
    public static ObservableList<Contact> getContact() throws SQLException
    {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();

        String query = "SELECT * FROM contacts;";

        QueryDatabase.setStatement(JDBC.getConnection(),query);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        try
        {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next())
            {
                Contact newContact = new Contact(
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name"),
                        resultSet.getString("Email")
                );
                contacts.add(newContact);
            }
            return contacts;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a Contact by the contactName
     * @param contactName String variable for contact name
     * @throws SQLException for SQL related errors*/
    public  static Contact getContactByName(String contactName) throws SQLException
    {
        String query = "SELECT * FROM contacts WHERE Contact_Name=?";

        QueryDatabase.setStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setString(1, contactName);

        try
        {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next())
            {
                Contact newContact = new Contact(
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name"),
                        resultSet.getString("Email")
                );
                return newContact;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
