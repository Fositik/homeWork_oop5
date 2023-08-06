package familytree.model.person;

import familytree.model.familyTree.TreeNode;

import java.util.Comparator;

public class PersonComparatorByBirthDate<E extends TreeNode> implements Comparator<E> {
    @Override
    public int compare(E p1, E p2) {
        return Integer.compare(p1.getAge(), p2.getAge());
    }
}
