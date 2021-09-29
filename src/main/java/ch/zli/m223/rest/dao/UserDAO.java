package ch.zli.m223.rest.dao;

import ch.zli.m223.rest.data.User;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public interface UserDAO extends AbstractDAO<User> {
    String url = "http://localhost:8080/users";

    default String getUrl() {
        return url;
    }

    String[] login(User user);

}
