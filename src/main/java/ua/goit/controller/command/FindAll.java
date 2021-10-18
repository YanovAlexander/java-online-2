package ua.goit.controller.command;

import ua.goit.model.Publication;
import ua.goit.repository.Storage;
import ua.goit.view.View;

import static ua.goit.controller.command.Commands.FIND_ALL;

public class FindAll implements Command {
    private final View view;
    private final Storage storage;

    public FindAll(View view, Storage storage) {
        this.view = view;
        this.storage = storage;
    }

    @Override
    public boolean canProcess(String input) {
        return input.equals(FIND_ALL.getName());
    }

    @Override
    public void process() {
        Publication[] publications = storage.findAll();
        boolean isArrayEmpty = true;
        for (Publication publication : publications) {
            if (publication != null) {
                view.write(publication.toString());
                isArrayEmpty = false;
            }
        }
        if (isArrayEmpty) {
            view.write("Sorry, the library is empty.");
        }
    }
}
