package dao;

import config.DBConnections;

import java.sql.*;
import java.time.LocalDate;

public class BorrowingDao {
    public void getBorrowedBooks() {

        String sql = """
            SELECT
                borrowings.id,
                students.name,
                books.title,
                borrowings.issue_date,
                borrowings.return_date
            FROM borrowings
            JOIN students
                ON borrowings.student_id = students.id
            JOIN books
                ON borrowings.book_id = books.id
            """;

        try (
                Connection connection =
                        DBConnections.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            System.out.println(
                    "\nID | Student | Book | Issue Date | Return Date"
            );

            System.out.println(
                    "----------------------------------------------------------"
            );

            while (resultSet.next()) {

                int id =
                        resultSet.getInt("id");

                String studentName =
                        resultSet.getString("name");

                String bookTitle =
                        resultSet.getString("title");

                Date issueDate =
                        resultSet.getDate("issue_date");

                Date returnDate =
                        resultSet.getDate("return_date");

                String returned =
                        returnDate == null
                                ? "Not Returned"
                                : returnDate.toString();

                System.out.println(
                        id + " | " +
                                studentName + " | " +
                                bookTitle + " | " +
                                issueDate + " | " +
                                returned
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void returnBook(int borrowingId) {

        String findBookSql = """
            SELECT book_id
            FROM borrowings
            WHERE id = ? AND return_date IS NULL
            """;

        String returnSql = """
            UPDATE borrowings
            SET return_date = ?
            WHERE id = ?
            """;

        String updateBookSql = """
            UPDATE books
            SET status = 'Available'
            WHERE id = ?
            """;

        Connection connection = null;

        try {

            connection = DBConnections.getConnection();

            connection.setAutoCommit(false);

            int bookId;

            try (PreparedStatement statement =
                         connection.prepareStatement(findBookSql)) {

                statement.setInt(1, borrowingId);

                ResultSet resultSet =
                        statement.executeQuery();

                if (!resultSet.next()) {
                    System.out.println(
                            "Borrowing record not found or book already returned!"
                    );
                    connection.rollback();
                    return;
                }

                bookId = resultSet.getInt("book_id");
            }

            try (PreparedStatement statement =
                         connection.prepareStatement(returnSql)) {

                statement.setDate(
                        1,
                        Date.valueOf(LocalDate.now())
                );

                statement.setInt(2, borrowingId);

                statement.executeUpdate();
            }

            try (PreparedStatement statement =
                         connection.prepareStatement(updateBookSql)) {

                statement.setInt(1, bookId);

                statement.executeUpdate();
            }

            connection.commit();

            System.out.println(
                    "Book returned successfully!"
            );

        } catch (SQLException e) {

            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void issueBook(int studentId, int bookId) {

        String checkBookSql =
                "SELECT status FROM books WHERE id = ?";

        String insertSql = """
                INSERT INTO borrowings
                (student_id, book_id, issue_date)
                VALUES (?, ?, ?)
                """;

        String updateBookSql =
                "UPDATE books SET status = 'Issued' WHERE id = ?";

        Connection connection = null;

        try {

            connection = DBConnections.getConnection();

            connection.setAutoCommit(false);

            try (PreparedStatement checkStatement =
                         connection.prepareStatement(checkBookSql)) {

                checkStatement.setInt(1, bookId);

                ResultSet resultSet =
                        checkStatement.executeQuery();

                if (!resultSet.next()) {
                    System.out.println("Book not found!");
                    connection.rollback();
                    return;
                }

                String status =
                        resultSet.getString("status");

                if (!status.equalsIgnoreCase("Available")) {
                    System.out.println(
                            "Book is not available!"
                    );
                    connection.rollback();
                    return;
                }
            }

            try (PreparedStatement insertStatement =
                         connection.prepareStatement(insertSql)) {

                insertStatement.setInt(1, studentId);
                insertStatement.setInt(2, bookId);
                insertStatement.setDate(
                        3,
                        Date.valueOf(LocalDate.now())
                );

                insertStatement.executeUpdate();
            }

            try (PreparedStatement updateStatement =
                         connection.prepareStatement(updateBookSql)) {

                updateStatement.setInt(1, bookId);

                updateStatement.executeUpdate();
            }

            connection.commit();

            System.out.println(
                    "Book issued successfully!"
            );

        } catch (SQLException e) {

            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}