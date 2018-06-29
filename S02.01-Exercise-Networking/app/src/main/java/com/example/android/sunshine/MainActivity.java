/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.sunshine.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getName();

    private TextView mWeatherTextView;

    private EditText mSearchWeatherEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        /*
         * Using findViewById, we get a reference to our TextView from xml. This allows us to
         * do things like set the text of the TextView.
         */
        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);
        mSearchWeatherEditText = (EditText) findViewById(R.id.et_weather_search);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // ok (9) Call loadWeatherData to perform the network request to get the weather
        switch (item.getItemId()) {
            case R.id.action_search:
                loadWeatherData(mSearchWeatherEditText.getText().toString());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // ok (8) Create a method that will get the user's preferred location and execute your new AsyncTask and call it
    public void loadWeatherData(String usersPreferredLocation){
        Log.d(TAG, "loadWeatherData: " + usersPreferredLocation);
        ConnectToNetwork connectToNetwork = new ConnectToNetwork();
        connectToNetwork.execute(NetworkUtils.buildUrl(usersPreferredLocation));
    }

    // ok (5) Create a class that extends AsyncTask to perform network requests
    public class ConnectToNetwork extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            // ok (6) Override the doInBackground method to perform your network requests
            String results = null;
            Log.d(TAG, "doInBackground: "+urls.length);
            for (URL url: urls) {
                try {
                    results = NetworkUtils.getResponseFromHttpUrl(url);
                    Log.i(TAG, "doInBackground: results=" + results);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    return results;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // ok (7) Override the onPostExecute method to display the results of the network request
            mWeatherTextView.setText(s);
        }

    }
}