package ua.goit;

import ua.goit.controller.LibraryController;
import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
import ua.goit.model.converter.AuthorConverter;
import ua.goit.model.converter.JournalDtoToJournalDaoConverter;
import ua.goit.repository.*;
import ua.goit.service.AuthorService;
import ua.goit.service.JournalService;
import ua.goit.view.Console;
import ua.goit.view.View;


public class Application {

    public static void main(String[] args) {
        PropertiesUtil util = new PropertiesUtil();

        DatabaseManager dbConnector = new PostgresHikariProvider(util.getHostname(), util.getPort(), util.getSchema(),
                util.getUser(), util.getPassword());

        JournalRepository journalRepository = new JournalRepository(dbConnector);
        AuthorRepository authorRepository = new AuthorRepository(dbConnector);

        JournalDtoToJournalDaoConverter journalConverter = new JournalDtoToJournalDaoConverter();
        AuthorConverter authorConverter = new AuthorConverter();

        JournalService journalService = new JournalService(journalRepository, journalConverter);
        AuthorService authorService = new AuthorService(authorRepository, authorConverter);

        View view = new Console();
        PublicationStorage storage = new InMemoryListStorage();
        LibraryController controller = new LibraryController(view, storage, authorService, journalService);
        controller.run();
    }
}
