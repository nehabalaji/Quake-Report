package com.example.android.quakereport;

public class Earth {

    private String mMagnitude;
    private String mPlace;
    private String mDate;

    public Earth(String Magnitude, String Place, String Date) {
        mMagnitude = Magnitude;
        mPlace = Place;
        mDate = Date;
    }

    public String getmMagnitude() {
        return mMagnitude;
    }

    public String getmPlace() {
        return mPlace;
    }

    public String getmDate() {
        return mDate;
    }
}


