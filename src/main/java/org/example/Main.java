package org.example;
import org.example.tasks.Task;
import org.example.tasks.TaskManager;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int counter = 0; //Счётчик для проставления айди задачам

        while(true) { //Цикл программы
            System.out.println("Добавьте задачу. Доступные команды: add, list, remove, id, description, exit");
            String command = sc.nextLine();
            switch (command) {
                case "add":
                    System.out.println("Введите имя задачи:");
                    Task task = new Task(sc.nextLine());
                    task.setId(counter);
                    counter ++;
                    TaskManager.addTask(task);
                    break;
                case "remove":
                    System.out.println("Введите имя задачи, которую нужно удалить:");
                    TaskManager.listAll();
                    TaskManager.removeTask(sc.nextLine());
                    break;
                case "list":
                    TaskManager.listAll();
                    break;
                case "id":
                    System.out.println("Введите айди задачи:");
                    System.out.println(TaskManager.findById(Integer.parseInt(sc.nextLine())));
                    break;
                case "description":
                    System.out.println("Введите id задачи:");
                    TaskManager.listAll();
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println("Введите описание для задачи: " + TaskManager.findById(id));
                    String description = sc.nextLine();
                    TaskManager.setDescription(id, description);
                    System.out.println("Описание для задачи добавлено!");
                    break;
                case "exit"://Выход из программы, цикла
                    System.out.println("Закрытие...");
                    return;
                default://Случай несовпадения команды
                    System.out.println("Неизвестная команда");
            }
        }
    }
}