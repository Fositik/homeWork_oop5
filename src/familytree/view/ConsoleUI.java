package familytree.view;

import familytree.familyTree.FamilyTree;
import familytree.model.Gender;
import familytree.model.Person;
import familytree.service.PersonManager;
import familytree.service.PersonService;
import familytree.storage.FileManager;
import familytree.storage.FileStorage;
import familytree.storage.Writable;
import familytree.view.commands.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ConsoleUI implements View {
    private Map<Integer, Command> commands = new HashMap<>();
    private Scanner scanner;
    private boolean work;
    private FamilyTree<familytree.model.Person> currentFamilyTree;
    private PersonManager personManager;
    private FileManager fileManager;

    public ConsoleUI() {
        currentFamilyTree = new FamilyTree<>();
        this.personManager = new PersonService(currentFamilyTree);
        // Инициализация команд
        commands.put(1, new AddPerson(this));
        commands.put(2, new GetChildren(this));
        commands.put(3, new GetPersonList(this));
        commands.put(4, new SortByBirthDate(this));
        commands.put(5, new SortByFirstName(this));
        commands.put(6, new SortByChildrenCount(this));
        commands.put(7, new LoadFile(this));
        commands.put(8, new SaveFile(this));
        commands.put(9, new Finish(this));

        scanner = new Scanner(System.in);
        work = true;

    }

    @Override
    public void start() {
        showWelcomeMessage();
        while (work) {
            printMenu();
            System.out.println("Выберите действие:");

            try {
                int choice = scanner.nextInt();
                Command command = commands.get(choice);
                if (command != null) {
                    command.execute();
                } else {
                    System.out.println("Команда не найдена.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Неверный выбор. Введите корректное число.");
                scanner.nextLine();
            }
        }
    }

    public void addChildToPerson() {
        Person child = addPersonToFamilyTree();
        if (child == null) {
            return;
        }

        System.out.println("Введите данные отца (если информация отсутствует, нажмите 'Enter')");
        Person father = null;
        String userInput = scanner.nextLine();
        if (!userInput.trim().isEmpty()) {
            father = addPersonToFamilyTree();
        }

        System.out.println("Введите данные матери (если информация отсутствует, нажмите 'Enter')");
        Person mother = null;
        userInput = scanner.nextLine();
        if (!userInput.trim().isEmpty()) {
            mother = addPersonToFamilyTree();
        }

        // Валидация данных родителей
        if ((father != null && !personExistsInFamilyTree(father.getFirstName(), father.getLastName()))
                || (mother != null && !personExistsInFamilyTree(mother.getFirstName(), mother.getLastName()))) {
            System.out.println("Пожалуйста, проверьте корректность введенных данных родителей. Одного из родителей нет в древе.");
        }

        if (father != null) {
            father.addChild(child);
        }
        if (mother != null) {
            mother.addChild(child);
        }
    }

    public void getChildren() {
        String firstName = getInput("Введите имя:");
        String lastName = getInput("Введите фамилию:");
        printChildren(firstName, lastName);
    }


    public void sortByFirstName() {
        List<Person> sortedByNamePersonList = personManager.getPersonsSortedByName();
        System.out.println(sortedByNamePersonList.toString());
    }

    public void sortByChildrenCount() {
        List<Person> sortedByChildenCountPersonList = personManager.getPersonsSortedByChildrenCount();
        System.out.println(sortedByChildenCountPersonList.toString());
    }

    public void sortByBirthDate() {
        List<Person> sortedByAgePersonList = personManager.getPersonsSortedByAge();
        System.out.println(sortedByAgePersonList.toString());
    }

    public void getPersonList() {
        List<Person> personsList = personManager.getAllPersons();
        System.out.println(personsList.toString());
    }

    public Person addPersonToFamilyTree() {
        String firstName = getInput("Введите имя:");
        String lastName = getInput("Введите фамилию:");

        if (personExistsInFamilyTree(firstName, lastName)) {
            System.out.println("Такой человек уже существует в семейном древе!");
            return null;
        }

        System.out.println("Введите дату рождения в формате 2016-12-25:");
        LocalDate birthday = getValidDate();

        System.out.println("Введите дату смерти в формате 2016-12-25 (если человек жив, нажмите 'Enter'):");

        LocalDate dateOfDeath = null;
        String userInput = scanner.nextLine();

        if (!userInput.trim().isEmpty()) {
            dateOfDeath = getValidDate();
        }

        System.out.println("Введите пол ('м'/'ж'):");
        Gender gender = getValidGender();


        Person newPerson = new Person.Builder(firstName, lastName)
                .birthday(birthday)
                .gender(gender)
                .dayOfDeath(dateOfDeath)
                .build();

        if (currentFamilyTree == null) {
            currentFamilyTree = new FamilyTree<>(newPerson);
        }

        return newPerson;
    }


    public void loadFile() {
        System.out.println("Введите имя загружаемого файла:");
        String filePath = scanner.next();

        currentFamilyTree = fileManager.loadFamilyTree(filePath);
    }

    public void saveFile() {
        System.out.println("Введите имя сохраняемого файла:");
        String filePath = scanner.next();

        if (currentFamilyTree != null) {
            fileManager.saveFamilyTree(currentFamilyTree, filePath);
        } else {
            System.out.println("Семейное древо еще не загружено!");
        }
    }

    @Override
    public void printAnswer(String text) {
        System.out.println(text);
    }

    public boolean hasRootPerson() {
        return currentFamilyTree != null && currentFamilyTree.getRoot() != null;
    }

    public void finish() {
        System.out.println("Окончание работы");
        work = false;
    }

    public boolean personExistsInFamilyTree(String firstName, String lastName) {
        Optional<Person> person = searchPersonInFamilyTree(firstName, lastName);
        return person.isPresent();
    }

    public Optional<Person> searchPersonInFamilyTree(String firstName, String lastName) {
        for (Person person : currentFamilyTree) {
            if (person.getFirstName().equalsIgnoreCase(firstName)
                    && person.getLastName().equalsIgnoreCase(lastName)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    //Метод для избежания дублирования кода
    private String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.next();
    }

    private void printChildren(String firstName, String lastName) {
        Optional<Person> parent = personManager.findPersonByNameAndSurname(firstName, lastName);
        List<Person> children = personManager.getChildrenOfPerson(parent);
        System.out.println(children.toString());
    }

    private void printMenu() {
        System.out.println("Меню:");
        for (int key : commands.keySet()) {
            System.out.println(key + ". " + commands.get(key).getDescription());
        }
    }

    private void showWelcomeMessage() {
        System.out.println("Добро пожаловать в приложение Family Tree!");
    }

    private Gender getValidGender() {
        Gender gender = null;
        boolean validGender = false;

        while (!validGender) {
            String genderInput = getInput("Введите пол ('м'/'ж'):");

            switch (genderInput.toLowerCase()) {
                case "м":
                    gender = Gender.MALE;
                    validGender = true;
                    break;
                case "ж":
                    gender = Gender.FEMALE;
                    validGender = true;
                    break;
                default:
                    System.out.println("Некорректный ввод. Пожалуйста, введите 'м' для мужского или 'ж' для женского пола.");
                    break;
            }
        }

        return gender;
    }


    private LocalDate getValidDate() {
        LocalDate date = null;
        boolean validDate = false;

        while (!validDate) {
            String dateInput = scanner.next();
            try {
                date = LocalDate.parse(dateInput);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Некорректный формат даты. Введите дату в формате 2016-12-25.");
            }
        }

        return date;
    }

}
