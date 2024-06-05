import java.util.ArrayList;
import java.util.Scanner;

public class DigitalLibrary {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>(); // List to store books

        int choice;

        do {
            System.out.println("\nDigital Library System");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBook(books, scanner);
                    break;
                case 2:
                    viewBooks(books);
                    break;
                case 3:
                    issueBook(books, scanner);
                    break;
                case 4:
                    returnBook(books, scanner);
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        scanner.close();
    }

    public static void addBook(ArrayList<Book> books, Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        scanner.nextLine(); // Consume extra newline character

        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        books.add(new Book(title, author, true)); // Add book with initial availability
        System.out.println("Book added successfully!");
    }

    public static void viewBooks(ArrayList<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Available Books:");
            for (Book book : books) {
                if (book.isAvailable()) {
                    System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor());
                }
            }
        }
    }

    public static void issueBook(ArrayList<Book> books, Scanner scanner) {
        System.out.print("Enter book title to issue: ");
        String title = scanner.nextLine();
        scanner.nextLine(); // Consume extra newline character

        Book book = findBook(books, title);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("Book issued successfully!");
        } else if (book != null) {
            System.out.println("Book already issued!");
        } else {
            System.out.println("Book not found!");
        }
    }

    public static void returnBook(ArrayList<Book> books, Scanner scanner) {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();
        scanner.nextLine(); // Consume extra newline character

        Book book = findBook(books, title);
        if (book != null) {
            book.setAvailable(true);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }

    public static Book findBook(ArrayList<Book> books, String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }
}

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author, boolean available) {
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
