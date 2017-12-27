package com.example.dokdofamily01;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.ssomai.android.scalablelayout.ScalableLayout;

/**
 * Created by mapl0 on 2017-12-27.
 */

public class PrologueActivity extends Activity {

    int deviceHeight;
    int deviceWidth;
    CustomScrollView sv;
    ScalableLayout sl;
    private android.widget.ImageView prologueTextImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prologue);

        bindViews();
        setValue();
        setUpEvents();

    }

    public void setUpEvents() {

        sl.post(new Runnable() {
            @Override
            public void run() {
                int innerWidth = sl.getWidth();
                int innerHeight = sl.getHeight();
                Log.e("length", "" + innerHeight);
                Log.e("length", "" + deviceHeight);
                sv.scrollTo(0, (innerHeight - deviceHeight) / 2);
//                if(innerWidth>deviceWidth){
//                    sv.scrollTo((innerWidth-deviceWidth)/2,0);
//                }else{
//                    sv.scrollTo(0,(innerHeight-deviceHeight)/2);
//                }
            }
        });
        sv.setScrolling(false);

        prologueTextImage.setImageResource(R.drawable.prologue_text_01);

        new Handler().post(new Runnable() {
            @Override
            public void run() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        prologueTextImage.setImageResource(R.drawable.prologue_text_02);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                prologueTextImage.setImageResource(R.drawable.prologue_text_03);
                            }
                        }, 6000);

                    }
                }, 4000);

            }
        });

    }

    public void bindViews() {
        this.sv = (CustomScrollView) findViewById(R.id.sv);
        this.sl = (ScalableLayout) findViewById(R.id.sl);
        this.prologueTextImage = (ImageView) findViewById(R.id.prologueTextImage);
    }

    public void setValue() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        deviceHeight = displayMetrics.heightPixels;
        deviceWidth = displayMetrics.widthPixels;

        sv = (CustomScrollView) findViewById(R.id.sv);
        sl = (ScalableLayout) findViewById(R.id.sl);
    }
}
