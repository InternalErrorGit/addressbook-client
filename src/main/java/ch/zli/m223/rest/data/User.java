package ch.zli.m223.rest.data;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class User extends AbstractEntity<User> {

    private String username;
    private String password;

    public User() {
        super();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSONObject(boolean withId) {
        JSONObject jsonObject = super.toJSONObject(withId);
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        return jsonObject;
    }

    @Override
    public User fromJSONString(String jsonString) {
        User user = super.fromJSONString(jsonString, new User());
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
            if (jsonObject.containsKey("username")) user.setUsername((String) jsonObject.get("username"));
            if (jsonObject.containsKey("password")) user.setPassword((String) jsonObject.get("password"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
