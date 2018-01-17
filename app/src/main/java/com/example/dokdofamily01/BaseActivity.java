package com.example.dokdofamily01;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

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
//        setUpEvents();    ``

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
