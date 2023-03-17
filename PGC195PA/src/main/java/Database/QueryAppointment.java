package Database;

import Model.Appointment;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The QueryAppointment class is used to perform SQL operations on Appointment objects.*/
public class QueryAppointment {

    /**
     * Retrieves a list of Appointments
     * @return ObservableList of Appointments
     * @throws SQLException for SQL related errors*/
    public static ObservableList<Appointment> getAppointment() throws SQLException
    {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String query = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID=c.Contact_ID;";

        QueryDatabase.setStatement(JDBC.getConnection(),query);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        try
        {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next())
            {
                Appointment newAppointment = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );
                appointments.add(newAppointment);
            }
            return appointments;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a list of Appointments by month
     * @return ObservableList of Appointments from the past month
     * @throws SQLException for SQL related errors*/
    public static ObservableList<Appointment> getAppointmentByMonth() throws SQLException
    {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime previousMonth = today.minusDays(30);

        String query = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID=c.Contact_ID WHERE Start < ? AND Start > ?;";

        QueryDatabase.setStatement(JDBC.getConnection(),query);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setDate(1, java.sql.Date.valueOf(today.toLocalDate()));
        preparedStatement.setDate(2, java.sql.Date.valueOf(previousMonth.toLocalDate()));

        try
        {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next())
            {
                Appointment newAppointment = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );
                appointments.add(newAppointment);
            }
            return appointments;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves Appointments by week
     * @return ObservableList of Appointments from the past week
     * @throws SQLException for SQL related errors*/
    public static  ObservableList<Appointment> getAppointmentByWeek() throws SQLException
    {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime previousWeek = today.minusDays(7);

        String query = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID=c.Contact_ID WHERE Start < ? AND Start > ?;";

        QueryDatabase.setStatement(JDBC.getConnection(),query);
        PreparedStatement preparedStatement =QueryDatabase.getStatement();

        preparedStatement.setDate(1, java.sql.Date.valueOf(today.toLocalDate()));
        preparedStatement.setDate(2, java.sql.Date.valueOf(previousWeek.toLocalDate()));

        try
        {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next())
            {
                Appointment newAppointment = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );
                appointments.add(newAppointment);
            }
            return appointments;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a list of Appointments by customer ID
     * @param CustomerID Int variable for the customer ID
     * @return ObservableList of Appointments
     * @throws SQLException for SQL related errors*/
    public static ObservableList<Appointment> getAppointmentByCustomerID(int CustomerID) throws SQLException
    {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String query = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID=c.Contact_ID WHERE Customer_ID=?;";

        QueryDatabase.setStatement(JDBC.getConnection(),query);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setInt(1, CustomerID);

        try
        {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next())
            {
                Appointment newAppointment = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );
                appointments.add(newAppointment);
            }
            return appointments;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

    /**
     * Retrieves an Appointment by appointment id
     * @param AppointmentID Int variable for appointment id
     * @throws SQLException for SQL related errors*/
    public static Appointment getAppointmentByID(int AppointmentID) throws SQLException
    {
        String query = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID=c.Contact_ID WHERE Appointment_ID=?;";

        QueryDatabase.setStatement(JDBC.getConnection(),query);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setInt(1,AppointmentID);

        try
        {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next())
            {
                Appointment newAppointment = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );
                return newAppointment;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves an appointment by the contact id
     * @param contactID Int variable for the contact id
     * @return ObservableList of Appointments
     * @throws SQLException for SQL related errors*/
    public static ObservableList<Appointment> getAppointmentByContact(int contactID) throws SQLException
    {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String query = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID=c.Contact_ID WHERE a.Contact_ID=?;";

        QueryDatabase.setStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setInt(1, contactID);

        try
        {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next())
            {
                Appointment newAppointment = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );
                appointments.add(newAppointment);
            }
            return appointments;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


    /**
     * Creates an appointment with the provided parameters
     * @param title String variable for title
     * @param contactName String variable for the contact name
     * @param description String variable for the description
     * @param userID Int variable for the user ID
     * @param location String variable for the location
     * @param type String variable for the type
     * @param startTime LocalDateTime variable for the start time
     * @param endTime LocalDateTime variable for the end time
     * @param customerID Int variable for the customer ID
     * @return Boolean true if the appointment was created and false if it was not*/
    public static boolean createAppointment(String title, String contactName, String description,
                                            int userID, String location, String type,
                                            LocalDateTime startTime, LocalDateTime endTime, int customerID) throws SQLException
    {
        Contact cont = QueryContact.getContactByName(contactName);

        String insert = "INSERT INTO APPOINTMENTS(Title, Description, Location, Type, Start, End, " + "Customer_ID, User_ID, Contact_ID) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)";

        QueryDatabase.setStatement(JDBC.getConnection(),insert);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(startTime));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(endTime));
        preparedStatement.setInt(7, customerID);
        preparedStatement.setInt(8, userID);
        preparedStatement.setInt(9, cont.getContact_ID());

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
     * Deletes an Appointment with the provided appointmentID parameter
     * @param appointmentID Int variable for the appointment id
     * @return Boolean true if the Appointment was deleted and false if else
     * @throws SQLException for SQL related errors*/
    public static  boolean deleteAppointment(int appointmentID) throws SQLException
    {
        String insert = "DELETE from appointments WHERE Appointment_Id=?";

        QueryDatabase.setStatement(JDBC.getConnection(), insert);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setInt(1, appointmentID);

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
     * Updates an appointment using the provided parameters
     * @param contactName String variable for contact name
     * @param title String variable for title
     * @param description String variable for description
     * @param location String variable for location
     * @param type String variable for type
     * @param startTime LocalDateTme variable for start time
     * @param endTime LocalDateTime variable for end time
     * @param customerID Int variable for customer id
     * @param userID Int variable for user id
     * @param appointmentID Int variable for appointment id
     * @return Boolean true if the appointment updated correctly and false if otherwise
     * @throws SQLException for SQL related errors*/
    public  static boolean updateAppointment(String contactName, String title, String description, String location,
                                             String type, LocalDateTime startTime, LocalDateTime endTime, Integer customerID,
                                             Integer userID, Integer appointmentID) throws SQLException
    {
        Contact cont = QueryContact.getContactByName(contactName);

        String update = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Customer_ID=?, Contact_ID=?, User_ID=? WHERE Appointment_ID = ?;";

        QueryDatabase.setStatement(JDBC.getConnection(), update);
        PreparedStatement preparedStatement = QueryDatabase.getStatement();

        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(startTime));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(endTime));
        preparedStatement.setInt(7, customerID);
        preparedStatement.setInt(8, cont.getContact_ID());
        preparedStatement.setInt(9, userID);
        preparedStatement.setInt(10, appointmentID);

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
