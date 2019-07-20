package com.banana.coolweather.db;

import org.litepal.crud.LitePalSupport;

/**
 * @anthor: Banana
 * @function:
 * @date: 2019/7/20
 */
public class City extends LitePalSupport {

    private int id;

    private String cityName;//城市名

    private int cityCode;//城市代码

    private int provinceId;//省id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
