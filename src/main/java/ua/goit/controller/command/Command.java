package ua.goit.controller.command;

public interface Command {

    boolean canProcess(String input);

    void process();
}
