package ch.zli.m223.model;

import ch.zli.m223.rest.dao.impl.AddressDAOImpl;
import ch.zli.m223.rest.dao.impl.CityDAOImpl;
import ch.zli.m223.rest.dao.impl.PersonDAOImpl;
import ch.zli.m223.rest.data.Address;
import ch.zli.m223.rest.data.City;
import ch.zli.m223.rest.data.Person;
import ch.zli.m223.rest.data.User;
import ch.zli.m223.view.node.TableObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class Model {

    private static Model instance;
    private User user;
    private String token;
    public final ObservableList<City> cities = FXCollections.observableArrayList();
    public final ObservableList<Person> people = FXCollections.observableArrayList();
    public final ObservableList<Address> addresses = FXCollections.observableArrayList();
    public final ObservableList<TableObject> tableObjects = FXCollections.observableArrayList();

    private Model() {

    }

    public void update() {
        cities.clear();
        addresses.clear();
        people.clear();
        cities.addAll(new CityDAOImpl().findAll());
        addresses.addAll(new AddressDAOImpl().findAll());
        people.addAll(new PersonDAOImpl().findAll());
        tableObjects.clear();
        people.forEach(p -> {
            TableObject tableObject = new TableObject(p, p.getAddress(), p.getAddress().getCity());
            tableObjects.add(tableObject);
        });
        addresses.forEach(p -> {
            TableObject tableObject = new TableObject(null, p, p.getCity());
            tableObjects.add(tableObject);
        });
        cities.forEach(p -> {
            TableObject tableObject = new TableObject(null, null, p);
            tableObjects.add(tableObject);
        });
    }

    public static Model getInstance() {
        if (instance == null) instance = new Model();
        return instance;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Model{" +
                "user=" + user +
                ", token='" + token + '\'' +
                '}';
    }

    public void logout() {
        token = null;
        user = null;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

}
