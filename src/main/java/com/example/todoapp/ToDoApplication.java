package com.example.todoapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ToDoApplication extends Application {
    public static Stage StaticStage;
    @Override
    public void start(Stage stage) throws IOException {
        FileOperations.readTask(AddTaskController.tasks);
        FileOperations.readDoneTask(DoneTaskController.doneTasks);
        FXMLLoader toDoFxml = new FXMLLoader(ToDoApplication.class.getResource("to-do-main.fxml"));
        StaticStage = stage;
        Scene scene = new Scene(toDoFxml.load(), 700, 520);
        stage.setTitle("Görev Takip");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}