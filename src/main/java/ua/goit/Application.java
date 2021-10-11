package ua.goit;

import ua.goit.model.Book;
import ua.goit.model.Journal;
import ua.goit.model.Publication;
import ua.goit.repository.Storage;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        menu();
    }


    public static void menu() {
        Storage library = new Storage();
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
                    addJournalToLibrary(library,scanner);
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
        System.out.println("enter Book name");
        String bookName = scanner.nextLine();

        int countPage;
        while (true) {
            try {
                System.out.println("enter pages count");
                countPage = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException ex) {
                System.out.println("You enter not pages count");
            }
        }
        System.out.println("enter author book");
        String authorName = scanner.nextLine();
        Book book = new Book(bookName, countPage, authorName);
        library.add(book);
        System.out.println("Book created");
    }

    private static void addJournalToLibrary(Storage library, Scanner scanner) {
        System.out.println("enter Journal name");
        String journalName = scanner.nextLine();
        int countPage;
        int number;
        int publicationYear;
        while (true) {
            try {
                System.out.println("enter pages count");
                countPage = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException ex) {
                System.out.println("You enter not pages count");
            }
        }
        while (true) {
            try {
                System.out.println("enter number journal");
                number = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException ex) {
                System.out.println("You enter not number journal");
            }
        }
        while (true) {
            try {
                System.out.println("Enter publication year");
                publicationYear = Integer.parseInt(scanner.nextLine());
                if (publicationYear < 1660){
                    System.out.println("you enter not correct year");
                }
                break;
            } catch (NumberFormatException ex) {
                System.out.println("You enter not correct year");
            }
        }
        Journal journal = new Journal(journalName, countPage, number, publicationYear);
        library.add(journal);
        System.out.println("Journal created");
    }
}
