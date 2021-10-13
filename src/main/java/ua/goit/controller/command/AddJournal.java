package ua.goit.controller.command;

import ua.goit.model.Journal;
import ua.goit.repository.Storage;
import ua.goit.view.View;

public class AddJournal implements Command {
    private final View view;
    private final Storage storage;
    private static final String COMMAND_NAME = "addJournal";

    public AddJournal(View view, Storage storage) {
        this.view = view;
        this.storage = storage;
    }

    @Override
    public boolean canProcess(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void process() {
        view.write("Enter journal name:");
        String journalName = view.read();
        System.out.println("Enter count of pages:");
        int countPages = 0;
        int number;
        int publicationYear;
        while (true) {
            try {
                countPages = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Please, enter correct number:");
            }
        }
        while (true) {
            try {
                number = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Please, enter correct number:");
            }
        }
        while (true) {
            try {
                publicationYear = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Please, enter correct number:");
            }
        }
        view.write("Enter author name:");
        Journal journal = new Journal(journalName, countPages, number, publicationYear);
        storage.add(journal);
        view.write("Journal created");
    }
}
