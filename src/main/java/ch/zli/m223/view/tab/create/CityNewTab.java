package ch.zli.m223.view.tab.create;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.impl.CityDAOImpl;
import ch.zli.m223.rest.data.City;
import ch.zli.m223.view.node.NumberTextField;
import ch.zli.m223.view.tab.AbstractTab;
import ch.zli.m223.view.tab.overview.CityOverviewTab;
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
public class CityNewTab extends AbstractTab {

    private TextField inputName;
    private NumberTextField inputZip;

    public CityNewTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
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

        Label name = new Label("Name");
        form.getChildren().add(name);
        inputName = new TextField();
        form.getChildren().add(inputName);
        Label zip = new Label("Zip code");
        form.getChildren().add(zip);
        inputZip = new NumberTextField();
        form.getChildren().add(inputZip);

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
        String name = inputName.getText();
        int zip = inputZip.getNumber().intValue();
        City city = new City();
        city.setName(name);
        city.setZip(zip);
        city.setUser(Model.getInstance().getUser());
        new CityDAOImpl().create(city);
        getTabPane().getTabs().add(new CityOverviewTab("Citites"));
        getTabPane().getTabs().remove(this);
    }

}
