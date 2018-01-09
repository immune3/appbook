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
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale06 extends BaseFragment {
    ImageView sea;
    ImageView waveshadow;
    ImageView momDokdo;
    ImageView[] smallwave = new ImageView[4];
    ImageView[] bigwave = new ImageView[3];
    ImageView[] seagull = new ImageView[2];

    TranslateAnimation waveAppear;
    TranslateAnimation backgroundWaving;
    TranslateAnimation momAppear;
    TranslateAnimation[] wavingTranslateAni = new TranslateAnimation[7];
    TranslateAnimation[] wavingUpperAndLower = new TranslateAnimation[3];
    AlphaAnimation seagullFadein;
    AlphaAnimation blink;
    AlphaAnimation fadeIn;
    AnimationSet[] wavingAniSet = new AnimationSet[3];
    int animationFlag = 0;

    SoundPool gullSoundPool, waveSoundPool;
    int gullSound;
    int waveSound;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale06;

        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();

        sea = (ImageView) layout.findViewById(R.id.sea);
        waveshadow = (ImageView) layout.findViewById(R.id.waveshadow);
        smallwave[0] = (ImageView) layout.findViewById(R.id.smallwave1);
        smallwave[1] = (ImageView) layout.findViewById(R.id.smallwave2);
        smallwave[2] = (ImageView) layout.findViewById(R.id.smallwave3);
        smallwave[3] = (ImageView) layout.findViewById(R.id.smallwave4);
        bigwave[0] = (ImageView) layout.findViewById(R.id.bigwave1);
        bigwave[1] = (ImageView) layout.findViewById(R.id.bigwave2);
        bigwave[2] = (ImageView) layout.findViewById(R.id.bigwave3);
        seagull[0] = (ImageView) layout.findViewById(R.id.seagull1);
        seagull[1] = (ImageView) layout.findViewById(R.id.seagull2);
        momDokdo = (ImageView) layout.findViewById(R.id.momDokdo);
    }

    @Override
    public void setValues() {
        super.setValues();
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(1000);
        fadeIn.setFillAfter(true);

        seagullFadein = new AlphaAnimation(0, 1);
        seagullFadein.setDuration(1000);
        seagullFadein.setFillAfter(true);
        seagullFadein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationFlag = 0;
                seagull[0].startAnimation(blink);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        blink = new AlphaAnimation(1, 0.3f);
        blink.setDuration(500);
        blink.setInterpolator(new LinearInterpolator());
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        seagull[0].setOnTouchListener(new BlockObjListener());
    }

    @Override
    public void blockAnimFunc() {
        if(animationFlag == 0) {
            checkedAnimation = false;
            animationFlag = 1;
            bigwave[0].startAnimation(wavingAniSet[0]);
            bigwave[1].startAnimation(wavingAniSet[1]);
            bigwave[2].startAnimation(wavingAniSet[2]);
            smallwave[0].startAnimation(wavingTranslateAni[3]);
            smallwave[1].startAnimation(wavingTranslateAni[4]);
            smallwave[2].startAnimation(wavingTranslateAni[5]);
            smallwave[3].startAnimation(wavingTranslateAni[6]);
            gullSound = gullSoundPool.load(getContext(),R.raw.effect_06_gull,1);
            waveSound = waveSoundPool.load(getContext(),R.raw.effect_06_wave,1);
        }

        super.blockAnimFunc();
    }

    private void animationClear() {
        momDokdo.setVisibility(View.INVISIBLE);
        smallwave[0].setVisibility(View.INVISIBLE);
        smallwave[1].setVisibility(View.INVISIBLE);
        smallwave[2].setVisibility(View.INVISIBLE);
        smallwave[3].setVisibility(View.INVISIBLE);
        waveshadow.setVisibility(View.INVISIBLE);
        seagull[0].setVisibility(View.INVISIBLE);
        seagull[1].setVisibility(View.INVISIBLE);
        animationFlag = 0;
        sea.clearAnimation();
        waveshadow.clearAnimation();
        momDokdo.clearAnimation();
        smallwave[0].clearAnimation();
        smallwave[1].clearAnimation();
        smallwave[2].clearAnimation();
        smallwave[3].clearAnimation();
        bigwave[0].clearAnimation();
        bigwave[1].clearAnimation();
        bigwave[2].clearAnimation();
        seagull[0].clearAnimation();
        seagull[1].clearAnimation();

    }

    @Override
    public void soundPlayFunc() {
        if( ((TaleActivity) getActivity()).isAutoRead) {
            musicController = new MusicController(getActivity(), R.raw.scene_6, vp,
                new int[]{R.drawable.sub_06_01, 8000},
                new int[]{R.drawable.sub_06_02, 16500},
                new int[]{R.drawable.sub_06_03, 20500},
                new int[]{R.drawable.sub_06_04, 25500},
                new int[]{R.drawable.sub_06_05, 29000},
                new int[]{R.drawable.sub_06_06, 31500},
                new int[]{R.drawable.sub_06_07, 33000},
                new int[]{R.drawable.sub_06_08, 36500},
                new int[]{R.drawable.sub_06_09, 40000},
                new int[]{R.drawable.sub_06_10, 99999});
        } else {
            subtitleController = new SubtitleController(vp,
                    R.drawable.sub_06_01,
                    R.drawable.sub_06_02,
                    R.drawable.sub_06_03,
                    R.drawable.sub_06_04,
                    R.drawable.sub_06_05,
                    R.drawable.sub_06_06,
                    R.drawable.sub_06_07,
                    R.drawable.sub_06_08,
                    R.drawable.sub_06_09,
                    R.drawable.sub_06_10);
        }

        sea.post(new Runnable() {
            @Override
            public void run() {
                gullSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
                waveSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
                gullSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        gullSoundPool.play(gullSound, 1, 1, 0, 0, 1);
                    }
                });

                waveSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        waveSoundPool.play(waveSound, 0.8f, 0.8f, 0, 3, 1);
                    }
                });

                waveAppear = new TranslateAnimation(0, 0, (int) (sea.getHeight() * 0.8), 0);
                waveAppear.setDuration(800);
