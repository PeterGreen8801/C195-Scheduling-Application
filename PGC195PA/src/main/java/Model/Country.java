package Model;

/**
 * The Country class is used to create and control Country objects.*/
public class Country {
    private int countryID;
    private String country;

    /**
     * Constructor for Country objects
     * @param countryID Int variable for countryID
     * @param country String variable country*/
    public Country(int countryID, String country)
    {
        this.countryID = countryID;
        this.country = country;
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
     * @param countryID Int variable used to set countryID*/
    public void setCountryID(int countryID)
    {
        this.countryID = countryID;
    }

    /**
     * Getter method for country
     * @return String variable for country*/
    public String getCountry()
    {
        return country;
    }

    /**
     * Setter method for country
     * @param country String variable used to set country*/
    public void setCountry(String country)
    {
        this.country = country;
    }
}
