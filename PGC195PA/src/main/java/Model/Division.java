package Model;

/**
 * The Division class is used to create and control Division objects.*/
public class Division {

    private int divisionID;
    private String division;
    private int countryID;

    /**
     * Constructor for Division objects
     * @param divisionID Int variable for divisionID
     * @param division String variable for division
     * @param countryID Int variable for countryID*/
    public Division(int divisionID, String division, int countryID)
    {
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }

    /**
     * Getter method for divisionID
     * @return Int variable for divisionID*/
    public int getDivisionID()
    {
        return divisionID;
    }

    /**
     * Setter method for divisionID
     * @param divisionID Int variable for divisionID*/
    public void setDivisionID(int divisionID)
    {
        this.divisionID = divisionID;
    }

    /**
     * Getter method for division
     * @return String variable for division*/
    public String getDivision()
    {
        return division;
    }

    /**
     * Setter method for division
     * @param division String variable for division*/
    public void setDivision(String division)
    {
        this.division = division;
    }

    /**
     * Getter method for countryID
     * @return Int variable for countryID*/
    public int getCountryID()
    {
        return countryID;
    }

    /**
     * Setter method for countryID
     * @param countryID Int variable for countryID*/
    public void setCountryID(int countryID)
    {
        this.countryID = countryID;
    }
}
