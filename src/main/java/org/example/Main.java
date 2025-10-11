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
            System.out.println("Добавьте задачу. Доступные команды: add, list, remove, id, description, deadline, status, exit");
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
                    System.out.println("Введите id задачи, которую нужно удалить:");
                    TaskManager.listAllId();
                    //цикл обработки исключения
                    int idRem;
                    while(true) {
                        try {
                            idRem = Integer.parseInt(sc.nextLine());
                            if (TaskManager.findById(idRem) == "Id not found") {
                                System.out.println("Введите корректный id");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException exc) {
                            System.out.println("Id должен быть в целочисленном формате!");
                        }
                    }
                    TaskManager.removeTask(idRem);
                    break;
                case "list":
                    TaskManager.listAll();
                    break;
                case "id":
                    System.out.println("Введите айди задачи:");
                    TaskManager.listAllId();
                    System.out.println(TaskManager.findById(Integer.parseInt(sc.nextLine())));
                    break;
                case "description":
                    System.out.println("Введите id задачи:");
                    TaskManager.listAllId();
                    int idDes;
                    //цикл обработки исключения
                    while(true) {
                        try {
                            idDes = Integer.parseInt(sc.nextLine());
                            if (TaskManager.findById(idDes) == "Id not found") {
                                System.out.println("Введите корректный id");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException exc) {
                            System.out.println("Id должен быть в целочисленном формате!");
                        }
                    }
                    System.out.println("Введите описание для задачи: " + TaskManager.findById(idDes));
                    String description = sc.nextLine();
                    TaskManager.setDescription(idDes, description);
                    System.out.println("Описание для задачи добавлено!");
                    break;
                case "status":
                    System.out.println("Введите id задачи:");
                    TaskManager.listAllId();
                    int idSta;
                    //цикл обработки исключения
                    while(true) {
                        try {
                            idSta = Integer.parseInt(sc.nextLine());
                            if (TaskManager.findById(idSta) == "Id not found") {
                                System.out.println("Введите корректный id");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException exc) {
                            System.out.println("Id должен быть в целочисленном формате!");
                        }
                    }
                    System.out.println("Введите статус для задачи (В работе, Готово, Отложено): " + TaskManager.findById(idSta));
                    String status = sc.nextLine();
                    TaskManager.setStatus(idSta, status);

                    System.out.println("Статус для задачи добавлен!");
                    break;
                case "deadline":
                    System.out.println("Введите id задачи:");
                    TaskManager.listAllId();
                    int idDead = Integer.parseInt(sc.nextLine());
                    System.out.println("Введите конечную дату для задачи: " + TaskManager.findById(idDead));
                    String deadline = sc.nextLine();
                    TaskManager.setDeadline(idDead, deadline);
                    System.out.println("Конечная дата для задачи установлена!");
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