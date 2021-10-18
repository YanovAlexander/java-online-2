package ua.goit.controller.command;

import ua.goit.view.View;

import static ua.goit.controller.command.Commands.EXIT;

public class Exit implements Command {
    private final View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public boolean canProcess(String input) {
        return input.equals(EXIT.getName());
    }

    @Override
    public void process() {
        view.write("Good bye!");
        System.exit(0);
    }
}
