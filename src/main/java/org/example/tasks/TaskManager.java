package org.example.tasks;
import java.io.*;
import java.util.*;

public class TaskManager {

    private static final HashMap<Integer, Task> tasks = new HashMap<>();
    static int counter = 0;

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

    public static int increaseCounter() {
        int temp = counter;
        counter++;
        return temp;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        TaskManager.counter = counter;
    }

    public static void saveTask() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"))) {
            writer.write(String.valueOf(getCounter()));
            writer.newLine();
            for(Task task : tasks.values()) {
                writer.write(task.getId() + ";" + task.getName() + ";" + task.getStatus()
                        + ";" + task.getDescription() + ";" + task.getDeadline() + ";");
                writer.newLine();
            }
            //System.out.println("Задачи сохранены успешно!");
        } catch (IOException e) {
            System.out.println("Ошибка при создании или записи файла");
        }
    }

    public static void loadTask() {
        try (Scanner sc = new Scanner(new File ("tasks.txt"))) {
            String firstLine = sc.nextLine().replace("\uFEFF", "").trim(); //удаляет невидимый символ и пробелы
            setCounter(Integer.parseInt(firstLine));
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(";");
                Task task = new Task(parts[1]);
                task.setId(Integer.parseInt(parts[0]));
                task.setStatus(parts[2]);
                task.setDescription(parts[3]);
                task.setDeadline(parts[4]);
                tasks.put(task.getId(), task);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Добро пожаловать в Task Manager! Создайте свою первую задачу!");
        }
    }

}
