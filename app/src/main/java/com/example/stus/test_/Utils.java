package com.example.stus.test_;

import com.example.stus.test_.models.GeonamesModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    public static String randomString(int lenth) {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();

        char tempChar;
        for (int i = 0; i < lenth; i++) {
            tempChar = (char) (generator.nextInt(25) + 65);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

    public static List<GeonamesModel> parseJsonToModels(String responce) {
        List<GeonamesModel> res = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(responce);
            JSONArray citys = jsonObject.getJSONArray("geonames");
            for (int i = 0; i < citys.length(); i++) {
                GeonamesModel geonamesModel = new GeonamesModel();
                geonamesModel.setSummary(citys.getJSONObject(i).getString("summary"));
                geonamesModel.setThumbnailImg(citys.getJSONObject(i).getString("thumbnailImg"));
                geonamesModel.setWikipediaUrl(citys.getJSONObject(i).getString("wikipediaUrl"));
                res.add(geonamesModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

}
