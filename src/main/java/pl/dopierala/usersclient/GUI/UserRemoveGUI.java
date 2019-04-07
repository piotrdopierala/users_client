package pl.dopierala.usersclient.GUI;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pl.dopierala.usersclient.Client;
import pl.dopierala.usersclient.User;

import java.util.ArrayList;
import java.util.List;

@Route("removeUser")
public class UserRemoveGUI extends VerticalLayout {
    List<User> users;
    private Button btnMenu = new Button("Back to MENU");
    private List<HorizontalLayout> userRows = new ArrayList<>();

    public UserRemoveGUI() {
        users = Client.getDataFromApi();

        for (User u : users) {
            HorizontalLayout hl = new HorizontalLayout();
            hl.add(new Label("ID:" + u.getId() + ", " + u.getName() + " " + u.getLastName()));
            Button btnDeleteUser = new Button("Remove");
            btnDeleteUser.addClickListener(b -> Client.deleteUser(u.getId()));
            hl.add(btnDeleteUser);
            userRows.add(hl);
        }
        prepareGuiObjects();
    }

    private void prepareGuiObjects() {
        btnMenu.addClickListener(
                c -> UI.getCurrent().navigate("menu")
        );

        userRows.forEach(this::add);
        add(btnMenu);
    }
}
