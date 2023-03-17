package Controller;

import Database.QueryAppointment;
import Database.QueryContact;
import Database.QueryCustomer;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
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

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

/**
 * The ReportsController class allows the user to generate various reports.*/
public class ReportsController implements Initializable {

    @FXML
    private ComboBox<Integer> CustomerIDComboBox;
    @FXML
    private ComboBox<Integer> ContactIDComboBox;
    @FXML
    private RadioButton TypeRadioButton;
    @FXML
    private RadioButton MonthRadioButton;
    @FXML
    private ToggleGroup TypeMonthToggleGroup;
    @FXML
    private Button GenerateButtonOne;
    @FXML
    private Button GenerateButtonTwo;
    @FXML
    private Button GenerateButtonThree;




    /**
     * Activates the return button. Changes the screen back to the Main menu
     * @param actionEvent ActionEvent triggered when the return button is clicked*/
    public void ReturnHandler(ActionEvent actionEvent) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/pgc195pa/MainMenuView.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Error Loading Screen.");
            alert.showAndWait();
        }
    }

    /**
     * Generates the total number of appointments by the customer id
     * @param actionEvent ActionEvent generates the report based on the selected item in the combo box and is activated when the generate button is clicked*/
    public void GenerateCustomerIDHandler(ActionEvent actionEvent) {
        Integer customerID = CustomerIDComboBox.getSelectionModel().getSelectedItem();

        try
        {
            ObservableList<Appointment> appointments = QueryAppointment.getAppointmentByCustomerID(customerID);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Report: Customer Appointments by Customer ID");
            alert.setContentText("Overall count of Customer Appointments by Customer ID #" + customerID + ": " + appointments.size());
            alert.setResizable(true);
            alert.showAndWait();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Generates a report by the selected contact
     * Shows appointment schedules in a dialog box for the selected contact
     * @param actionEvent ActionEvent generates a report based on which option is selected in the combo box*/
    public void GenerateContactHandler(ActionEvent actionEvent) {
        Integer contactID = ContactIDComboBox.getSelectionModel().getSelectedItem();
        try
        {
            ObservableList<Appointment> appointments = QueryAppointment.getAppointmentByContact(contactID);
            if(appointments != null)
            {
                for(Appointment appointment: appointments)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Report: Customer Appointments by Contact ID");
                    alert.setContentText("Appointments by Contact ID #" + contactID + ": " +
                            "\nAppointment ID: " + appointment.getAppointmentId() +
                            "\nTitle: " + appointment.getTitle() +
                            "\nType: " + appointment.getType() +
                            "\nDescription " + appointment.getDescription() +
                            "\nStart Date: " + appointment.getStartDate() +
                            "\nStart Time: " + appointment.getStartTime() +
                            "\nEnd Date: " + appointment.getEndDate() +
                            "\nEnd Time: " + appointment.getEndTime() +
                            "\nCustomer ID: " + appointment.getCustomerId() +
                            "\nUser ID: " + appointment.getUserId()
                    );
                    alert.setResizable(true);
                    alert.showAndWait();
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Generates a report by either type or month
     * Gives the total number of appointments by type or month
     * @param actionEvent ActionEvent generates report depending on which option is selected*/
    public void GenerateTypeMonthHandler(ActionEvent actionEvent) {
        ObservableList<String> Planning = FXCollections.observableArrayList();
        ObservableList<String> Debriefing = FXCollections.observableArrayList();
        ObservableList<String> Followup = FXCollections.observableArrayList();
        ObservableList<String> Prebriefing = FXCollections.observableArrayList();
        ObservableList<String> Open = FXCollections.observableArrayList();

        ObservableList<Integer> January = FXCollections.observableArrayList();
        ObservableList<Integer> February = FXCollections.observableArrayList();
        ObservableList<Integer> March = FXCollections.observableArrayList();
        ObservableList<Integer> April = FXCollections.observableArrayList();
        ObservableList<Integer> May = FXCollections.observableArrayList();
        ObservableList<Integer> June = FXCollections.observableArrayList();
        ObservableList<Integer> July = FXCollections.observableArrayList();
        ObservableList<Integer> August = FXCollections.observableArrayList();
        ObservableList<Integer> September = FXCollections.observableArrayList();
        ObservableList<Integer> October = FXCollections.observableArrayList();
        ObservableList<Integer> November = FXCollections.observableArrayList();
        ObservableList<Integer> December = FXCollections.observableArrayList();

        try
        {
            ObservableList<Appointment> appointments = QueryAppointment.getAppointment();

            if(appointments != null)
            {
                for(Appointment appointment: appointments)
                {
                    String type = appointment.getType();
                    LocalDate date = appointment.getStartDate();

                    if(type.equals("Planning Session"))
                    {
                        Planning.add(type);
                    }
                    else if(type.equals("De-Briefing"))
                    {
                        Debriefing.add(type);
                    }
                    else if(type.equals("Follow-up"))
                    {
                        Followup.add(type);
                    }
                    else if(type.equals("Pre-Briefing"))
                    {
                        Prebriefing.add(type);
                    }
                    else if(type.equals("Open Session"))
                    {
                        Open.add(type);
                    }
                    else
                    {

                    }

                    if(date.getMonth().equals(Month.of(1)))
                    {
                        January.add(date.getMonthValue());
                    }
                    if(date.getMonth().equals(Month.of(2)))
                    {
                        February.add(date.getMonthValue());
                    }
                    if(date.getMonth().equals(Month.of(3)))
                    {
                        March.add(date.getMonthValue());
                    }
                    if(date.getMonth().equals(Month.of(4)))
                    {
                        April.add(date.getMonthValue());
                    }
                    if(date.getMonth().equals(Month.of(5)))
                    {
                        May.add(date.getMonthValue());
                    }
                    if(date.getMonth().equals(Month.of(6)))
                    {
                        June.add(date.getMonthValue());
                    }
                    if(date.getMonth().equals(Month.of(7)))
                    {
                        July.add(date.getMonthValue());
                    }
                    if(date.getMonth().equals(Month.of(8)))
                    {
                        August.add(date.getMonthValue());
                    }
                    if(date.getMonth().equals(Month.of(9)))
                    {
                        September.add(date.getMonthValue());
                    }
                    if(date.getMonth().equals(Month.of(10)))
                    {
                        October.add(date.getMonthValue());
                    }
                    if(date.getMonth().equals(Month.of(11)))
                    {
                        November.add(date.getMonthValue());
                    }
                    if(date.getMonth().equals(Month.of(12)))
                    {
                        December.add(date.getMonthValue());
                    }
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        if(TypeRadioButton.isSelected())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Report: Number of customer Appointments by Type");
            alert.setContentText("Total number of Customer Appointments by Type: " +
                    "\nPlanning Session: " + Planning.size() +
                    "\nDe-Briefing: " + Debriefing.size() +
                    "\nFollow-up: " + Followup.size() +
                    "\nPre-Briefing: " + Prebriefing.size() +
                    "\nOpen Session: " + Open.size());
            alert.setResizable(true);
            alert.showAndWait();
        }

        if(MonthRadioButton.isSelected())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Report: Customer Appointment Count by Month");
            alert.setContentText("Total number of Customer Appointments by Month: " +
                    "\nJanuary: " + January.size() +
                    "\nFebruary: " + February.size() +
                    "\nMarch: " + March.size() +
                    "\nApril: " + April.size() +
                    "\nMay: " + May.size() +
                    "\nJune: " + June.size() +
                    "\nJuly: " + July.size() +
                    "\nAugust: " + August.size() +
                    "\nSeptember: " + September.size() +
                    "\nOctober: " + October.size() +
                    "\nNovember: " + November.size() +
                    "\nDecember: " + December.size()
            );
            alert.showAndWait();
        }

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
     * Fills the contact combo box with contact ids*/
    private void fillContactIDComboBox()
    {
        ObservableList<Integer> list = FXCollections.observableArrayList();

        try
        {
            ObservableList<Contact> contacts = QueryContact.getContact();
            if(contacts != null)
            {
                for(Contact contact: contacts)
                {
                    if(!list.contains(contact.getContact_ID()))
                    {
                        list.add(contact.getContact_ID());
                    }
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        ContactIDComboBox.setItems(list);
    }

    /**
     * Sets the combo boxes with the correct information and toggles the radio buttons
     * @param location Location for paths
     * @param resources Resources*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TypeRadioButton.setToggleGroup(TypeMonthToggleGroup);
        MonthRadioButton.setToggleGroup(TypeMonthToggleGroup);
        TypeRadioButton.setSelected(true);
        MonthRadioButton.setSelected(false);
        fillCustomerIDComboBox();
        fillContactIDComboBox();
    }
}
