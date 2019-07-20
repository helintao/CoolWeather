package com.banana.coolweather.db;


import org.litepal.crud.LitePalSupport;

/**
 * @anthor: Banana
 * @function:
 * @date: 2019/7/20
 */
public class Province extends LitePalSupport {

    private int id;

    private String provinceName;//省名

    private int provinceCode;//省代号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}