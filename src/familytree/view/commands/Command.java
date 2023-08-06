package familytree.view.commands;

import familytree.view.ConsoleUI;

public interface Command {
    String getDescription();
    void execute();
}
