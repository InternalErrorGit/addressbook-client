package ch.zli.m223.rest.data;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.LocalDateTime;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public abstract class AbstractEntity<T extends AbstractEntity<T>> {

    protected Long id;
    protected LocalDateTime createDate;
    protected LocalDateTime updateDate;

    public AbstractEntity() {
        createDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }

    @SuppressWarnings("unchecked")
    public JSONObject toJSONObject(boolean withId) {
        JSONObject jsonObject = new JSONObject();
        if (withId) jsonObject.put("id", getId());
        jsonObject.put("createDate", getCreateDate().toString());
        jsonObject.put("updateDate", getUpdateDate().toString());
        return jsonObject;
    }

    protected T fromJSONString(String jsonString, T entity) {
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
            if (jsonObject.containsKey("id")) entity.setId((Long) jsonObject.get("id"));
            if (jsonObject.containsKey("createDate"))
                entity.setCreateDate(LocalDateTime.parse((CharSequence) jsonObject.get("createDate")));
            if (jsonObject.containsKey("updateDate"))
                entity.setUpdateDate(LocalDateTime.parse((CharSequence) jsonObject.get("updateDate")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public abstract T fromJSONString(String jsonString);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
