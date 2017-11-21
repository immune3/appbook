package com.example.dokdofamily01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ssomai.android.scalablelayout.ScalableLayout;

import static android.content.ContentValues.TAG;
import static java.lang.Integer.parseInt;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale01 extends BaseFragment{
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale01;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
    }

    @Override
    public void setValues() {
        super.setValues();
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
    }

}
