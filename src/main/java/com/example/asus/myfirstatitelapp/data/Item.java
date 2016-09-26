package com.example.asus.myfirstatitelapp.data;

import org.json.JSONObject;

/**
 * Created by asus on 9/26/2016.
 */
public class Item implements JSONParser{
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void parse(JSONObject data) {
        condition= new Condition();
        condition.parse(data.optJSONObject("condition"));
    }
}
