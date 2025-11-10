package org.example.tasks;

import java.util.Optional;

public class Task {
    private String name = "Имя не задано";
    private int id;
    private String description = "Описание не добавлено";
    private String status = "Статус не задан";
    private String deadline = "Срок не добавлен";

    public Task(String name) {
        this.name = name;
        System.out.println("Создана задача с названием: " + name);
    }

    public Task() {}

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Integer> setId(int id) {
        this.id = id;
        return null;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus () {
        return status;
    }

    public String getDeadline() {
        return deadline;
    }
}