//                waveAppear.setFillAfter(true);
                waveAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                waveAppear.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        smallwave[0].startAnimation(fadeIn);
                        smallwave[1].startAnimation(fadeIn);
                        smallwave[2].startAnimation(fadeIn);
                        smallwave[3].startAnimation(fadeIn);
                        waveshadow.startAnimation(fadeIn);
                        seagull[0].startAnimation(seagullFadein);
                        seagull[1].startAnimation(fadeIn);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


                momAppear = new TranslateAnimation(-momDokdo.getWidth(), 0, 0, 0);
                momAppear.setStartOffset(1000);
                momAppear.setDuration(1000);
                momAppear.setFillAfter(true);
                momAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                momAppear.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        sea.startAnimation(backgroundWaving);
                        waveshadow.startAnimation(backgroundWaving);
                        seagull[0].startAnimation(blink);
                        checkedAnimation = true;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        waveshadow.clearAnimation();
                    }
                });

                backgroundWaving = new TranslateAnimation(0, 0, 0, waveshadow.getHeight() * 0.05f);
                backgroundWaving.setDuration(2000);
                backgroundWaving.setInterpolator(new AccelerateDecelerateInterpolator());
                backgroundWaving.setRepeatCount(Animation.INFINITE);
                backgroundWaving.setRepeatMode(Animation.REVERSE);

                int baseDuration = 2000;
                for(int iter = 0; iter < 3; iter++){
                    wavingTranslateAni[iter] = new TranslateAnimation(0, bigwave[iter].getWidth() * 0.1f, 0, 0);
                    wavingTranslateAni[iter].setDuration(baseDuration);
                    wavingTranslateAni[iter].setInterpolator(new AccelerateDecelerateInterpolator());
                    wavingTranslateAni[iter].setRepeatCount(3);
                    wavingTranslateAni[iter].setRepeatMode(Animation.REVERSE);

                    wavingUpperAndLower[iter] = new TranslateAnimation(0,0,0 , bigwave[iter].getHeight() * 0.2f);
                    wavingUpperAndLower[iter].setDuration(baseDuration);
                    wavingUpperAndLower[iter].setInterpolator(new AccelerateDecelerateInterpolator());
                    wavingUpperAndLower[iter].setRepeatCount(3);
                    wavingUpperAndLower[iter].setRepeatMode(Animation.REVERSE);

                    wavingAniSet[iter] = new AnimationSet(false);
                    wavingAniSet[iter].addAnimation(wavingTranslateAni[iter]);
                    wavingAniSet[iter].addAnimation(wavingUpperAndLower[iter]);
                }

                wavingTranslateAni[3] = new TranslateAnimation(0, 0, 0, smallwave[0].getHeight() * 0.1f);
                wavingTranslateAni[3].setDuration(2600);
                wavingTranslateAni[3].setInterpolator(new CycleInterpolator(0.5f));
                wavingTranslateAni[3].setRepeatCount(2);
                wavingTranslateAni[3].setRepeatMode(Animation.REVERSE);

                wavingTranslateAni[4] = new TranslateAnimation(0, 0, 0, smallwave[1].getHeight() * 0.1f);
                wavingTranslateAni[4].setDuration(5300);
                wavingTranslateAni[4].setInterpolator(new CycleInterpolator(1));
                wavingTranslateAni[4].setRepeatCount(1);
                wavingTranslateAni[4].setRepeatMode(Animation.REVERSE);

                wavingTranslateAni[5] = new TranslateAnimation(0, 0, 0, smallwave[2].getHeight() * 0.1f);
                wavingTranslateAni[5].setDuration(2000);
                wavingTranslateAni[5].setInterpolator(new CycleInterpolator(0.5f));
                wavingTranslateAni[5].setRepeatCount(3);
                wavingTranslateAni[5].setRepeatMode(Animation.REVERSE);
                wavingTranslateAni[5].setAnimationListener(new Animation.AnimationListener(){
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animationFlag = 0;
                        seagull[0].startAnimation(blink);
                        checkedAnimation = true;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        smallwave[0].setVisibility(View.VISIBLE);
                        smallwave[1].setVisibility(View.VISIBLE);
                        smallwave[2].setVisibility(View.VISIBLE);
                        smallwave[3].setVisibility(View.VISIBLE);
                        seagull[0].setVisibility(View.VISIBLE);
                        seagull[0].clearAnimation();
                    }
                });

                wavingTranslateAni[6] = new TranslateAnimation(0, 0, 0, smallwave[3].getHeight() * 0.1f);
                wavingTranslateAni[6].setDuration(2600);
                wavingTranslateAni[6].setInterpolator(new CycleInterpolator(1));
                wavingTranslateAni[6].setRepeatCount(2);
                wavingTranslateAni[6].setRepeatMode(Animation.REVERSE);

                animationClear();
                if (animationFlag == 0) {
                    animationFlag = 1;
                    checkedAnimation = false;
                    sea.startAnimation(waveAppear);
                    bigwave[0].startAnimation(waveAppear);
                    bigwave[1].startAnimation(waveAppear);
                    bigwave[2].startAnimation(waveAppear);
                    momDokdo.startAnimation(momAppear);
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
            if(gullSoundPool != null) {
                gullSoundPool.release();
            }
            if(waveSoundPool != null) {
                waveSoundPool.release();
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
