package ua.goit.controller.command;

import ua.goit.model.Book;
import ua.goit.repository.Storage;
import ua.goit.view.View;

import static ua.goit.controller.command.Commands.ADD_BOOK;

public class AddBook implements Command {
    private final View view;
    private final Storage storage;

    public AddBook(View view, Storage storage) {
        this.view = view;
        this.storage = storage;
    }

    @Override
    public boolean canProcess(String input) {
        return input.equals(ADD_BOOK.getName());
    }

    @Override
    public void process() {
        view.write("Enter book name:");
        String bookName = view.read();
        view.write("Enter count of pages:");
        int countPages = 0;
        while (true) {
            try {
                countPages = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Please, enter correct number:");
            }
        }
        view.write("Enter author name:");
        String authorName = view.read();
        Book book = new Book(bookName, countPages, authorName);
        storage.add(book);
        view.write("Book created");
    }
}
