package VirtualLibraryStack;
import java.util.*;
import java.util.Scanner;
import java.util.Stack;

public class LibrarySystem {
    private Stack<Book> borrowedBooks = new Stack<>();
    private Stack<Book> availableBooks = new Stack<>();
    private Stack<Book> addedBooks = new Stack<>();

    public void borrowBook(Book book) {
        availableBooks.push(book);
    }

    public void borrowBook() {
        if (!availableBooks.isEmpty()) {
            Book borrowed = availableBooks.pop();
            borrowedBooks.push(borrowed);
            System.out.println("Borrowed: " + borrowed);
        } else {
            System.out.println("No available books to borrow.");
        }
    }

    public void returnBook() {
        if (!borrowedBooks.isEmpty()) {
            Book returned = borrowedBooks.pop();
            availableBooks.push(returned);
            System.out.println("Returned: " + returned);
        } else {
            System.out.println("No books to return.");
        }
    }

    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No borrowed books.");
        } else {
            System.out.println("Borrowed Books:");
            displayBooks(borrowedBooks);
        }
    }

    public void displayAvailableBooks() {
        if (availableBooks.isEmpty()) {
            System.out.println("No available books.");
        } else {
            System.out.println("Available Books:");
            displayBooks(availableBooks);
        }
    }

    private void displayBooks(Stack<Book> books) {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-30s | %-30s | %-13s |%n", "No.", "Book Title", "Book Author", "Year Released");
        System.out.println("---------------------------------------------------------------------------------------");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.printf("| %-4d | %-30s | %-30s | %d              |%n", i + 1, book.getTitle(), book.getAuthor(), book.getYear());
        }
        System.out.println("---------------------------------------------------------------------------------------");
    }

    public void addBook(Scanner scanner) {
        System.out.println("Enter the title of the book: ");
        String bookTitle = scanner.nextLine();
        System.out.println("Enter the author of the book: ");
        String bookAuthor = scanner.nextLine();
        System.out.println("Enter the year of the book: ");
        int bookYear = scanner.nextInt();
        scanner.nextLine();

        Book newBook = new Book(bookTitle, bookAuthor, bookYear);
        availableBooks.push(newBook);
        addedBooks.push(newBook);
    }

    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();

        // Pre-defined books
        library.borrowBook(new Book("The Secret Garden", "Frances Hodgson Burnett", 1911));
        library.borrowBook(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        library.borrowBook(new Book("1984", "George Orwell", 1949));
        library.borrowBook(new Book("Pride and Prejudice", "Jane Austen", 1813));
        library.borrowBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925));

        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("~~~~~~~~~ My Library System ~~~~~~~~~");
                System.out.println("| Library Menu:                     |");
                System.out.println("| 1. Borrow a Book                  |");
                System.out.println("| 2. Return a Book                  |");
                System.out.println("| 3. Display Borrowed Books         |");
                System.out.println("| 4. Display Available Books        |");
                System.out.println("| 5. Add a Book                     |");
                System.out.println("| 6. Exit                           |");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        library.borrowBook();
                        break;
                    case 2:
                        library.returnBook();
                        break;
                    case 3:
                        library.displayBorrowedBooks();
                        break;
                    case 4:
                        library.displayAvailableBooks();
                        break;
                    case 5:
                        library.addBook(scanner);
                        break;
                    case 6:
                        System.out.println("Thank you for using :)");
                        System.out.println("Exiting the Library System. Goodbye!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            main(args);
        }
    }
}