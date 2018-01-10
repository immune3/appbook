package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;


/**
 * Created by heronation on 2017-11-06.
 */

public class Tale19 extends BaseFragment {

    ImageView byul;
    ImageView starLight;
    ImageView star1;
    ImageView star2;
    ImageView star3;
    ImageView star4;
    ImageView star5;
    ImageView star6;
    ImageView light;

    TranslateAnimation starAppear;
    TranslateAnimation starFall;
    AlphaAnimation fadein;
    AlphaAnimation fadeout;
    AlphaAnimation starLightFadeout;
    AlphaAnimation starFadeout;
    AlphaAnimation[] miniStarFadein = new AlphaAnimation[5];
    AlphaAnimation[] miniStarFadeout = new AlphaAnimation[5];
    TranslateAnimation[] miniStarAppear = new TranslateAnimation[5];
    TranslateAnimation[] miniStarDisappear = new TranslateAnimation[5];
    AnimationSet[] miniStarAppearAniSet = new AnimationSet[5];

    AlphaAnimation blink;
    AnimationSet starAppearAniSet;
    AnimationSet starDisappearAniSet;
    int animationFlag = 0;
    int starFallCount = 0;

    SoundPool starAppearSp, starFallSp;
    int starAppearEffect, starFallEffect;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale19;
        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public void bindViews() {
        super.bindViews();
        byul = (ImageView) layout.findViewById(R.id.byul);
        starLight = (ImageView) layout.findViewById(R.id.starLight);
        star1 = (ImageView) layout.findViewById(R.id.star1);
        star2 = (ImageView) layout.findViewById(R.id.star2);
        star3 = (ImageView) layout.findViewById(R.id.star3);
        star4 = (ImageView) layout.findViewById(R.id.star4);
        star5 = (ImageView) layout.findViewById(R.id.star5);
        star6 = (ImageView) layout.findViewById(R.id.star6);
        light = (ImageView) layout.findViewById(R.id.light);


    }

    @Override
    public void setValues() {
        super.setValues();
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadein = new AlphaAnimation(0, 1);
        fadein.setDuration(2000);
        fadein.setFillAfter(true);

        fadeout = new AlphaAnimation(1, 0);
        fadeout.setStartOffset(500);
        fadeout.setDuration(2500);
        fadeout.setFillAfter(true);

        starFadeout = new AlphaAnimation(1, 0);
        starFadeout.setDuration(1500);
        starFadeout.setFillAfter(true);

        starLightFadeout = new AlphaAnimation(1, 0);
        starLightFadeout.setDuration(2500);
        starLightFadeout.setFillAfter(true);
        starLightFadeout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                star1.startAnimation(starDisappearAniSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        blink = new AlphaAnimation(1, 0.3f);
        blink.setDuration(700);
        blink.setInterpolator(new LinearInterpolator());
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        star1.setOnTouchListener(new BlockObjListener());
    }

    @Override
    public void blockAnimFunc() {

        if(animationFlag == 0) {
            starAppearEffect = starAppearSp.load(getContext(), R.raw.effect_18_appear, 1);
            checkedAnimation = false;
            starFallCount = 0;
            animationFlag = 1;
            star1.clearAnimation();
//                starLight.setVisibility(View.VISIBLE);
//                light.setVisibility(View.VISIBLE);
            starLight.startAnimation(fadein);
            light.startAnimation(fadein);

            star2.startAnimation(miniStarAppearAniSet[0]);
            star3.startAnimation(miniStarAppearAniSet[1]);
            star4.startAnimation(miniStarAppearAniSet[2]);
            star5.startAnimation(miniStarAppearAniSet[3]);
            star6.startAnimation(miniStarAppearAniSet[4]);
        }

        super.blockAnimFunc();
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            animationFlag = 0;
            star1.startAnimation(blink);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            starLight.setVisibility(View.INVISIBLE);
            light.setVisibility(View.INVISIBLE);
            star1.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void soundPlayFunc() {
        if( ((TaleActivity) getActivity()).isAutoRead) {
            musicController = new MusicController(getActivity(), R.raw.scene_19, vp,
                    new int[]{R.drawable.sub_19_01, 9000},
                    new int[]{R.drawable.sub_19_02, 15000},
                    new int[]{R.drawable.sub_19_03, 20500},
                    new int[]{R.drawable.sub_19_04, 25500},
                    new int[]{R.drawable.sub_19_05, 28000},
                    new int[]{R.drawable.sub_19_06, 33000},
                    new int[]{R.drawable.sub_19_07, 38000},
                    new int[]{R.drawable.sub_19_08, 99999});
        } else {
            subtitleController = new SubtitleController(vp,
                    R.drawable.sub_19_01,
                    R.drawable.sub_19_02,
                    R.drawable.sub_19_03,
                    R.drawable.sub_19_04,
                    R.drawable.sub_19_05,
                    R.drawable.sub_19_06,
                    R.drawable.sub_19_07,
                    R.drawable.sub_19_08);
        }

        light.post(new Runnable() {
            @Override
            public void run() {
                starAppearSp = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
                starAppearSp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        starAppearSp.play(starAppearEffect, 1, 1, 1, 0, 1);
                    }
                });

                starFallSp = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
                starFallSp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        starFallSp.play(starFallEffect, 1, 1, 1, 0, 1);
                    }
                });

