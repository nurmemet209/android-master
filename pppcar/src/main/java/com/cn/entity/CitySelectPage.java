package com.cn.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nurmemet on 2016/3/30.
 */
public class CitySelectPage  extends Message{
    public Map<String, ArrayList<City>> getAbcCityMap() {
        return abcCityMap;
    }

    public void setAbcCityMap(Map<String, ArrayList<City>> abcCityMap) {
        this.abcCityMap = abcCityMap;
    }

    public List<City> getHotCity() {
        return hotCity;
    }

    public void setHotCity(List<City> hotCity) {
        this.hotCity = hotCity;
    }

    public Map<String,ArrayList<City>> abcCityMap;
    public List<City> hotCity;


}
