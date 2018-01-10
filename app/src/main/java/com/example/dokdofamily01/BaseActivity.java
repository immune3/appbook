package com.example.dokdofamily01;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

import com.ssomai.android.scalablelayout.ScalableLayout;

/**
 * Created by mapl0 on 2018-01-05.
 */

public class BaseActivity extends Activity {

    public static Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

//        bindViews();
//        setValues();
//        setAnimation();
//        setUpEvents();

    }

    public void setAnimation() {

    }

    public void setUpEvents() {
    }

    public void setValues() {
    }

    public void bindViews() {
    }


}
