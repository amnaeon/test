package com.example.stus.test_.holders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.stus.test_.App;
import com.example.stus.test_.interfaces.IOnItemClick;
import com.example.stus.test_.models.CityModel;
import com.example.stus.testtask.R;

public class CountryHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private IOnItemClick itemClick;

    public CountryHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
    }


    public static CountryHolder getInstance() {
        LayoutInflater inflater = LayoutInflater.from(App.getCurrentActivity());
        return new CountryHolder(inflater.inflate(R.layout.country_list_item, null, false));
    }

    public void update(CityModel city) {
        name.setText(city.getName());
        itemView.setOnClickListener(v-> itemClick.onClick(city));
    }

    public void setItemClick(IOnItemClick itemClick) {
        this.itemClick = itemClick;
    }
}
