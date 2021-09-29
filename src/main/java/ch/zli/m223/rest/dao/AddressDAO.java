package ch.zli.m223.rest.dao;

import ch.zli.m223.rest.data.Address;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public interface AddressDAO extends AbstractDAO<Address> {
    String url = "http://localhost:8080/addresses";

    default String getUrl() {
        return url;
    }
}
