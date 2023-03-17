package Model;

/**
 * The Customer class is used to create and control Customer objects.*/
public class Customer {

    private int Customer_ID;
    private String Customer_Name;
    private String address;
    private String Postal_Code;
    private String Phone;
    private String Division;
    private String country;
    private int Division_ID;


    /**
     * Constructor for Customer objects
     * @param Customer_ID Int variable for Customer_ID
     * @param customer_Name String variable for Customer_Name
     * @param address String variable for address
     * @param postalCode String variable for Postal_Code
     * @param phone String variable for Phone
     * @param division String variable for Division
     * @param country String variable for country
     * @param divisionID Int variable for Division_ID*/
    public Customer(int Customer_ID, String customer_Name, String address, String postalCode, String phone, String division, String country, int divisionID)
    {
        this.Customer_ID = Customer_ID;
        this.Customer_Name = customer_Name;
        this.address = address;
        this.Postal_Code = postalCode;
        this.Phone = phone;
        this.Division = division;
        this.country = country;
        this.Division_ID = divisionID;
    }

    /**
     * Getter method for Customer_ID
     * @return Int variable for Customer_ID*/
    public int getCustomer_ID()
    {
        return Customer_ID;
    }

    /**
     * Setter method for Customer_ID
     * @param customer_ID Int variable used to set Customer_ID*/
    public void setCustomer_ID(int customer_ID)
    {
         this.Customer_ID = Customer_ID;
    }

    /**
     * Getter method for Customer_Name
     * @return String variable for Customer_Name*/
    public String getCustomer_Name()
    {
        return Customer_Name;
    }

    /**
     * Setter method for Customer_Name
     * @param customer_Name String variable used to set Customer_Name*/
    public void setCustomer_Name(String customer_Name)
    {
        this.Customer_Name = customer_Name;
    }

    /**
     * Getter method for address
     * @return String variable for address*/
    public String getAddress()
    {
        return address;
    }

    /**
     * Setter method for address
     * @param address String variable used to set address*/
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * Getter method for Postal_Code
     * @return String variable for Postal_Code*/
    public String getPostal_Code()
    {
        return Postal_Code;
    }

    /**
     * Setter method for Postal_Code
     * @param postal_Code String variable used to set Postal_Code*/
    public void setPostal_Code(String postal_Code)
    {
        this.Postal_Code = postal_Code;
    }

    /**
     * Getter method for Phone
     * @return String variable for Phone*/
    public String getPhone()
    {
        return Phone;
    }

    /**
     * Setter method for Phone
     * @param phone String variable used to set Phone*/
    public void setPhone(String phone)
    {
        this.Phone = phone;
    }

    /**
     * Getter method for Division
     * @return String variable for Division*/
    public String getDivision()
    {
        return Division;
    }

    /**
     * Setter method for Division
     * @param division String variable used to set Division*/
    public void setDivision(String division)
    {
        this.Division = division;
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
/**
 * Getter method for Division_ID
 * @return Int variable for Division_ID*/
    public int getDivision_ID()
    {
        return Division_ID;
    }

    /**
     * Setter method for Division_ID
     * @param division_ID Int variable used to set Division_ID*/
    public void setDivision_ID(int division_ID)
    {
        this.Division_ID = division_ID;
    }
}
