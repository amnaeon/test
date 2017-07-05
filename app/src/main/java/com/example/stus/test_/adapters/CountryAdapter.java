package com.example.stus.test_.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.stus.test_.holders.CountryHolder;
import com.example.stus.test_.interfaces.IOnItemClick;
import com.example.stus.test_.models.CityModel;

import java.util.ArrayList;
import java.util.List;


public class CountryAdapter extends RecyclerView.Adapter<CountryHolder> {
    private List<CityModel> data = new ArrayList<>();
    private IOnItemClick itemClick;

    @Override
    public CountryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CountryHolder instance = CountryHolder.getInstance();
        instance.setItemClick(itemClick);
        return instance;
    }

    @Override
    public void onBindViewHolder(CountryHolder holder, int position) {
        holder.update(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<CityModel> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setItemClick(IOnItemClick itemClick) {
        this.itemClick = itemClick;
    }
}
