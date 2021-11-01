package ua.goit;

import ua.goit.controller.LibraryController;
import ua.goit.repository.AuthorStorage;
import ua.goit.repository.InMemoryAuthorStorage;
import ua.goit.repository.InMemoryListStorage;
import ua.goit.repository.PublicationStorage;
import ua.goit.view.Console;
import ua.goit.view.View;

public class Application {

    public static void main(String[] args) {
        View view = new Console();
        PublicationStorage storage = new InMemoryListStorage();
        AuthorStorage authorStorage = new InMemoryAuthorStorage();
        LibraryController controller = new LibraryController(view, storage, authorStorage);
        controller.run();
    }
}
