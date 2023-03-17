package Controller;

import Database.QueryAppointment;
import Database.QueryUser;
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
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

interface LogActivity{public String fileName();}

/**
 * The LoginController class controls all the functionality related to the Login Screen.
 * Contains a lambda expression*/
public class LoginController implements Initializable {

    @FXML
    Label ErrorMessageLabel, LocationLabel, LocationResult, TimeZoneLabel, TimeZoneResult, UsernameLabel, PasswordLabel, AppointmentLabel;
    @FXML
    TextField UsernameTextField, PasswordTextField;
    @FXML
    Button LogInButton, CancelButton;

    /**
     * The LogActivity is a Lambda Expression that helps return a report of the login activity.
     * Interacts with correctLogin, incorrectLogin, and startFile*/
    LogActivity logReport = () ->
    {
        return "login_activity.txt";
    };

    private ResourceBundle resourceBundle;

    /*
    public void MainMenuHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/pgc195pa/MainMenuView.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
     */

    /**
     * Allows the user to log in to the program
     * Uses helper methods to create a new log activity text file and checks if the login was a success
     * @param actionEvent ActionEvent Logs the user into the program when the login button is clicked*/
    public void LogInUser(ActionEvent actionEvent) {
        checkUsernameNotEmpty(UsernameTextField.getText());
        checkPasswordNotEmpty(PasswordTextField.getText());

        startFile();

        try
        {
            boolean validUsername = QueryUser.validateLogin(UsernameTextField.getText(), PasswordTextField.getText());
            if(validUsername)
            {
                correctLogin();
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

                    if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en"))
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(resourceBundle.getString("errorMessage"));
                        alert.setContentText(resourceBundle.getString("errorLoadingScreen"));
                        alert.showAndWait();
                    }
                }
            }
            else
            {
                incorrectLogin();

                if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en"))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(resourceBundle.getString("errorMessage"));
                    alert.setContentText(resourceBundle.getString("invalidUsernamePassword"));
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
     * Checks that the username field is not empty
     * @param username String variable for username*/
    public void checkUsernameNotEmpty(String username)
    {
        if(username.isEmpty())
        {
            if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en"))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(resourceBundle.getString("errorMessage"));
                alert.setContentText(resourceBundle.getString("usernameRequired"));
                alert.showAndWait();
            }
        }
    }

    /**
     * Checks that the password field is not empty
     * @param password String variable for password*/
    public void checkPasswordNotEmpty(String password)
    {
        if(password.isEmpty())
        {
            if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en"))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(resourceBundle.getString("errorMessage"));
                alert.setContentText(resourceBundle.getString("passwordRequired"));
                alert.showAndWait();
            }
        }
    }

    /**
     * Retrieves the file name value from the Lambda Expression and writes to login_activity.txt if the login is a success*/
    private void correctLogin()
    {
        appointmentAlert();

        try
        {
            FileWriter fileWriter = new FileWriter(logReport.fileName(), true);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            fileWriter.write("Successful Login " + UsernameTextField.getText() + " Password: " +
                    PasswordTextField.getText() + " Timestamp: " + simpleDateFormat.format(date) + "\n");
            fileWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the file name value from the Lambda Expression and writes to login_activity.txt if the login was a failure*/
    private void incorrectLogin()
    {
        try
        {
            FileWriter fileWriter = new FileWriter(logReport.fileName(), true);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date theDate = new Date(System.currentTimeMillis());
            fileWriter.write("Failed Login: " + UsernameTextField.getText() + "\nPassword: " + PasswordTextField.getText() +
                    "\nTimestamp: " + simpleDateFormat.format(theDate) + "\n");
            fileWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Alerts the user if there is an appointment in 15 minutes or no upcoming appointments*/
    private void appointmentAlert()
    {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTimeInFifteen = localDateTime.plusMinutes(15);

        ObservableList<Appointment> futureAppointments = FXCollections.observableArrayList();

        try
        {
            ObservableList<Appointment> appointments = QueryAppointment.getAppointment();

            if(appointments != null)
            {
                for(Appointment appointment : appointments)
                {
                    if(appointment.getStartTime().isAfter(localDateTime) &&
                    appointment.getStartTime().isBefore(localDateTimeInFifteen))
                    {
                        futureAppointments.add(appointment);

                        if(Locale.getDefault().getLanguage().equals("fr") ||
                        Locale.getDefault().getLanguage().equals("en"))
                        {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(resourceBundle.getString("alertAppointment"));
                            alert.setContentText(
                                    resourceBundle.getString("fifteenMin") +
                                    "\n" +
                                    resourceBundle.getString("appointmentID") +
                                    " " +
                                    appointment.getAppointmentId() +
                                    "\n" +
                                    resourceBundle.getString("date") +
                                    " " +
                                    appointment.getStartDate() +
                                    "\n" +
                                    resourceBundle.getString("time") +
                                    " " +
                                    appointment.getStartTime().toLocalTime());

                                    alert.setResizable(true);
                                    alert.showAndWait();
                        }
                    }
                }

                if(futureAppointments.size() < 1)
                {
                    if(Locale.getDefault().getLanguage().equals("fr") ||
                    Locale.getDefault().getLanguage().equals("en"))
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(resourceBundle.getString("alertAppointment"));
                        alert.setContentText(resourceBundle.getString("UpcomingAppointments"));
                        alert.setResizable(true);
                        alert.showAndWait();
                    }
                }
            }
            else
            {
                if(Locale.getDefault().getLanguage().equals("fr") ||
                Locale.getDefault().getLanguage().equals("en"))
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(resourceBundle.getString("alertAppointment"));
                    alert.setContentText(resourceBundle.getString("UpcomingAppointments"));
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
     * Controls the cancel button and will end the program if selected
     * @param actionEvent ActionEvent triggered when the cancel button is clicked*/
    public void CancelHandler(ActionEvent actionEvent) {
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("language", Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en"))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, resourceBundle1.getString("cancelError"));
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK)
            {
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                stage.close();
            }
        }
    }

    /**
     * Checks if a login file exists and creates a login_activity.txt file if it does not.
     * Retrieves the value from Lambda expression*/
    private void startFile()
    {
        try
        {
            File newfile = new File(logReport.fileName());
            if(newfile.createNewFile() == true)
            {
                System.out.println("New File: " + newfile.getName() + " has been created");
            }
            else
            {
                System.out.println("File Location: " + newfile.getPath() + " has already been created.");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the login controller
     * Will change text to french if the region is fr
     * @param location Location for paths
     * @param resources Resources*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = ResourceBundle.getBundle("language", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en"))
        {
            UsernameLabel.setText(resourceBundle.getString("username"));
            PasswordLabel.setText(resourceBundle.getString("password"));
            LocationLabel.setText(resourceBundle.getString("location"));
            LocationResult.setText(resourceBundle.getString("country"));
            TimeZoneLabel.setText(resourceBundle.getString("timezone"));
            TimeZoneResult.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
            LogInButton.setText(resourceBundle.getString("login"));
            CancelButton.setText(resourceBundle.getString("cancel"));
            AppointmentLabel.setText(resourceBundle.getString("title"));
        }

    }
}
