import models.Book;
import models.Student;
import services.LibraryService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LibraryService service = new LibraryService();

        while (true) {

            System.out.println("\n====================================");
            System.out.println("       LIBRARY MANAGEMENT SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add Student");
            System.out.println("4. View Students");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Borrowed Books");
            System.out.println("8. Exit");
            System.out.println("====================================");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1:

                    System.out.println("\n========== ADD BOOK ==========");

                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();

                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();

                    Book book = new Book(
                            title,
                            author,
                            isbn,
                            category,
                            "Available"
                    );

                    service.addBook(book);

                    break;

                case 2:

                    service.viewBooks();

                    break;

                case 3:

                    System.out.println("\n========== ADD STUDENT ==========");

                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();

                    Student student = new Student(
                            name,
                            email,
                            phone
                    );

                    service.addStudent(student);

                    break;

                case 4:

                    service.viewStudents();

                    break;

                case 5:

                    System.out.println("\n========== ISSUE BOOK ==========");

                    System.out.print("Enter student ID: ");
                    int studentId =
                            Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter book ID: ");
                    int bookId =
                            Integer.parseInt(scanner.nextLine());

                    service.issueBook(
                            studentId,
                            bookId
                    );

                    break;

                case 6:

                    System.out.println("\n========== RETURN BOOK ==========");

                    System.out.print("Enter borrowing ID: ");
                    int borrowingId =
                            Integer.parseInt(scanner.nextLine());

                    service.returnBook(borrowingId);

                    break;

                case 7:

                    service.viewBorrowedBooks();

                    break;

                case 8:

                    System.out.println(
                            "Thank you for using Library Management System!"
                    );

                    scanner.close();

                    return;

                default:

                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }
}