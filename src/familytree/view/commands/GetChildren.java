package familytree.view.commands;

import familytree.view.ConsoleUI;

public class GetChildren implements Command {
    private final ConsoleUI consoleUI;

    public GetChildren(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }

    @Override
    public String getDescription() {
        return "Получить список детей";
    }

    @Override
    public void execute() {
        consoleUI.getChildren();
    }
}
