package ch.zli.m223.rest.dao.impl;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.PersonDAO;
import ch.zli.m223.rest.data.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class PersonDAOImpl implements PersonDAO {

    @Override
    public Person update(Person entity) {
        try {
            URL url = new URL(getUrl());
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("PUT");
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            c.setRequestProperty("Authorization", "Bearer " + Model.getInstance().getToken());
            c.setDoOutput(true);
            String json = entity.toJSONObject(true).toJSONString();
            OutputStream os = c.getOutputStream();
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = reader.readLine()) != null) {
                response.append(responseLine);
            }
            return entity.fromJSONString(response.toString());
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
        List<Person> people = new ArrayList<>();
        try {
            URL url = new URL(getUrl());
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
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
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(response.toString());
            jsonArray.forEach(o -> {
                JSONObject jsonObject = (JSONObject) o;
                Person person = new Person().fromJSONString(jsonObject.toJSONString());
                people.add(person);
            });
            return people;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return people;
    }

}
