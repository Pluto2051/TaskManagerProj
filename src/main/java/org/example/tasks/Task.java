package org.example.tasks;

public class Task {
    private String name;
    private int id;
    private String description;
    private String status;
    private String deadline;

    public Task(String name) {
        this.name = name;
        System.out.println("Создана задача с названием: " + name);
    }

    public Task() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
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



