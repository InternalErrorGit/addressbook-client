package ch.zli.m223.view;

import ch.zli.m223.model.Model;
import ch.zli.m223.view.tab.create.NewAddressTab;
import ch.zli.m223.view.tab.create.NewCityTab;
import ch.zli.m223.view.tab.create.NewPersonTab;
import ch.zli.m223.view.tab.OverviewTab;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class MainView extends VBox {

    TabPane tabPane;

    public MainView() {
        setPrefSize(960, 540);
        {
            MenuBar menuBar = new MenuBar();
            {
                Menu application = new Menu("Application");
                MenuItem logout = new MenuItem("Logout");
                logout.setOnAction(this::actionLogout);
                application.getItems().add(logout);
                application.getItems().add(new SeparatorMenuItem());
                MenuItem quit = new MenuItem("Quit");
                logout.setOnAction(this::actionQuit);
                application.getItems().add(quit);
                menuBar.getMenus().add(application);
            }
            {
                Menu action = new Menu("Action");
                MenuItem overview = new MenuItem("Overview");
                overview.setOnAction(this::actionOverview);
                action.getItems().add(overview);
                action.getItems().add(new SeparatorMenuItem());
                {
                    Menu newEntry = new Menu("New");
                    MenuItem newPerson = new MenuItem("Person");
                    newPerson.setOnAction(this::actionNewPerson);
                    MenuItem newAddress = new MenuItem("Address");
                    newAddress.setOnAction(this::actionNewAddress);
                    MenuItem newCity = new MenuItem("City");
                    newCity.setOnAction(this::actionNewCity);
                    newEntry.getItems().addAll(newPerson, newAddress, newCity);
                    action.getItems().add(newEntry);
                }
                MenuItem import_ = new MenuItem("Import");
                import_.setOnAction(this::actionImport);
                action.getItems().add(import_);
                action.getItems().add(new SeparatorMenuItem());
                {
                    Menu edit = new Menu("Edit");
                    MenuItem editPerson = new MenuItem("Person");
                    editPerson.setOnAction(this::actionEditPerson);
                    MenuItem editAddress = new MenuItem("Address");
                    editAddress.setOnAction(this::actionEditAddress);
                    MenuItem editCity = new MenuItem("City");
                    editCity.setOnAction(this::actionEditCity);
                    edit.getItems().addAll(editPerson, editAddress, editCity);
                    action.getItems().add(edit);
                }
                MenuItem export = new MenuItem("Export");
                export.setOnAction(this::actionExport);
                action.getItems().add(export);
                MenuItem delete = new MenuItem("Delete");
                delete.setOnAction(this::actionDelete);
                action.getItems().add(delete);
                menuBar.getMenus().add(action);
            }
            {
                Menu help = new Menu("Help");
                MenuItem report = new MenuItem("Report bug");
                report.setOnAction(this::actionReportABug);
                MenuItem dumpLog = new MenuItem("Dump Log");
                dumpLog.setOnAction(this::actionDumpLog);
                help.getItems().addAll(report, dumpLog);
                menuBar.getMenus().add(help);
            }
            getChildren().add(menuBar);
        }
        {
            tabPane = new TabPane();
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
            tabPane.setPrefSize(960, 515);
            getChildren().add(tabPane);
        }
    }

    public void showTooltip(String message) {

    }

    private void actionDumpLog(ActionEvent actionEvent) {
        showTooltip("Not implemented yet");
        throw new NullPointerException();
    }

    private void actionReportABug(ActionEvent actionEvent) {
        throw new NullPointerException();
    }

    private void actionExport(ActionEvent actionEvent) {
        throw new NullPointerException();
    }

    private void actionDelete(ActionEvent actionEvent) {
        throw new NullPointerException();
    }

    private void actionEditCity(ActionEvent actionEvent) {
        throw new NullPointerException();
    }

    private void actionEditAddress(ActionEvent actionEvent) {
        throw new NullPointerException();
    }

    private void actionEditPerson(ActionEvent actionEvent) {
        throw new NullPointerException();
    }

    private void actionImport(ActionEvent actionEvent) {
        throw new NullPointerException();
    }

    private void actionNewAddress(ActionEvent actionEvent) {
        NewAddressTab tab = new NewAddressTab("New Address");
        tabPane.getTabs().add(tab);
    }

    private void actionNewCity(ActionEvent actionEvent) {
        NewCityTab tab = new NewCityTab("New City");
        tabPane.getTabs().add(tab);
    }

    private void actionNewPerson(ActionEvent actionEvent) {
        NewPersonTab tab = new NewPersonTab("New Person");
        tabPane.getTabs().add(tab);
    }

    private void actionOverview(ActionEvent actionEvent) {
        OverviewTab tab = new OverviewTab("Overview");
        tabPane.getTabs().add(tab);
    }

    private void actionQuit(ActionEvent actionEvent) {
        System.exit(0);
    }

    private void actionLogout(ActionEvent actionEvent) {
        Model.getInstance().logout();
        getScene().setRoot(new LoginView());
    }

}
