package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;


/**
 * Created by heronation on 2017-11-06.
 */

public class Tale03 extends BaseFragment {

    ImageView[] cloud = new ImageView[6];
    ImageView byulHand;
    ImageView[] wing = new ImageView[4];
    ImageView blinkStar;
    ImageView head;
    ImageView body;
    Boolean isAuto;

    Animation fadein;
    AlphaAnimation blink;
    AlphaAnimation wingAppear1;
    AlphaAnimation wingAppear2;
    TranslateAnimation[] cloudAnimation = new TranslateAnimation[5];
    int animationFlag = 0;
    int repeatFlag=0;
    int headHeight=0;

    SoundPool sp;
    SoundPool wingSp;
    int soundID;

    private Handler postSoundHandler;
    private Runnable postSoundRun;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale03;

        subtitleTextView.setText(null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }




    @Override
    public void bindViews() {
        super.bindViews();
        cloud[0] = (ImageView) layout.findViewById(R.id.cloud0);
        cloud[1] = (ImageView) layout.findViewById(R.id.cloud1);
        cloud[2] = (ImageView) layout.findViewById(R.id.cloud2);
        cloud[3] = (ImageView) layout.findViewById(R.id.cloud3);
        cloud[4] = (ImageView) layout.findViewById(R.id.cloud4);
        cloud[5] = (ImageView) layout.findViewById(R.id.cloud5);
        byulHand = (ImageView) layout.findViewById(R.id.byulHand);
        wing[0] = (ImageView)layout.findViewById(R.id.wingLeft1);
        wing[1] = (ImageView)layout.findViewById(R.id.wingLeft2);
        wing[2] = (ImageView)layout.findViewById(R.id.wingRight1);
        wing[3] = (ImageView)layout.findViewById(R.id.wingRight2);
        blinkStar = (ImageView)layout.findViewById(R.id.blinkStar);
        head = (ImageView)layout.findViewById(R.id.head);
        body = (ImageView)layout.findViewById(R.id.body);
    }

    @Override
    public void setValues() {
        super.setValues();

    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadein = new AlphaAnimation(0, 1);
        fadein.setDuration(1000);

        blink = new AlphaAnimation(0.3f, 1);
        blink.setDuration(500);
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        blinkStar.setOnTouchListener(new BlockObjListener());



    }

