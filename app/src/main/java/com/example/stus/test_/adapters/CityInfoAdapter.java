package com.example.stus.test_.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.stus.test_.holders.CityInfoHolder;
import com.example.stus.test_.models.GeonamesModel;

import java.util.ArrayList;
import java.util.List;

public class CityInfoAdapter extends RecyclerView.Adapter<CityInfoHolder>{
    List<GeonamesModel> data = new ArrayList<>();

    @Override
    public CityInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CityInfoHolder.getInstance();
    }

    @Override
    public void onBindViewHolder(CityInfoHolder holder, int position) {
        holder.update(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<GeonamesModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
