package com.example.dokdofamily01;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import static com.example.dokdofamily01.TaleActivity.MIN_DISTANCE;
import static com.example.dokdofamily01.TaleActivity.checkedAnimation;


/**
 * Created by hero on 2017-12-11.
 */

public class CustomTouchListener implements View.OnTouchListener {
    private float x1 = 0;
    private float x2 = 0;
    private float y1 = 0;
    private float y2 = 0;

    float delta = 0;

    private int checkDistanceX = 0;
    private int checkDistanceY = 0;
    private float diff = 0;
    Context mContext;
    CustomViewPager customViewPager;

    public interface AsyncResponse {
        void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff);
    }

    public AsyncResponse delegate = null;

    public CustomTouchListener(){


        delegate = new AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {

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
                y1 = motionEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = motionEvent.getX();
                y2 = motionEvent.getY();
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;
                delta = Math.abs(deltaX) - Math.abs(deltaY);
                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    if(x2>x1) {
                        checkDistanceX = -1;
                        decreaseFunc();
                    }
                    else if(x2<x1) {
                        checkDistanceX = 1;
                        increaseFunc();
                    }

                } else
                {
                    Log.i("position", "short");
                    checkDistanceX = 0;
                    animationFunc();
                }

                if(Math.abs(deltaY) > MIN_DISTANCE) {
                    if(y2<y1) {
                        checkDistanceY = 1;
                    }
                    else if(y2>y1) {
                        checkDistanceY = -1;
                    }
                } else {
                    Log.i("position", "short");
                    checkDistanceY = 0;
                    animationFunc();
                }
                break;
        }

        if(delta > 0) diff = 1;
        else if(delta < 0) diff = -1;
        else diff = 0;

        delegate.onAction(motionEvent, checkDistanceX, checkDistanceY, diff);

        return true;
    }

    public int checkDistance() {
        return checkDistanceX;
    }

    public void decreaseFunc(){
        if(checkedAnimation) {
            if(customViewPager.getCurrentItem() == 0){
                Toast.makeText(mContext, "첫번째 페이지입니다.", Toast.LENGTH_SHORT).show();
            }else{
                customViewPager.setCurrentItem(customViewPager.getCurrentItem()-1, true);
                Log.d("decreaseFunc", "if");
            }

        } else Log.d("decreaseFunc", "else");

    }

    public void increaseFunc(){
        Log.d("currentItem",customViewPager.getCurrentItem() +"");
        if(checkedAnimation) {
            if(customViewPager.getCurrentItem() == 20){
                Toast.makeText(mContext, "마지막 페이지입니다.", Toast.LENGTH_SHORT).show();
            }else{
                customViewPager.setCurrentItem(customViewPager.getCurrentItem()+1,true);
                Log.d("increaseFunc", "if.........");
            }

        } else Log.d("increaseFunc", "else");
    }

    public void animationFunc(){

    }
}
