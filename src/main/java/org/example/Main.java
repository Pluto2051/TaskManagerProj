package org.example;
import org.example.tasks.TaskManager;
import org.example.database.DatabaseManager;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    //private static int counter = 0; //Счётчик для проставления айди задачам
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, SQLException {
        DatabaseManager.connect();
        //DatabaseManager.addTask("test", "test description", Timestamp.valueOf("2025-11-10 15:00:00"), "In progress");
        while(true) { //Цикл программы
            System.out.println("Добавьте задачу командой add. Для получения справки используйте help");
            String command = sc.nextLine();
            switch (command) {
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask();
                    break;
                case "list":
                    DatabaseManager.showNames();
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
                case "help":
                    System.out.println("Доступные команды: add, list, remove, id, description, deadline, status, exit");
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
        DatabaseManager.addTask(sc.nextLine());
    }

    private static void removeTask() {
        System.out.println("Введите id задачи, которую нужно удалить:");
        DatabaseManager.showNames();
        int idRem = safeReadInt();
        taskIfExists(idRem);
        DatabaseManager.removeTask(idRem);
    }

    private static void setStatus() {
        System.out.println("Введите id задачи:");
        DatabaseManager.showNames();
        int idSta = safeReadInt();
        if(!taskIfExists(idSta)) {
            System.out.println("Задача не найдена или произошла ошибка при запросе!");
        } else {
            System.out.println("Введите статус для задачи (В работе, Готово, Отложено)");
            String status = sc.nextLine();
            DatabaseManager.setStatus(idSta, status);
        }
    }

    private static void getById() {
        System.out.println("Введите айди задачи:");
        DatabaseManager.showNames();
        int id = safeReadInt();
        if(!taskIfExists(id)) {
            System.out.println("Задача не найдена или произошла ошибка при запросе!");
        } else {
           System.out.println(DatabaseManager.getById(id));
        }
    }

    private static void setDescription() {
        System.out.println("Введите id задачи:");
        DatabaseManager.showNames();
        int idDes = safeReadInt();
        if (!taskIfExists(idDes)) {
            System.out.println("Задача не найдена или произошла ошибка при запросе!");
        } else {
            System.out.println("Введите описание для задачи");
            String description = sc.nextLine();
            DatabaseManager.setDescription(idDes, description);
        }
    }

    public static void setDeadline() {
        System.out.println("Введите id задачи:");
        DatabaseManager.showNames();
        int idDead = safeReadInt();
        if(!taskIfExists(idDead)) {
            System.out.println("Задача не найдена или произошла ошибка при запросе!");
        } else {
            System.out.println("Введите конечный срок для задачи в формате гггг-мм-дд");
            try {
                LocalDate deadline = LocalDate.parse(sc.nextLine());
                DatabaseManager.setDeadline(idDead, deadline);
            } catch (DateTimeParseException e) {
                System.out.println("Введите корректную дату в формате гггг-мм-дд!");
            }
        }
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

    private static boolean taskIfExists(int id) {
        if (DatabaseManager.getById(id).equals("Задача не найдена!")
                || DatabaseManager.getById(id).equals("Ошибка при запросе")) {
            return false;
        } else {
            return true;
        }
    }
}