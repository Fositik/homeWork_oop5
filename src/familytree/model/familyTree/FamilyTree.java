package familytree.model.familyTree;


import familytree.model.person.PersonComparatorByBirthDate;
import familytree.model.person.PersonComparatorByChildrenCount;
import familytree.model.person.PersonComparatorByFistName;
import java.io.*;
import java.util.*;

public class FamilyTree <E extends TreeNode<E>> implements Serializable,Iterable<E> {
    private List<E> people;
    private Set<String> parentNames;


    public FamilyTree() {
        people = new ArrayList<>();
        parentNames = new HashSet<>();
    }



    public void addPerson(E person) {
        people.add(person);
        parentNames.add(person.getFatherFirstName() +" "+ person.getFatherLastName());
        parentNames.add(person.getMotherFirstName() +" "+ person.getMotherLastName());
    }




    public void sortByBirthDate() {
        people.sort(new PersonComparatorByBirthDate<>());
    }

    public void sortByChildrenCount(){
        people.sort(new PersonComparatorByChildrenCount<>());
    }

    public void sortByFirstName() {
        people.sort(new PersonComparatorByFistName<>());
    }

    public List<E> getChildren(String firstName, String lastName) {
        List<E> children = new ArrayList<>();
        for (E person : people) {
            if (person.getFatherFirstName().equalsIgnoreCase(firstName) && person.getFatherLastName().equalsIgnoreCase(lastName)) {
                children.add(person);
            } else if (person.getMotherFirstName().equalsIgnoreCase(firstName) && person.getMotherLastName().equalsIgnoreCase(lastName)) {
                children.add(person);
            }
        }
        return children;
    }


    @Override
    public Iterator<E> iterator() {
        return people.iterator();
    }

    @Override
    public String toString() {
        return  "Человек: " + people +
                ", Родители: " + parentNames +
                '}';
    }



    public int countChildren(E person) {
        int count = 0;

        for (E child : people) {
            if (child.getFatherFirstName().equals(person.getFirstName()) && child.getFatherLastName().equals(person.getLastName())) {
                count++;
            } else if (child.getMotherFirstName().equals(person.getFirstName()) && child.getMotherLastName().equals(person.getLastName())) {
                count++;
            }
        }
        return count;
    }


}
