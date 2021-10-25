package ua.goit;

import ua.goit.controller.LibraryController;
import ua.goit.repository.InMemoryListStorage;
import ua.goit.repository.PublicationStorage;
import ua.goit.view.Console;
import ua.goit.view.View;

public class Application {

    public static void main(String[] args) {
        View view = new Console();
        PublicationStorage storage = new InMemoryListStorage();
        LibraryController controller = new LibraryController(view, storage);
        controller.run();
    }
}
