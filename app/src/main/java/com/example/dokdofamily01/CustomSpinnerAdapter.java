package com.example.dokdofamily01;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by heronation on 2018-01-11.
 */

public class CustomSpinnerAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<String> data;
    private LayoutInflater inflater;
    private LinearLayout llWrapper;

    public CustomSpinnerAdapter(Context context, ArrayList<String> data){
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(data != null) return data.size();
        else return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = inflater.inflate(R.layout.spinner_normal_view, parent, false);
        }

        if(data!=null){
            //데이터세팅
            String text = data.get(position);
            ((TextView)convertView.findViewById(R.id.spinnerText)).setText(text);
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = inflater.inflate(R.layout.spinner_dropdown_view, parent, false);
//            convertView.setPadding(convertView.getPaddingLeft(),10,convertView.getPaddingRight(),10);
        }
        if(data!=null){
//            llWrapper = (LinearLayout) convertView.findViewById(R.id.ll_wrapper);
//
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT
//            );
//
//            if(position == data.size() - 1){
//                params.setMargins(10,0,10,10);
//                llWrapper.setLayoutParams(params);
//            }
//            else if(position == 0){
//                params.setMargins(10,10,10,0);
//                llWrapper.setLayoutParams(params);
//            }
//            else{
//                params.setMargins(10,0,10,0);
//                llWrapper.setLayoutParams(params);
//            }

            //데이터세팅
            String text = data.get(position);
            String ImageName = "@drawable/spinner_item_"+data.get(position);
            Log.d("ImageName", ImageName+"/");
            int imageID =context.getResources().getIdentifier(ImageName, "drawable", context.getPackageName());

            ((ImageView)convertView.findViewById(R.id.spinnerImage)).setImageResource(imageID);
        }

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
