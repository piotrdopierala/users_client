package pl.dopierala.usersclient.GUI;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("menu")
public class MenuGUI extends VerticalLayout {
    private Button btnAdd = new Button("Add new user");
    private Button btnRemove = new Button("Remove user");


    public MenuGUI() {
        btnAdd.addClickListener(c -> UI.getCurrent().navigate("addUser"));
        btnRemove.addClickListener(c -> UI.getCurrent().navigate("removeUser"));
        add(btnAdd);
    }
}
