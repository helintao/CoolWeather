package com.banana.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @anthor: Banana
 * @function:
 * @date: 2019/7/20
 */
public class Weather {
    public String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    @SerializedName("lifestyle")
    public List<Lifestyle> lifestyleList;

    public Update update;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
