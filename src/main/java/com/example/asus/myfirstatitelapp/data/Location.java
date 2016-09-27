package com.example.asus.myfirstatitelapp.data;

import org.json.JSONObject;

/**
 * Created by asus on 9/27/2016.
 */
public class Location implements JSONParser {
    private String city;
    private String region;
    private String country;

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public void parse(JSONObject data) {

        city=new String("");
        country=new String("");
        region=new String("");
        city=data.optString("city");
        country=data.optString("country");
        region=data.optString("region");
    }
}
