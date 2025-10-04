package org.example.tasks;
import java.util.*;

public class TaskManager {

    private static final HashMap<Integer, Task> tasks = new HashMap<>();

    public static void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public static void removeTask(String title) { // удаляет последнюю задачу в совпадении
        Integer idToRemove = null; // запоминает айди задачи, так как в цикле for-each нельзя удалять
        for(Task task : tasks.values()) {
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

    public static String findById(int id) {
        return tasks.get(id).getName(); // получает номер от пользователя и возвращает поле имени задачи
    }

    public static void listAll() { // выводит айди, имя, и описания всех задач в ХэшМапе
        for(Task task : tasks.values()) {
            System.out.println(task.getId() + " " + task.getName() + " " + task.getDescription());
        }
    }

    public static void setDescription(int id, String description) {
        tasks.get(id).setDescription(description); //получает айди задачи и присваивает ей описание
    }

}
