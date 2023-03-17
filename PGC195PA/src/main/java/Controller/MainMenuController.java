package Controller;

import com.example.pgc195pa.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The MainMenuController class controls the Main Menu, which is the main vein of navigation for the program.
 * */
public class MainMenuController {
    /**
     * The LogActivity is a Lambda Expression that assists with the text file name. 
     * Interacts with makeFile and logoutCheck */
    LogActivity logActivity = () ->
    {
        return "login_activity.txt";
    };

    /**
     * Brings the user to the Appointments screen
     * @param actionEvent ActionEvent navigates the user to Appointments screen when the Appointments button is clicked*/
    public void AppointmentsHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/pgc195pa/AppointmentView.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigates the user to the Customers screen
     * @param actionEvent ActionEvent brings up the Customer screen when the Customer button is clicked*/
    public void CustomersHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/pgc195pa/CustomerView.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Brings the user to the Reports screen
     * @param actionEvent ActionEvent brings up the Reports screen when the Reports button is clicked*/
    public void ReportsHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/pgc195pa/ReportsView.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Will log out the user from the program. It calls the makeFile and logoutCheck methods
     * to add the logout activity to the log file
     * @param actionEvent ActionEvent logs out the user from the application when the logout button is selected*/
    public void LogOutHandler(ActionEvent actionEvent) {
        makeFile();
        logoutCheck();

        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/pgc195pa/LoginView.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the value from Lambda expression to help create or append login_activity.txt file*/
    private void makeFile()
    {
        try
        {
            File newfile = new File(logActivity.fileName());
            if (newfile.createNewFile())
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
     * Retrieves value from Lambda Expression to help write the logout activity to the login_activity.txt file*/
    private void logoutCheck()
    {
        try
        {
            FileWriter fileWriter = new FileWriter(logActivity.fileName(), true);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            fileWriter.write("Logout successful: " + simpleDateFormat.format(date) + "\n");
            fileWriter.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
