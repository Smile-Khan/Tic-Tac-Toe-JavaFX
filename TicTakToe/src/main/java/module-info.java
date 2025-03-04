module com.example.tictaktoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictaktoe to javafx.fxml;
    exports com.example.tictaktoe;
}