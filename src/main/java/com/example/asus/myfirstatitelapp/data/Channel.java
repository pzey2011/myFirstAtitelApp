package com.example.asus.myfirstatitelapp.data;

import org.json.JSONObject;

/**
 * Created by asus on 9/26/2016.
 */
public class Channel implements JSONParser{
    private Item item;
    private Units units;

    public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }

    @Override
    public void parse(JSONObject data) {
        units = new Units();
        units.parse(data.optJSONObject("units"));
        item.parse(data.optJSONObject("item"));
    }
}
