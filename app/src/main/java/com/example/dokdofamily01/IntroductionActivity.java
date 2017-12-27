package com.example.dokdofamily01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ssomai.android.scalablelayout.ScalableLayout;

/**
 * Created by heronation on 2017-12-26.
 */

public class IntroductionActivity extends AppCompatActivity {
    CustomScrollView sv;
    ScalableLayout sl;
    Button waveBtn;
    ImageView wave;
    ImageView waveText;
    View mask;

    int deviceHeight;
    int deviceWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        deviceHeight = displayMetrics.heightPixels;
        deviceWidth = displayMetrics.widthPixels;
        sv = (CustomScrollView)findViewById(R.id.sv);
        sl = (ScalableLayout)findViewById(R.id.sl);
        sl.post(new Runnable() {
            @Override
            public void run() {
                int innerWidth = sl.getWidth();
                int innerHeight = sl.getHeight();
                Log.e("length", ""+innerHeight );
                Log.e("length", ""+deviceHeight );
                sv.scrollTo(0,(innerHeight-deviceHeight)/2);
//                if(innerWidth>deviceWidth){
//                    sv.scrollTo((innerWidth-deviceWidth)/2,0);
//                }else{
//                    sv.scrollTo(0,(innerHeight-deviceHeight)/2);
//                }
            }
        });
        sv.setScrolling(false);

        mask = (View)findViewById(R.id.mask);
        waveBtn = (Button) findViewById(R.id.waveBtn);
        wave = (ImageView)findViewById(R.id.wave);
        waveText = (ImageView)findViewById(R.id.waveText);

        mask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wave.setVisibility(View.INVISIBLE);
                waveText.setVisibility(View.INVISIBLE);
                mask.setVisibility(View.INVISIBLE);
            }
        });

        waveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mask.setVisibility(View.VISIBLE);
                wave.setVisibility(View.VISIBLE);
                waveText.setVisibility(View.VISIBLE);
            }
        });

    }
}
