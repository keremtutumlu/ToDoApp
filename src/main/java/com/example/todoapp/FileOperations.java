package com.example.todoapp;

import javafx.collections.ObservableList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class FileOperations {

    public static void readTask(ObservableList<Tasks> tasks){
        try{
            File f = new File("tasks.txt");
            if(!f.exists()) {
                f.createNewFile();
            }
            FileReader fReader = new FileReader(f);
            BufferedReader bReader = new BufferedReader(fReader);
            String taskTextLine = bReader.readLine();
            while (taskTextLine != null) {
                String date = taskTextLine.substring(1, 11);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(date, formatter);
                String taskTitle = taskTextLine.split(" :")[0].substring(13);
                String taskText = taskTextLine.split(" :")[1].substring(1);
                var newTask = new Tasks(localDate, taskTitle, taskText);
                tasks.add(newTask);
                taskTextLine = bReader.readLine();
            }
            bReader.close();
        }
        catch (IOException e){
            System.out.println("Hata mesajı : " + e.getMessage());
        }
    }

    public static void readDoneTask(ObservableList<Tasks> doneTasks) {
        try{
            File f = new File("doneTasks.txt");
            if(!f.exists()) {
                f.createNewFile();
            }
            FileReader fReader = new FileReader(f);
            BufferedReader bReader = new BufferedReader(fReader);
            String taskTextLine = bReader.readLine();
            while (taskTextLine != null) {
                String date = taskTextLine.substring(1, 11);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(date, formatter);
                String taskTitle = taskTextLine.split(" :")[0].substring(13);
                String taskText = taskTextLine.split(" :")[1].substring(1);
                var newTask = new Tasks(localDate, taskTitle, taskText);
                doneTasks.add(newTask);
                taskTextLine = bReader.readLine();
            }
            bReader.close();
        }
        catch (IOException e){
            System.out.println("Hata mesajı : " + e.getMessage());
        }
    }

    public static void addTaskFile(Tasks newTask) throws IOException {
        File f = new File("tasks.txt");
        if(!f.exists()){
            f.createNewFile();
        }
        String taskText = newTask.toString();
        FileWriter fWriter = new FileWriter(f, true);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
        bWriter.write(taskText);
        bWriter.newLine();
        bWriter.close();
    }

    public static void addDoneTaskFile(Tasks doneTask) throws IOException {
        File f = new File("doneTasks.txt");
        if(!f.exists()){
            f.createNewFile();
        }
        String taskText = doneTask.toString();
        FileWriter fWriter = new FileWriter(f, true);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
        bWriter.write(taskText);
        bWriter.newLine();
        bWriter.close();
    }

    public static void removeTaskFile(ObservableList<Tasks> tasks){
        try {
            Files.delete(Paths.get("tasks.txt"));

            File f = new File("tasks.txt");
            f.createNewFile();

            FileWriter fWriter = new FileWriter(f, true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            for(int i = 0; i < tasks.size(); i++) {
                bWriter.write(tasks.get(i).toString());
                bWriter.newLine();
            }
            bWriter.close();
        }
        catch (IOException e){
            System.out.println("Hata mesajı : " + e.getMessage());
        }
    }

}
