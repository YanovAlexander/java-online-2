package ua.goit.controller.command;

import ua.goit.view.View;

public class Help implements Command {
    private final View view;
    private final static String COMMAND_NAME = "help";

    public Help(View view) {
        this.view = view;
    }


    @Override
    public boolean canProcess(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void process() {
        view.write("Enter help to see available commands.");
        view.write("Enter exit to exit.");
        view.write("Enter addBook to add book to library");
        view.write("Enter addJournal to add journal to library");
        view.write("Enter findAll to view all publications");
    }
}
