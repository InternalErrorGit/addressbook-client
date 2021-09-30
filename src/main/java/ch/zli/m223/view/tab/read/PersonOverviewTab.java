package ch.zli.m223.view.tab.read;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.data.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class PersonOverviewTab extends AbstractOverviewTab<Person> {

    public PersonOverviewTab() {
        super("People");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initGUI() {
        super.initGUI();
        Model.getInstance().update();
        tableView.setItems(Model.getInstance().people);
        TableColumn<Person, String> rowFirstname = new TableColumn<>("Firstname");
        rowFirstname.setCellValueFactory(personStringCellDataFeatures -> new SimpleStringProperty(personStringCellDataFeatures.getValue().getFirstname()));
        TableColumn<Person, String> rowLastname = new TableColumn<>("Lastname");
        rowLastname.setCellValueFactory(personStringCellDataFeatures -> new SimpleStringProperty(personStringCellDataFeatures.getValue().getLastname()));
        TableColumn<Person, String> rowBirthdate = new TableColumn<>("Birthday");
        rowBirthdate.setCellValueFactory(personStringCellDataFeatures -> new SimpleStringProperty(personStringCellDataFeatures.getValue().getBirthdate().toString()));
        TableColumn<Person, String> rowAddress = new TableColumn<>("Address");
        rowAddress.setCellValueFactory(personStringCellDataFeatures -> new SimpleStringProperty(personStringCellDataFeatures.getValue().getAddress().toString()));
        tableView.getColumns().addAll(rowFirstname, rowLastname, rowBirthdate, rowAddress);
    }

}
