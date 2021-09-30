package ch.zli.m223.view;

import ch.zli.m223.model.Model;
import ch.zli.m223.view.tab.create.AddressNewTab;
import ch.zli.m223.view.tab.create.CityNewTab;
import ch.zli.m223.view.tab.create.PersonNewTab;
import ch.zli.m223.view.tab.delete.AddressDeleteTab;
import ch.zli.m223.view.tab.delete.CityDeleteTab;
import ch.zli.m223.view.tab.delete.PersonDeleteTab;
import ch.zli.m223.view.tab.edit.AddressEditTab;
import ch.zli.m223.view.tab.edit.CityEditTab;
import ch.zli.m223.view.tab.edit.PersonEditTab;
import ch.zli.m223.view.tab.overview.AddressOverviewTab;
import ch.zli.m223.view.tab.overview.CityOverviewTab;
import ch.zli.m223.view.tab.overview.OverviewTab;
import ch.zli.m223.view.tab.overview.PersonOverviewTab;
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
                quit.setOnAction(this::actionQuit);
                application.getItems().add(quit);
                menuBar.getMenus().add(application);
            } // APPLICATION
            {
                Menu action = new Menu("Action");
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
                } // CREATE
                {
                    Menu overview = new Menu("Overview");
                    MenuItem allOverview = new MenuItem("All");
                    allOverview.setOnAction(this::actionAllOverview);
                    MenuItem personOverview = new MenuItem("People");
                    personOverview.setOnAction(this::actionPersonOverview);
                    MenuItem addressOverview = new MenuItem("Addresses");
                    addressOverview.setOnAction(this::actionAddressOverview);
                    MenuItem cityOverview = new MenuItem("Cities");
                    cityOverview.setOnAction(this::actionCityOverview);
                    overview.getItems().addAll(allOverview, personOverview, addressOverview, cityOverview);
                    action.getItems().add(overview);
                } // READ
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
                } // UPDATE
                {
                    Menu delete = new Menu("Delete");
                    MenuItem deletePerson = new MenuItem("Person");
                    deletePerson.setOnAction(this::actionDeletePerson);
                    MenuItem deleteAddress = new MenuItem("Address");
                    deleteAddress.setOnAction(this::actionDeleteAddress);
                    MenuItem deleteCity = new MenuItem("City");
                    deleteCity.setOnAction(this::actionDeleteCity);
                    delete.getItems().addAll(deletePerson, deleteAddress, deleteCity);
                    action.getItems().add(delete);
                } // DELETE
                {
                    action.getItems().add(new SeparatorMenuItem());
                    MenuItem import_ = new MenuItem("Import");
                    import_.setOnAction(this::actionImport);
                    action.getItems().add(import_);
                    MenuItem export = new MenuItem("Export");
                    export.setOnAction(this::actionExport);
                    action.getItems().add(export);
                    menuBar.getMenus().add(action);
                } // Import & Export
            } // ACTION
            {
                Menu help = new Menu("Help");
                MenuItem report = new MenuItem("Report bug");
                report.setOnAction(this::actionReportABug);
                MenuItem dumpLog = new MenuItem("Dump Log");
                dumpLog.setOnAction(this::actionDumpLog);
                help.getItems().addAll(report, dumpLog);
                menuBar.getMenus().add(help);
            } // HELP
            getChildren().add(menuBar);
        }
        {
            tabPane = new TabPane();
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
            tabPane.setPrefSize(960, 515);
            getChildren().add(tabPane);
        }
    }

    /**
     * Delete Tabs
     */
    private void actionDeleteCity(ActionEvent actionEvent) {
        tabPane.getTabs().add(new CityDeleteTab("Delete City"));
    }

    private void actionDeleteAddress(ActionEvent actionEvent) {
        tabPane.getTabs().add(new AddressDeleteTab("Delete Address"));
    }

    private void actionDeletePerson(ActionEvent actionEvent) {
        tabPane.getTabs().add(new PersonDeleteTab("Delete Person"));
    }

    /**
     * Overview tabs
     */
    private void actionAllOverview(ActionEvent actionEvent) {
        tabPane.getTabs().add(new OverviewTab("Overview"));
    }

    private void actionPersonOverview(ActionEvent actionEvent) {
        tabPane.getTabs().add(new PersonOverviewTab("People"));
    }

    private void actionAddressOverview(ActionEvent actionEvent) {
        tabPane.getTabs().add(new AddressOverviewTab("Addresses"));
    }

    private void actionCityOverview(ActionEvent actionEvent) {
        tabPane.getTabs().add(new CityOverviewTab("Cities"));
    }


    /**
     * Edit tabs
     */

    private void actionEditCity(ActionEvent actionEvent) {
        tabPane.getTabs().add(new CityEditTab("Edit City"));
    }

    private void actionEditAddress(ActionEvent actionEvent) {
        tabPane.getTabs().add(new AddressEditTab("Edit Address"));
    }

    private void actionEditPerson(ActionEvent actionEvent) {
        tabPane.getTabs().add(new PersonEditTab("Edit Person"));
    }


    private void actionNewAddress(ActionEvent actionEvent) {
        tabPane.getTabs().add(new AddressNewTab("New Address"));
    }

    private void actionNewCity(ActionEvent actionEvent) {
        tabPane.getTabs().add(new CityNewTab("New City"));
    }

    private void actionNewPerson(ActionEvent actionEvent) {
        tabPane.getTabs().add(new PersonNewTab("New Person"));
    }

    private void actionDumpLog(ActionEvent actionEvent) {
        throw new NullPointerException();
    }

    private void actionReportABug(ActionEvent actionEvent) {
        throw new NullPointerException();
    }

    private void actionImport(ActionEvent actionEvent) {
        throw new NullPointerException();
    }

    private void actionExport(ActionEvent actionEvent) {
        throw new NullPointerException();
    }

    private void actionQuit(ActionEvent actionEvent) {
        System.exit(420);
    }

    private void actionLogout(ActionEvent actionEvent) {
        Model.getInstance().logout();
        getScene().setRoot(new LoginView());
    }

}
