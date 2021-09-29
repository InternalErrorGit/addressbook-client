package ch.zli.m223.rest.data;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class Address extends AbstractEntity<Address> {

    private String street;
    private String houseNumber;
    private User user;
    private City city;

    public Address() {
        super();
    }

    public Address(String street, String houseNumber, User user, City city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.user = user;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSONObject(boolean withId) {
        JSONObject jsonObject = super.toJSONObject(withId);
        jsonObject.put("user", getUser().toJSONObject(true));
        jsonObject.put("city", getCity().toJSONObject(true));
        jsonObject.put("street", getStreet());
        jsonObject.put("houseNumber", getHouseNumber());
        return jsonObject;
    }

    @Override
    public String toString() {
        return city.toString() + " " + street + " " + houseNumber;
    }

    @Override
    public Address fromJSONString(String jsonString) {
        Address address = super.fromJSONString(jsonString, new Address());
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
            if (jsonObject.containsKey("street")) address.setStreet((String) jsonObject.get("street"));
            if (jsonObject.containsKey("houseNumber")) address.setHouseNumber((String) jsonObject.get("houseNumber"));

            if (jsonObject.containsKey("user"))
                address.setUser(new User().fromJSONString(((JSONObject) jsonObject.get("user")).toJSONString()));
            if (jsonObject.containsKey("city"))
                address.setCity(new City().fromJSONString(((JSONObject) jsonObject.get("city")).toJSONString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return address;
    }
}
