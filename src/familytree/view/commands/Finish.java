package familytree.view.commands;


import familytree.view.ConsoleUI;

public class Finish implements Command {

    private final ConsoleUI consoleUI;

    public Finish(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }

    @Override
    public String getDescription() {
        return "Закончить работу";
    }

    @Override
    public void execute() {
        consoleUI.finish();
    }
}