package com.example.dokdofamily01;

import android.content.Context;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;

import android.view.MotionEvent;

/**
 * Created by heronation on 2017-11-16.
 */

public class CustomViewPager extends ViewPager {

    public static boolean isPageScrollEnabled = true;
    GestureDetector gd = new GestureDetector(new GestureDetector.SimpleOnGestureListener());


    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                gd.onTouchEvent(ev);
                break;
        }

        return true;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (MotionEventCompat.getActionMasked(ev) == MotionEvent.ACTION_MOVE) {
// ignore move action
        } else {
            if (super.onInterceptTouchEvent(ev)) {
                super.onTouchEvent(ev);
            }
        }
        return false;

    }

    public void setPageScrollEnabled(boolean isPageScrollEnabled) {
        this.isPageScrollEnabled = isPageScrollEnabled;
    }
}
