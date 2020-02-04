package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class earthAdapter extends ArrayAdapter<Earth> {

    public earthAdapter(@NonNull Context context, @NonNull List<Earth> objects) {
        super(context, 0, objects);
    }

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

        TextView Place = (TextView) listItemView.findViewById(R.id.place);
        Place.setText(earth.getmPlace());

        TextView Date = (TextView) listItemView.findViewById(R.id.date);
        Date.setText(earth.getmDate());

        return listItemView;
    }

}
