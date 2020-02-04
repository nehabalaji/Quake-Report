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
package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<Earth> earthquakes = new ArrayList<>();
        earthquakes.add(new Earth("7.2", "San Fransisco", "Feb 2,2016"));
        earthquakes.add(new Earth("6.1", "London", "Feb 2,2016"));
        earthquakes.add(new Earth("3.9", "Tokyo", "Feb 2,2016"));
        earthquakes.add(new Earth("5.4", "Mexico City", "Feb 2,2016"));
        earthquakes.add(new Earth("2.8", "Moscow", "Feb 2,2016"));
        earthquakes.add(new Earth("4.9", "Rio de Janeiro", "Feb 2,2016"));
        earthquakes.add(new Earth("1.6", "Paris", "Feb 2,2016"));

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        earthAdapter earthAdapter = new earthAdapter(this, earthquakes);
        earthquakeListView.setAdapter(earthAdapter);


    }
}
