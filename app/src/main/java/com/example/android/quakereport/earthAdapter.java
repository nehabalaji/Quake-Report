package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class earthAdapter extends ArrayAdapter<Earth> {

    public earthAdapter(@NonNull Context context, @NonNull List<Earth> objects) {
        super(context, 0, objects);
    }

    private static final String LOCATION_SEPERATOR = "of";

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Earth earth = getItem(position);



        TextView magnitude = (TextView) listItemView.findViewById(R.id.mag);
        String formattedMagnitude = formatMagnitude(earth.getmMagnitude());
        magnitude.setText(formattedMagnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(earth.getmMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

                String OriginalLOcation = earth.getmPlace();
        String primaryLocation;
        String locationOffSet;

        if(OriginalLOcation.contains(LOCATION_SEPERATOR)){
            String[] parts = OriginalLOcation.split(LOCATION_SEPERATOR);
            locationOffSet = parts[0] + LOCATION_SEPERATOR;
            primaryLocation = parts[1];

        }
        else
        {
            locationOffSet = getContext().getResources().getString(R.string.near_the);
            primaryLocation = OriginalLOcation;
        }


        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locstionOffSetView = (TextView) listItemView.findViewById(R.id.location_offset);
        locstionOffSetView.setText(locationOffSet);




        Date dateObject = new Date(earth.getmTimeInMilliSeconds());
        TextView dateView = (TextView)listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);

        return listItemView;



    }

    private int getMagnitudeColor(double magnitudecolor) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitudecolor);
        switch (magnitudeFloor){
            case 0:
            case 1:  magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }




    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);

    }


}
