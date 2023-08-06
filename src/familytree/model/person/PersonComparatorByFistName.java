package familytree.model.person;
import familytree.model.familyTree.TreeNode;

import java.util.Comparator;

public class PersonComparatorByFistName <E extends TreeNode> implements Comparator<E>{
    @Override
    public int compare(E p1, E p2) {
        return p1.getFirstName().compareTo(p2.getFirstName());
    }
}
