package pl.dopierala.usersclient.GUI;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import pl.dopierala.usersclient.Client;
import pl.dopierala.usersclient.User;

@Route("addUser")
public class UserAddGUI extends VerticalLayout {

    private Label lblName = new Label("Name: ");
    private TextField tfName = new TextField();
    private Label lblLastName = new Label("Last name: ");
    private TextField tfLastName = new TextField();
    private Label lblAge = new Label("Age: ");
    private TextField tfAge = new TextField();
    private Label lblSex = new Label("Sex: ");
    private RadioButtonGroup<String> radSex = new RadioButtonGroup<>();

    private Button btnAdd = new Button("Add new user");
    private Button btnMenu = new Button("Back to MENU");

    public UserAddGUI() {
        prepareGuiObjects();
    }

    private void AddUserClick(ComponentEvent e) {
        User newUser = new User();
        newUser.setName(tfName.getValue());
        newUser.setLastName(tfLastName.getValue());
        newUser.setAge(Integer.parseInt(tfAge.getValue()));
        newUser.setSex(maleParse(radSex.getValue()));
        Client.addUserToAPI(newUser);
        UI.getCurrent().navigate("menu");
    }

    private boolean maleParse(String maleText){
        if(maleText.equalsIgnoreCase("MALE")){
            return true;
        }
        return false;
    }

    private void prepareGuiObjects() {
        HorizontalLayout h1 = new HorizontalLayout();
        h1.add(lblName, tfName);

        HorizontalLayout h2 = new HorizontalLayout();
        h2.add(lblLastName, tfLastName);

        HorizontalLayout h3 = new HorizontalLayout();
        h3.add(lblAge, tfAge);

        HorizontalLayout h4 = new HorizontalLayout();
        radSex.setItems("Male","Female");
        h4.add(lblSex, radSex);

        btnMenu.addClickListener(c -> UI.getCurrent().navigate("menu")
        );

        btnAdd.addClickListener(this::AddUserClick);

        add(h1, h2, h3, h4, btnAdd, btnMenu);
    }

}
