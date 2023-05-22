package com.example.todoapp;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.time.LocalDate;

public class Tasks{
    private LocalDate date;
    private String taskTitle;
    private String taskText;
    public BooleanProperty isDone = new SimpleBooleanProperty(false);
    public Boolean isDoneBool = isDone.get();

    public boolean isIsDone() {
        return isDone.get();
    }

    public Tasks(LocalDate date, String taskTitle, String taskText) {
        this.date = date;
        this.taskTitle = taskTitle;
        this.taskText = taskText;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "(" + date + ") " + taskTitle + " : " + taskText;
    }
}