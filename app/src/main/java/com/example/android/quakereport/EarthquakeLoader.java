package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earth>> {
    private String mUrl;

    public EarthquakeLoader(Context context, String Url){
        super(context);
        mUrl = Url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    public List<Earth> loadInBackground(){
        if(mUrl==null){
            return null;
        }
        List<Earth> earths = QueryUtils.fetchEarthquakeData(mUrl);
        return earths;
    }
}
