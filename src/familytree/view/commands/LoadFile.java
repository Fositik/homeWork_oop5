package familytree.view.commands;



import familytree.view.ConsoleUI;

public class LoadFile implements Command {
    private final ConsoleUI consoleUI;
    public LoadFile(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }
    @Override
    public String getDescription() {
        return "Загрузить данные из файла";
    }

    @Override
    public void execute() {
        consoleUI.loadFile();
    }
}