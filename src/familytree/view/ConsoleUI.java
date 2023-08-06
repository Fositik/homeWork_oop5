package familytree.view;

import familytree.model.person.Person;
import familytree.model.presenter.Presenter;
import familytree.service.PresenterService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI implements View {
    private Presenter presenter;
    private PresenterService presenterService;
    private Scanner scanner;
    private boolean work;
    private MainMenu menu;

    public ConsoleUI() {
        presenterService = new PresenterService();
        presenter = new Presenter(this);
        scanner = new Scanner(System.in);
        work = true;
        menu = new MainMenu(this);
    }

    @Override
    public void start() {
        menu.showWelcomeMessage(); // Добавить приветственное сообщение
        while (work) {
            printMenu();
            System.out.println("Выберите действие:");

            try {
                int choice = scanner.nextInt();
                menu.executeCommand(choice);
            } catch (InputMismatchException e) {
                System.out.println("Неверный выбор. Введите корректное число.");
                scanner.nextLine();
            }
        }
    }

    private void printMenu() {
        menu.showMenu();
    }

    //Метод для избежания дублирования кода
    private String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.next();
    }

    public void getChildren() {
        String firstName = getInput("Введите имя:");
        String lastName = getInput("Введите фамилию:");
        printChildren(firstName, lastName);
    }


    private void printChildren(String firstName, String lastName) {
        presenterService.printChildren(firstName, lastName);
    }

    public void sortByFirstName() {
        presenterService.sortByFirstName();
    }

    public void sortByChildrenCount() {
        presenterService.sortByChildrenCount();
    }

    public void sortByBirthDate() {
        presenterService.sortByBirthDate();
    }

    public void finish() {
        System.out.println("Окончание работы");
        work = false;
    }

    public void getPersonList() {
        presenter.getPersonList();
    }


    @Override
    public void printAnswer(String text) {
        System.out.println(text);
    }

    public void addPersonFamily() {
        Person person = new Person();
        String firstName = getInput("Введите имя:");
        String lastName = getInput("Введите фамилию:");

        System.out.println("Введите дату рождения в формате 2016-12-25:");

        LocalDate birthday = null;
        boolean validDate = false;

        while (!validDate) {
            String birthdayInput = scanner.next();
            try {
                birthday = LocalDate.parse(birthdayInput);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Некорректный формат даты. Введите дату в формате 2016-12-25.");
            }
        }

        System.out.println("Введите имя отца:");
        String fatherFirstName = scanner.next();
        System.out.println("Введите фамилию отца:");
        String fatherLastName = scanner.next();
        System.out.println("Введите имя матери:");
        String motherFirstName = scanner.next();
        System.out.println("Введите фамилию матери:");
        String motherLastName = scanner.next();
        presenter.addPersonFamily(firstName, lastName, birthday, fatherFirstName, fatherLastName,
                motherFirstName, motherLastName);

    }

    public void loadFile() {
        System.out.println("Введите имя загружаемого файла:");
        String filePath = scanner.next();

        presenterService.loadFile(filePath);
    }

    public void saveFile() {
        System.out.println("Введите имя сохраняемого файла:");
        String filePath = scanner.next();
        presenterService.saveFile(filePath);
    }

}
