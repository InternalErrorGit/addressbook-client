package ch.zli.m223.model;

import ch.zli.m223.rest.dao.impl.AddressDAOImpl;
import ch.zli.m223.rest.dao.impl.CityDAOImpl;
import ch.zli.m223.rest.dao.impl.PersonDAOImpl;
import ch.zli.m223.rest.data.Address;
import ch.zli.m223.rest.data.City;
import ch.zli.m223.rest.data.Person;
import ch.zli.m223.rest.data.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Timer;
import java.util.TimerTask;

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

    private Model() {

    }

    public void update() {
        cities.clear();
        addresses.clear();
        people.clear();
        cities.addAll(new CityDAOImpl().findAll());
        addresses.addAll(new AddressDAOImpl().findAll());
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
