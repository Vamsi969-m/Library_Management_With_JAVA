package services;

import dao.BookDao;
import dao.StudentDao;
import dao.BorrowingDao;
import models.Book;
import models.Student;

public class LibraryService {

    private final BookDao bookDAO;
    private final StudentDao studentDAO;
    private final BorrowingDao borrowingDAO;

    public LibraryService() {
        bookDAO = new BookDao();
        studentDAO = new StudentDao();
        borrowingDAO = new BorrowingDao();
    }

    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    public void viewBooks() {

        var books = bookDAO.getAllBooks();

        System.out.println("\n========== BOOKS ==========");

        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        for (Book book : books) {

            System.out.println(
                    "ID: " + book.getId()
                            + " | Title: " + book.getTitle()
                            + " | Author: " + book.getAuthor()
                            + " | ISBN: " + book.getIsbn()
                            + " | Category: " + book.getCategory()
                            + " | Status: " + book.getStatus()
            );
        }
    }

    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }

    public void viewStudents() {

        var students = studentDAO.getAllStudents();

        System.out.println("\n========== STUDENTS ==========");

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {

            System.out.println(
                    "ID: " + student.getId()
                            + " | Name: " + student.getName()
                            + " | Email: " + student.getEmail()
                            + " | Phone: " + student.getPhone()
            );
        }
    }

    public void issueBook(int studentId, int bookId) {
        borrowingDAO.issueBook(studentId, bookId);
    }

    public void returnBook(int borrowingId) {
        borrowingDAO.returnBook(borrowingId);
    }

    public void viewBorrowedBooks() {

        System.out.println("\n========== BORROWED BOOKS ==========");

        borrowingDAO.getBorrowedBooks();
    }
}