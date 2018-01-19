package com.example.dokdofamily01;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ssomai.android.scalablelayout.ScalableLayout;

public class MainActivity extends BaseActivity {
    static Context context;
    Button taleBtn, menualBtn;
    boolean splashFlag=true;
    private com.ssomai.android.scalablelayout.ScalableLayout sl;
    private CustomScrollView sv;
    RelativeLayout rl;
    CustomHorizontalScrollView hv;
    private android.widget.Button prologueBtn;
    MediaPlayer titleBgm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

//        if(splashFlag) {
//            splashFlag=false;
//            startActivity(new Intent(this,SplashActivity.class));
//        }

        bindViews();
        setValues();
        setUpEvents();

    }

    @Override
    protected void onResume() {
        super.onResume();
        playBgm();
        scrollCenter();
    }

    @Override
    protected void onPause() {
        super.onPause();
        titleBgm.release();
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();
        scrollCenter();
        taleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TaleActivity.class);
                intent.putExtra("isAutoRead", true);
                startActivity(intent);
                titleBgm.release();
            }
        });

        menualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TaleActivity.class);
                intent.putExtra("isAutoRead", false);
                startActivity(intent);
                titleBgm.release();
            }
        });

        prologueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, IntroActivity.class);
//                startActivity(intent);
//                titleBgm.release();
            }
        });

    }

    @Override
    public void setValues() {
        super.setValues();
        prologueBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        sv = (CustomScrollView) findViewById(R.id.sv);
        rl = (RelativeLayout)findViewById(R.id.rl);
        sl = (ScalableLayout)findViewById(R.id.sl);
        hv = new CustomHorizontalScrollView(this);
        HorizontalScrollView.LayoutParams lp = new HorizontalScrollView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        hv.setLayoutParams(lp);

        taleBtn = (Button)findViewById(R.id.taleBtn);
        menualBtn = (Button)findViewById(R.id.menualBtn);
        prologueBtn = (Button) findViewById(R.id.prologueBtn);

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
                            rl.setVisibility(View.VISIBLE);

                        }
                    });

                }else{
                    int innerHeight = sl.getHeight();
                    sv.scrollTo(0,(innerHeight-deviceHeight)/2);
                    Log.e("innerHeight", innerHeight + "");
                    sv.setScrolling(false);
                    rl.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void playBgm(){
        titleBgm = MediaPlayer.create(context, R.raw.title_bgm);
        titleBgm.setVolume(1f, 1f);
        titleBgm.start();
        titleBgm.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                titleBgm.start();
            }
        });
    }

    private long pressedTime;

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if(pressedTime == 0){
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();
        }else{
            int second = (int)(System.currentTimeMillis() - pressedTime);

            if(second > 2000){
                pressedTime = 0;
            }else{
                finish();
            }
        }


    }
}
