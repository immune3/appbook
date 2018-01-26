package com.example.dokdofamily01;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

import com.example.dokdofamily01.DB.LocalDB;
import com.ssomai.android.scalablelayout.ScalableLayout;

/**
 * Created by heronation on 2017-12-01.
 */

public class SplashActivity extends BaseActivity {

    LocalDB db;
    String[][] isFirstArray;
    int isFirst;

    ScalableLayout sl;
    CustomScrollView sv;
    RelativeLayout rl;
    CustomHorizontalScrollView hv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        bindViews();
        setValues();
        setAnimation();
        setUpEvents();

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    public void setUpEvents() {
        super.setUpEvents();
        scrollCenter();
        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000); // 3초 후 이미지를 닫습니다

    }

    public void setAnimation() {

    }



    public void setValues() {

//        db = new LocalDB(BaseActivity.context);
//        isFirstArray = db.selectQuery("select isFirst from Prologue;");
//        if(isFirstArray == null) {
//            db.query("insert into Prologue values(1);");
//        }
//
//        isFirstArray = db.selectQuery("select isFirst from Prologue;");
//        System.out.println("isFirstArray : " + isFirstArray);
//        Log.d("isFirstArray[0][0]", isFirstArray[0][0]);
//        isFirst = Integer.parseInt(isFirstArray[0][0]);
//        isFirstArray = null; // 메모리 비어줌

    }

    public void bindViews() {
        super.bindViews();

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

                    if(hv.getChildCount()==0) {
                        sv.removeView(sl);
                        rl.removeView(sv);
                        hv.addView(sl);
                        rl.addView(hv);
                    }

                    hv.post(new Runnable() {
                        @Override
                        public void run() {
                            int innerWidth = hv.getChildAt(0).getWidth();
                            Log.e("innerWidth", ""+innerWidth);
                            hv.scrollTo((innerWidth-deviceWidth)/2,0);
                            hv.setScrolling(false);
                            rl.setVisibility(View.VISIBLE);
                            hv.setHorizontalScrollBarEnabled(false);
                        }
                    });

                }else{
                    int innerHeight = sl.getHeight();
                    sv.scrollTo(0,(innerHeight-deviceHeight)/2);
                    Log.e("innerHeight", innerHeight + "");
                    sv.setScrolling(false);
                    rl.setVisibility(View.VISIBLE);
                    sv.setVerticalScrollBarEnabled(false);
                }
            }
        });

    }
}
