module com.example.todoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.lang3;


    opens com.example.todoapp to javafx.fxml;
    exports com.example.todoapp;
}