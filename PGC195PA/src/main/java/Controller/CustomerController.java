package Controller;

import Database.QueryAppointment;
import Database.QueryCustomer;
import Model.Appointment;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The CustomerController class is the controller to view and control Customer information*/
public class CustomerController implements Initializable {

    static ObservableList<Customer> customers;
    @FXML
    private Button CreateCustomerButton;
    @FXML
    private Button UpdateCustomerButton;
    @FXML
    private Button  DeleteCustomerButton;
    @FXML
    private Button ReturnButton;
    @FXML
    private Button SearchButton;
    @FXML
    private TextField SearchTextField;
    @FXML
    private TableView<Customer> CustomerTableView;
    @FXML
    private TableColumn<?, ?> CustomerIDColumn;
    @FXML
    private TableColumn<?, ?> NameColumn;
    @FXML
    private TableColumn<?, ?> AddressColumn;
    @FXML
    private TableColumn<?, ?> PostalCodeColumn;
    @FXML
    private TableColumn<?, ?> DivisionColumn;
    @FXML
    private TableColumn<?, ?> CountryColumn;
    @FXML
    private TableColumn<?, ?> PhoneColumn;

    /**
     * Brings the user to the Create Customer screen
     * @param actionEvent ActionEvent triggered when the create customer button is clicked*/
    public void CreateCustomerHandler(ActionEvent actionEvent) throws IOException {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/pgc195pa/CreateCustomerView.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Create Customer");
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
     * Brings the user to the Update Customer screen
     * @param actionEvent ActionEvent triggered when the Update Customer button is clicked*/
    public void UpdateCustomerHandler(ActionEvent actionEvent) throws IOException {

        UpdateCustomerController.transferCustomer(CustomerTableView.getSelectionModel().getSelectedItem());

        if (CustomerTableView.getSelectionModel().getSelectedItem() != null)
        {
            try
            {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/pgc195pa/UpdateCustomerView.fxml"));
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Update Customer");
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
            alert.setContentText("Customer not selected.");
            alert.showAndWait();
        }
    }


    /**
     * Checks if a selected customer can be deleted
     * @param customer String variable for the selected customer
     * @return Boolean true if the customer is allowed to be deleted and false if otherwise*/
    private boolean appointmentCheck(Customer customer)
    {
        try
        {
            ObservableList appointments = QueryAppointment.getAppointmentByCustomerID(customer.getCustomer_ID());
            if(appointments != null && appointments.size() > 0)
            {
                String appoint = "";
                StringBuilder appointDis = new StringBuilder(appoint);
                for(int i = 0; i < appointments.size(); i++)
                {
                    Appointment appointment = (Appointment) appointments.get(i);
                    appointDis.append("ID: " + appointment.getAppointmentId() + "      on: " + appointment.getStartDate().toString() +
                            "     at: " + appointment.getStartTime().toString() + "\n");
                }
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a customer if they are selected and allowed to be deleted
     * @param actionEvent ActionEvent triggered when the Deleted Customer button is clicked*/
    public void DeleteCustomerHandler(ActionEvent actionEvent) {
        Customer customer = CustomerTableView.getSelectionModel().getSelectedItem();
        if(customer == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Select a customer to delete.");
            alert.showAndWait();
        }
        else if(CustomerTableView.getSelectionModel().getSelectedItem() != null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This action will delete this customer. Proceed?");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && (result.get() == ButtonType.OK))
            {
                try
                {
                    boolean isValid = appointmentCheck(customer);

                    if(!isValid)
                    {
                        boolean deleteSuccess = QueryCustomer.delete(CustomerTableView.getSelectionModel().getSelectedItem().getCustomer_ID());

                        if(deleteSuccess)
                        {
                            customers = QueryCustomer.getCustomers();
                            CustomerTableView.setItems(customers);
                            CustomerTableView.refresh();
                        }
                        else
                        {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setContentText("Unable to delete Customer.");
                            alert.showAndWait();
                        }

                    }
                    else
                    {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setContentText("Unable to delete customer that has existing appointments.");
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
     * Brings the user back to the Main Menu
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
     * Returns a list of customers depending on the String parameter
     * @param text String variable for the user's text that they want to search with
     * @return A list of Customers*/
    private static ObservableList<Customer> findCustomer(String text)
    {
        ObservableList<Customer> foundCustomers = FXCollections.observableArrayList();

        for(Customer customer: customers)
        {
            if(customer.getCustomer_Name().contains(text))
            {
                foundCustomers.add(customer);
            }
            else if(Integer.toString(customer.getCustomer_ID()).contains(text))
            {
                foundCustomers.add(customer);
            }
        }
        return foundCustomers;
    }


    /**
     * Initializes the Customer table
     * @param location Location for paths
     * @param resources Resources*/
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try
        {
            customers = QueryCustomer.getCustomers();
            CustomerTableView.setItems(customers);
            CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            NameColumn.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
            AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
            PostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
            PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            DivisionColumn.setCellValueFactory(new PropertyValueFactory<>("Division"));
            CountryColumn.setCellValueFactory(new PropertyValueFactory<>("Country"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Shows searched customers on the table based on the text the user entered
     * @param actionEvent ActionEvent triggered when the Search button is clicked*/
    public void SearchHandler(ActionEvent actionEvent) {
        ObservableList<Customer> search = findCustomer(SearchTextField.getText());
        CustomerTableView.setItems(search);
    }
}
