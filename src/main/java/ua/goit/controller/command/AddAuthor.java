package ua.goit.controller.command;

import ua.goit.model.dto.AuthorDto;
import ua.goit.service.AuthorService;
import ua.goit.view.View;

import static ua.goit.controller.command.Commands.ADD_AUTHOR;

public class AddAuthor implements Command{
    private final View view;
    private final AuthorService service;

    public AddAuthor(View view, AuthorService service) {
        this.view = view;
        this.service = service;

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
            if(!service.isAuthorExistByEmail(email)) {
                break;
            }
            view.write("Email already exists.");
        }

        AuthorDto author = new AuthorDto(firstName, lastName, email);
        service.save(author);
        view.write("Author added to library");
    }
}
