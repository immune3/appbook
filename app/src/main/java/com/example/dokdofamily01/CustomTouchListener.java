package com.example.dokdofamily01;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static com.example.dokdofamily01.TaleActivity.MIN_DISTANCE;
import static com.example.dokdofamily01.TaleActivity.checkedAnimation;


/**
 * Created by hero on 2017-12-11.
 */

public class CustomTouchListener implements View.OnTouchListener {
    private float x1 = 0;
    private float x2 = 0;
    float deltaX = x2 - x1;

    private int checkDistance = 0;

    CustomViewPager customViewPager;

    public interface AsyncResponse {
        void onAction(MotionEvent motionEvent, int checkDistance);
    }

    public AsyncResponse delegate = null;

    public CustomTouchListener(){
        delegate = new AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistance) {

            }
        };
    };

    public CustomTouchListener(AsyncResponse delegate) {
        this.delegate = delegate;
    }

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
                        checkDistance = -1;
                        decreaseFunc();
                    }
                    else if(x2<x1) {
                        checkDistance = 1;
                        increaseFunc();
                    }
                }
                else
                {
                    Log.i("position", "short");
                    checkDistance = 0;
                    animationFunc();
                }
                break;
        }

        delegate.onAction(motionEvent, checkDistance);

        return true;
    }

    public int checkDistance() {
        return checkDistance;
    }

    public void decreaseFunc(){
        if(checkedAnimation) {
            customViewPager.setCurrentItem(customViewPager.getCurrentItem()-1, true);
            Log.d("decreaseFunc", "if");
        } else Log.d("decreaseFunc", "else");

    }

    public void increaseFunc(){

        if(checkedAnimation) {
            customViewPager.setCurrentItem(customViewPager.getCurrentItem()+1,true);
            Log.d("increaseFunc", "if.........");
        } else Log.d("increaseFunc", "else");
    }

    public void animationFunc(){

    }
}
