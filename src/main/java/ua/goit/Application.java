package ua.goit;

import ua.goit.model.Book;
import ua.goit.model.Journal;
import ua.goit.repository.InMemoryStorage;
import ua.goit.repository.Storage;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        menu();
    }


    public static void menu() {
        Storage library = new InMemoryStorage();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Library. Please, enter help to see available commands:");
        String command = null;
        do {
            command = scanner.nextLine();
            switch (command) {
                case ("help"):
                    System.out.println("Enter help to see available commands.");
                    System.out.println("Enter exit to exit.");
                    System.out.println("Enter addBook to add book to library");
                    System.out.println("Enter addJournal to add journal to library");
                    break;
                case ("addBook"):
                    addBookToLibrary(library, scanner);
                    break;
                case ("addJournal"):
                    addJournalToLibrary(library, scanner);
                    break;
                case ("exit"):
                    System.out.println("Good bye!");
                    break;
                default:
                    System.out.println("Please, try again.");
                    break;
            }
        } while (!command.equals("exit"));
        scanner.close();
    }

    private static void addBookToLibrary(Storage library, Scanner scanner) {
        System.out.println("Enter book name:");
        String bookName = scanner.nextLine();
        System.out.println("Enter count of pages:");
        int countPages = 0;
        while (true) {
            try {
                countPages = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please, enter correct number:");
            }
        }
        System.out.println("Enter author name:");
        String authorName = scanner.nextLine();
        Book book = new Book(bookName, countPages, authorName);
        library.add(book);
        System.out.println("Book created");
    }

    private static void addJournalToLibrary(Storage library, Scanner scanner) {
        System.out.println("Enter journal name:");
        String journalName = scanner.nextLine();
        System.out.println("Enter count of pages:");
        int countPages = 0;
        int number;
        int publicationYear;
        while (true) {
            try {
                countPages = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please, enter correct number:");
            }
        }
        while (true) {
            try {
                number = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please, enter correct number:");
            }
        }
        while (true) {
            try {
                publicationYear = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please, enter correct number:");
            }
        }
        System.out.println("Enter author name:");
        String authorName = scanner.nextLine();
        Journal journal = new Journal(journalName, countPages, number, publicationYear);
        library.add(journal);
        System.out.println("Journal created");
    }
}
