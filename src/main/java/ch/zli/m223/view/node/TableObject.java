package ch.zli.m223.view.node;

import ch.zli.m223.rest.data.Address;
import ch.zli.m223.rest.data.City;
import ch.zli.m223.rest.data.Person;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class TableObject {

    private Person person;
    private Address address;
    private City city;

    public TableObject() {
    }

    public TableObject(Person person, Address address, City city) {

        this.person = person;
        this.address = address;
        this.city = city;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
