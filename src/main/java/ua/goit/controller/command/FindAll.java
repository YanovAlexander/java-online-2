package ua.goit.controller.command;

import ua.goit.model.Publication;
import ua.goit.repository.Storage;
import ua.goit.view.View;

public class FindAll implements Command {
    private final View view;
    private final Storage storage;
    private final static String COMMAND_NAME = "findAll";

    public FindAll(View view, Storage storage) {
        this.view = view;
        this.storage = storage;
    }

    @Override
    public boolean canProcess(String input) {
        return input.equals(COMMAND_NAME);
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
