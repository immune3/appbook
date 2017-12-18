package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.MotionEvent;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale16 extends BaseFragment {
    ImageView moon;
    ImageView bubble;
    ImageView bomb;
    ImageView dokdo_father;
    ImageView dokdo_mom;
    ImageView wave;

    TranslateAnimation moonAppearAnimation;
    TranslateAnimation dokdoFatherAppearAnimation;
    TranslateAnimation dokdoMomAppearAnimation;
    TranslateAnimation waveAppearAnimation;
    TranslateAnimation wavingAnimation;
    ScaleAnimation bubbleScaleAni;
    ScaleAnimation bubbleScaleAni2;
    ScaleAnimation bubbleBombScaleAni;
    AlphaAnimation blink;
    AlphaAnimation fadein;
    AlphaAnimation fadeout;
    AlphaAnimation bubbleFadein;
    AlphaAnimation bombFadeout;
//    AnimationSet bubbleAniSet = new AnimationSet(false);
    AnimationSet bubbleBombAniSet = new AnimationSet(false);

    int animationFlag = 0;
    int bubbleSP=0;
    MediaPlayer mp = null;
    ArrayList<SubTitleData> subtitleList;

    SoundPool sp;
    int bubbleEffect;
    int bumbEffect;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale16;

        subtitleTextView.setText(null);


        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public void bindViews() {
        super.bindViews();
        moon = (ImageView) layout.findViewById(R.id.moon);
        bubble = (ImageView) layout.findViewById(R.id.bubble);
        bomb = (ImageView) layout.findViewById(R.id.bomb);
        dokdo_father = (ImageView) layout.findViewById(R.id.dokdo_father);
        dokdo_mom = (ImageView) layout.findViewById(R.id.dokdo_mom);
        wave = (ImageView) layout.findViewById(R.id.wave);
        sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        bubbleEffect = sp.load(getContext(), R.raw.effect_16_bubble, 1);
        bumbEffect = sp.load(getContext(), R.raw.effect_16_bumb, 2);
    }

    @Override
    public void setValues() {
        super.setValues();

    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadein = new AlphaAnimation(0, 1);
        fadein.setDuration(500);
        fadein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bomb.startAnimation(bombFadeout);
                        bubble.startAnimation(bubbleFadein);
                    }
                }, 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//        fadein.setFillAfter(true);

        fadeout = new AlphaAnimation(1, 0);
        fadeout.setDuration(500);
//        fadeout.setFillAfter(true);

        blink = new AlphaAnimation(1, 0.5f);
        blink.setDuration(1000);
        blink.setInterpolator(new LinearInterpolator());
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);

        bubbleFadein = new AlphaAnimation(0, 1);
        bubbleFadein.setDuration(800);
        bubbleFadein.setFillAfter(true);
        bubbleFadein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                bomb.setVisibility(View.INVISIBLE);
                bubble.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationFlag = 0;
                bubble.startAnimation(bubbleScaleAni);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bombFadeout = new AlphaAnimation(1, 0);
        bombFadeout.setFillAfter(true);
        bombFadeout.setDuration(800);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        moon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch(motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        if(animationFlag == 0) {
                            animationFlag = 1;
                            bomb.setVisibility(View.VISIBLE);
                            bubble.setVisibility(View.INVISIBLE);
                            bubbleScaleAni.reset();
                            bubbleScaleAni2.reset();
                            bubble.startAnimation(fadeout);
                            bomb.startAnimation(fadein);
                            sp.stop(bubbleSP);
                            sp.play(bumbEffect, 1, 1, 1, 0, 1);
                        }
                        break;

                }

                return false;
            }
        });

        bubble.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch(motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        if(animationFlag == 0) {
                            animationFlag = 1;
                            bomb.setVisibility(View.VISIBLE);
                            bubble.setVisibility(View.INVISIBLE);
                            bubbleScaleAni.reset();
                            bubbleScaleAni2.reset();
                            bubble.startAnimation(fadeout);
                            bomb.startAnimation(fadein);
                            sp.stop(bubbleSP);
                            sp.play(bumbEffect, 1, 1, 1, 0, 1);
                        }
                        break;
                }

                return false;
            }
        });
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            animationFlag = 0;
            bubble.startAnimation(bubbleScaleAni);
            checkedAnimation = true;
