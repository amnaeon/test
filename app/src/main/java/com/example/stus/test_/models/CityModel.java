package com.example.stus.test_.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.example.stus.test_.App.rm;
import static com.example.stus.test_.Utils.randomString;

public class CityModel extends RealmObject {
    @PrimaryKey
    private String id =  randomString(10);
    private String name = "";

    public CityModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        rm().beginTransaction();
        this.name = name;
        rm().commitTransaction();

    }


}
