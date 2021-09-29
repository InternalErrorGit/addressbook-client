package ch.zli.m223.rest.dao.impl;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.PersonDAO;
import ch.zli.m223.rest.data.Person;
import org.json.simple.JSONObject;

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
public class PersonDAOImpl implements PersonDAO {

    @Override
    public Person create(Person object) {
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

    @Override
    public Person findById(Long id) {
        return null;
    }

    @Override
    public List<Person> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
