package familytree.model.person;


import familytree.model.familyTree.FamilyTree;
import familytree.model.familyTree.TreeNode;

import java.util.Comparator;

public class PersonComparatorByChildrenCount <E extends TreeNode>extends FamilyTree implements Comparator<E> {
    @Override
    public int compare(E p1, E p2) {
        int p1ChildrenCount = countChildren(p1);
        int p2ChildrenCount = countChildren(p2);

        return Integer.compare(p1ChildrenCount, p2ChildrenCount);
    }



}
