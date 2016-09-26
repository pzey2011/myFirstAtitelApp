package com.example.asus.myfirstatitelapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.myfirstatitelapp.data.Channel;
import com.example.asus.myfirstatitelapp.service.WeatherServiceCallback;
import com.example.asus.myfirstatitelapp.service.YahooWeatherService;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {
    private ImageView weatherIcon;
    private TextView temperature;
    private TextView condition;
    private TextView location;
    private YahooWeatherService service;
    private ProgressDialog dialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherIcon=(ImageView)findViewById(R.id.weatherIcon);
        temperature=(TextView)findViewById(R.id.temperature);
        condition=(TextView)findViewById(R.id.condition);
        location=(TextView)findViewById(R.id.location);
        service=new YahooWeatherService(this);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.updateWeather("Tehran ,IR");
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
    }

    @Override
    public void serviceFailure(Exception exception) {
        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
    }
}
