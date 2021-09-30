package ch.zli.m223.view.tab.create;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.impl.AddressDAOImpl;
import ch.zli.m223.rest.data.Address;
import ch.zli.m223.rest.data.City;
import ch.zli.m223.rest.data.User;
import ch.zli.m223.view.tab.read.AddressOverviewTab;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Callback;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class AddressNewTab extends AbstractNewTab {

    private TextField inputHouseNumber;
    private TextField inputStreet;
    private ComboBox<City> cities;

    public AddressNewTab() {
        super("Create Address");
    }

    @Override
    public void initGUI() {
        super.initGUI();
        form.getChildren().add(new Label("Street"));
        inputStreet = new TextField();
        form.getChildren().add(inputStreet);
        form.getChildren().add(new Label("House number"));
        inputHouseNumber = new TextField();
        form.getChildren().add(inputHouseNumber);
        cities = new ComboBox<>();
        form.getChildren().add(new Label("City"));
        form.getChildren().add(cities);
        Model.getInstance().update();
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
        addButtons();
    }

    @Override
    protected void actionSave(ActionEvent actionEvent) {
        String street = inputStreet.getText();
        String houseNumber = inputHouseNumber.getText();
        City city = cities.getValue();
        User user = Model.getInstance().getUser();
        Address address = new Address();
        address.setCity(city);
        address.setUser(user);
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        new AddressDAOImpl().create(address);
        Model.getInstance().update();
        getTabPane().getTabs().add(new AddressOverviewTab());
        getTabPane().getTabs().remove(this);
    }
}
