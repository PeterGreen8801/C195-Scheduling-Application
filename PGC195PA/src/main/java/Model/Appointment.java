package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The Appointment class is used to create and control Appointment objects.*/
public class Appointment {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDate startDate;
    private LocalDateTime startTime;
    private LocalDate endDate;
    private LocalDateTime endTime;
    private int customerId;
    private int userId;
    private int contactId;
    private String contactName;

    /**
     * Constructor for Appointment objects
     * @param appointmentId Int variable for appointmentID
     * @param title String variable for title
     * @param description String variable for description
     * @param location String variable for location
     * @param type String variable for type
     * @param startDate LocalDate variable for startDate
     * @param startTime LocalDateTime variable for startTime
     * @param endDate LocalDate variable for endDate
     * @param endTime LocalDateTime variable for endTime
     * @param customerId Int variable for customerId
     * @param userId Int variable for userId
     * @param contactId Int variable for contactId
     * @param contactName String variable for contactName*/
    public Appointment(int appointmentId, String title, String description, String location, String type,
                       LocalDate startDate, LocalDateTime startTime,
                       LocalDate endDate, LocalDateTime endTime, int customerId,
                       int userId, int contactId, String contactName) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.contactName = contactName;
    }

    /**
     * Getter method for appointmentId
     * @return Int variable for appointmentId*/
    public int getAppointmentId()
    {
        return appointmentId;
    }

    /**
     * Setter method for appointmentId
     * @param appointmentId Int variable used to set appointmentId*/
    public void setAppointmentId(int appointmentId)
    {
        this.appointmentId = appointmentId;
    }

    /**
     * Getter method for title
     * @return String variable for title*/
    public String getTitle()
    {
        return title;
    }

    /**
     * Setter method for title
     * @param title String variable used to set title*/
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Getter method for description
     * @return String variable for description*/
    public String getDescription()
    {
        return description;
    }

    /**
     * Setter method for description
     * @param description String variable used to set description*/
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Getter method for location
     * @return String variable for location*/
    public String getLocation()
    {
        return location;
    }

    /**
     * Setter method for location
     * @param location String variable used to set location*/
    public void setLocation(String location)
    {
        this.location = location;
    }

    /**
     * Getter method for type
     * @return String variable for type*/
    public String getType()
    {
        return type;
    }

    /**
     * Setter method for type
     * @param type String variable used to set type*/
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Getter method for startDate
     * @return LocalDate variable startDate*/
    public LocalDate getStartDate()
    {
        return startDate;
    }

    /**
     * Setter method for startDate
     * @param startDate LocalDate variable used to set startDate*/
    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    /**
     * Getter method for startTime
     * @return LocalDateTime variable for startTime*/
    public LocalDateTime getStartTime()
    {
        return startTime;
    }

    /**
     * Setter method for startTime
     * @param startTime LocalDateTime variable used to set startTime*/
    public void setStartTime(LocalDateTime startTime)
    {
        this.startTime = startTime;
    }

    /**
     * Getter method for endDate
     * @return LocalDate variable for endDate*/
    public LocalDate getEndDate()
    {
        return endDate;
    }

    /**
     * Setter method for endDate
     * @param endDate LocalDate variable for endDate*/
    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    /**
     * Getter method for endTime
     * @return LocalDateTime variable for endTime*/
    public LocalDateTime getEndTime()
    {
        return endTime;
    }

    /**
     * Setter method for endTime
     * @param endTime LocalDateTime variable used to set endTime*/
    public void setEndTime(LocalDateTime endTime)
    {
        this.endTime = endTime;
    }

    /**
     * Getter method for customerId
     * @return Int variable for customerId*/
    public int getCustomerId()
    {
        return customerId;
    }

    /**
     * Setter method for customerId
     * @param customerId Int variable used to set customerId*/
    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    /**
     * Getter method for userId
     * @return Int variable for userId*/
    public int getUserId()
    {
        return userId;
    }

    /**
     * Setter method for userId
     * @param userId Int variable used to set userId*/
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    /**
     * Getter method for contactId
     * @return Int variable for contactId*/
    public int getContactId()
    {
        return contactId;
    }

    /**
     * Setter method for contactId
     * @param contactId Int variable used to set contactId*/
    public void setContactId(int contactId)
    {
        this.contactId = contactId;
    }

    /**
     * Getter method for contactName
     * @return String variable for contactName*/
    public String getContactName()
    {
        return contactName;
    }

    /**
     * Setter method for contactName
     * @param contactName String variable used to set contactName*/
    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }
}
