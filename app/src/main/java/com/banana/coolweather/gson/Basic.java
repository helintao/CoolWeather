package com.banana.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @anthor: Banana
 * @function:
 * @date: 2019/7/20
 */
public class Basic {

    @SerializedName("location")
    public String cityName;

    @SerializedName("cid")
    public String weatherId;

}
