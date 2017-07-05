package com.example.stus.test_.holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stus.test_.App;
import com.example.stus.test_.Const;
import com.example.stus.testtask.R;
import com.example.stus.test_.activitys.WebViewActivity;
import com.example.stus.test_.models.GeonamesModel;
import com.squareup.picasso.Picasso;

public class CityInfoHolder extends RecyclerView.ViewHolder {
    private TextView info;
    private ImageView image;
    private Button showWiki;

    public CityInfoHolder(View itemView) {
        super(itemView);
        info = (TextView) itemView.findViewById(R.id.info);
        image = (ImageView) itemView.findViewById(R.id.city_image);
        showWiki = (Button) itemView.findViewById(R.id.show_wiki_page);
    }

    public static CityInfoHolder getInstance() {
        LayoutInflater inflater = LayoutInflater.from(App.getCurrentActivity());
        return new CityInfoHolder(inflater.inflate(R.layout.city_info_list_item, null, false));
    }

    public void update(GeonamesModel model) {
        info.setText(model.getSummary());
        Picasso.with(App.getCurrentActivity()).load(model.getThumbnailImg()).resize(500, 500).centerCrop().into(image);
        showWiki.setOnClickListener(v -> {
            Intent intent = new Intent(App.getCurrentActivity(), WebViewActivity.class);
            intent.putExtra(Const.URL, model.getWikipediaUrl());
            App.getCurrentActivity().startActivity(intent);
        });
    }
}
