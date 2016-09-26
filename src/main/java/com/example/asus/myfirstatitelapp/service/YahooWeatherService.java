package com.example.asus.myfirstatitelapp.service;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.asus.myfirstatitelapp.MainActivity;
import com.example.asus.myfirstatitelapp.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by asus on 9/26/2016.
 */
public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private String location;
    private Exception exception;
    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }



    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }
    public void updateWeather(String l)
    {
        this.location=l;
       new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... strings) {
                String YahooQuery=String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")",location);
                String urlString=String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YahooQuery));
                try {
                    URL url=new URL(urlString);
                    URLConnection conn=url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb=new StringBuilder();
                    String line;
                    while((line= br.readLine())!=null)
                    {
                        sb.append(line);
                    }

                    return sb.toString();
                } catch (Exception e) {
                    exception=e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s==null && exception!=null)
                {
                    callback.serviceFailure(exception);
                    return;
                }
                try {
                    JSONObject responseJSON=new JSONObject(s);//parse the JSON GET response string
                    JSONObject query=responseJSON.optJSONObject("query");
                    int count=query.optInt("count");
                   if(count==0)
                    {
                        callback.serviceFailure(new Exception("No weather informations found for"+location));
                        return;
                    }
                    Channel channel=new Channel();
                   channel.parse(query.optJSONObject("results").optJSONObject("channel"));
                   callback.serviceSuccess(channel);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(location);
    }
}
