package familytree.view.commands;



import familytree.view.ConsoleUI;

public class SaveFile implements Command {
    private final ConsoleUI consoleUI;
    public SaveFile(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }
    @Override
    public String getDescription() {
        return "Сохранить данные в файла";
    }

    @Override
    public void execute() {
        consoleUI.saveFile();
    }
}