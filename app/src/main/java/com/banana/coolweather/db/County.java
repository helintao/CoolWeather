package com.banana.coolweather.db;

import org.litepal.crud.LitePalSupport;

/**
 * @anthor: Banana
 * @function:
 * @date: 2019/7/20
 */
public class County extends LitePalSupport {

    private int id;

    private String countyName;//县名

    private String weatherId;//天气id

    private int cityId;//县所属市id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
