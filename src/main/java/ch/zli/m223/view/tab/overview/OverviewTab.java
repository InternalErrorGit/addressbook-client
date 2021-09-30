package ch.zli.m223.view.tab.overview;

import ch.zli.m223.model.Model;
import ch.zli.m223.view.node.TableObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class OverviewTab extends AbstractOverviewTab<TableObject> {

    public OverviewTab(String s) {
        super(s);
    }

    @Override
    public void initGUI() {
        super.initGUI();
        Model.getInstance().update();
        tableView.setItems(Model.getInstance().tableObjects);
        {
            TableColumn<TableObject,String> colPerson = new TableColumn<>("Person");
            TableColumn<TableObject, String> colPersonId = new TableColumn<>("ID");
            colPersonId.setCellValueFactory(v -> v.getValue().getPerson() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getPerson().getId()));
            TableColumn<TableObject, String> colPersonCreateDate = new TableColumn<>("CreateDate");
            colPersonCreateDate.setCellValueFactory(v -> v.getValue().getPerson() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getPerson().getCreateDate()));
            TableColumn<TableObject, String> colPersonUpdateDate = new TableColumn<>("UpdateDate");
            colPersonUpdateDate.setCellValueFactory(v -> v.getValue().getPerson() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getPerson().getUpdateDate()));
            TableColumn<TableObject, String> colPersonUsername = new TableColumn<>("Username");
            colPersonUsername.setCellValueFactory(v -> v.getValue().getPerson() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getPerson().getUser().getUsername()));
            TableColumn<TableObject, String> colPersonFirstname = new TableColumn<>("Firstname");
            colPersonFirstname.setCellValueFactory(v -> v.getValue().getPerson() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getPerson().getFirstname()));
            TableColumn<TableObject, String> colPersonLastname = new TableColumn<>("Lastname");
            colPersonLastname.setCellValueFactory(v -> v.getValue().getPerson() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getPerson().getLastname()));
            colPerson.getColumns().addAll(colPersonId, colPersonCreateDate, colPersonUpdateDate, colPersonUsername, colPersonFirstname, colPersonLastname);
            tableView.getColumns().add(colPerson);
        }
        {
            TableColumn<TableObject,String> colAddress = new TableColumn<>("Address");
            TableColumn<TableObject, String> colAddressId = new TableColumn<>("ID");
            colAddressId.setCellValueFactory(v -> v.getValue().getAddress() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getAddress().getId()));
            TableColumn<TableObject, String> colAddressCreateDate = new TableColumn<>("CreateDate");
            colAddressCreateDate.setCellValueFactory(v -> v.getValue().getAddress() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getAddress().getCreateDate()));
            TableColumn<TableObject, String> colAddressUpdateDate = new TableColumn<>("UpdateDate");
            colAddressUpdateDate.setCellValueFactory(v -> v.getValue().getAddress() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getAddress().getUpdateDate()));
            TableColumn<TableObject, String> colAddressUsername = new TableColumn<>("Username");
            colAddressUsername.setCellValueFactory(v -> v.getValue().getAddress() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getAddress().getUser().getUsername()));
            TableColumn<TableObject, String> colAddressStreet = new TableColumn<>("Street");
            colAddressStreet.setCellValueFactory(v -> v.getValue().getAddress() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getAddress().getStreet()));
            TableColumn<TableObject, String> colAddressHouseNumber = new TableColumn<>("HouseNumber");
            colAddressHouseNumber.setCellValueFactory(v -> v.getValue().getAddress() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getAddress().getHouseNumber()));
            colAddress.getColumns().addAll(colAddressId, colAddressCreateDate, colAddressUpdateDate, colAddressUsername, colAddressStreet, colAddressHouseNumber);
            tableView.getColumns().add(colAddress);

        }
        {
            TableColumn<TableObject,String> colCity = new TableColumn<>("City");
            TableColumn<TableObject, String> colCityId = new TableColumn<>("ID");
            colCityId.setCellValueFactory(v -> v.getValue().getCity() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getCity().getId()));
            TableColumn<TableObject, String> colCityCreateDate = new TableColumn<>("CreateDate");
            colCityCreateDate.setCellValueFactory(v -> v.getValue().getCity() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getCity().getCreateDate()));
            TableColumn<TableObject, String> colCityUpdateDate = new TableColumn<>("UpdateDate");
            colCityUpdateDate.setCellValueFactory(v -> v.getValue().getCity() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getCity().getUpdateDate()));
            TableColumn<TableObject, String> colCityUsername = new TableColumn<>("Username");
            colCityUsername.setCellValueFactory(v -> v.getValue().getCity() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getCity().getUser().getUsername()));
            TableColumn<TableObject, String> colCityName = new TableColumn<>("Name");
            colCityName.setCellValueFactory(v -> v.getValue().getCity() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getCity().getName()));
            TableColumn<TableObject, String> colCityZip = new TableColumn<>("Zip");
            colCityZip.setCellValueFactory(v -> v.getValue().getCity() == null ? new SimpleStringProperty("") : new SimpleStringProperty("" + v.getValue().getCity().getZip()));

            colCity.getColumns().addAll(colCityId, colCityCreateDate, colCityUpdateDate, colCityUsername, colCityName, colCityZip);
            tableView.getColumns().add(colCity);
        }
    }

    @Override
    void update(ActionEvent actionEvent) {

    }


}
