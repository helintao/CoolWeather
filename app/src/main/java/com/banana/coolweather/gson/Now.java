package com.banana.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @anthor: Banana
 * @function:
 * @date: 2019/7/20
 */
public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond_txt")
    public String info;

}
