package com.banana.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @anthor: Banana
 * @function:
 * @date: 2019/7/20
 */
public class Forecast {

    public String date;

    @SerializedName("tmp_max")
    public String max;

    @SerializedName("tmp_min")
    public String min;

    @SerializedName("cond_txt_d")
    public String info;

}
