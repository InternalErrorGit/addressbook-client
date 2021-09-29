package ch.zli.m223.rest.data;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.Date;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class Person extends AbstractEntity<Person> {

    private String firstname;
    private String lastname;
    private Date birthdate;
    private Address address;
    private User user;

    public Person() {
        super();
    }

    public Person(String firstname, String lastname, Date birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public JSONObject toJSONObject(boolean withId) {
        JSONObject jsonObject = super.toJSONObject(withId);
        jsonObject.put("firstname", getFirstname());
        jsonObject.put("lastname", getLastname());
        jsonObject.put("user", getUser().toJSONObject(true));
        jsonObject.put("address", getAddress().toJSONObject(true));
        jsonObject.put("birthdate", birthdate.toString());
        return jsonObject;
    }

    @Override
    public Person fromJSONString(String jsonString) {
        Person person = super.fromJSONString(jsonString, new Person());
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
            if (jsonObject.containsKey("firstname")) person.setFirstname((String) jsonObject.get("firstname"));
            if (jsonObject.containsKey("lastname")) person.setLastname((String) jsonObject.get("lastname"));
            if (jsonObject.containsKey("birthdate"))
                person.setBirthdate(Date.valueOf((String) jsonObject.get("birthdate")));
            if (jsonObject.containsKey("address"))
                person.setAddress(new Address().fromJSONString(((JSONObject) jsonObject.get("address")).toJSONString()));
            if (jsonObject.containsKey("user"))
                person.setUser(new User().fromJSONString(((JSONObject) jsonObject.get("user")).toJSONString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return person;
    }
}
