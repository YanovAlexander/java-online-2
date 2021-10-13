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
        int countPages = 0;
        int number = 0;
        int publicationYear = 0;
        while (true) {
            try {
                view.write("Enter count of pages:");
                countPages = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Please, enter correct number:");
            }
        }
        while (true) {
            try {
                view.write("Enter journal number:");
                number = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Please, enter correct number:");
            }
        }
        while (true) {
            try {
                view.write("Enter journal publication year:");
                publicationYear = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Please, enter correct number:");
            }
        }
        Journal journal = new Journal(journalName, countPages, number, publicationYear);
        storage.add(journal);
        view.write("Journal created");
    }
}
