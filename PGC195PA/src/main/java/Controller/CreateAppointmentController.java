package Controller;

import Database.QueryAppointment;
import Database.QueryContact;
import Database.QueryCustomer;
import Database.QueryUser;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The CreateAppointmentController class is the controller to create Appointments.*/
public class CreateAppointmentController implements Initializable {
    private ZonedDateTime StartDateTimeConvert;
    private ZonedDateTime EndDateTimeConvert;
    @FXML
    private DatePicker StartDateDatePicker;
    @FXML
    private DatePicker EndDateDatePicker;
    @FXML
    private TextField LocationTextField;
    @FXML
    private TextField DescriptionTextField;
    @FXML
    private TextField TitleTextField;
    @FXML
    private TextField AppointmentIDTextField;
    @FXML
    private ComboBox<Integer> CustomerIDComboBox;
    @FXML
    private ComboBox<Integer> UserIDComboBox;
    @FXML
    private ComboBox<String> TypeComboBox;
    @FXML
    private ComboBox<String> EndTimeComboBox;
    @FXML
    private ComboBox<String> StartTimeComboBox;
    @FXML
    private ComboBox<String> ContactComboBox;
    @FXML
    private Button SaveButton;
    @FXML
    private Button ReturnButton;

    /**
     * Sets the end date value to the same one as the start date*/
    public void StartDateHandler(ActionEvent actionEvent) {
        EndDateDatePicker.setValue(StartDateDatePicker.getValue());
    }

    public void StartTimeHandler(ActionEvent actionEvent) {
    }

    public void EndDateHandler(ActionEvent actionEvent) {
    }

    public void EndTimeHandler(ActionEvent actionEvent) {
    }

    public void TypeHandler(ActionEvent actionEvent) {
    }

    /**
     * Checks and Saves the entered information as a new Appointment
     * @param actionEvent ActionEvent triggered when the save button is clicked*/
    public void SaveHandler(ActionEvent actionEvent) throws SQLException {
        boolean isValid = appointmentCheck(
                TitleTextField.getText(),
                DescriptionTextField.getText(),
                LocationTextField.getText(),
                AppointmentIDTextField.getText()
        );

        if(isValid)
        {
            try
            {
                boolean correct = QueryAppointment.createAppointment(
                        TitleTextField.getText(),
                        ContactComboBox.getValue(),
                        DescriptionTextField.getText(),
                        UserIDComboBox.getSelectionModel().getSelectedItem(),
                        LocationTextField.getText(),
                        TypeComboBox.getValue(),
                        LocalDateTime.of(StartDateDatePicker.getValue(),LocalTime.parse(StartTimeComboBox.getSelectionModel().getSelectedItem())),
                        LocalDateTime.of(EndDateDatePicker.getValue(), LocalTime.parse(EndTimeComboBox.getSelectionModel().getSelectedItem())),
                        CustomerIDComboBox.getValue()
                );

                if(correct)
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "New appointment created.");
                    Optional<ButtonType> result = alert.showAndWait();

                    if(result.isPresent() && (result.get() == ButtonType.OK))
                    {
                        try
                        {
                            Parent root = FXMLLoader.load(getClass().getResource("/com/example/pgc195pa/AppointmentView.fxml"));
                            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setTitle("Appointments");
                            stage.setScene(scene);
                            stage.show();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setContentText("Error Loading Screen.");
                            alert.showAndWait();
                        }
                    }
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to create new appointment");
                    Optional<ButtonType> result = alert.showAndWait();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    /**
     * Checks that combo boxes are not empty and that the fields have the correct information entered
     * @param appointmentTitle String variable for appointment title
     * @param appointmentDescription String variable for appointment description
     * @param appointmentLocation String variable for appointment location
     * @param appointmentID String variable for appointment id
     * @return Boolean true if information is correct and false if it is incorrect*/
    private boolean appointmentCheck(String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentID) throws SQLException
    {
        if(ContactComboBox.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("A contact needs to be entered.");
            alert.showAndWait();
            return false;
        }
        else if(UserIDComboBox.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("A User ID needs to be entered.");
            alert.showAndWait();
            return false;
        }
        else if(CustomerIDComboBox.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("A Customer ID needs to be entered.");
            alert.showAndWait();
            return false;
        }
        else if(TypeComboBox.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("A Type needs to be entered.");
            alert.showAndWait();
            return false;
        }
        else if(StartTimeComboBox.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("A Start Time needs to be entered.");
            alert.showAndWait();
            return false;
        }
        else if(EndTimeComboBox.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("A End Time needs to be entered.");
            alert.showAndWait();
            return false;
        }
        else if(StartDateDatePicker.getValue() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("A Start Date needs to be entered.");
            alert.showAndWait();
            return false;
        }
        else if(EndDateDatePicker.getValue() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("An End Date needs to be entered.");
            alert.showAndWait();
            return false;
        }
        else if(appointmentTitle.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("A Title needs to be entered.");
            alert.showAndWait();
            return false;
        }
        else if(appointmentDescription.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("A Description needs to be entered.");
            alert.showAndWait();
            return false;
        }
        else if(appointmentLocation.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("A Location needs to be entered.");
            alert.showAndWait();
            return false;
        }
        else if(appointmentID.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("An Appointment ID needs to be entered.");
            alert.showAndWait();
            return false;
        }

        LocalTime start = LocalTime.parse(StartTimeComboBox.getSelectionModel().getSelectedItem());
        LocalTime end = LocalTime.parse(EndTimeComboBox.getSelectionModel().getSelectedItem());

        if(end.isBefore(start))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("The start time needs to be before the end time.");
            alert.showAndWait();
            return false;
        }

        LocalDate dateStart = StartDateDatePicker.getValue();
        LocalDate dateEnd = EndDateDatePicker.getValue();

        if(!dateStart.equals(dateEnd))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("The start date and end date need to be on the same day.");
            alert.showAndWait();
            return false;
        }

        LocalDateTime appointmentStart = dateStart.atTime(start);
        LocalDateTime appointmentEnd = dateEnd.atTime(end);

        return timeDifference(appointmentStart, appointmentEnd);

    }

