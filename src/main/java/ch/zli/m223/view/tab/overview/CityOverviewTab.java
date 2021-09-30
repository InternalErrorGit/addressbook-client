package ch.zli.m223.view.tab.overview;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.data.City;
import ch.zli.m223.rest.data.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class CityOverviewTab extends AbstractOverviewTab<City> {
    public CityOverviewTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
        super.initGUI();
        Model.getInstance().update();
        tableView.setItems(Model.getInstance().cities);
        TableColumn<City, String> rowName = new TableColumn<>("Name");
        rowName.setCellValueFactory(personStringCellDataFeatures -> new SimpleStringProperty(personStringCellDataFeatures.getValue().getName()));
        TableColumn<City, String> rowZip = new TableColumn<>("ZIP");
        rowZip.setCellValueFactory(personStringCellDataFeatures -> new SimpleStringProperty(String.valueOf(personStringCellDataFeatures.getValue().getZip())));
        tableView.getColumns().addAll(rowName, rowZip);
    }

    @Override
    void update(ActionEvent actionEvent) {

    }
}
