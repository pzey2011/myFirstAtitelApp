package com.example.asus.myfirstatitelapp.data;

import org.json.JSONObject;

/**
 * Created by asus on 9/26/2016.
 */
public class Condition implements JSONParser {
    private int code;
    private int temperature;
    private String discription;

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDiscription() {
        return discription;
    }

    @Override
    public void parse(JSONObject data) {
        code= data.optInt("code");
        discription=new String("");
        temperature = data.optInt("temp");
        discription=data.optString("text");
    }
}
