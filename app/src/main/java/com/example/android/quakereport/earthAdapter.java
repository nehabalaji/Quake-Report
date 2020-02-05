package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        magnitude.setText(earth.getmMagnitude());

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

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);

    }


}