                starAppear = new TranslateAnimation(0, 0, -star1.getHeight(), 0);
                starAppear.setDuration(3000);
                starAppear.setInterpolator(new BounceInterpolator());
                starAppear.setAnimationListener(new MyAnimationListener());

                starAppearAniSet = new AnimationSet(false);
                starAppearAniSet.addAnimation(starAppear);
                starAppearAniSet.addAnimation(fadein);
                starAppearAniSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        checkedAnimation = true;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                int offsetDelay = 100;

                for (int iter = 0; iter < 5; iter++) {
                    miniStarFadein[iter] = new AlphaAnimation(0, 1);
                    miniStarFadein[iter].setStartOffset(iter*offsetDelay);
                    miniStarFadein[iter].setDuration(1000);

                    miniStarAppear[iter] = new TranslateAnimation(0, 0, -star1.getHeight()*1.5f, 0);
                    miniStarAppear[iter].setStartOffset(iter*offsetDelay);
                    miniStarAppear[iter].setDuration(1500);
                    miniStarAppear[iter].setInterpolator(new BounceInterpolator());

                    miniStarFadeout[iter] = new AlphaAnimation(1, 0);
                    miniStarFadeout[iter].setStartOffset(iter*offsetDelay + 5500);
                    miniStarFadeout[iter].setDuration(1000);

                    miniStarDisappear[iter] = new TranslateAnimation(0, 0, 0, star2.getHeight());
                    miniStarDisappear[iter].setStartOffset(iter*offsetDelay + 5000);
                    miniStarDisappear[iter].setDuration(1000);
                    miniStarDisappear[iter].setInterpolator(new AnticipateInterpolator());

                    miniStarAppearAniSet[iter] = new AnimationSet(false);
                    miniStarAppearAniSet[iter].addAnimation(miniStarFadein[iter]);
                    miniStarAppearAniSet[iter].addAnimation(miniStarAppear[iter]);
                    miniStarAppearAniSet[iter].addAnimation(miniStarFadeout[iter]);
                    miniStarAppearAniSet[iter].addAnimation(miniStarDisappear[iter]);
                    miniStarAppearAniSet[iter].setFillAfter(true);
                }
                starFall = new TranslateAnimation(0, 0, 0, star2.getHeight());
                starFall.setDuration(1000);
                starFall.setInterpolator(new AnticipateInterpolator());
                starFall.setFillAfter(true);
                starFall.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        starFallEffect = starFallSp.load(getContext(), R.raw.effect_03_clouds, 1);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                starDisappearAniSet = new AnimationSet(false);
                starDisappearAniSet.addAnimation(starFall);
                starDisappearAniSet.addAnimation(starFadeout);
                starDisappearAniSet.setFillAfter(true);

                miniStarAppear[4].setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                starLight.startAnimation(starLightFadeout);
                                light.startAnimation(fadeout);
                            }
                        }, 2000);

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

                miniStarFadeout[4].setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                star1.startAnimation(starAppearAniSet);
                            }
                        }, 800);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                checkedAnimation = false;
                star1.setVisibility(View.INVISIBLE);
                starLight.clearAnimation();
                light.clearAnimation();
                star2.clearAnimation();
                star3.clearAnimation();
                star4.clearAnimation();
                star5.clearAnimation();
                star6.clearAnimation();
                animationFlag = 1;
                star1.startAnimation(starAppearAniSet);
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
            if (starAppearSp != null) {
                starAppearSp.release();
            }
            if (starFallSp != null) {
                starFallSp.release();
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
