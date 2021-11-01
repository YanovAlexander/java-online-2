package ua.goit.controller.command;

import ua.goit.model.Author;
import ua.goit.repository.AuthorStorage;
import ua.goit.view.View;

import static ua.goit.controller.command.Commands.ADD_AUTHOR;

public class AddAuthor implements Command{
    private final View view;
    private final AuthorStorage storage;

    public AddAuthor(View view, AuthorStorage storage) {
        this.view = view;
        this.storage = storage;

    }

    @Override
    public boolean canProcess(String input) {
        return input.equals(ADD_AUTHOR.getName());
    }

    @Override
    public void process() {
        view.write("Enter author first name:");
        String firstName = view.read();
        view.write("Enter author last name:");
        String lastName = view.read();
        String email;
        while(true) {
            view.write("Enter author email:");
            email = view.read();
            if(storage.get(email) == null) {
                break;
            }
            view.write("Email already exists.");
        }

        Author author = new Author(firstName, lastName, email);
        storage.add(author);
    }
}
