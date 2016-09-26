package com.example.asus.myfirstatitelapp.service;

import android.os.AsyncTask;

/**
 * Created by asus on 9/26/2016.
 */
public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private String location;
    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }



    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }
    public void updateWeather(String location)
    {
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... strings) {
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute(location);
    }
}
