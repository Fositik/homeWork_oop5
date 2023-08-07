package familytree.view.commands;


import familytree.view.ConsoleUI;

public class SortByChildrenCount implements Command {
    private final ConsoleUI consoleUI;

    public SortByChildrenCount(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }

    @Override
    public String getDescription() {
        return "Отсортировать список по колличеству детей";
    }

    @Override
    public void execute() {
        consoleUI.sortByChildrenCount();
    }

    @Override
    public String toString() {
        return "Command: " + getDescription();
    }

}