package com.banana.coolweather.gson;

/**
 * @anthor: Banana
 * @function:
 * @date: 2019/7/20
 */
public class AQI {

    public AQICity city;

    public class AQICity{
        public String aqi;

        public String pm25;
    }
}
