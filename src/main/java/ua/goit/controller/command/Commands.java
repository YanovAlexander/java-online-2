package ua.goit.controller.command;

public enum Commands {
    ADD_BOOK("addBook"),
    ADD_JOURNAL("addJournal"),
    EXIT("exit"),
    FIND_ALL("findAll"),
    HELP("help");

    private String name;

    Commands(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
