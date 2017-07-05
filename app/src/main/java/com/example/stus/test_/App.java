package com.example.stus.test_;

import android.app.Application;
import android.support.annotation.Nullable;

import com.example.stus.test_.activitys.MainActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
    private static MainActivity mainActivity;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void registerActivity(MainActivity mainActivity) {
        App.mainActivity = mainActivity;
    }

    @Nullable
    public static MainActivity getCurrentActivity() {
        return mainActivity;
    }


    public static Realm rm(){
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(mainActivity).build();
        Realm.setDefaultConfiguration(realmConfig);
        return Realm.getInstance(realmConfig);
    }
}
