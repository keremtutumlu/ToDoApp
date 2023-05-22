package com.example.todoapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class DoneTaskController implements Initializable {

    @FXML
    private ImageView logo;

    @FXML
    private Button toDoButton;

    @FXML
    private Button cleanListButton;

    @FXML
    private ListView<Tasks> doneTaskListView;

    static ObservableList<Tasks> doneTasks = FXCollections.observableArrayList();

    public void changeSceneToDoMain() throws IOException {
        FXMLLoader toDoMainFxml = new FXMLLoader(ToDoApplication.class.getResource("to-do-main.fxml"));
        Scene scene = new Scene(toDoMainFxml.load(), 700, 520);
        ToDoApplication.StaticStage.setScene(scene);
        try{
            Thread.sleep(300);
        }
        catch (InterruptedException e){
            System.out.println("Hata mesajı : " + e.getMessage());
        }
        ToDoApplication.StaticStage.show();
    }

    public void cleanList() throws IOException{
        doneTaskListView.getItems().removeAll(doneTasks);
        Files.delete(Paths.get("doneTasks.txt"));

        Alert cleanedList = new Alert(Alert.AlertType.INFORMATION);
        cleanedList.setTitle("Liste temizlendi !");
        cleanedList.setHeaderText(null);
        cleanedList.setContentText("Yapılmış görevler listesi başarıyla temizlendi...");
        cleanedList.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doneTaskListView.setItems(doneTasks);
    }
}
