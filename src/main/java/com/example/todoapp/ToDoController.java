package com.example.todoapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;



public class ToDoController implements Initializable{

    @FXML
    private ImageView logo;

    @FXML
    private Button addTaskButton;

    @FXML
    private Button doneTaskButton;

    @FXML
    private Button removeTasks;

    @FXML
    private ListView<Tasks> taskListView;

    public void changeSceneAddTask() throws IOException {
        FXMLLoader addTaskFxml = new FXMLLoader(ToDoApplication.class.getResource("add-task.fxml"));
        Scene scene = new Scene(addTaskFxml.load(), 700, 520);
        ToDoApplication.StaticStage.setScene(scene);
        try{
            Thread.sleep(300);
        }
        catch (InterruptedException e){
            System.out.println("Hata mesajı : " + e.getMessage());
        }
        ToDoApplication.StaticStage.show();
    }

    public void changeSceneDoneTask() throws IOException{
        FXMLLoader addTaskFxml = new FXMLLoader(ToDoApplication.class.getResource("done-task.fxml"));
        Scene scene = new Scene(addTaskFxml.load(), 700, 520);
        ToDoApplication.StaticStage.setScene(scene);
        try{
            Thread.sleep(300);
        }
        catch (InterruptedException e){
            System.out.println("Hata mesajı : " + e.getMessage());
        }
        ToDoApplication.StaticStage.show();
    }

    public void removeListTasks() throws IOException{
        int sayac = 0;
        for(int i  = 0 ; i < AddTaskController.tasks.size(); i++){
            Tasks isDoneControl = AddTaskController.tasks.get(i);
            isDoneControl.isDoneBool = isDoneControl.isIsDone();
            if(isDoneControl.isDoneBool == true) {
                isDoneControl.setDate(LocalDate.now());
                DoneTaskController.doneTasks.add(isDoneControl);
                FileOperations.addDoneTaskFile(isDoneControl);
                taskListView.getItems().remove(i);
                FileOperations.removeTaskFile(AddTaskController.tasks);
                i--;
                sayac++;
            }
        }
        if(sayac == 1){
            Alert removedTasks = new Alert(Alert.AlertType.INFORMATION);
            removedTasks.setTitle("Görev Kaldırıldı !");
            removedTasks.setHeaderText(null);
            removedTasks.setContentText("Görev başarıyla kaldırıldı. Dilerseniz yapılmış görevler listesinden tamamladığınız görevi görebilirsiniz.");
            removedTasks.showAndWait();
        }
        if(sayac > 1) {
            Alert removedTasks = new Alert(Alert.AlertType.INFORMATION);
            removedTasks.setTitle("Görevler Kaldırıldı !");
            removedTasks.setHeaderText(null);
            removedTasks.setContentText("Görevleriniz başarıyla kaldırıldı. Dilerseniz yapılmış görevler listesinden tamamladığınız görevleri görebilirsiniz.");
            removedTasks.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        taskListView.setCellFactory(CheckBoxListCell.forListView(Tasks ->Tasks.isDone));
        taskListView.setItems(AddTaskController.tasks);
    }
}