package ch.zli.m223.view.tab.update;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.data.Address;
import ch.zli.m223.rest.data.Person;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author P. Gatzka
 * @version 30.09.2021
 * Project: addressbookclient
 */
public class PersonEditTab extends AbstractEditTab<Person> {
    private TextField inputFirstname;
    private TextField inputLastname;
    private DatePicker inputBirthdate;
    private ComboBox<Address> addresses;

    public PersonEditTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
        super.initGUI();
        Model.getInstance().update();
        entries.setItems(Model.getInstance().people);
        inputFirstname = new TextField();
        inputLastname = new TextField();
        inputBirthdate = new DatePicker();
        inputFirstname.setMaxWidth(300);
        inputLastname.setMaxWidth(300);
        inputBirthdate.setMaxWidth(300);
        addresses = new ComboBox<>();
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
        vBox.getChildren().add(new Label("Firstname"));
        vBox.getChildren().add(inputFirstname);
        vBox.getChildren().add(new Label("Lastname"));
        vBox.getChildren().add(inputLastname);
        vBox.getChildren().add(new Label("Birthday"));
        vBox.getChildren().add(inputBirthdate);
        vBox.getChildren().add(new Label("Address"));
        vBox.getChildren().add(addresses);
        Button submit = new Button("Apply");
        submit.setOnAction(this::actionSubmit);
        Button cancel = new Button("Cancel");
        cancel.setOnAction(this::actionCancel);
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(submit, cancel);
        vBox.getChildren().add(hBox);
    }

    @Override
    protected void updateForm() {
        Person person = selectedEntry;
        inputFirstname.setText(person.getFirstname());
        inputLastname.setText(person.getLastname());
        inputBirthdate.setValue(person.getBirthdate().toLocalDate());
        addresses.getSelectionModel().select(person.getAddress());
    }

    @Override
    protected void actionSave() {
        Person person = selectedEntry;
        person.setFirstname(inputFirstname.getText());
        person.setLastname(inputLastname.getText());
        person.setBirthdate(Date.valueOf(inputBirthdate.getValue()));
        person.setAddress(addresses.getValue());
        person.setUpdateDate(LocalDateTime.now());
    }
}
