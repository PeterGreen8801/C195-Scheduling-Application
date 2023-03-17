package Model;

/**
 * The Contact class is used to create and control Contact objects.*/
public class Contact {
    private int Contact_ID;
    private String Contact_Name;
    private  String Email;

    /**
     * Constructor for Contact objects
     * @param Contact_ID Int variable for Contact_ID
     * @param Contact_Name String variable for Contact_Name
     * @param Email String variable for Email*/
    public Contact(int Contact_ID, String Contact_Name, String Email)
    {
        this.Contact_ID = Contact_ID;
        this.Contact_Name = Contact_Name;
        this.Email = Email;
    }

    /**
     * Getter method for Contact_ID
     * @return Int variable for Contact_ID*/
    public int getContact_ID()
    {
        return Contact_ID;
    }

    /**
     * Setter method for Contact_ID
     * @param Contact_ID Int variable used to set Contact_ID*/
    public void setContact_ID(int Contact_ID)
    {
        this.Contact_ID = Contact_ID;
    }

    /**
     * Getter method for Contact_Name
     * @return String variable for Contact_Name*/
    public String getContact_Name()
    {
        return Contact_Name;
    }

    /**
     * Setter method for Contact_Name
     * @param Contact_Name String variable used to set Contact_Name*/
    public void setContact_Name(String Contact_Name)
    {
        this.Contact_Name = Contact_Name;
    }

    /**
     * Getter method for Email
     * @return String variable for Email*/
    public String getEmail()
    {
        return Email;
    }

    /**
     * Setter method for Email
     * @param Email String variable used to set Email*/
    public void setEmail(String Email)
    {
        this.Email = Email;
    }
}
