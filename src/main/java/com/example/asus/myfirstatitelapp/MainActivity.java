package com.example.asus.myfirstatitelapp;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.myfirstatitelapp.data.Channel;
import com.example.asus.myfirstatitelapp.service.WeatherServiceCallback;
import com.example.asus.myfirstatitelapp.service.YahooWeatherService;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {
    private ImageView weatherImage;
    private TextView temperatureView;
    private TextView conditionView;
    private TextView locationView;
    private EditText changeCity;
    private Button okButton;
    private YahooWeatherService service;
    private ProgressDialog dialog;

    public ProgressDialog getDialog() {
        return dialog;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherImage=(ImageView)findViewById(R.id.weatherIcon);
        temperatureView=(TextView)findViewById(R.id.temperature);
        conditionView=(TextView)findViewById(R.id.condition);
        locationView=(TextView)findViewById(R.id.location);
        changeCity=(EditText) findViewById(R.id.editText);
        okButton=(Button)findViewById(R.id.button);
        service=new YahooWeatherService(this);
        dialog=new ProgressDialog(this);

        dialog.setMessage("Loading...");
        dialog.show();

        service.updateWeather("Tehran ,IR");
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.setMessage("Loading...");
                dialog.show();
                service.updateWeather(changeCity.getText().toString());
            }
        });

    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
        int iconNumber= getResources().getIdentifier("@drawable/icon"+channel.getItem().getCondition().getCode(),null,getPackageName());
        @SuppressWarnings("deprecation")
        Drawable weatherIcon= getResources().getDrawable(iconNumber);
        weatherImage.setImageDrawable(weatherIcon);
        temperatureView.setText(channel.getItem().getCondition().getTemperature()+"\u00B0"+channel.getUnits().getUnit());
        conditionView.setText(channel.getItem().getCondition().getDiscription());
        locationView.setText(service.getLocation());
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
    }
}
