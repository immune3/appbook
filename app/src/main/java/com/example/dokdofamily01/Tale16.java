package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

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
    Boolean isAuto;

    TranslateAnimation moonAppearAnimation;
    TranslateAnimation dokdoFatherAppearAnimation;
    TranslateAnimation dokdoMomAppearAnimation;
    TranslateAnimation waveAppearAnimation;
    TranslateAnimation wavingAnimation;
    ScaleAnimation bubbleScaleAni;
    ScaleAnimation bubbleScaleAni2;
    AlphaAnimation blink;
    AlphaAnimation fadein;
    AlphaAnimation fadeout;
    AlphaAnimation bubbleFadein;
    AlphaAnimation bombFadeout;

    int animationFlag = 0;
    int bubbleSP = 0;
    int bubbleSoundFlag = 0;

    SoundPool bubbleEffectSp, bombSp;
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
        bombSp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

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

        moon.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                animationCaseFlag = 0;
                return super.onTouch(view, motionEvent);
            }
        });

        bubble.setOnTouchListener(new BlockObjListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                animationCaseFlag = 1;
                return super.onTouch(view, motionEvent);
            }
        });



        bombSp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                bombSp.play(bumbEffect, 1, 1, 1, 0, 1);
                bubbleSoundFlag = 1;
            }
        });

    }

    int animationCaseFlag = 0;

    @Override
    public void blockAnimFunc() {
        switch (animationCaseFlag) {
            case 0:
                if (animationFlag == 0) {
                    animationFlag = 1;
                    bomb.setVisibility(View.VISIBLE);
                    bubble.setVisibility(View.INVISIBLE);
                    bubbleScaleAni.reset();
                    bubbleScaleAni2.reset();
                    bubble.startAnimation(fadeout);
                    bomb.startAnimation(fadein);
                    bubbleEffectSp.stop(bubbleSP);
                    bumbEffect = bombSp.load(getContext(), R.raw.effect_16_bumb, 2);
                }
                break;
            case 1:
                if (animationFlag == 0) {
                    animationFlag = 1;
                    bomb.setVisibility(View.VISIBLE);
                    bubble.setVisibility(View.INVISIBLE);
                    bubbleScaleAni.reset();
                    bubbleScaleAni2.reset();
                    bubble.startAnimation(fadeout);
                    bomb.startAnimation(fadein);
                    bubbleEffectSp.stop(bubbleSP);
                    bumbEffect = bombSp.load(getContext(), R.raw.effect_16_bumb, 2);
                }

                break;
        }
        super.blockAnimFunc();
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
        this.isAuto = getArguments().getBoolean("isAuto");

        if(isAuto) {
            musicController = new MusicController(getActivity(), R.raw.scene_16, vp,
                    new int[]{R.drawable.sub_16_01, 4000},
                    new int[]{R.drawable.sub_16_02, 10000},
                    new int[]{R.drawable.sub_16_03, 16500},
                    new int[]{R.drawable.sub_16_04, 19300},
                    new int[]{R.drawable.sub_16_05, 25000},
                    new int[]{R.drawable.sub_16_06, 29500},
                    new int[]{R.drawable.sub_16_07, 33700},
                    new int[]{R.drawable.sub_16_08, 41000},
                    new int[]{R.drawable.sub_16_09, 47500},
                    new int[]{R.drawable.sub_16_10, 55000},
                    new int[]{R.drawable.sub_16_11, 99999});
        } else {
            subtitleController = new SubtitleController(vp,
                    R.drawable.sub_16_01,
                    R.drawable.sub_16_02,
                    R.drawable.sub_16_03,
                    R.drawable.sub_16_04,
                    R.drawable.sub_16_05,
                    R.drawable.sub_16_06,
                    R.drawable.sub_16_07,
                    R.drawable.sub_16_08,
                    R.drawable.sub_16_09,
                    R.drawable.sub_16_10,
                    R.drawable.sub_16_11);
        }

        wave.post(new Runnable() {
            @Override
            public void run() {
                bubbleEffectSp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
                bubbleEffectSp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        if(bubbleSoundFlag == 0) {
                            bubbleEffectSp.play(bubbleEffect, 0.05f, 0.05f, 1, 0, 1);
                        }
                    }
                });

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
                bubbleScaleAni.setAnimationListener(new MyAnimationListener() {
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
                bubbleScaleAni2.setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        bubbleEffect = bubbleEffectSp.load(getContext(), R.raw.effect_16_bubble, 1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        bubble.startAnimation(bubbleScaleAni);
                    }
                });


                checkedAnimation = false;
                bubbleSoundFlag=0;
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
        if (!isVisibleToUser) {
            if(bubbleEffectSp != null) {
                bubbleEffectSp.release();
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
