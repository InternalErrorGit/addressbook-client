package ch.zli.m223.view.tab.update;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.impl.AddressDAOImpl;
import ch.zli.m223.rest.data.Address;
import ch.zli.m223.rest.data.City;
import ch.zli.m223.view.tab.read.AddressOverviewTab;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

/**
 * @author P. Gatzka
 * @version 30.09.2021
 * Project: addressbookclient
 */
public class AddressEditTab extends AbstractEditTab<Address> {

    private TextField inputStreet;
    private TextField inputHouseNumber;
    private ComboBox<City> inputCity;
    private ComboBox<City> cities;

    public AddressEditTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
        super.initGUI();
        Model.getInstance().update();
        entries.setItems(Model.getInstance().addresses);
        inputCity = new ComboBox<>();
        inputStreet = new TextField();
        inputHouseNumber = new TextField();
        inputHouseNumber.setMaxWidth(300);
        inputStreet.setMaxWidth(300);

        cities = new ComboBox<>();
        cities.setItems(Model.getInstance().cities);
        cities.setCellFactory(new Callback<ListView<City>, ListCell<City>>() {
            @Override
            public ListCell<City> call(ListView<City> cityListView) {
                return new ListCell<City>() {
                    @Override
                    protected void updateItem(City city, boolean b) {
                        super.updateItem(city, b);
                        if (city == null || b) {
                            setGraphic(null);
                        } else {
                            setText(city.toString());
                        }
                    }
                };
            }
        });

        vBox.getChildren().add(new Label("Street"));
        vBox.getChildren().add(inputStreet);
        vBox.getChildren().add(new Label("House number"));
        vBox.getChildren().add(inputHouseNumber);
        vBox.getChildren().add(new Label("City"));
        vBox.getChildren().add(inputCity);

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
        Address address = selectedEntry;
        inputHouseNumber.setText(address.getHouseNumber());
        inputStreet.setText(address.getStreet());
        inputCity.getSelectionModel().select(address.getCity());
    }

    @Override
    protected void actionSave() {
        Address address = selectedEntry;
        address.setStreet(inputStreet.getText());
        address.setHouseNumber(inputHouseNumber.getText());
        address.setCity(inputCity.getValue());
        new AddressDAOImpl().update(address);
        getTabPane().getTabs().add(new AddressOverviewTab());
    }
}
