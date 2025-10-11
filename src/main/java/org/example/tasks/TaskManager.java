package org.example.tasks;
import java.io.IOException;
import java.util.*;

public class TaskManager {

    private static final HashMap<Integer, Task> tasks = new HashMap<>();

    public static void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public static void removeTask(String title) { // удаляет последнюю задачу в совпадении
        Integer idToRemove = null; // запоминает айди задачи, так как в цикле for-each нельзя удалять
        for (Task task : tasks.values()) {
            if(task.getName().equals(title)){
                idToRemove = task.getId(); // проверяем совпадение в хэшмапе и запоминаем айди
            }
        }
        if(idToRemove == null){
            System.out.println("Задача не найдена"); //если не нашли совпадение в хешмапе
        } else {
            System.out.println("Задача удалена " + title);
            tasks.remove(idToRemove);
        }
    }

    public static void removeTask(int id) { //удаление задачи по айди
        Integer idToRemove = null;
        for (Task task : tasks.values()) {
            if (task.getId() == id) {
                idToRemove = task.getId();
            }
        }
            if(idToRemove == null) {
                System.out.println("Задача не найдена");
            } else {
                System.out.println("Задача удалена " + findById(idToRemove));
                tasks.remove(idToRemove);
            }
    }

    public static String findById(int id) {
        try {
            return tasks.get(id).getName();// получает номер от пользователя и возвращает поле имени задачи
        } catch (NullPointerException exc) {
            return "Id not found";
        }
    }

    public static void listAll() { // выводит айди, имя, и описания всех задач в ХэшМапе
        for(Task task : tasks.values()) {
            System.out.println("===================" + "\n"
                    + "Статус: " + task.getStatus() + "\n"
                    + "Конечный срок: " + task.getDeadline() + "\n"
                    + "id " + task.getId() + " " + task.getName() + " Описание: " + task.getDescription() +
                    "\n" + "===================");
        }
    }

    public static void listAllId() {
        for(Task task : tasks.values()) {
            System.out.println("id " + task.getId() + " " + task.getName());
        }
    }

    public static void setDescription(int id, String description) {
        tasks.get(id).setDescription(description); //получает айди задачи и присваивает ей описание
    }

    public static void setDeadline(int id, String deadline) {
        tasks.get(id).setDeadline(deadline);
    }

    public static void setStatus(int id, String status) {
        tasks.get(id).setStatus(status);
    }

}
