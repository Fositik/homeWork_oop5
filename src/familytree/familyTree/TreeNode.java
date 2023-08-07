package familytree.familyTree;


import java.util.List;

public interface TreeNode<T> {
    List<T> getParents();

    List<T> getChildren();
}

