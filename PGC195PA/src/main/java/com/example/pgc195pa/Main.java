package com.example.pgc195pa;

import Database.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;

/**
 * The Main class creates the GUI scheduling program, opens the connection to the database, and runs the first screen.
 * */
public class Main extends Application {
    /**
     * The start method for the program. Starts the first screen (Login) of the program.
     * @param stage JavaFX Stage
     * @throws IllegalStateException is activated if it's called more than once
     * @throws RuntimeException is activated to catch runtime exceptions*/
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method for the program. It opens the database connection, launches the program, and closes the connection
     * once the program is closed.
     * @param args arguments*/
    public static void main(String[] args)
    {
        //Locale.setDefault(new Locale("fr"));
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();
    }
}