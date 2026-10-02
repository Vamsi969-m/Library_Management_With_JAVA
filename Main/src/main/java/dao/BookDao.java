package dao;

import config.DBConnections;
import models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public void addBook(Book book) {

        String sql = """
                INSERT INTO books
                (title, author, isbn, category, status)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DBConnections.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setString(4, book.getCategory());
            statement.setString(5, book.getStatus());

            statement.executeUpdate();

            System.out.println("Book added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {

        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM books";

        try (
                Connection connection = DBConnections.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Book book = new Book();

                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setCategory(resultSet.getString("category"));
                book.setStatus(resultSet.getString("status"));

                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
}