package ua.goit.controller;

import ua.goit.controller.command.*;
import ua.goit.exception.ExitException;
import ua.goit.service.AuthorService;
import ua.goit.service.JournalService;
import ua.goit.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LibraryController {
    private final View view;
    private final List<Command> commands;

    public LibraryController(View view, AuthorService authorService, JournalService service) {
        this.view = view;
        this.commands = new ArrayList<>(Arrays.asList(new Help(view),
                new Exit(view),
                new AddBook(view),
                new AddJournal(view, service),
                new AddAuthor(view, authorService)
        ));

    }


    public void run() {
        view.write("Welcome to Library");
        executeCommand();
    }

    private void executeCommand() {
        try {
            while (true) {
                view.write("Please, enter help to see available commands:");
                String input = view.read();
                boolean isIncorrectCommand = true;
                for (Command command : commands) {
                    if (command.canProcess(input)) {
                        command.process();
                        isIncorrectCommand = false;
                    }
                }
                if (isIncorrectCommand) {
                    view.write("Incorrect command. Please, try again.");
                }
            }
        } catch (ExitException e) {
         view.write("Good Bye!");
        }
    }
}
