package dao;

import config.DBConnections;
import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public void addStudent(Student student) {

        String sql = """
                INSERT INTO students
                (name, email, phone)
                VALUES (?, ?, ?)
                """;

        try (
                Connection connection = DBConnections.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPhone());

            statement.executeUpdate();

            System.out.println("Student added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (
                Connection connection = DBConnections.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Student student = new Student();

                student.setId(
                        resultSet.getInt("id")
                );

                student.setName(
                        resultSet.getString("name")
                );

                student.setEmail(
                        resultSet.getString("email")
                );

                student.setPhone(
                        resultSet.getString("phone")
                );

                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
}