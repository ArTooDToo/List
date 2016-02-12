package com.padwandroid.softax_1_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {


    private ArrayList<Item> objects;


    public ItemAdapter(Context context, int textViewResourceId, ArrayList<Item> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }


    public View getView(int position, View convertView, ViewGroup parent){


        View v = convertView;


        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
        }


        Item i = objects.get(position);

        if (i != null) {


            TextView ttd = (TextView) v.findViewById(R.id.toptextdata);


            if (ttd != null){
                ttd.setText(i.getName() + i.getLiczba());
                ttd.setBackgroundColor(i.getColor());
            }
        }

        return v;

    }

}
