package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The QueryDatabase class sets and gets a prepared statement.*/
public class QueryDatabase {

    private static PreparedStatement preparedStatement;

    /**
     * Setter method for PreparedStatement
     * @param connection Database Connection
     * @param statement SQL Statement
     * @throws SQLException for SQL related errors*/
    public static void setStatement(Connection connection, String statement) throws SQLException
    {
        preparedStatement = connection.prepareStatement(statement);
    }

    /**
     * Getter method for PreparedStatement
     * @return PreparedStatement*/
    public static PreparedStatement getStatement()
    {
        return preparedStatement;
    }
}
