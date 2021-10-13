package ua.goit.controller;

import ua.goit.controller.command.*;
import ua.goit.repository.Storage;
import ua.goit.view.View;


public class LibraryController {
    private final View view;
    private final Command[] commands;

    public LibraryController(View view, Storage storage) {
        this.view = view;
        this.commands = new Command[]{new Help(view), new Exit(view), new AddBook(view, storage),
                new AddJournal(view, storage)};
    }


    public void run() {
        view.write("Welcome to Library");
        executeCommand();
    }

    private void executeCommand() {
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
    }
}
