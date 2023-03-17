package Model;

/**
 * The User class is used to create and control User objects.*/
public class User {

    private int userID;
    private String username;
    private String password;

    /**
     * Constructor for User objects
     * @param userID Int variable used to hold the user's id
     * @param username String variable used to hold user's username
     * @param password String variable used to hold the user's password*/
    public User(int userID, String username, String password)
    {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    /**
     * Getter method for userID
     * @return userID*/
    public int getUserID()
    {
        return userID;
    }

    /**
     * Setter method for userID
     * @param userID Int variable used to hold the userID*/
    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    /**
     * Getter method for username
     * @return username String for username*/
    public String getUsername()
    {
        return username;
    }

    /**
     * Setter method for username
     * @param username String used to hold username*/
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Getter method for password
     * @return password String for password*/
    public String getPassword()
    {
        return  password;
    }

    /**
     * Setter method for password
     * @param password String used to hold password*/
    public void setPassword(String password)
    {
        this.password = password;
    }
}