    @Override
    public void blockAnimFunc() {
        if (animationFlag == 0) {
            checkedAnimation = false;
            animationFlag = 1;
            repeatFlag = 0;
            blinkStar.clearAnimation();
            wing[1].clearAnimation();
            blinkStar.startAnimation(wingAppear2);
            soundID = wingSp.load(getContext(),R.raw.effect_03_wings,1);
//            soundsPool.play(wings,1,1,1,1,1);
        }
        super.blockAnimFunc();
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            if (animationFlag == 1) {
                animationFlag = 0;
                byulHand.setVisibility(View.VISIBLE);
                byulHand.startAnimation(fadein);
                blinkStar.startAnimation(blink);
                checkedAnimation = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        @Override
        public void onAnimationStart(Animation animation) {
            byulHand.setVisibility(View.INVISIBLE);
            if(animationFlag==1){
                cloud[0].setVisibility(View.VISIBLE);
                cloud[1].setVisibility(View.VISIBLE);
                cloud[2].setVisibility(View.VISIBLE);
                cloud[3].setVisibility(View.VISIBLE);
                cloud[4].setVisibility(View.VISIBLE);
                cloud[5].setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void soundPlayFunc() {

        this.isAuto = getArguments().getBoolean("isAuto");

        if(isAuto) {
            musicController = new MusicController(getActivity(), R.raw.scene_3, vp,
                    new int[]{R.drawable.sub_03_01, 4000},
                    new int[]{R.drawable.sub_03_02, 12500},
                    new int[]{R.drawable.sub_03_03, 20000},
                    new int[]{R.drawable.sub_03_04, 23500},
                    new int[]{R.drawable.sub_03_05, 31000});
        } else {
            subtitleController = new SubtitleController(vp,
                    R.drawable.sub_03_01,
                    R.drawable.sub_03_02,
                    R.drawable.sub_03_03,
                    R.drawable.sub_03_04,
                    R.drawable.sub_03_05);
        }

        byulHand.post(new Runnable() {
            @Override
            public void run() {
                wingSp = new SoundPool(1, AudioManager.STREAM_MUSIC,0);

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1){
                    //사용가능한 버전이 아님
                    wingSp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                        @Override
                        public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                            wingSp.play(soundID, 1, 1, 1, 2, 1);
                        }
                    });

                }
                else{
                    //사용가능한 버전
                    wingSp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                        @Override
                        public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                            wingSp.play(soundID, 1, 1, 1, 1, 1);
                        }
                    });

                }

                sp = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
                sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        sp.play(soundID, 1, 1, 1, 0, 1);
                    }
                });

                // 왼쪽 위 구름(cloud[0])
                headHeight = (int)(head.getHeight()*0.2);

                cloudAnimation[0] = new TranslateAnimation(-cloud[0].getWidth(), 0, -cloud[0].getHeight(), 0);
                cloudAnimation[0].setDuration(3000);
                cloudAnimation[0].setFillAfter(true);
                cloudAnimation[0].setInterpolator(new AccelerateDecelerateInterpolator());

                // 왼쪽 아래 구름(cloud[1], cloud[2])
                cloudAnimation[1] = new TranslateAnimation(-cloud[2].getWidth(), 0, cloud[2].getHeight(), 0);
                cloudAnimation[1].setStartOffset(500);
                cloudAnimation[1].setDuration(2500);
                cloudAnimation[1].setFillAfter(true);
                cloudAnimation[1].setInterpolator(new AccelerateDecelerateInterpolator());

                // 중앙 위 구름(cloud[3])
                cloudAnimation[2] = new TranslateAnimation(0, 0, -cloud[3].getHeight()*1.3f, 0);
                cloudAnimation[2].setStartOffset(2000);
                cloudAnimation[2].setDuration(2000);
                cloudAnimation[2].setFillAfter(true);
                cloudAnimation[2].setAnimationListener(new MyAnimationListener());
                cloudAnimation[2].setInterpolator(new AccelerateDecelerateInterpolator());

                // 오른쪽 아래 구름(cloud[4])
                cloudAnimation[3] = new TranslateAnimation(cloud[4].getWidth(), 0, 0, 0);
                cloudAnimation[3].setStartOffset(500);
                cloudAnimation[3].setDuration(2000);
                cloudAnimation[3].setFillAfter(true);
                cloudAnimation[3].setInterpolator(new AccelerateDecelerateInterpolator());

                // 오른쪽 위 구름(cloud[5])
                cloudAnimation[4] = new TranslateAnimation(cloud[5].getWidth(), 0, -cloud[5].getHeight(), 0);
                cloudAnimation[4].setStartOffset(1500);
                cloudAnimation[4].setDuration(2000);
                cloudAnimation[4].setFillAfter(true);
                cloudAnimation[4].setInterpolator(new AccelerateDecelerateInterpolator());

                wingAppear1 = new AlphaAnimation(1, 0);
                wingAppear1.setDuration(400);
                wingAppear1.setRepeatCount(7);
                wingAppear1.setRepeatMode(Animation.REVERSE);

                wingAppear2 = new AlphaAnimation(1, 1);
                wingAppear2.setDuration(400);
                wingAppear2.setRepeatCount(5);
                wingAppear2.setRepeatMode(Animation.REVERSE);
                wingAppear2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        byulHand.setVisibility(View.INVISIBLE);
                        head.setTranslationY(headHeight);
                        body.setTranslationY(headHeight);
                        wing[0].setVisibility(View.INVISIBLE);
                        wing[1].setVisibility(View.VISIBLE);
                        wing[2].setVisibility(View.INVISIBLE);
                        wing[3].setVisibility(View.VISIBLE);
                        wing[1].setTranslationY(headHeight);
                        wing[3].setTranslationY(headHeight);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animationFlag=0;
                        head.setTranslationY(0);
                        body.setTranslationY(0);
                        wing[0].setVisibility(View.VISIBLE);
                        wing[1].setVisibility(View.INVISIBLE);
                        wing[2].setVisibility(View.VISIBLE);
                        wing[3].setVisibility(View.INVISIBLE);
                        byulHand.setVisibility(View.VISIBLE);
                        byulHand.startAnimation(fadein);
                        blinkStar.startAnimation(blink);
                        checkedAnimation = true;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        if(repeatFlag==0){
                            repeatFlag=1;
                            wing[0].setVisibility(View.VISIBLE);
                            wing[1].setVisibility(View.INVISIBLE);
                            wing[2].setVisibility(View.VISIBLE);
                            wing[3].setVisibility(View.INVISIBLE);
                            head.setTranslationY(0);
                            body.setTranslationY(0);
                        }else{
                            repeatFlag=0;
                            wing[0].setVisibility(View.INVISIBLE);
                            wing[1].setVisibility(View.VISIBLE);
                            wing[2].setVisibility(View.INVISIBLE);
                            wing[3].setVisibility(View.VISIBLE);
                            head.setTranslationY(headHeight);
                            body.setTranslationY(headHeight);
                        }
                    }
                });

                animationClear();
                checkedAnimation = false;
                animationFlag = 1;

                new AsyncTask<Void, Void, Integer>(){
                    int sID;
                    @Override
                    protected Integer doInBackground(Void... voids) {
                        sID = R.raw.effect_03_clouds;
                        return sID;
                    }

                    @Override
                    protected void onPostExecute(Integer integer) {
                        super.onPostExecute(integer);
                        try {
                            soundID = sp.load(getContext(),integer,1);
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                            doInBackground();

                        }
                    }
                }.execute();


                cloud[0].startAnimation(cloudAnimation[0]);
                cloud[1].startAnimation(cloudAnimation[1]);
                cloud[2].startAnimation(cloudAnimation[1]);
                cloud[3].startAnimation(cloudAnimation[2]);
                cloud[4].startAnimation(cloudAnimation[3]);
                cloud[5].startAnimation(cloudAnimation[4]);
            }
        });
    }

    private void animationClear() {
        animationFlag=0;
        byulHand.setVisibility(View.INVISIBLE);
        blinkStar.setVisibility(View.INVISIBLE);
        blinkStar.clearAnimation();
        wing[1].clearAnimation();
        cloud[0].clearAnimation();
        cloud[1].clearAnimation();
        cloud[2].clearAnimation();
        cloud[3].clearAnimation();
        cloud[4].clearAnimation();
        cloud[5].clearAnimation();

        cloud[0].setVisibility(View.INVISIBLE);
        cloud[1].setVisibility(View.INVISIBLE);
        cloud[2].setVisibility(View.INVISIBLE);
        cloud[3].setVisibility(View.INVISIBLE);
        cloud[4].setVisibility(View.INVISIBLE);
        cloud[5].setVisibility(View.INVISIBLE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isVisibleToUser){
            if(wingSp != null){
                wingSp.release();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (postSoundHandler != null && postSoundRun != null) {
            postSoundHandler.removeCallbacks(postSoundRun);
            postSoundHandler = null;
        }
    }
}
