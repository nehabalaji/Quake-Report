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

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity
implements LoaderManager.LoaderCallbacks<List<Earth>> {
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private earthAdapter mAdapter;
    private static final int EARTHQUAKE_LOADER_ID = 1;



    @Override
    public Loader<List<Earth>> onCreateLoader(int i, Bundle bundle) {
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earth>> loader, List<Earth> earths) {
        mEmptyStateTextView.setText(R.string.no_earthquake);
        View loadingIndicator = findViewById(R.id.loading_spinner);
        loadingIndicator.setVisibility(View.GONE);
        mAdapter.clear();
        if(earths != null && !earths.isEmpty()){
            mAdapter.addAll(earths);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earth>> loader) {
        mAdapter.clear();
    }

    private TextView mEmptyStateTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);




        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new earthAdapter(this, new ArrayList<Earth>());

        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Earth earth = mAdapter.getItem(i);
                Uri earthquakeUri = Uri.parse(earth.getmURL());
                Intent WebsiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(WebsiteIntent);
            }
        });

      ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

      if(networkInfo!=null && networkInfo.isConnected()){
          LoaderManager loaderManager = getLoaderManager();
          loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
      }
      else
      {
          View loadingIndicator = findViewById(R.id.loading_spinner);
          loadingIndicator.setVisibility(View.GONE);
          mEmptyStateTextView.setText(R.string.no_internet);
      }




    }



}
