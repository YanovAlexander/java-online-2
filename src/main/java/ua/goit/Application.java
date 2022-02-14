package ua.goit;

import ua.goit.controller.LibraryController;
import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
import ua.goit.model.converter.JournalDtoToJournalDaoConverter;
import ua.goit.repository.*;
import ua.goit.service.JournalService;
import ua.goit.view.Console;
import ua.goit.view.View;


public class Application {

    public static void main(String[] args) {
        PropertiesUtil util = new PropertiesUtil();

        DatabaseManager dbConnector = new PostgresHikariProvider(util.getHostname(), util.getPort(), util.getSchema(),
                util.getUser(), util.getPassword());

        JournalRepository repository = new JournalRepository(dbConnector);
        JournalDtoToJournalDaoConverter converter = new JournalDtoToJournalDaoConverter();
        JournalService service = new JournalService(repository, converter);

        View view = new Console();
        PublicationStorage storage = new InMemoryListStorage();
        AuthorStorage authorStorage = new InMemoryAuthorStorage();
        LibraryController controller = new LibraryController(view, storage, authorStorage, service);
        controller.run();
    }
}
