package ua.goit.controller.command;

import ua.goit.model.Journal;
import ua.goit.repository.PublicationStorage;
import ua.goit.repository.Repository;
import ua.goit.view.View;

import static ua.goit.controller.command.Commands.ADD_JOURNAL;

public class AddJournal implements Command {
    private final View view;
    private final Repository<Journal> repository;

    public AddJournal(View view, Repository<Journal> repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public boolean canProcess(String input) {
        return input.equals(ADD_JOURNAL.getName());
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
        repository.save(journal);
        view.write("Journal created");
    }
}
