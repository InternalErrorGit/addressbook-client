package ch.zli.m223.view.tab.create;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.impl.PersonDAOImpl;
import ch.zli.m223.rest.data.Address;
import ch.zli.m223.rest.data.Person;
import ch.zli.m223.rest.data.User;
import ch.zli.m223.view.tab.read.PersonOverviewTab;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.sql.Date;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class PersonNewTab extends AbstractNewTab {


    private TextField inputLastName;
    private TextField inputFirstName;
    private ComboBox<Address> addresses;
    private DatePicker inputBirthdate;

    public PersonNewTab() {
        super("Create Person");
    }

    @Override
    public void initGUI() {
        super.initGUI();

        form.getChildren().add(new Label("Firstname"));
        inputFirstName = new TextField();
        form.getChildren().add(inputFirstName);
        form.getChildren().add(new Label("Lastname"));
        inputLastName = new TextField();
        form.getChildren().add(inputLastName);
        form.getChildren().add(new Label("Birthday"));
        inputBirthdate = new DatePicker();
        form.getChildren().add(inputBirthdate);
        form.getChildren().add(new Label("Address"));
        addresses = new ComboBox<>();
        form.getChildren().add(addresses);

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

        addButtons();
    }

    @Override
    protected void actionSave(ActionEvent actionEvent) {
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
        getTabPane().getTabs().add(new PersonOverviewTab());
        getTabPane().getTabs().remove(this);
    }


}
