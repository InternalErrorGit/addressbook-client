package ch.zli.m223.rest.dao.impl;

import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.AddressDAO;
import ch.zli.m223.rest.data.Address;
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
public class AddressDAOImpl implements AddressDAO {


    @Override
    public Address update(Address entity) {
        return null;
    }

    @Override
    public Address findById(Long id) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = new ArrayList<>();
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
                Address address = new Address().fromJSONString(jsonObject.toJSONString());
                addresses.add(address);
            });
            return addresses;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return addresses;
    }

}
