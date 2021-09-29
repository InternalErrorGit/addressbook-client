package ch.zli.m223.view.tab.create;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.AddressDAO;
import ch.zli.m223.rest.dao.impl.AddressDAOImpl;
import ch.zli.m223.rest.data.Address;
import ch.zli.m223.rest.data.City;
import ch.zli.m223.rest.data.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class NewAddressTab extends AbstractTab {

    private TextField inputHouseNumber;
    private TextField inputStreet;
    private ComboBox<City> cities;

    public NewAddressTab(String s) {
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

        Label title = new Label("Create a City");
        title.setFont(new Font("System Bold", 22));
        form.getChildren().add(title);

        Label street = new Label("Street");
        form.getChildren().add(street);
        inputStreet = new TextField();
        form.getChildren().add(inputStreet);
        Label houseNumber = new Label("House number");
        form.getChildren().add(houseNumber);
        inputHouseNumber = new TextField();
        form.getChildren().add(inputHouseNumber);

         cities = new ComboBox<>();
        Model.getInstance().update();
        cities.setItems(Model.getInstance().cities);
        cities.setCellFactory(new Callback<ListView<City>, ListCell<City>>() {
            @Override
            public ListCell<City> call(ListView<City> cityListView) {
                return new ListCell<City>(){
                    @Override
                    protected void updateItem(City city, boolean b) {
                        super.updateItem(city, b);
                        if (city == null || b){
                            setGraphic(null);
                        }else{
                            setText(city.toString());
                        }
                    }
                };
            }
        });

        form.getChildren().add(cities);

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
        String street = inputStreet.getText();
        String houseNumber = inputHouseNumber.getText();
        City city = cities.getValue();
        User user = Model.getInstance().getUser();

        Address address = new Address();
        address.setCity(city);
        address.setUser(user);
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        System.out.println(address);
        new AddressDAOImpl().create(address);
        Model.getInstance().update();
    }
}
