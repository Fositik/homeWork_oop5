package familytree.view.commands;

import familytree.view.ConsoleUI;

public class GetPersonList implements Command {
    private final ConsoleUI consoleUI;

    public GetPersonList(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }

    @Override
    public String getDescription() {
        return "Получить список людей";
    }

    @Override
    public void execute() {
        consoleUI.getPersonList();
    }

    @Override
    public String toString() {
        return "Command: " + getDescription();
    }

}