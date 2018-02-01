package com.example.dokdofamily01;

import android.media.MediaPlayer;
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
import static com.example.dokdofamily01.TaleActivity.homeKeyFlag;
import static com.example.dokdofamily01.TaleActivity.screenFlag;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale20 extends BaseFragment {
    ImageView man;
    ImageView dokdo_father;
    ImageView sqeed;
    ImageView dokdo_mom;
    ImageView wave;
    ImageView cutain;
    ImageView byul;
    //    CustomTextView cutainText;
    Boolean isAuto;

    TranslateAnimation manAppearAnimation;
    TranslateAnimation dokdoFatherAppearAnimation;
    TranslateAnimation sqeedAppearAnimation;
    TranslateAnimation dokdoMomAppearAnimation;
    TranslateAnimation waveAppearAnimation;
    TranslateAnimation cutainAppearAni;
    TranslateAnimation cutainDownAnimation1;
    TranslateAnimation cutainDownAnimation2;
    AlphaAnimation fadein;
    AlphaAnimation fadeout;

    MediaPlayer loopBgm;

    private Handler cutainHandler;
    private Runnable cutainRunnable;


    int animationFlag = 0;
//    static int endFlag = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale20;
        subtitleTextView.setText(null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void bindViews() {
        super.bindViews();
        man = (ImageView) layout.findViewById(R.id.man);
        dokdo_father = (ImageView) layout.findViewById(R.id.dokdo_father);
        sqeed = (ImageView) layout.findViewById(R.id.sqeed);
        dokdo_mom = (ImageView) layout.findViewById(R.id.dokdo_mom);
        wave = (ImageView) layout.findViewById(R.id.wave);
        cutain = (ImageView) layout.findViewById(R.id.cutain);
        byul = (ImageView) layout.findViewById(R.id.byul);
//        cutainText = (CustomTextView) layout.findViewById(R.id.cutain_text);
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
//        cutain.setOnTouchListener(new BlockObjListener());
    }

    @Override
    public void blockAnimFunc() {
        super.blockAnimFunc();
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            animationFlag = 0;
            checkedAnimation = true;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

    }

    @Override
    public void soundPlayFunc() {
        this.isAuto = getArguments().getBoolean("isAuto");

        checkedAnimation = false;

        if (isAuto) {
            musicController = new MusicController(((TaleActivity)getActivity()), R.raw.scene_20, vp,
                    new int[]{R.drawable.sub_20_01, 3000},
                    new int[]{R.drawable.sub_20_02, 8500},
                    new int[]{R.drawable.sub_20_03, 15000},
                    new int[]{R.drawable.sub_20_04, 18000},
                    new int[]{R.drawable.sub_20_05, 21000},
                    new int[]{R.drawable.sub_20_06, 24000},
                    new int[]{R.drawable.sub_20_07, 29500},
                    new int[]{R.drawable.sub_20_08, 36500},
                    new int[]{R.drawable.sub_20_09, 99999});
        } else {
            subtitleController = new SubtitleController(((TaleActivity)getActivity()), vp,
                    R.drawable.sub_20_01,
                    R.drawable.sub_20_02,
                    R.drawable.sub_20_03,
                    R.drawable.sub_20_04,
                    R.drawable.sub_20_05,
                    R.drawable.sub_20_06,
                    R.drawable.sub_20_07,
                    R.drawable.sub_20_08,
                    R.drawable.sub_20_09);
        }

        wave.post(new Runnable() {
            @Override
            public void run() {
//                endFlag = 0;
//                cutainText.setVisibility(View.INVISIBLE);
                fadein = new AlphaAnimation(0, 1);
                fadein.setDuration(500);
                fadein.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
//                cutainText.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                fadeout = new AlphaAnimation(1, 0);
                fadeout.setDuration(10);

                manAppearAnimation = new TranslateAnimation(0, 0, man.getHeight(), 0);
                manAppearAnimation.setDuration(1000);
                manAppearAnimation.setStartOffset(400);
                manAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                manAppearAnimation.setFillAfter(true);

                dokdoFatherAppearAnimation = new TranslateAnimation(0, 0, dokdo_father.getHeight(), 0);
                dokdoFatherAppearAnimation.setDuration(1000);
                dokdoFatherAppearAnimation.setStartOffset(700);
                dokdoFatherAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                dokdoFatherAppearAnimation.setFillAfter(true);

                sqeedAppearAnimation = new TranslateAnimation(0, 0, sqeed.getHeight(), 0);
                sqeedAppearAnimation.setDuration(1000);
                sqeedAppearAnimation.setStartOffset(200);
                sqeedAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                sqeedAppearAnimation.setFillAfter(true);

                dokdoMomAppearAnimation = new TranslateAnimation(0, 0, dokdo_mom.getHeight(), 0);
                dokdoMomAppearAnimation.setDuration(1000);
                dokdoMomAppearAnimation.setStartOffset(800);
                dokdoMomAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                dokdoMomAppearAnimation.setFillAfter(true);
                dokdoMomAppearAnimation.setAnimationListener(new MyAnimationListener());
                dokdoMomAppearAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (cutainHandler != null && cutainRunnable != null) {
                            cutainHandler.removeCallbacks(cutainRunnable);
                            cutainHandler = null;
                        }

                        cutainRunnable = new Runnable() {
                            @Override
                            public void run() {
                                if(cutain!=null && cutainDownAnimation2 != null) cutain.startAnimation(cutainDownAnimation2);
                            }
                        };
                        cutainHandler = new Handler();
                        cutainHandler.postDelayed(cutainRunnable, 2000);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                waveAppearAnimation = new TranslateAnimation(0, 0, wave.getHeight(), 0);
                waveAppearAnimation.setDuration(1000);
                waveAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                waveAppearAnimation.setFillAfter(true);

                cutainDownAnimation2 = new TranslateAnimation(0, 0, 0, -(cutain.getHeight() * 0.55f));
                cutainDownAnimation2.setDuration(30000);
                cutainDownAnimation2.setFillAfter(true);

                cutainDownAnimation2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
//                        cutainText.clearAnimation();
//                        endFlag = 0;
                        animationFlag = 0;
                        checkedAnimation = true;
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        endFlag = 1;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                if (!homeKeyFlag && screenFlag) {
                    loopBgm = MediaPlayer.create(getActivity(), R.raw.title_bgm);
                    loopBgm.setVolume(0.1f, 0.1f);
                    loopBgm.start();
                    loopBgm.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            loopBgm.start();
                        }
                    });
                }

                if (animationFlag == 0) {
                    checkedAnimation = false;
                    animationFlag = 1;
//                    cutainText.clearAnimation();
                    cutain.clearAnimation();
                    man.clearAnimation();
                    dokdo_father.clearAnimation();
                    dokdo_mom.clearAnimation();
                    sqeed.clearAnimation();
                    wave.clearAnimation();
                    man.startAnimation(manAppearAnimation);
                    dokdo_father.startAnimation(dokdoFatherAppearAnimation);
                    sqeed.startAnimation(sqeedAppearAnimation);
                    dokdo_mom.startAnimation(dokdoMomAppearAnimation);
                    wave.startAnimation(waveAppearAnimation);
//                    cutain.startAnimation(cutainAppearAni);
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
        if (isAttached) {
            if (!isVisibleToUser) {
                try {
                    loopBgm.pause();
                    loopBgm.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            loopBgm.pause();
            loopBgm.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void returnMemory() {

        man = null;
        dokdo_father = null;
        sqeed = null;
        dokdo_mom = null;
        wave = null;
        cutain = null;
        byul = null;

        if(manAppearAnimation != null) manAppearAnimation.cancel();
        if(dokdoFatherAppearAnimation != null) dokdoFatherAppearAnimation.cancel();
        if(sqeedAppearAnimation != null) sqeedAppearAnimation.cancel();
        if(dokdoMomAppearAnimation != null) dokdoMomAppearAnimation.cancel();
        if(waveAppearAnimation != null) waveAppearAnimation.cancel();
        if(cutainAppearAni != null) cutainAppearAni.cancel();
        if(cutainDownAnimation1 != null) cutainDownAnimation1.cancel();
        if(cutainDownAnimation2 != null) cutainDownAnimation2.cancel();
        if(fadein != null) fadein.cancel();
        if(fadeout != null) fadeout.cancel();

        manAppearAnimation = null;
        dokdoFatherAppearAnimation = null;
        sqeedAppearAnimation = null;
        dokdoMomAppearAnimation = null;
        waveAppearAnimation = null;
        cutainAppearAni = null;
        cutainDownAnimation1 = null;
        cutainDownAnimation2 = null;
        fadein = null;
        fadeout = null;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            if(loopBgm.isPlaying()) {
                loopBgm.pause();
                loopBgm.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        returnMemory();
    }
}

