package familytree.storage;

import familytree.familyTree.FamilyTree;
import familytree.model.Person;

public interface FileManager {
    boolean saveFamilyTree(FamilyTree<Person> familyTree, String filePath);

    FamilyTree<Person> loadFamilyTree(String filePath);
}
