package ch.zli.m223.rest.dao;

import ch.zli.m223.rest.data.Person;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public interface PersonDAO extends AbstractDAO<Person>{
    String url = "http://localhost:8080/persons";

    default String getUrl() {
        return url;
    }
}
