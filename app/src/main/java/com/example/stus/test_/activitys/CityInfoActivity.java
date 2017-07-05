package com.example.stus.test_.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.stus.test_.Const;
import com.example.stus.test_.Utils;
import com.example.stus.test_.adapters.CityInfoAdapter;
import com.example.stus.test_.models.GeonamesModel;
import com.example.stus.testtask.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.List;



public class CityInfoActivity extends AppCompatActivity {
    private String name = "";
    private RecyclerView sityInfoList;
    private CityInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_info);
        init();
        sendRequest();

    }

    private void init() {
        Intent intent = getIntent();
        name = intent.getStringExtra(Const.CITY);
        adapter = new CityInfoAdapter();
        sityInfoList = (RecyclerView) findViewById(R.id.city_info_list);
        sityInfoList.setLayoutManager(new LinearLayoutManager(this));
        sityInfoList.setAdapter(adapter);
    }

    public void sendRequest() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("q", name);
        params.add("username", "amnaeon");
        client.get(this, "http://api.geonames.org/wikipediaSearchJSON", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                List<GeonamesModel> geonamesModelList;
                geonamesModelList = Utils.parseJsonToModels(new String(responseBody));
                adapter.setData(geonamesModelList);
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }

        });


    }
}
