package familytree.service;

import familytree.model.familyTree.FamilyTree;
import familytree.model.person.Person;
import familytree.model.wriitable.FileHandler;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class PresenterService {
    private FamilyTree<Person> tree;

    public PresenterService() {
        tree = new FamilyTree<>();
    }

    public void addPersonFamily(String firstName, String lastName, LocalDate birthday, String fatherFirstName,
                                String fatherLastName, String motherFirstName, String motherLastName) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setBirthday(birthday);
        person.setFatherFirstName(fatherFirstName);
        person.setFatherLastName(fatherLastName);
        person.setMotherFirstName(motherFirstName);
        person.setMotherLastName(motherLastName);
        tree.addPerson(person);
    }

    public void sortByBirthDate() {
        tree.sortByBirthDate();
    }

    public void sortByChildrenCount() {
        tree.sortByChildrenCount();
    }

    public void sortByFirstName() {
        tree.sortByFirstName();
    }

    public void printChildren(String firstName, String lastName) {
        tree.getChildren(firstName, lastName);
    }

    public String getPersonListInfo() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Person person : tree) {
            stringBuilder.append(person);
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public void loadFile(String filePath) {
        FileHandler fileHandlerLoad = new FileHandler();
        FamilyTree<Person> loadedTree = (FamilyTree<Person>) fileHandlerLoad.read(filePath);
        if (loadedTree != null) {
            tree = loadedTree;
        }
    }


    public void saveFile(String filePath) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.save(tree, filePath);
    }
}
