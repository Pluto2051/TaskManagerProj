package org.example.database;
import java.sql.*;
import java.time.LocalDate;


public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void addTask(String taskName) {
        String sql = "INSERT INTO tasks (task_name) VALUES (?)";
        try (Connection conn = connect();
        PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, taskName);
            pstm.executeUpdate();
            System.out.println("Задача добавлена в базу данных!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeTask(int id) {
        String sql = "DELETE FROM tasks " +
                "WHERE id = ?";
        try (Connection conn = connect();
        PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
            System.out.println("Задача успешно удалена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void setDescription(int id, String description) {
        String sql = "UPDATE tasks " +
                "SET description = ? " +
                "WHERE id = ? ";
        try (Connection conn = connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, description);
            pstm.setInt(2, id);
            pstm.executeUpdate();
            System.out.println("Описание добавлено/обновлено!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setDeadline(int id, LocalDate deadline) {
        String sql = "UPDATE tasks " +
                "SET deadline = ? " +
                "WHERE id = ? ";
        try (Connection conn = connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setDate(1, Date.valueOf(deadline));
            pstm.setInt(2, id);
            pstm.executeUpdate();
            System.out.println("Дэдлайн добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setStatus(int id, String status) {
        String sql = "UPDATE tasks " +
                "SET status = ? " +
                "WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, status);
            pstm.setInt(2, id);
            pstm.executeUpdate();
            System.out.println("Статус добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showNames() {
        String sql = "SELECT id, task_name " +
                "FROM tasks " +
                "ORDER BY id ASC";
        try (Connection conn = connect();
        PreparedStatement pstm = conn.prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("task_name");
                System.out.println("id: " + id + " Task name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        try (Connection conn = connect();
        PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                String name = rs.getString("Task_name");
                String des = rs.getString("description");
                Date deadline = rs.getDate("deadline");
                String status = rs.getString("status");
                return ("Task name: " + name + "\n " +
                        "Description: " + des + "\n " +
                        "Deadline: " + deadline + "\n " +
                        "Status: " + status);
            } else {
                return "Задача не найдена!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Ошибка при запросе";
        }
    }
}