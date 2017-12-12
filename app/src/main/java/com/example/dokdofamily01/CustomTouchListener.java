package com.example.dokdofamily01;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static com.example.dokdofamily01.TaleActivity.MIN_DISTANCE;


/**
 * Created by hero on 2017-12-11.
 */

public class CustomTouchListener implements View.OnTouchListener {
    private float x1,x2;

     CustomViewPager customViewPager;


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = motionEvent.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = motionEvent.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    if(x2>x1) {

                        decreaseFunc();
                    }
                    else if(x2<x1) {

                        increaseFunc();
                    }
                }
                else
                {
                    Log.i("position", "short");
                }
                break;
        }
        return true;
    }

    public void decreaseFunc(){
        customViewPager.setCurrentItem(customViewPager.getCurrentItem()-1);

    }

    public void increaseFunc(){
        customViewPager.setCurrentItem(customViewPager.getCurrentItem()+1);
    }
}
