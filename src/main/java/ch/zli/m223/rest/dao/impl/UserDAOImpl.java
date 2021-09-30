package ch.zli.m223.rest.dao.impl;

import ch.zli.m223.rest.dao.UserDAO;
import ch.zli.m223.rest.data.User;
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
import java.util.List;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class UserDAOImpl implements UserDAO {

    @Override
    public synchronized User create(User object) {
        try {
            URL url = new URL(getUrl());
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("POST");
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            c.setDoOutput(true);
            String json = object.toJSONObject(false).toJSONString();
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
    public User update(User entity) {
        return null;
    }

    @Override
    public synchronized String[] login(User user) {
        try {
            URL url = new URL(getUrl() + "/login");
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("POST");
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            c.setDoOutput(true);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", user.getUsername());
            jsonObject.put("password", user.getPassword());
            String json = jsonObject.toJSONString();
            OutputStream os = c.getOutputStream();
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = reader.readLine()) != null) {
                response.append(responseLine);
            }
            JSONObject r = (JSONObject) new JSONParser().parse(response.toString());
            String[] result = new String[2];
            result[0] = (String) r.get("token");
            result[1] = String.valueOf(r.get("userId"));
            return result;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized User findById(Long id) {
        try {
            URL url = new URL(getUrl() + "/" + id);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            c.setDoOutput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = reader.readLine()) != null) {
                response.append(responseLine);
            }
            return new User().fromJSONString(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }


}
