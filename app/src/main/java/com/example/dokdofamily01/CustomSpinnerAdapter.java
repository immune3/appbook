package com.example.dokdofamily01;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by heronation on 2018-01-11.
 */


public class CustomSpinnerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> data;
    private LayoutInflater inflater;

    public CustomSpinnerAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        if (data != null) return data.size();
        else return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_normal_view, parent, false);
        }

        if (data != null) {
            //데이터세팅
            String text = data.get(position);
            ((TextView) convertView.findViewById(R.id.spinnerText)).setText(text);
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_dropdown_view, parent, false);
        }
        if (data != null) {

            //데이터세팅
            String text = data.get(position);
            String ImageName = "@drawable/spinner_item_" + data.get(position);
            int imageID = context.getResources().getIdentifier(ImageName, "drawable", context.getPackageName());

            ((ImageView) convertView.findViewById(R.id.spinnerImage)).setImageResource(imageID);
        }
        parent.setPadding(0, (int) context.getResources().getDimension(R.dimen.spinnerPadding), 0, (int) context.getResources().getDimension(R.dimen.spinnerPadding));
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
