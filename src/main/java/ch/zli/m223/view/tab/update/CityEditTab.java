package ch.zli.m223.view.tab.update;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.impl.CityDAOImpl;
import ch.zli.m223.rest.data.City;
import ch.zli.m223.view.node.NumberTextField;
import ch.zli.m223.view.tab.read.CityOverviewTab;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.math.BigDecimal;

/**
 * @author P. Gatzka
 * @version 30.09.2021
 * Project: addressbookclient
 */
public class CityEditTab extends AbstractEditTab<City> {

    private TextField inputName;
    private NumberTextField inputZip;

    public CityEditTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
        super.initGUI();
        Model.getInstance().update();
        entries.setItems(Model.getInstance().cities);


        inputName = new TextField();
        inputZip = new NumberTextField();

        inputName.setMaxWidth(300);
        inputZip.setMaxWidth(300);

        vBox.getChildren().add(new Label("Name"));
        vBox.getChildren().add(inputName);
        vBox.getChildren().add(new Label("Zip"));
        vBox.getChildren().add(inputZip);

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
        City city = selectedEntry;
        inputZip.setNumber(BigDecimal.valueOf(city.getZip()));
        inputName.setText(city.getName());
    }

    @Override
    protected void actionSave() {
        City city = selectedEntry;
        city.setName(inputName.getText());
        city.setZip(inputZip.getNumber().intValue());
        new CityDAOImpl().update(city);
        getTabPane().getTabs().add(new CityOverviewTab());
    }
}
