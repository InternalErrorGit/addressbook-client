package ch.zli.m223.view.tab.create;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.impl.CityDAOImpl;
import ch.zli.m223.rest.data.City;
import ch.zli.m223.view.node.NumberTextField;
import ch.zli.m223.view.tab.AbstractTab;
import ch.zli.m223.view.tab.read.CityOverviewTab;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class CityNewTab extends AbstractNewTab {

    private TextField inputName;
    private NumberTextField inputZip;

    public CityNewTab() {
        super("Create City");
    }

    @Override
    public void initGUI() {
        super.initGUI();
        form.getChildren().add( new Label("Name"));
        inputName = new TextField();
        form.getChildren().add(inputName);
        form.getChildren().add(new Label("Zip code"));
        inputZip = new NumberTextField();
        form.getChildren().add(inputZip);
        addButtons();
    }

    @Override
    protected void actionSave(ActionEvent actionEvent) {
        String name = inputName.getText();
        int zip = inputZip.getNumber().intValue();
        City city = new City();
        city.setName(name);
        city.setZip(zip);
        city.setUser(Model.getInstance().getUser());
        new CityDAOImpl().create(city);
        getTabPane().getTabs().add(new CityOverviewTab());
        getTabPane().getTabs().remove(this);
    }

}
