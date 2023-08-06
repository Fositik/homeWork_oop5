package familytree.model.presenter;

import familytree.service.PresenterService;
import familytree.view.View;

import java.time.LocalDate;

public class Presenter {
    private View view;
    private PresenterService service;

    public Presenter(View view) {
        this.view = view;
        service = new PresenterService();
    }

    public void addPersonFamily(String firstName, String lastName, LocalDate birthday, String fatherFirstName,
                                String fatherLastName, String motherFirstName, String motherLastName) {
        service.addPersonFamily(firstName, lastName, birthday, fatherFirstName, fatherLastName, motherFirstName, motherLastName);
    }

    public void getPersonList() {
        String info = service.getPersonListInfo();
        view.printAnswer(info);
    }
}
