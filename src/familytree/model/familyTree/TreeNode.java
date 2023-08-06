package familytree.model.familyTree;


import java.time.LocalDate;

public interface TreeNode <T>{

    int getAge();
    void setFatherFirstName(String fatherFirstName);
    void setFatherLastName(String fatherLastName);
    void setMotherFirstName(String motherFirstName);
    void setMotherLastName(String motherLastName);
    void setBirthday(LocalDate dateBirthday);
    String getFatherFirstName();
    String getMotherLastName();
    String getMotherFirstName();
    String getFatherLastName();
    String getFirstName();
    void setFirstName(String firstName);
    String getLastName();
    void setLastName(String lastName);








}
