package org.example.database;
import java.sql.*;


public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "7qaz8wsx";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void addTask(String taskName) {
        //String sql = "INSERT INTO tasks (task_name, description, deadline, status) VALUES (?, ?, ?, ?)";
        String sql = "INSERT INTO tasks (task_name) VALUES (?)";

        try (Connection conn = connect();
        PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, taskName);
//            pstm.setString(2, description);
//            pstm.setTimestamp(3, deadline);
//            pstm.setString(4, status);
            pstm.executeUpdate();

            System.out.println("Задача добавлена в базу данных!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addDescription(String description, int id) {
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

    public static void addDeadline(Timestamp deadline) {
        String sql = "INSERT INTO tasks (deadline) VALUES (?)";

        try (Connection conn = connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setTimestamp(1, deadline);
            pstm.executeUpdate();

            System.out.println("Дэдлайн добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addStatus(String status) {
        String sql = "INSERT INTO tasks (status) VALUES (?)";

        try (Connection conn = connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, status);
            pstm.executeUpdate();

            System.out.println("Статус добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showNames() {
        String sql = "SELECT id, task_name " +
                "FROM tasks";

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
}