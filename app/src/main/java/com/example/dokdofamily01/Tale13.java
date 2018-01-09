package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.ssomai.android.scalablelayout.ScalableLayout;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale13 extends BaseFragment {

    private android.widget.ImageView ivBuyl13;
    private android.widget.ImageView ivWall13;
    private android.widget.ImageView ivBottom13;
    private android.widget.ImageView ivFishes13;
    ImageView bubble;
    ImageView[] wave = new ImageView[4];

    private TranslateAnimation wallAnimation, bottomAnimation, characterAnimation, fishAnimation;
    AlphaAnimation bubbleAlpha;
    TranslateAnimation bubbleTranslate;
    TranslateAnimation[] wavingTransAni = new TranslateAnimation[4];
    AlphaAnimation[] wavingFadeinAni = new AlphaAnimation[4];
    AnimationSet[] wavingAniSet = new AnimationSet[4];
    AnimationSet bubbleAppear;
    AlphaAnimation blink;
    int animationFlag = 0;
    int clickFlag=0;

    private SoundPool bubbleSoundPool, tickSoundPool;
    private int bubbleSound, tickSound;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale13;


        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override

    public void bindViews() {
        super.bindViews();

        this.sv = (CustomScrollView) layout.findViewById(R.id.sv);
        this.sl = (ScalableLayout) layout.findViewById(R.id.sl);
        this.ivBottom13 = (ImageView) layout.findViewById(R.id.ivBottom13);
        this.ivWall13 = (ImageView) layout.findViewById(R.id.ivWall13);
        this.ivBuyl13 = (ImageView) layout.findViewById(R.id.ivBuyl13);
        ivFishes13 = (ImageView) layout.findViewById(R.id.ivFishes13);
        bubble = (ImageView) layout.findViewById(R.id.bubble);
        wave[0] = (ImageView) layout.findViewById(R.id.wave0);
        wave[1] = (ImageView) layout.findViewById(R.id.wave1);
        wave[2] = (ImageView) layout.findViewById(R.id.wave2);
        wave[3] = (ImageView) layout.findViewById(R.id.wave3);

    }

    @Override
    public void setValues() {
        super.setValues();


    }

    @Override
    public void setAnimation() {
        super.setAnimation();
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        ivBuyl13.setOnTouchListener(new BlockObjListener());

    }

    @Override
    public void blockAnimFunc() {
        if(clickFlag==0) {
            clickFlag=1;
            checkedAnimation = false;
            ivBuyl13.clearAnimation();
            bubble.startAnimation(bubbleAppear);
            bubbleSound = bubbleSoundPool.load(getContext(), R.raw.effect_13_bubble, 1);
            tickSound = tickSoundPool.load(getContext(), R.raw.effect_13_tick, 1);
        }

        super.blockAnimFunc();
    }

    private class MyAnimationListener extends com.example.dokdofamily01.MyAnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            super.onAnimationStart(animation);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            if (animationFlag == 1) {
                animationClear();
                animationFlag = 0;
                ivBuyl13.startAnimation(blink);
                checkedAnimation = true;
                wave[0].startAnimation(wavingAniSet[0]);
                wave[1].startAnimation(wavingAniSet[1]);
                wave[2].startAnimation(wavingAniSet[2]);
                wave[3].startAnimation(wavingAniSet[3]);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            super.onAnimationRepeat(animation);
        }
    }

    private void animationClear() {
        animationFlag = 0;
        ivFishes13.clearAnimation();
        ivWall13.clearAnimation();
        ivBottom13.clearAnimation();
        ivBuyl13.clearAnimation();
        wave[0].clearAnimation();
        wave[1].clearAnimation();
        wave[2].clearAnimation();
        wave[3].clearAnimation();
    }
    @Override
    public void soundPlayFunc() {
        if( ((TaleActivity) getActivity()).isAutoRead) {
            musicController = new MusicController(getActivity(), R.raw.scene_13, vp,
                    new int[]{R.drawable.sub_13_01, 5000},
                    new int[]{R.drawable.sub_13_02, 10000},
                    new int[]{R.drawable.sub_13_03, 14000},
                    new int[]{R.drawable.sub_13_04, 23000},
                    new int[]{R.drawable.sub_13_05, 29500},
                    new int[]{R.drawable.sub_13_06, 99999});
        } else {
            subtitleController = new SubtitleController(vp,
                    R.drawable.sub_13_01,
                    R.drawable.sub_13_02,
                    R.drawable.sub_13_03,
                    R.drawable.sub_13_04,
                    R.drawable.sub_13_05,
                    R.drawable.sub_13_06);
        }

        ivBuyl13.post(new Runnable() {
            @Override
            public void run() {
                bubbleSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
                tickSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
                bubbleSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        bubbleSoundPool.play(bubbleSound, 1, 1, 0, 0, 1);
                    }
                });

                tickSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        tickSoundPool.play(tickSound, 1, 1, 0, 0, 1);
                    }
                });
                bottomAnimation = new TranslateAnimation(0, 0, ivBottom13.getHeight(), 0);
                bottomAnimation.setDuration(2000);
                bottomAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                wallAnimation = new TranslateAnimation(ivWall13.getWidth(), 0, 0, 0);
                wallAnimation.setStartOffset(500);
                wallAnimation.setDuration(2000);
                wallAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                characterAnimation = new TranslateAnimation(-ivBottom13.getWidth(), 0, 0, 0);
                characterAnimation.setDuration(3000);
                characterAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                characterAnimation.setAnimationListener(new MyAnimationListener());

                fishAnimation = new TranslateAnimation(ivFishes13.getWidth(), 0, 0, 0);
                fishAnimation.setStartOffset(2000);
                fishAnimation.setDuration(1000);
                fishAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                bubbleAlpha = new AlphaAnimation(0, 1);
                bubbleAlpha.setDuration(1000);
                bubbleAlpha.setRepeatCount(1);
                bubbleAlpha.setRepeatMode(Animation.REVERSE);

                bubbleTranslate = new TranslateAnimation(0, 0, 0, -bubble.getHeight() * 0.5f);
                bubbleTranslate.setDuration(2000);
                bubbleTranslate.setInterpolator(new AnticipateInterpolator());

                bubbleAppear = new AnimationSet(false);
                bubbleAppear.addAnimation(bubbleAlpha);
                bubbleAppear.addAnimation(bubbleTranslate);
                bubbleAppear.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        checkedAnimation = true;
                        ivBuyl13.startAnimation(blink);
                        clickFlag=0;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                blink = new AlphaAnimation(1, 0.3f);
                blink.setDuration(500);
                blink.setRepeatCount(Animation.INFINITE);
                blink.setRepeatMode(Animation.REVERSE);

                wavingTransAni[0] = new TranslateAnimation(0, 0, 0, wave[0].getHeight() * 0.1f);
                wavingTransAni[0].setDuration(2000);
                wavingTransAni[0].setInterpolator(new AccelerateDecelerateInterpolator());
                wavingTransAni[0].setRepeatCount(Animation.INFINITE);
                wavingTransAni[0].setRepeatMode(Animation.REVERSE);

                wavingFadeinAni[0] = new AlphaAnimation(0, 1);
                wavingFadeinAni[0].setDuration(5000);
                wavingFadeinAni[0].setInterpolator(new DecelerateInterpolator());
                wavingFadeinAni[0].setRepeatCount(Animation.INFINITE);
                wavingFadeinAni[0].setRepeatMode(Animation.REVERSE);

                wavingTransAni[1] = new TranslateAnimation(0, 0, 0, wave[1].getHeight() * 0.1f);
                wavingTransAni[1].setDuration(3000);
                wavingTransAni[1].setInterpolator(new AccelerateDecelerateInterpolator());
                wavingTransAni[1].setRepeatCount(Animation.INFINITE);
                wavingTransAni[1].setRepeatMode(Animation.REVERSE);

                wavingFadeinAni[1] = new AlphaAnimation(0, 1);
                wavingFadeinAni[1].setStartOffset(400);
                wavingFadeinAni[1].setDuration(3700);
                wavingFadeinAni[1].setInterpolator(new DecelerateInterpolator());
                wavingFadeinAni[1].setRepeatCount(Animation.INFINITE);
                wavingFadeinAni[1].setRepeatMode(Animation.REVERSE);

                wavingTransAni[2] = new TranslateAnimation(0, 0, 0, wave[2].getHeight() * 0.15f);
                wavingTransAni[2].setDuration(3500);
                wavingTransAni[2].setInterpolator(new AccelerateDecelerateInterpolator());
                wavingTransAni[2].setRepeatCount(Animation.INFINITE);
                wavingTransAni[2].setRepeatMode(Animation.REVERSE);

                wavingFadeinAni[2] = new AlphaAnimation(0, 1);
                wavingFadeinAni[2].setStartOffset(500);
                wavingFadeinAni[2].setDuration(2500);
                wavingFadeinAni[2].setInterpolator(new DecelerateInterpolator());
                wavingFadeinAni[2].setRepeatCount(Animation.INFINITE);
                wavingFadeinAni[2].setRepeatMode(Animation.REVERSE);

                wavingTransAni[3] = new TranslateAnimation(0, 0, 0, wave[2].getHeight() * 0.1f);
                wavingTransAni[3].setDuration(5000);
                wavingTransAni[3].setInterpolator(new AccelerateDecelerateInterpolator());
                wavingTransAni[3].setRepeatCount(Animation.INFINITE);
                wavingTransAni[3].setRepeatMode(Animation.REVERSE);

                wavingFadeinAni[3] = new AlphaAnimation(0, 1);
                wavingFadeinAni[3].setDuration(5500);
                wavingFadeinAni[3].setInterpolator(new DecelerateInterpolator());
                wavingFadeinAni[3].setRepeatCount(Animation.INFINITE);
                wavingFadeinAni[3].setRepeatMode(Animation.REVERSE);

                for(int iter=0; iter < 4; iter++) {
                    wavingAniSet[iter] = new AnimationSet(false);
                    wavingAniSet[iter].addAnimation(wavingTransAni[iter]);
                    wavingAniSet[iter].addAnimation(wavingFadeinAni[iter]);
                }


                if (animationFlag == 0 && bottomAnimation != null) {
                    animationClear();
                    checkedAnimation = false;
                    animationFlag = 1;
                    ivBottom13.startAnimation(bottomAnimation);
                    ivWall13.startAnimation(wallAnimation);
                    ivBuyl13.startAnimation(characterAnimation);
                    ivFishes13.startAnimation(fishAnimation);
                }
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
            if(bubbleSoundPool != null) {
                bubbleSoundPool.release();
            }
            if(tickSoundPool != null) {
                tickSoundPool.release();
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
