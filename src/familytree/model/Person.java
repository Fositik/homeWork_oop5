package familytree.model;

import familytree.familyTree.TreeNode;
import familytree.comparator.Ageable;
import familytree.comparator.Childbearing;
import familytree.comparator.Nameable;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Person implements Serializable, TreeNode<Person>, Ageable, Nameable, Childbearing {
    private static final long serialVersionUID = 1L;
    int id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private LocalDate dayOfDeath;
    private Gender gender;
    private Person mother;
    private Person father;
    private List<Person> children = new ArrayList<>();

    private Person(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthday = builder.birthday;
        this.dayOfDeath = builder.dayOfDeath;
        this.gender = builder.gender;
        this.mother = builder.mother;
        this.father = builder.father;
        this.children = builder.children;
    }
    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private LocalDate birthday;
        private LocalDate dayOfDeath;
        private Gender gender;
        private Person mother;
        private Person father;
        private List<Person> children = new ArrayList<>();

        public Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Builder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder dayOfDeath(LocalDate dayOfDeath) {
            this.dayOfDeath = dayOfDeath;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder mother(Person mother) {
            this.mother = mother;
            return this;
        }

        public Builder father(Person father) {
            this.father = father;
            return this;
        }

        public Builder children(List<Person> children) {
            this.children = children;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    //Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate dateBirthday) {
        this.birthday = dateBirthday;
    }

    public LocalDate getDayOfDeath() {
        return dayOfDeath;
    }

    public void setDayOfDeath(LocalDate dayOfDeath) {
        this.dayOfDeath = dayOfDeath;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    @Override
    public List<Person> getParents() {
        List<Person> parents = new ArrayList<>();
        if (mother != null) {
            parents.add(mother);
        }
        if (father != null) {
            parents.add(father);
        }
        return parents;
    }

    public void addChild(Person child) {
        if (child != null) {
            children.add(child);

            switch (this.gender) {
                case MALE:
                    child.setFather(this);
                    break;
                case FEMALE:
                    child.setMother(this);
                    break;
                default:
                    throw new IllegalStateException("Unexpected gender value");
            }
        }
    }

    public List<Person> getChildren() {
        return children;
    }

    public int getAge() {
        if (dayOfDeath == null) {
            return getPeriod(birthday, LocalDate.now());
        } else {
            return getPeriod(birthday, dayOfDeath);
        }
    }

    private int getPeriod(LocalDate birthday, LocalDate dayOfDeath) {
        Period diff = Period.between(birthday, dayOfDeath);
        return diff.getYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName)
                && Objects.equals(birthday, person.birthday)
                && Objects.equals(dayOfDeath, person.dayOfDeath)
                && gender == person.gender
                && Objects.equals(mother, person.mother)
                && Objects.equals(father, person.father);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthday, dayOfDeath, gender, mother, father);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", dayOfDeath=" + (dayOfDeath == null ? "Alive" : dayOfDeath) +
                ", gender=" + gender +
                ", mother=" + (mother != null ? mother.getFirstName() : "Unknown") +
                ", father=" + (father != null ? father.getFirstName() : "Unknown") +
                ", children=" + (children.isEmpty() ? "None" : children.stream().map(Person::getFirstName)
                .collect(Collectors.joining(", "))) +
                '}';
    }

}