package Database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The JDBC class creates and closes the connection to the database.*/
public abstract class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "sqlUser";
    private static String password = "Passw0rd!";
    public static Connection connection = null;

    /**
     * Starts the connection to the database*/
    public static void openConnection()
    {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("Connection Successful");
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Closes the connection to the database*/
    public static void closeConnection()
    {
        try{
            connection.close();
            System.out.println("Connection Closed Successfully");
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Getter method for the connection
     * @return the connection*/
    public static Connection getConnection()
    {
        return connection;
    }
}
