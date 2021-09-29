package ch.zli.m223.rest.dao;

import ch.zli.m223.rest.data.City;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public interface CityDAO extends AbstractDAO<City> {
    String url = "http://localhost:8080/cities";

    default String getUrl() {
        return url;
    }
}
