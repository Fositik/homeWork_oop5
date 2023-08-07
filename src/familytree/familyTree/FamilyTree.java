package familytree.familyTree;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FamilyTree<E extends TreeNode<E>> implements Serializable, Iterable<E> {

    private static final long serialVersionUID = 1L;
    private E root;

    public FamilyTree() {
        this.root = null;
    }
    public FamilyTree(E root) {
        this.root = root;
    }

    public E getRoot() {
        return root;
    }

    public void setRoot(E root) {
        this.root = root;
    }

    @Override
    public Iterator<E> iterator() {
        return new FamilyTreeIterator<>(root);
    }

    private static class FamilyTreeIterator<E extends TreeNode<E>> implements Iterator<E> {
        private final Stack<E> stack = new Stack<>();

        public FamilyTreeIterator(E startNode) {
            stack.push(startNode);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            E currentNode = stack.pop();
            List<E> children = currentNode.getChildren();
            for (E child : children) {
                stack.push(child);
            }
            return currentNode;
        }
    }
}
