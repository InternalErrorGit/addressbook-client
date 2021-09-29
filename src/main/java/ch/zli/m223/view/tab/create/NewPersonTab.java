package ch.zli.m223.view.tab.create;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.impl.PersonDAOImpl;
import ch.zli.m223.rest.data.Address;
import ch.zli.m223.rest.data.Person;
import ch.zli.m223.rest.data.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.sql.Date;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class NewPersonTab extends AbstractTab {


    private TextField inputLastName;
    private TextField inputFirstName;
    private ComboBox<Address> addresses;
    private DatePicker inputBirthdate;

    public NewPersonTab(String s) {
        super(s);
    }

    @Override
    void initGUI() {
        BorderPane content = new BorderPane();
        setContent(content);
        VBox form = new VBox();
        form.setPrefWidth(300);
        form.setMaxWidth(300);
        form.setMinWidth(300);
        form.setSpacing(5);

        Label title = new Label("Create a Person");
        title.setFont(new Font("System Bold", 22));
        form.getChildren().add(title);

        Label firstname = new Label("Firstname");
        form.getChildren().add(firstname);
        inputFirstName = new TextField();
        form.getChildren().add(inputFirstName);
        Label lastname = new Label("Lastname");
        form.getChildren().add(lastname);
        inputLastName = new TextField();
        form.getChildren().add(inputLastName);

        Label birthdate = new Label("Birthday");
        form.getChildren().add(birthdate);
        inputBirthdate = new DatePicker();
        form.getChildren().add(inputBirthdate);

        addresses = new ComboBox<>();
        Model.getInstance().update();
        addresses.setItems(Model.getInstance().addresses);
        addresses.setCellFactory(new Callback<ListView<Address>, ListCell<Address>>() {
            @Override
            public ListCell<Address> call(ListView<Address> addressListView) {
                return new ListCell<Address>() {
                    @Override
                    protected void updateItem(Address address, boolean b) {
                        super.updateItem(address, b);
                        if (address == null || b) {
                            setGraphic(null);
                        } else {
                            setText(address.toString());
                        }
                    }
                };
            }
        });

        form.getChildren().add(addresses);

        Button save = new Button("Submit");
        save.setOnAction(this::actionSave);
        Button cancel = new Button("Cancel");
        cancel.setOnAction(this::actionCancel);

        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.getChildren().addAll(save, cancel);
        form.getChildren().add(hBox);

        content.setPadding(new Insets(10, 10, 10, 10));
        content.setCenter(form);
    }

    private void actionCancel(ActionEvent actionEvent) {
        getTabPane().getTabs().remove(this);
    }

    private void actionSave(ActionEvent actionEvent) {
        String firstname = inputFirstName.getText();
        String lastname = inputLastName.getText();
        User user = Model.getInstance().getUser();
        Date birthdate = Date.valueOf(inputBirthdate.getValue());
        Address address = addresses.getValue();

        Person person = new Person();
        person.setFirstname(firstname);
        person.setLastname(lastname);
        person.setAddress(address);
        person.setUser(user);
        person.setBirthdate(birthdate);
        new PersonDAOImpl().create(person);

    }


}
