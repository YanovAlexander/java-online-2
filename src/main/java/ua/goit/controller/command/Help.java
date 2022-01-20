package ua.goit.controller.command;

import ua.goit.view.View;

import static ua.goit.controller.command.Commands.*;

public class Help implements Command {
    private final View view;

    public Help(View view) {
        this.view = view;
    }


    @Override
    public boolean canProcess(String input) {
        return input.equals(HELP.getName());
    }

    @Override
    public void process() {
        view.write("Enter " + HELP.getName() + " to see available commands.");
        view.write("Enter " + EXIT.getName() + " to exit.");
        view.write("Enter " + ADD_BOOK.getName()  + " to add book to library.");
        view.write("Enter " + ADD_JOURNAL.getName() + " to add journal to library.");
        view.write("Enter " + ADD_AUTHOR.getName() + " to add author to library.");
        view.write("Enter " + FIND_ALL.getName() + " to view all publications.");
    }
}
