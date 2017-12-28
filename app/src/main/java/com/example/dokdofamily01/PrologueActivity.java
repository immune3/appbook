package com.example.dokdofamily01;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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

    AlphaAnimation fadeIn;
    AlphaAnimation fadeOut;
    int animationFlag = 1;

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

        setAnimation();

    }

    public void setAnimation() {

        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(1000);
        fadeIn.setStartOffset(1000);
        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new MyAnimationListener());

        fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setStartOffset(4000);
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new MyAnimationListener());

        new Handler().post(new Runnable() {
            @Override
            public void run() {
//                prologueTextImage.setVisibility(View.INVISIBLE);
                prologueTextImage.setImageResource(R.drawable.prologue_text_01);
                animationFlag = 1;
                prologueTextImage.startAnimation(fadeIn);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        prologueTextImage.startAnimation(fadeOut);

                    }
                }, 4000);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                prologueTextImage.clearAnimation();
//                prologueTextImage.setVisibility(View.INVISIBLE);
                prologueTextImage.setImageResource(R.drawable.prologue_text_02);
                prologueTextImage.startAnimation(fadeIn);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fadeOut.setStartOffset(5000);
                        prologueTextImage.startAnimation(fadeOut);

                    }
                }, 5000);
            }
        }, 9000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                prologueTextImage.clearAnimation();
//                prologueTextImage.setVisibility(View.INVISIBLE);
                prologueTextImage.setImageResource(R.drawable.prologue_text_03);
                prologueTextImage.startAnimation(fadeIn);

            }
        }, 20000);

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

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag) {
                case 1:
                    animationFlag = 2;
//                    prologueTextImage.setAnimation(fadeIn);

                    break;
                case 2:
                    animationFlag = 1;
//                    prologueTextImage.setAnimation(fadeOut);

                    break;

            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }
    }

}


