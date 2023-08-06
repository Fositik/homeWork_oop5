package familytree.view.commands;

import familytree.view.ConsoleUI;

public class AddPerson implements Command{

    private final ConsoleUI consoleUI;

    public AddPerson(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }
    @Override
    public String getDescription() {
        return "Добавить человека";
    }

    @Override
    public void execute() {
        consoleUI.addPersonFamily();
    }
}