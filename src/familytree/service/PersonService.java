package familytree.service;

import familytree.model.Person;
import familytree.comparator.AgeComparator;
import familytree.comparator.ChildrenCountComparator;
import familytree.comparator.NameComparator;
import familytree.familyTree.FamilyTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonService implements PersonManager {
    private final FamilyTree<Person> familyTree;

    public PersonService(FamilyTree<Person> familyTree) {
        this.familyTree = familyTree;
    }

    // Установить корневого человека (предка)
    @Override
    public void setRootPerson(Person rootPerson) {
        familyTree.setRoot(rootPerson);
    }

    // Добавить нового ребенка к определенному родителю
    @Override
    public void addChildToParent(Person child, Person parent) {
        parent.getChildren().add(child);
        child.getParents().add(parent);
    }

    // Удалить человека из дерева (но только если у него нет детей)
    @Override
    public boolean deletePerson(Person person) {
        if (!person.getChildren().isEmpty()) {
            return false; // Не удаляем, если у человека есть дети
        }
        person.getParents().forEach(parent -> parent.getChildren().remove(person));
        return true;
    }

    // Поиск по имени
    @Override
    public Optional<Person> findPersonByName(String firstName) {
        return getAllPersons().stream()
                .filter(person -> person.getFirstName().equals(firstName))
                .findFirst();
    }

    // Поиск по имени и фамилии
    @Override
    public Optional<Person> findPersonByNameAndSurname(String firstName, String lastName) {
        return getAllPersons().stream()
                .filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
                .findFirst();
    }

    // Поиск по идентификатору
    @Override
    public Optional<Person> findPersonById(int id) {
        return getAllPersons().stream()
                .filter(person -> person.getId() == id)
                .findFirst();
    }

    // Получение списка всех людей
    @Override
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        familyTree.iterator().forEachRemaining(persons::add);
        return persons;
    }

    // Метод для вывода списка всех детей человека
    @Override
    public List<Person> getChildrenOfPerson(Optional<Person> parent) {
        if (parent.isPresent()) {
            return parent.get().getChildren();
        } else {
            return Collections.emptyList();
        }
    }

    // Метод для вывода родителей человека
    @Override
    public List<Person> getParentsOfPerson(Person person) {
        return person.getParents();
    }

    // Сортировка по имени
    @Override
    public List<Person> getPersonsSortedByName() {
        return getAllPersons().stream()
                .sorted(new NameComparator<>())
                .collect(Collectors.toList());
    }

    // Сортировка по количеству детей
    @Override
    public List<Person> getPersonsSortedByChildrenCount() {
        return getAllPersons().stream()
                .sorted(new ChildrenCountComparator<>())
                .collect(Collectors.toList());
    }

    // Сортировка по возрасту
    @Override
    public List<Person> getPersonsSortedByAge() {
        return getAllPersons().stream()
                .sorted(new AgeComparator<>())
                .collect(Collectors.toList());
    }

}
