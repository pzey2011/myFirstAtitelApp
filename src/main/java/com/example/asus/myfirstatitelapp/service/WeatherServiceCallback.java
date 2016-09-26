package com.example.asus.myfirstatitelapp.service;

import com.example.asus.myfirstatitelapp.data.Channel;

/**
 * Created by asus on 9/26/2016.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
