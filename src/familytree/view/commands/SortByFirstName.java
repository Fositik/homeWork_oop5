package familytree.view.commands;



import familytree.view.ConsoleUI;

public class SortByFirstName implements Command {
    private final ConsoleUI consoleUI;
    public SortByFirstName(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }
    @Override
    public String getDescription() {
        return "Отсортировать список по имени";
    }

    @Override
    public void execute() {
        consoleUI.sortByFirstName();    }
}