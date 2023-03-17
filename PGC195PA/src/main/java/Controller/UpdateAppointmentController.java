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
import java.util.TimeZone;

/**
 * The UpdateAppointmentController class is the controller to update Appointment information*/
public class UpdateAppointmentController implements Initializable
{
    private static Appointment updateAppointment;
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
     * Converts time to Eastern Standard Time
     * @param time LocalDateTime variable that will be converted
     * @return ZonedDateTime converted time*/
    private ZonedDateTime estConvert(LocalDateTime time) {
        return ZonedDateTime.of(time, ZoneId.of("America/New_York"));
    }

    /**
     * Converts time to desired time by zoneId
     * @param time LocalDateTime variable that will be converted
     * @param zoneId String variable of the desired time zone
     * @return ZonedDateTime converted time*/
    private ZonedDateTime timeConvert(LocalDateTime time, String zoneId) {
        return ZonedDateTime.of(time, ZoneId.of(zoneId));
    }
    public void StartDateHandler(ActionEvent actionEvent) {
    }

    public void EndDateHandler(ActionEvent actionEvent) {
    }

    /**
     * Checks that appointment information is entered correctly and saves it
     * @param actionEvent ActionEvent triggered when the save button is clicked*/
    public void SaveHandler(ActionEvent actionEvent) {
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
                boolean correct = QueryAppointment.updateAppointment(
                        ContactComboBox.getSelectionModel().getSelectedItem(),
                        TitleTextField.getText(),
                        DescriptionTextField.getText(),
                        LocationTextField.getText(),
                        TypeComboBox.getSelectionModel().getSelectedItem(),
                        LocalDateTime.of(StartDateDatePicker.getValue(),
                        LocalTime.parse(StartTimeComboBox.getSelectionModel().getSelectedItem())),
                        LocalDateTime.of(EndDateDatePicker.getValue(),
                        LocalTime.parse(EndTimeComboBox.getSelectionModel().getSelectedItem())),
                        CustomerIDComboBox.getSelectionModel().getSelectedItem(),
                        UserIDComboBox.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(AppointmentIDTextField.getText())
                );

                if(correct)
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment Updated Successfully.");
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
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to update appointment.");
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
     * Checks that there are no empty fields on the form and that the entered information is correct
     * @param appointmentTitle String variable for appointment title
     * @param appointmentDescription String variable for appointment description
     * @param appointmentLocation String variable for appointment location
     * @param appointmentID String variable for appointment id
     * @return Boolean true if everything is entered correctly and false if otherwise*/
    private boolean appointmentCheck(String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentID)
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
        else if (EndDateDatePicker.getValue().isBefore(StartDateDatePicker.getValue()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("The start date and end date need to be on the same day.");
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

        LocalDateTime possibleStart;
        LocalDateTime possibleEnd;

        try
        {
            ObservableList<Appointment> appointments = QueryAppointment.getAppointmentByCustomerID(CustomerIDComboBox.getSelectionModel().getSelectedItem());
            for(Appointment appointment: appointments)
            {
                possibleStart = appointment.getStartDate().atTime(appointment.getStartTime().toLocalTime());
                possibleEnd = appointment.getEndDate().atTime(appointment.getEndTime().toLocalTime());

                if(possibleStart.isAfter(appointmentStart) && possibleStart.isBefore(appointmentEnd))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setContentText("The selected time would overlap with an existing appointment.");
                    alert.showAndWait();
                    return false;
                }
                else if (possibleEnd.isAfter(appointmentStart) && possibleEnd.isBefore(appointmentEnd))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setContentText("The selected time would overlap with an existing appointment.");
                    alert.showAndWait();
                    return false;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        StartDateTimeConvert = estConvert(LocalDateTime.of(StartDateDatePicker.getValue(), LocalTime.parse(StartTimeComboBox.getSelectionModel().getSelectedItem())));
        EndDateTimeConvert = estConvert(LocalDateTime.of(EndDateDatePicker.getValue(), LocalTime.parse(EndTimeComboBox.getSelectionModel().getSelectedItem())));

        if(StartDateTimeConvert.toLocalTime().isAfter(LocalTime.of(22,0)))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Hours must be between 8AM and 10PM EST.");
            alert.showAndWait();
            return false;
        }

        if(EndDateTimeConvert.toLocalTime().isAfter(LocalTime.of(22,0)))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Hours must be between 8AM and 10PM EST.");
            alert.showAndWait();
            return false;
        }

        if(StartDateTimeConvert.toLocalTime().isBefore(LocalTime.of(8,0)))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Hours must be between 8AM and 10PM EST.");
            alert.showAndWait();
            return false;
        }
        if(EndDateTimeConvert.toLocalTime().isBefore(LocalTime.of(8,0)))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Hours must be between 8AM and 10PM EST.");
            alert.showAndWait();
            return false;
        }

        return true;
    }

    /**
     * Fills the selected appointment's information with the one from the referenced appointment in the parameter
     * @param appointment Referenced appointment*/
    public static void transferAppointment(Appointment appointment)
    {
        updateAppointment = appointment;
    }

    /**
     * Fills the Start and End Time combo boxes with times*/
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
     * Fills the Contacts combo box with contacts*/
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
     * Fills the Customer ID combo box with customer ids*/
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
     * Fills the User ID combo box with user ids*/
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
     * Fills Type combo box with types*/
    private void fillTypeComboBox()
    {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("Planning Session", "De-Briefing", "Follow-up", "Pre-Briefing", "Open Session");
        TypeComboBox.setItems(list);
    }

    /**
     * Activates the return button. Changes the screen back to the Appointment menu
     * @param actionEvent ActionEvent triggered when the return button is clicked*/
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
     * Fills all fields with the correct appointment information when the screen is initialized
     * @param location Location for paths
     * @param resources Resources*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTimeComboBox();
        fillContactsComboBox();
        fillCustomerIDComboBox();
        fillUserIDComboBox();
        fillTypeComboBox();

        try
        {
            Appointment appointment = QueryAppointment.getAppointmentByID(updateAppointment.getAppointmentId());

            ZonedDateTime startTime =
                    timeConvert(appointment.getStartDate().atTime(appointment.getStartTime().toLocalTime()),
                            String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
            ZonedDateTime endTime =
                    timeConvert(appointment.getEndDate().atTime(appointment.getEndTime().toLocalTime()),
                            String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));

            if(appointment != null)
            {
                ContactComboBox.getSelectionModel().select(appointment.getContactName());
                TitleTextField.setText(appointment.getTitle());
                DescriptionTextField.setText(appointment.getDescription());
                LocationTextField.setText(appointment.getLocation());
                TypeComboBox.getSelectionModel().select(appointment.getType());
                UserIDComboBox.getSelectionModel().select(Integer.valueOf(appointment.getUserId()));
                AppointmentIDTextField.setText(String.valueOf(appointment.getAppointmentId()));
                StartDateDatePicker.setValue(appointment.getStartDate());
                StartTimeComboBox.getSelectionModel().select(String.valueOf(startTime.toLocalTime()));
                EndDateDatePicker.setValue(appointment.getEndDate());
                EndTimeComboBox.getSelectionModel().select(String.valueOf(endTime.toLocalTime()));
                CustomerIDComboBox.getSelectionModel().select(Integer.valueOf(appointment.getCustomerId()));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
