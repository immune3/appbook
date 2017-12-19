package com.example.dokdofamily01;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by heronation on 2017-11-16.
 */

public class CustomViewPager extends ViewPager {
    private boolean isPageScrollEnabled = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev) && this.isPageScrollEnabled;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev) && this.isPageScrollEnabled;
        return false;
    }
    public void setPageScrollEnabled(boolean isPageScrollEnabled) {
        this.isPageScrollEnabled = isPageScrollEnabled;
    }


}
