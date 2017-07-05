package com.example.stus.test_.models;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.example.stus.test_.App.rm;
import static com.example.stus.test_.Utils.randomString;

public class CountryModel extends RealmObject{
    @PrimaryKey
    private String id =  randomString(10);
    private RealmList<CityModel> sityList = new RealmList<>();
    private String countryName = "";
    private String countryArray;
    public CountryModel() {
    }

    public List<CityModel> getCityList() {
        return sityList;
    }

    public void addCity(CityModel city){
        rm().beginTransaction();
        sityList.add(city);
        rm().commitTransaction();

    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        rm().beginTransaction();
        this.countryName = countryName;
        rm().commitTransaction();
    }

    public JSONArray getCountryArray() throws JSONException {
        return new JSONArray(countryArray);
    }

    public void setCountryArray(String countryArray) {
        rm().beginTransaction();
        this.countryArray = countryArray;
        rm().commitTransaction();
    }

}
