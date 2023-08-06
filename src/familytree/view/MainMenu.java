package familytree.view;

import familytree.view.commands.*;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    private List<Command> commandList;

    public MainMenu(ConsoleUI consoleUI) {
        commandList = new ArrayList<>();
        commandList.add(new AddPerson(consoleUI));
        commandList.add(new LoadFile(consoleUI));
        commandList.add(new SaveFile(consoleUI));
        commandList.add(new GetPersonList(consoleUI));
        commandList.add(new SortByBirthDate(consoleUI));
        commandList.add(new SortByChildrenCount(consoleUI));
        commandList.add(new SortByFirstName(consoleUI));
        commandList.add(new GetChildren(consoleUI));
        commandList.add(new Finish(consoleUI));
    }

    public void showMenu() {
        System.out.println("=== Главное меню ===");
        for (int i = 0; i < commandList.size(); i++) {
            System.out.println((i + 1) + ". " + commandList.get(i).getDescription());
        }
    }

    public void executeCommand(int choice) {
        if (choice >= 1 && choice <= commandList.size()) {
            Command command = commandList.get(choice - 1);
            command.execute();
        } else {
            System.out.println("Неверный выбор. Введите корректное число.");
        }
    }

    public void showWelcomeMessage() {
        System.out.println("Добро пожаловать в приложение FamilyTree!");
    }
}
