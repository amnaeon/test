package com.example.stus.test_.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.stus.test_.App;
import com.example.stus.test_.Const;
import com.example.stus.test_.GetСountriesTask;
import com.example.stus.test_.interfaces.IOnTaskSuccess;
import com.example.stus.testtask.R;
import com.example.stus.test_.adapters.CountryAdapter;
import com.example.stus.test_.interfaces.IOnItemClick;
import com.example.stus.test_.models.CityModel;
import com.example.stus.test_.models.CountryModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.example.stus.test_.App.rm;


public class MainActivity extends AppCompatActivity implements IOnItemClick, IOnTaskSuccess {

    private RecyclerView countryList;
    private List<CountryModel> countryModelList;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.registerActivity(this);
        initList();
        init();
    }

    private void initList() {
        countryAdapter = new CountryAdapter();
        countryList = (RecyclerView) findViewById(R.id.counrty_list);
        countryList.setLayoutManager(new LinearLayoutManager(this));
        countryList.setAdapter(countryAdapter);
        countryAdapter.setItemClick(this);
    }

    private void init() {
        countryModelList = rm().where(CountryModel.class).findAll();
        if (countryModelList.size() == 0) {
            GetСountriesTask task = new GetСountriesTask();
            task.setOnTaskSuccess(this);
            task.execute();
        } else {

            Spinner spinner = (Spinner) findViewById(R.id.country_selector);
            List<String> spinnerData = new ArrayList<>();

            for (int i = 0; i < countryModelList.size(); i++) {
                spinnerData.add(countryModelList.get(i).getCountryName());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, spinnerData);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    CountryModel currentCountry = rm().where(CountryModel.class).equalTo("countryName", countryModelList.get(position).getCountryName()).findFirst();
                    if (currentCountry.getCityList().size() == 0) {
                        JSONArray array = null;
                        try {
                            array = currentCountry.getCountryArray();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ProgressDialog progressBar = new ProgressDialog(MainActivity.this);
                        progressBar.setMessage("Please wait...");
                        progressBar.setCancelable(false);
                        progressBar.show();
                        for (int i = 0; i < array.length(); i++) {
                            CityModel city = new CityModel();
                            try {
                                city.setName(array.getString(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            currentCountry.addCity(city);
                        }
                        rm().beginTransaction();
                        rm().copyToRealmOrUpdate(currentCountry);
                        rm().commitTransaction();
                        progressBar.dismiss();
                        countryAdapter.setData(currentCountry.getCityList());

                    } else {
                        countryAdapter.setData(countryModelList.get(position).getCityList());
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

    @Override
    public void onClick(CityModel city) {
        Intent intent = new Intent(MainActivity.this, CityInfoActivity.class);
        intent.putExtra(Const.CITY, city.getName());
        startActivity(intent);
    }


    @Override
    public void onTaskSuccess() {
        init();
    }
}
