package ch.zli.m223.rest.data;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class City extends AbstractEntity<City> {

    private String name;
    private int zip;
    private User user;

    public City() {
        super();
    }

    public City(String name, int zip) {
        this.name = name;
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZip() {
        return zip;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    @Override
    public JSONObject toJSONObject(boolean withId) {
        JSONObject jsonObject = super.toJSONObject(withId);
        jsonObject.put("user", getUser().toJSONObject(true));
        jsonObject.put("name", getName());
        jsonObject.put("zip", getZip());
        return jsonObject;
    }

    @Override
    public City fromJSONString(String jsonString) {
        City city = super.fromJSONString(jsonString, new City());
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
            if (jsonObject.containsKey("name")) city.setName((String) jsonObject.get("name"));
            if (jsonObject.containsKey("zip")) city.setZip(Integer.parseInt(String.valueOf(jsonObject.get("zip"))));
            if (jsonObject.containsKey("user"))
                city.setUser(new User().fromJSONString(((JSONObject) jsonObject.get("user")).toJSONString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public String toString() {
        return zip + " " + name;
    }
}
