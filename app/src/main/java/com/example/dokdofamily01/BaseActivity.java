package com.example.dokdofamily01;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

import com.ssomai.android.scalablelayout.ScalableLayout;

/**
 * Created by mapl0 on 2018-01-05.
 */

public class BaseActivity extends Activity {

    public static Context context;

    ScalableLayout sl;
    CustomScrollView sv;
    RelativeLayout rl;
    CustomHorizontalScrollView hv;

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
        scrollCenter();
    }

    public void setValues() {

    }

    public void bindViews() {
        sv = (CustomScrollView) findViewById(R.id.sv);
        rl = (RelativeLayout)findViewById(R.id.rl);
        sl = (ScalableLayout)findViewById(R.id.sl);
        hv = new CustomHorizontalScrollView(this);
        HorizontalScrollView.LayoutParams lp = new HorizontalScrollView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        hv.setLayoutParams(lp);
    }

    public void scrollCenter(){
        sl.post(new Runnable() {
            @Override
            public void run() {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                final int deviceWidth= displayMetrics.widthPixels;
                int deviceHeight = displayMetrics.heightPixels;
                Log.d("slWidth", sl.getWidth() + "");

                float ratio = (float)deviceWidth/(float)deviceHeight;
                Log.e("ratio", ""+ratio);
                if(ratio<=1.66){

                    sv.removeView(sl);
                    rl.removeView(sv);
                    hv.addView(sl);
                    rl.addView(hv);

                    hv.post(new Runnable() {
                        @Override
                        public void run() {
                            int innerWidth = hv.getChildAt(0).getWidth();
                            Log.e("innerWidth", ""+innerWidth);
                            hv.scrollTo((innerWidth-deviceWidth)/2,0);
                            hv.setScrolling(false);

                        }
                    });

                }else{
                    int innerHeight = sl.getHeight();
                    sv.scrollTo(0,(innerHeight-deviceHeight)/2);
                    Log.e("innerHeight", innerHeight + "");
                    sv.setScrolling(false);

                }
            }
        });

    }

}
