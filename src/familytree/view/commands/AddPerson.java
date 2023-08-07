package familytree.view.commands;

import familytree.view.ConsoleUI;

public class AddPerson implements Command {

    private final ConsoleUI consoleUI;

    public AddPerson(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }

    @Override
    public String getDescription() {
        if (consoleUI.hasRootPerson()) {
            return "Добавить человека";
        } else {
            return "Добавить корневого человека (предка)";
        }
    }

    @Override
    public void execute() {
        if (consoleUI.hasRootPerson()) {
            consoleUI.addChildToPerson();
        } else {
            consoleUI.addPersonToFamilyTree();
        }
    }

    @Override
    public String toString() {
        return "Command: " + getDescription();
    }

}