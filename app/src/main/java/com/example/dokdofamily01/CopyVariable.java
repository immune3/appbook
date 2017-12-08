package com.example.dokdofamily01;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.ssomai.android.scalablelayout.ScalableLayout;

/**
 * Created by mapl0 on 2017-12-04.
 */

public class CopyVariable extends Activity {


    private android.widget.ImageView ivCave15;
    private android.widget.ImageView ivLand15;
    private android.widget.ImageView ivBuyl15;
    private android.widget.ImageView manImage1;
    private android.widget.ImageView manImage2;
    private android.widget.ImageView manImage3;
    private android.widget.ImageView manImage4;
    private android.widget.ImageView seaweadImage;
    private android.widget.ImageView fish;
    private com.ssomai.android.scalablelayout.ScalableLayout sl;
    private CustomScrollView sv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tale15);
        this.sv = (CustomScrollView) findViewById(R.id.sv);
        this.sl = (ScalableLayout) findViewById(R.id.sl);
        this.fish = (ImageView) findViewById(R.id.fish);
        this.seaweadImage = (ImageView) findViewById(R.id.seaweadImage);
        this.manImage4 = (ImageView) findViewById(R.id.manImage4);
        this.manImage3 = (ImageView) findViewById(R.id.manImage3);
        this.manImage2 = (ImageView) findViewById(R.id.manImage2);
        this.manImage1 = (ImageView) findViewById(R.id.manImage1);
        this.ivBuyl15 = (ImageView) findViewById(R.id.ivByul15);
        this.ivLand15 = (ImageView) findViewById(R.id.ivLand15);
        this.ivCave15 = (ImageView) findViewById(R.id.ivCave15);


    }
}
