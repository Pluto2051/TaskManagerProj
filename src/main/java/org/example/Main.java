package org.example;
import org.example.tasks.Task;
import org.example.tasks.TaskManager;

import java.io.*;
import java.util.*;

public class Main {
    private static int counter = 0; //Счётчик для проставления айди задачам
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        while(true) { //Цикл программы
            System.out.println("Добавьте задачу. Доступные команды: add, list, remove, id, description, deadline, status, exit");
            String command = sc.nextLine();
            switch (command) {
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask();
                    break;
                case "list":
                    TaskManager.listAll();
                    break;
                case "id":
                    getById();
                    break;
                case "description":
                    setDescription();
                    break;
                case "status":
                    setStatus();
                    break;
                case "deadline":
                    setDeadline();
                    break;
                case "exit"://Выход из программы, цикла
                    System.out.println("Закрытие...");
                    return;
                default://Случай несовпадения команды
                    System.out.println("Неизвестная команда");
            }
        }
    }

    private static void addTask() {
        System.out.println("Введите имя задачи:");
        Task task = new Task(sc.nextLine());
        task.setId(counter);
        counter ++;
        TaskManager.addTask(task);
    }

    private static void removeTask() {
        System.out.println("Введите id задачи, которую нужно удалить:");
        TaskManager.listAllId();
        //цикл обработки исключения
        int idRem;
        while(true) {
                idRem = safeReadInt();
                if (TaskManager.findById(idRem).equals("Id not found")) {
                    System.out.println("Введите корректный id");
                } else {
                    break;
                }
        }
        TaskManager.removeTask(idRem);
    }

    private static void setStatus() {
        System.out.println("Введите id задачи:");
        TaskManager.listAllId();
        int idSta;
        while (true) {
            idSta = safeReadInt();
            if (TaskManager.findById(idSta).equals("Id not found")) {
                System.out.println("Введите корректный id");
            } else {
                break;
            }
        }
        System.out.println("Введите статус для задачи (В работе, Готово, Отложено): " + TaskManager.findById(idSta));
        String status = sc.nextLine();
        TaskManager.setStatus(idSta, status);
        System.out.println("Статус для задачи добавлен!");
    }

    private static void getById() {
        System.out.println("Введите айди задачи:");
        TaskManager.listAllId();
        int id;
        while(true) {
            id = safeReadInt();
            if(TaskManager.findById(id).equals("Id not found")) {
                System.out.println("Введите корректный id");
            } else {
                break;
            }
        }
        System.out.println(TaskManager.findById(id));
    }

    private static void setDescription() {
        System.out.println("Введите id задачи:");
        TaskManager.listAllId();
        int idDes;
        while(true) {
            idDes = safeReadInt();
            if(TaskManager.findById(idDes).equals("Id not found")) {
                System.out.println("Введите корректный id");
            } else {
                break;
            }
        }
        System.out.println("Введите описание для задачи: " + TaskManager.findById(idDes));
        String description = sc.nextLine();
        TaskManager.setDescription(idDes, description);
        System.out.println("Описание для задачи добавлено!");
    }

    public static void setDeadline() {
        System.out.println("Введите id задачи:");
        TaskManager.listAllId();
        int idDead;
        while(true) {
            idDead = safeReadInt();
            if(TaskManager.findById(idDead).equals("Id not found")) {
                System.out.println("Введите корректный id");
            } else {
                break;
            }
        }
        System.out.println("Введите конечную дату для задачи: " + TaskManager.findById(idDead));
        String deadline = sc.nextLine();
        TaskManager.setDeadline(idDead, deadline);
        System.out.println("Конечная дата для задачи установлена!");
    }

    private static int safeReadInt() {
        while(true) {
            try {
               return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException exc) {
                System.out.println("Введите корректный Id в целочисленном формате!");
            }
        }
    }
}