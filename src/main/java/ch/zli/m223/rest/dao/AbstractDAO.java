package ch.zli.m223.rest.dao;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.data.AbstractEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public interface AbstractDAO<T extends AbstractEntity<T>> {

    // T create(T object);

    default T create(T object) {
        try {
            URL url = new URL(getUrl());
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("POST");
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            c.setRequestProperty("Authorization", "Bearer " + Model.getInstance().getToken());
            c.setDoOutput(true);
            String json = object.toJSONObject(false).toJSONString();
            System.out.println(json);
            OutputStream os = c.getOutputStream();
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = reader.readLine()) != null) {
                response.append(responseLine);
            }
            return object.fromJSONString(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    T update(T entity);

    default void delete(Long id) {
        try {
            URL url = new URL(getUrl() + "/" + id);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("DELETE");
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            c.setRequestProperty("Authorization", "Bearer " + Model.getInstance().getToken());
            c.setDoOutput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = reader.readLine()) != null) {
                response.append(responseLine);
            }
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    String getUrl();

    T findById(Long id);

    List<T> findAll();


}