    /**
     * Checks if the time is valid and between 8am and 10pm
     * @param startTime LocalTime variable for the start time
     * @param endTime LocalTime variable for the end time
     * @return Boolean true if tha time is valid and false if the time is invalid*/
    private boolean timeCheck(LocalTime startTime, LocalTime endTime)
    {
        if(startTime.isAfter(LocalTime.of(22,00)))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Hours must be between 8AM and 10PM EST.");
            alert.showAndWait();
            return false;
        }
        else if (endTime.isBefore(LocalTime.of(8,00)))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Hours must be between 8AM and 10PM EST.");
            alert.showAndWait();
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Checks to see if there are any problems with overlapping appointments
     * @param startTime LocalDateTime variable for the start time
     * @param endTime LocalDateTime variable for the end time
     * @return Boolean true if the time is valid and false if it is invalid*/
    private boolean timeDifference(LocalDateTime startTime, LocalDateTime endTime) throws SQLException
    {
        boolean correctTime = true;

        try
        {
            ObservableList<Appointment> appointments = QueryAppointment.getAppointment();

            for(Appointment appointment: appointments)
            {
                if(appointment.getStartTime().isEqual(startTime))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setContentText("The selected time would overlap with an existing appointment.");
                    alert.showAndWait();
                    correctTime = false;
                    break;
                }
                else if(appointment.getEndTime().isEqual(endTime))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setContentText("The selected time would overlap with an existing appointment.");
                    alert.showAndWait();
                    correctTime = false;
                    break;
                }
                else if(appointment.getStartTime().isBefore(startTime) && appointment.getEndTime().isAfter(startTime))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setContentText("The selected time would overlap with an existing appointment.");
                    alert.showAndWait();
                    correctTime = false;
                    break;
                }
                else if(appointment.getStartTime().isBefore(endTime) && appointment.getEndTime().isAfter(endTime))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setContentText("The selected time would overlap with an existing appointment.");
                    alert.showAndWait();
                    correctTime = false;
                    break;
                }
                else if(startTime.isBefore(appointment.getStartTime()) && !endTime.isBefore(appointment.getStartTime()))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setContentText("These times would overlap with: " + appointment.getContactName() + " from " + appointment.getStartTime() + " to " + appointment.getEndTime());
                    alert.showAndWait();
                    correctTime = false;
                    break;
                }
            }
            if(correctTime)
            {
                correctTime = timeCheck(startTime.toLocalTime(), endTime.toLocalTime());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return correctTime;
    }

    /**
     * Fills the star and end time combo boxes with times the user can select from*/
    private void fillTimeComboBox()
    {
        ObservableList<String> time = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(7,0);
        LocalTime end = LocalTime.of(23,0);

        time.add(start.toString());
        while(start.isBefore(end))
        {
            start = start.plusMinutes(15);
            time.add(start.toString());
        }

        StartTimeComboBox.setItems(time);
        EndTimeComboBox.setItems(time);
    }

    /**
     * Fills the contacts combo box with contact names*/
    private void fillContactsComboBox()
    {
        ObservableList<String> list = FXCollections.observableArrayList();

        try
        {
            ObservableList<Contact> contacts = QueryContact.getContact();

            if(contacts != null)
            {
                for(Contact contact: contacts)
                {
                    if(!list.contains(contact.getContact_Name()))
                    {
                        list.add(contact.getContact_Name());
                    }
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        ContactComboBox.setItems(list);
    }

    /**
     * Fills the customer id combo box with customer ids*/
    private void fillCustomerIDComboBox()
    {
        ObservableList<Integer> list = FXCollections.observableArrayList();

        try
        {
            ObservableList<Customer> customers = QueryCustomer.getCustomers();

            if(customers != null)
            {
                for(Customer customer: customers)
                {
                    list.add(customer.getCustomer_ID());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        CustomerIDComboBox.setItems(list);
    }

    /**
     * Fills user id combo box with user ids*/
    private void fillUserIDComboBox()
    {
        ObservableList<Integer> list = FXCollections.observableArrayList();

        try
        {
            ObservableList<User> users = QueryUser.getUser();
            if(users != null)
            {
                for(User user: users)
                {
                    list.add(user.getUserID());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        UserIDComboBox.setItems(list);
    }

    /**
     * Fills the type combo box with appointment types*/
    private void fillTypeComboBox()
    {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("Planning Session", "De-Briefing", "Follow-up", "Pre-Briefing", "Open Session");
        TypeComboBox.setItems(list);
    }

    /**
     * Brings the user back to the Appointments screen
     * @param actionEvent ActionEvent triggered when the Return button is clicked*/
    public void ReturnHandler(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Return to the Appointments Menu?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && (result.get() == ButtonType.OK))
        {
            try
            {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/pgc195pa/AppointmentView.fxml"));
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Appointments");
                stage.setScene(scene);
                stage.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setContentText("Error Loading Screen.");
                alert.showAndWait();
            }

        }
    }

    /**
     * Initializes the start/end time, contacts, customerId, userId, and type combo boxes
     * @param location Location for paths
     * @param resources Resources*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTimeComboBox();
        fillContactsComboBox();
        fillCustomerIDComboBox();
        fillUserIDComboBox();
        fillTypeComboBox();
    }
}
