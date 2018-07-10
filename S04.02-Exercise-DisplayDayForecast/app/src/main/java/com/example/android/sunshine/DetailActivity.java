package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private TextView mSelectedWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mSelectedWeather = (TextView) findViewById(R.id.tv_weather_selected);
        Intent intent = getIntent();
        // ok (2) Display the weather forecast that was passed from MainActivity
        if(intent.hasExtra(Intent.EXTRA_TEXT)){
//            Toast.makeText(this, intent.getStringExtra(Intent.EXTRA_TEXT), Toast.LENGTH_LONG).show();
            mSelectedWeather.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        }
    }


}