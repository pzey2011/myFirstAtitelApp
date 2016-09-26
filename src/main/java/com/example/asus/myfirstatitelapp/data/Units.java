package com.example.asus.myfirstatitelapp.data;

import org.json.JSONObject;

/**
 * Created by asus on 9/26/2016.
 */
public class Units implements JSONParser {
    private String unit;
    @Override
    public void parse(JSONObject data) {
        unit=new String("");
        unit= data.optString("temperature");
    }

    public String getUnit() {
        return unit;
    }
}
