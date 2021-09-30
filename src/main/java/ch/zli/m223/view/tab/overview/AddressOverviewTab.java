package ch.zli.m223.view.tab.overview;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.data.Address;
import ch.zli.m223.rest.data.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class AddressOverviewTab extends AbstractOverviewTab<Address> {
    public AddressOverviewTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
        super.initGUI();
        Model.getInstance().update();
        tableView.setItems(Model.getInstance().addresses);

        TableColumn<Address, String> rowFirstname = new TableColumn<>("Street");
        rowFirstname.setCellValueFactory(personStringCellDataFeatures -> new SimpleStringProperty(personStringCellDataFeatures.getValue().getStreet()));
        TableColumn<Address, String> rowLastname = new TableColumn<>("House number");
        rowLastname.setCellValueFactory(personStringCellDataFeatures -> new SimpleStringProperty(personStringCellDataFeatures.getValue().getHouseNumber()));
        TableColumn<Address, String> rowAddress = new TableColumn<>("City");
        rowAddress.setCellValueFactory(personStringCellDataFeatures -> new SimpleStringProperty(personStringCellDataFeatures.getValue().getCity().toString()));
        tableView.getColumns().addAll(rowFirstname, rowLastname,  rowAddress);
    }

    @Override
    void update(ActionEvent actionEvent) {

    }
}
