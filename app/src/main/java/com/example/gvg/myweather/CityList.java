package com.example.gvg.myweather;

/**
 * Created by GVG on 12.11.2017.
 */

public class CityList {
    private String name;
//    public static CityList[] cities = {new CityList("Москва"),new CityList("Санкт-Петербург"),new CityList("Тюмень")};

    public CityList(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
