package models;

import java.time.LocalDate;

public class Borrowing {

    private int id;
    private int studentId;
    private int bookId;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public Borrowing() {
    }

    public Borrowing(int studentId, int bookId,
                     LocalDate issueDate, LocalDate returnDate) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public Borrowing(int id, int studentId, int bookId,
                     LocalDate issueDate, LocalDate returnDate) {
        this.id = id;
        this.studentId = studentId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}