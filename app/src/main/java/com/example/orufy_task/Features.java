package com.example.orufy_task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class Features extends AppCompatActivity {
    private WebView web;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);
        setContentView(R.layout.activity_features);
        web = findViewById(R.id.feature_webView);

        try {
            JSONObject obj = new JSONObject(LoadJsonFromassets());
            JSONArray array  = obj.getJSONArray("tabs");
            JSONObject o = array.getJSONObject(2);
            String url = o.getString("link");

            WebSettings webSettings = web.getSettings();
            webSettings.setJavaScriptEnabled(true);
            web.setWebViewClient(new callback());
            web.loadUrl(url);


        }catch ( JSONException e){
            e.printStackTrace();
        }

        bottomNavigationView = findViewById(R.id.bottom_feature);
        bottomNavigationView.setSelectedItemId(R.id.feature);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplication()
                                , MainActivity.class));
                        overridePendingTransition(0,0);
                        return  true;

                    case R.id.showcase:
                        startActivity(new Intent(getApplication()
                                , Showcase.class));
                        overridePendingTransition(0,0);
                        return  true;


                    case R.id.feature:
                        return  true;

                    case R.id.faq:
                        startActivity(new Intent(getApplication()
                                , Faq.class));
                        overridePendingTransition(0,0);
                        return  true;

                    case R.id.pricing:
                        startActivity(new Intent(getApplication()
                                , Pricing.class));
                        overridePendingTransition(0,0);
                        return  true;
                }
                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()){
            web.goBack();
        }else{
            super.onBackPressed();
        }
    }

    private class callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
    public  String LoadJsonFromassets(){
        String json = null;
        try {
            InputStream in = this.getAssets().open("Orufy.json");
            int size = in.available();
            byte[] bbuffer = new byte [size];
            in.read(bbuffer);
            in.close();
            json = new String(bbuffer, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
            return  null;
        }
        return json;
    }
}