package ch.zli.m223.rest.dao.impl;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.CityDAO;
import ch.zli.m223.rest.data.City;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class CityDAOImpl implements CityDAO {

    @Override
    public City update(City entity) {
        return null;
    }

    @Override
    public City findById(Long id) {
        return null;
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
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
                City city = new City().fromJSONString(jsonObject.toJSONString());
                cities.add(city);
            });
            return cities;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return cities;
    }

}
