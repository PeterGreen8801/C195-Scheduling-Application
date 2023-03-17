package Controller;

import Database.QueryAppointment;
import Model.Appointment;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The AppointmentController class is the controller to view and control Appointment information*/
public class AppointmentController implements Initializable {

    static ObservableList<Appointment> appointmentObservableList;

    @FXML
    private RadioButton AllRadioButton;
    @FXML
    private RadioButton WeeklyRadioButton;
    @FXML
    private RadioButton MonthlyRadioButton;
    @FXML
    private Button DeleteAppointmentButton;
    @FXML
    private Button UpdateAppointmentButton;
    @FXML
    private Button CreateAppointmentButton;
    @FXML
    private Button SearchButton;
    @FXML
    private Button ReturnButton;
    @FXML
    private TableView<Appointment> AppointmentsTableView;
    @FXML
    private TextField SearchTextField;
    @FXML
    private ToggleGroup ToggleGroup;
    @FXML
    private TableColumn<?, ?> AppointmentIDColumn;
    @FXML
    private TableColumn<?, ?> TitleColumn;
    @FXML
    private TableColumn<?, ?> DescriptionColumn;
    @FXML
    private TableColumn<?, ?> LocationColumn;
    @FXML
    private TableColumn<?, ?> ContactColumn;
    @FXML
    private TableColumn<?, ?> TypeColumn;
    @FXML
    private TableColumn<?, ?> StartDateTimeColumn;
    @FXML
    private TableColumn<?, ?> EndDateTimeColumn;
    @FXML
    private TableColumn<?, ?> CustomerIDColumn;
    @FXML
    private TableColumn<?, ?> UserIDColumn;


    /**
     * Brings the user to the Create Appointment screen
     * @param actionEvent ActionEvent triggered when the Create Appointment button is clicked*/
    public void CreateAppointmentHandler(ActionEvent actionEvent) throws IOException {

        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/pgc195pa/CreateAppointmentView.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Create Appointment");
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
     * Brings the user to the Update Appointments screen
     * @param actionEvent ActionEvent triggered when the Update Appointments button is clicked*/
    public void UpdateAppointmentHandler(ActionEvent actionEvent) throws IOException {
        UpdateAppointmentController.transferAppointment(AppointmentsTableView.getSelectionModel().getSelectedItem());

        if(AppointmentsTableView.getSelectionModel().getSelectedItem() != null)
        {
            try
            {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/pgc195pa/UpdateAppointmentView.fxml"));
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Update Appointment");
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
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Select an appointment to update.");
            alert.showAndWait();
        }
    }

    /**
     * Deletes a selected appointment and updates the table
     * @param actionEvent ActionEvent triggered when the Delete Appointment button is clicked*/
    public void DeleteAppointmentHandler(ActionEvent actionEvent) {
        Appointment appointmentToDelete = AppointmentsTableView.getSelectionModel().getSelectedItem();
        if (appointmentToDelete == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Select an appointment to delete.");
            alert.showAndWait();
        }
        else if (AppointmentsTableView.getSelectionModel().getSelectedItem() != null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The selected appointment will be deleted. Proceed?");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && (result.get() == ButtonType.OK))
            {
                try
                {
                    boolean deleteSuccess = QueryAppointment.deleteAppointment(AppointmentsTableView.getSelectionModel().getSelectedItem().getAppointmentId());

                    if(deleteSuccess)
                    {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Delete Successful");
                        alert.setContentText("Deleted Appointment ID: " + appointmentToDelete.getAppointmentId() + " Type: " + appointmentToDelete.getType());
                        alert.showAndWait();

                        appointmentObservableList = QueryAppointment.getAppointment();
                        AppointmentsTableView.setItems(appointmentObservableList);
                        AppointmentsTableView.refresh();
                    }
                    else
                    {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setContentText("Unable to delete appointment.");
                        alert.showAndWait();
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Brings the user back to the Main Menu screen
     * @param actionEvent ActionEvent triggered when Return button is clicked*/
    public void ReturnHandler(ActionEvent actionEvent) throws IOException {

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
     * Allows the user to toggle by all, week, or month.
     * Will change the table depending on which option is selected
     * @param actionEvent ActionEvent triggered when a Radio Button is selected*/
    public void FilterHandler(ActionEvent actionEvent) {

        if(AllRadioButton.isSelected())
        {
            try
            {
                appointmentObservableList = QueryAppointment.getAppointment();
                fillTable(appointmentObservableList);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else if (ToggleGroup.getSelectedToggle().equals(MonthlyRadioButton))
        {
            try
            {
                appointmentObservableList = QueryAppointment.getAppointmentByMonth();
                fillTable(appointmentObservableList);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else if (ToggleGroup.getSelectedToggle().equals(WeeklyRadioButton))
        {
            try
            {
                appointmentObservableList = QueryAppointment.getAppointmentByWeek();
                fillTable(appointmentObservableList);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }

    //Had an error where the cells were not getting filled in correctly. Fixed by ensuring the names listed here match what they
    //should on the Appointment Model.
    /**
     * Fills the Appointment table with the correct information
     * @param appointments ObservableList of Appointments*/
    private void fillTable(ObservableList<Appointment> appointments)
    {
        AppointmentsTableView.setItems(appointments);
        AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        ContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        StartDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        EndDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        UserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        AppointmentsTableView.refresh();
    }

    /**
     * Allows the user to search for specific appointments
     * @param actionEvent ActionEvent triggered when the Search button is clicked*/
    public void SearchHandler(ActionEvent actionEvent) {
        ObservableList<Appointment> search = findAppointment(SearchTextField.getText());
        AppointmentsTableView.setItems(search);
    }

    /**
     * Returns a list of appointments depending on the user's entered text
     * @param text String variable for text to search
     * @return ObservableList of Appointments*/
    private static  ObservableList<Appointment> findAppointment(String text)
    {
        ObservableList<Appointment> foundAppointments = FXCollections.observableArrayList();

        for(Appointment appointment: appointmentObservableList)
        {
            if (appointment.getTitle().contains(text))
            {
                foundAppointments.add(appointment);
            }
            else if (Integer.toString(appointment.getAppointmentId()).contains(text))
            {
                foundAppointments.add(appointment);
            }
        }
        return foundAppointments;
    }

    /**
     * Initializes the Appointment table with appropriate information and sets Radio Buttons
     * @param url Location
     * @param resourceBundle Resources */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AllRadioButton.setToggleGroup(ToggleGroup);
        WeeklyRadioButton.setToggleGroup(ToggleGroup);
        MonthlyRadioButton.setToggleGroup(ToggleGroup);

        try
        {
            appointmentObservableList = QueryAppointment.getAppointment();
            fillTable(appointmentObservableList);
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
