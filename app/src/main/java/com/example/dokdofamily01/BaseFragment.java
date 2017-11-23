package com.example.dokdofamily01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import android.widget.RelativeLayout;

import com.ssomai.android.scalablelayout.ScalableLayout;

/**
 * Created by heronation on 2017-11-17.
 */

public class BaseFragment extends Fragment{
    CustomScrollView sv;
    ScalableLayout sl;
    public RelativeLayout layout;
    public int xml = 0;
    static public int firstFlag = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = (RelativeLayout) inflater.inflate(xml,container, false);

        sv = (CustomScrollView)layout.findViewById(R.id.sv);
        sl = (ScalableLayout)layout.findViewById(R.id.sl);
        sl.post(new Runnable() {
            @Override
            public void run() {
                int deviceWidth = TaleActivity.width;
                int deviceHeight = TaleActivity.height;
                int innerWidth = sl.getWidth();
                int innerHeight = sl.getHeight();
                if(innerWidth>deviceWidth){
                    sv.scrollTo((innerWidth-deviceWidth)/2,0);
                }else{
                    sv.scrollTo(0,(innerHeight-deviceHeight)/2);
                }
            }
        });
        sv.setScrolling(false);
        bindViews();
        setValues();
        setAnimation();
        setupEvents();
        return layout;
    }

    public void bindViews(){

    }

    public void setValues(){

    }

    public void setAnimation(){

    }

    public void setupEvents(){

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
        }else{
            System.gc();
        }
    }
}
