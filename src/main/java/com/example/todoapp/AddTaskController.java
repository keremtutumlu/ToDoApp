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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddTaskController implements Initializable {

    @FXML
    private DatePicker taskDatePicker;

    @FXML
    private TextField taskTitle;

    @FXML
    private TextField taskText;

    @FXML
    private ImageView addTaskImage;

    @FXML
    private Button toDoButton;

    @FXML
    private Button doneButton;

    static ObservableList<Tasks> tasks = FXCollections.observableArrayList();
    public void addTask() throws IOException{
        if(taskDatePicker.getValue().isBefore(LocalDate.now())) {
            Alert alertWrongDate = new Alert(Alert.AlertType.WARNING);
            alertWrongDate.setTitle("Geçersiz Tarih Girişi !");
            alertWrongDate.setHeaderText(null);
            alertWrongDate.setContentText("Lütfen daha ileri bir tarih giriniz. Girdiğiniz tarih bugünden önce...");
            alertWrongDate.showAndWait();
        }
        else if(taskTitle.getText() == "") {
            Alert alertEmptyTitle = new Alert(Alert.AlertType.WARNING);
            alertEmptyTitle.setTitle("Başlık Boş !");
            alertEmptyTitle.setHeaderText(null);
            alertEmptyTitle.setContentText("Lütfen başlığı boş bırakmayınız !");
            alertEmptyTitle.showAndWait();
        }
        else if(taskText.getText() == "") {
            Alert alertEmptyText = new Alert(Alert.AlertType.WARNING);
            alertEmptyText.setTitle("Görev detayı boş !");
            alertEmptyText.setHeaderText(null);
            alertEmptyText.setContentText("Lütfen görev detayını boş bırakmayınız !");
            alertEmptyText.showAndWait();
        }
        else  {
            var newTask = new Tasks(taskDatePicker.getValue(), taskTitle.getText(), taskText.getText());
            tasks.add(newTask);
            FileOperations.addTaskFile(newTask);
            Alert taskAddedInfo = new Alert(Alert.AlertType.INFORMATION);
            taskAddedInfo.setTitle("Görev Alındı !");
            taskAddedInfo.setHeaderText(null);
            taskAddedInfo.setContentText("Göreviniz başarıyla eklenmiştir...");
            taskAddedInfo.showAndWait();
            taskTitle.setText(null);
            taskText.setText(null);
        }
    }

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

    public void changeSceneDoneTask() throws IOException {
        FXMLLoader doneTaskFxml = new FXMLLoader(ToDoApplication.class.getResource("done-task.fxml"));
        Scene scene = new Scene(doneTaskFxml.load(), 700, 520);
        ToDoApplication.StaticStage.setScene(scene);
        try{
            Thread.sleep(300);
        }
        catch (InterruptedException e){
            System.out.println("Hata mesajı : " + e.getMessage());
        }
        ToDoApplication.StaticStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskDatePicker.setValue(LocalDate.now());
    }
}
