package Controller;

import Database.QueryCountry;
import Database.QueryCustomer;
import Database.QueryDivision;
import Model.Country;
import Model.Customer;
import Model.Division;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The UpdateCustomerController class is the controller to update Customer information.*/
public class UpdateCustomerController implements Initializable {

    private static Customer theCustomer;
    @FXML
    private Button SaveButton;
    @FXML
    private Button ReturnButton;
    @FXML
    private ComboBox<String> CountryComboBox;
    @FXML
    private ComboBox<String> DivisionComboBox;
    @FXML
    private TextField CustomerIDTextField;
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField AddressTextField;
    @FXML
    private TextField PostalCodeTextField;
    @FXML
    private TextField PhoneNumberTextField;

    /**
     * Fills the Division Combo Box with Divisions from a selected country
     * @param actionEvent ActionEvent triggered when a country is selected*/
    public void CountryHandler(ActionEvent actionEvent) {
        ObservableList<String> divList = FXCollections.observableArrayList();
        try
        {
            ObservableList<Division> divisions = QueryDivision.getDivisionByCountry(CountryComboBox.getSelectionModel().getSelectedItem());
            if(divisions != null)
            {
                for(Division division: divisions)
                {
                    divList.add(division.getDivision());
                }
            }
            DivisionComboBox.setItems(divList);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Checks that customer information is entered correctly and saves it
     * @param actionEvent ActionEvent triggered when the save button is clicked*/
    public void SaveHandler(ActionEvent actionEvent) {
        boolean isValid = emptyCheck(
                NameTextField.getText(),
                AddressTextField.getText(),
                PostalCodeTextField.getText(),
                PhoneNumberTextField.getText()
        );

        if(isValid)
        {
            try
            {
                boolean isComplete = QueryCustomer.updateInformation(
                        Integer.parseInt(CustomerIDTextField.getText()),
                        NameTextField.getText(),
                        AddressTextField.getText(),
                        PostalCodeTextField.getText(),
                        PhoneNumberTextField.getText(),
                        DivisionComboBox.getValue()
                );

                if(isComplete)
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Customer has been updated.");
                    Optional<ButtonType> result = alert.showAndWait();

                    if(result.isPresent() && (result.get() == ButtonType.OK))
                    {
                        try
                        {
                            Parent root = FXMLLoader.load(getClass().getResource("/com/example/pgc195pa/CustomerView.fxml"));
                            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setTitle("Customers");
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
                    Alert alert = new Alert(Alert.AlertType.ERROR, "New Customer could not be created.");
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
     * Fills the selected customer's information with the one from the referenced customer in the parameter
     * @param customer Referenced Customer*/
    public static void transferCustomer(Customer customer)
    {
        theCustomer = customer;
    }

    /**
     * Activates the return button. Changes the screen back to the Customer menu
     * @param actionEvent ActionEvent triggered when the return button is clicked*/
    public void ReturnHandler(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Return to the Customers Menu?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && (result.get() == ButtonType.OK))
        {
            try
            {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/pgc195pa/CustomerView.fxml"));
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Customers");
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
     * Checks that there are no empty fields on the form
     * @param Name String variable for customer name
     * @param Address String variable for customer address
     * @param PostalCode String variable for customer postal coded
     * @param PhoneNumber String variable for customer phone number
     * @return Boolean true if everything is entered correctly and false if otherwise*/
    private boolean emptyCheck(String Name, String Address, String PostalCode, String PhoneNumber)
    {
        if(Name.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("A name needs to be entered.");
            alert.showAndWait();
            return false;
        }
        if(Address.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("An address needs to be entered.");
            alert.showAndWait();
            return false;
        }
        if(PostalCode.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("A postal code needs to be entered.");
            alert.showAndWait();
            return false;
        }
        if(PhoneNumber.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("A phone number needs to be entered.");
            alert.showAndWait();
            return false;
        }
        if(DivisionComboBox.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Division needs to be selected.");
            alert.showAndWait();
            return false;
        }
        if(CountryComboBox.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Country needs to be selected.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    /**
     * Fills the division combo box with divisions*/
    private void setDivisionComboBox()
    {
        ObservableList<String> divList = FXCollections.observableArrayList();

        try
        {
            ObservableList<Division> divisions = QueryDivision.getDivision();
            if(divisions != null)
            {
                for (Division division: divisions)
                {
                    divList.add(division.getDivision());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        DivisionComboBox.setItems(divList);
    }

    /**
     * Fills the country combo box with countries*/
    private void setCountryComboBox()
    {
        ObservableList<String> countryList = FXCollections.observableArrayList();

        try
        {
            ObservableList<Country> countries = QueryCountry.getCountry();
            if(countries != null)
            {
                for (Country country: countries)
                {
                    countryList.add(country.getCountry());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        CountryComboBox.setItems(countryList);
    }

    /**
     * Fills all fields with the correct customer information when the screen is initialized
     * @param url Location for paths
     * @param resourceBundle Resources*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDivisionComboBox();
        setCountryComboBox();

        CustomerIDTextField.setText(Integer.toString(theCustomer.getCustomer_ID()));
        NameTextField.setText(theCustomer.getCustomer_Name());
        PostalCodeTextField.setText(theCustomer.getPostal_Code());
        AddressTextField.setText(theCustomer.getAddress());
        PhoneNumberTextField.setText(theCustomer.getPhone());
        CountryComboBox.getSelectionModel().select(theCustomer.getCountry());
        DivisionComboBox.getSelectionModel().select(theCustomer.getDivision());
    }
}