//            moon.startAnimation(blink);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            bomb.setVisibility(View.INVISIBLE);
        }

    }

    private void animationClear() {
        animationFlag = 0;
        bomb.setVisibility(View.INVISIBLE);
    }

    @Override
    public void soundPlayFunc() {

        musicController = new MusicController(getActivity(), R.raw.scene_16);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"달님과 별님이 깨어나는 시간이 되면 오늘도...", "4000"},
                new String[]{"빨간 우체통 엄마는 부스럭부스럭 \n" +
                        "편지를 꺼내 상냥하게 읽어주지.  ", "10000"},
                new String[]{"동도할머니는 옛날 옛날에~하며 \n" +
                        "마음 따뜻한 이야기를 시작하고...  ", "16500"},
                new String[]{"든든한 사철나무 아빠와 함께 \n" +
                        "독도가족들의 꿈나라를 지킨단다. ", "25000"},
                new String[]{"심심한 파도가 투정부리며 철썩철썩~", "29500"},
                new String[]{"멋쟁이 서도할아버지한테 간지럼장난을 치는 동안", "33700"},
                new String[]{"바다제비 친구들은 포근한 이불 속에서 \n" +
                        "사이좋게 재잘재잘... 재잘.", "41000"},
                new String[]{"활짝 웃는 땅채송화들이 불러주는 자장자장~ \n" +
                        "자장가는 하품을 데려오고 ", "47500"},
                new String[]{"부지런히 이불을 덮어주는 오징어 이모의 긴 다리들은 \n" +
                        "꿈나라의 문을 열어준단다.", "55000"},
                new String[]{"우리들은 오늘도 그렇게 \n" +
                        "꿀잠 속으로 스르르 빠져들지.", "62000"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();

        wave.post(new Runnable() {
            @Override
            public void run() {
                moonAppearAnimation = new TranslateAnimation(0, 0, -moon.getHeight(), 0);
                moonAppearAnimation.setDuration(2000);
                moonAppearAnimation.setStartOffset(1000);
                moonAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                moonAppearAnimation.setFillAfter(true);
                moonAppearAnimation.setAnimationListener(new MyAnimationListener());

                dokdoFatherAppearAnimation = new TranslateAnimation(-dokdo_father.getWidth(), 0, 0, 0);
                dokdoFatherAppearAnimation.setDuration(1200);
                dokdoFatherAppearAnimation.setStartOffset(1000);
                dokdoFatherAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                dokdoFatherAppearAnimation.setFillAfter(true);

                dokdoMomAppearAnimation = new TranslateAnimation(dokdo_mom.getWidth(), 0, 0, 0);
                dokdoMomAppearAnimation.setDuration(1200);
                dokdoMomAppearAnimation.setStartOffset(800);
                dokdoMomAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                dokdoMomAppearAnimation.setFillAfter(true);

                wavingAnimation = new TranslateAnimation(0, 0, 0, wave.getHeight() * 0.05f);
                wavingAnimation.setDuration(2000);
                wavingAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                wavingAnimation.setRepeatCount(Animation.INFINITE);
                wavingAnimation.setRepeatMode(Animation.REVERSE);

                waveAppearAnimation = new TranslateAnimation(0, 0, wave.getHeight(), 0);
                waveAppearAnimation.setDuration(1500);
                waveAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                waveAppearAnimation.setFillAfter(true);
                waveAppearAnimation.setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        wave.startAnimation(wavingAnimation);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {

                    }
                });


                bubbleScaleAni = new ScaleAnimation(1, 0.7f, 1, 0.7f, 0, 0);
                bubbleScaleAni.setDuration(800);
                bubbleScaleAni.setInterpolator(new AccelerateDecelerateInterpolator());
                bubbleScaleAni.setAnimationListener(new MyAnimationListener(){
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        bubble.startAnimation(bubbleScaleAni2);
                    }

                });

                bubbleScaleAni2 = new ScaleAnimation(0.7f, 1, 0.7f, 1, 0, 0);
                bubbleScaleAni2.setDuration(800);
                bubbleScaleAni2.setInterpolator(new AccelerateDecelerateInterpolator());
                bubbleScaleAni2.setAnimationListener(new MyAnimationListener(){
                    @Override
                    public void onAnimationStart(Animation animation) {
                        bubbleSP = sp.play(bubbleEffect,0.05f,0.05f,1,0, 1);
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        bubble.startAnimation(bubbleScaleAni);
                    }
                });


//                bubbleBombScaleAni = new ScaleAnimation(1, 0.7f, 1, 0.7f, 0, 0);
//                bubbleBombScaleAni.setDuration(800);
//                bubbleBombScaleAni.setInterpolator(new AccelerateDecelerateInterpolator());
//                bubbleBombScaleAni.setRepeatCount(2);
//                bubbleBombScaleAni.setRepeatMode(Animation.REVERSE);
//                bubbleBombScaleAni.setAnimationListener(new MyAnimationListener() {
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        bubble.setVisibility(View.INVISIBLE);
////                        sp.stop(bubbleEffect);
////                        sp.play(bumbEffect,1,1,1,0,1);
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
////                        sp.play(bubbleEffect,0.1f,0.1f,1,0,1);
//                    }
//
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//                        bomb.setVisibility(View.VISIBLE);
//                        bomb.startAnimation(fadein);
////                        sp.play(bubbleEffect,0.1f,0.1f,1,0,1);
//                    }
//                });

//                bubbleBombAniSet.addAnimation(bubbleBombScaleAni);
//                bubbleBombAniSet.addAnimation(fadeout);
                animationClear();
                checkedAnimation = false;
                animationFlag = 1;
                moon.startAnimation(moonAppearAnimation);
                bubble.startAnimation(moonAppearAnimation);
                dokdo_father.startAnimation(dokdoFatherAppearAnimation);
                dokdo_mom.startAnimation(dokdoMomAppearAnimation);
                wave.startAnimation(waveAppearAnimation);
            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isVisibleToUser) {
            if(bubbleSP!=0) {
                sp.stop(bubbleSP);
                sp.release();
                Log.e("asd", "setUserVisibleHint: "+bubbleSP);
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
    }
}
