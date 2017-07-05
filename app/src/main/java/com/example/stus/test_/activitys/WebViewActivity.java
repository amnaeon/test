package com.example.stus.test_.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.stus.test_.Const;
import com.example.stus.testtask.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent = getIntent();
        WebView webView = (WebView) findViewById(R.id.wiki_web_view);
        webView.loadUrl("http://"+intent.getStringExtra(Const.URL));

    }
}
