module com.example.pgc195pa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pgc195pa to javafx.fxml;
    exports com.example.pgc195pa;
    exports Controller;
    opens Controller to javafx.fxml;
    exports Database;
    opens Database to javafx.fxml;
    exports Model; //added after
    opens Model to javafx.fxml; //added after

}