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
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale07 extends BaseFragment {
    ImageView dokdo;
    ImageView seagull[] = new ImageView[3];
    ImageView shadow[] = new ImageView[3];

    RotateAnimation seagullAppear1;
    RotateAnimation seagullDisappear1;
    RotateAnimation seagullAppear2;
    RotateAnimation seagullAppear3;
    RotateAnimation seagullAppear4;
    RotateAnimation seagullDisappear3;
    RotateAnimation rotateDokdo;
    ScaleAnimation scaleDokdo;
    AnimationSet moveDokdo;
    AlphaAnimation blink;
    Boolean isAuto;

    int animationFlag = 0;
    boolean clickFlag = false;

    SoundPool sp;
    int seagullEffect;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale07;

        subtitleTextView.setText(null);


        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void bindViews() {
        super.bindViews();
        dokdo = (ImageView) layout.findViewById(R.id.dokdo);
        seagull[0] = (ImageView) layout.findViewById(R.id.seagull1);
        seagull[1] = (ImageView) layout.findViewById(R.id.seagull2);
        seagull[2] = (ImageView) layout.findViewById(R.id.seagull3);
        shadow[0] = (ImageView) layout.findViewById(R.id.shadow1);
        shadow[1] = (ImageView) layout.findViewById(R.id.shadow1);
        shadow[2] = (ImageView) layout.findViewById(R.id.shadow1);
    }

    @Override
    public void setValues() {
        super.setValues();

    }

    @Override
    public void setAnimation() {
        super.setAnimation();

        blink = new AlphaAnimation(1, 0.3f);
        blink.setDuration(500);
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        seagull[0].setOnTouchListener(new BlockObjListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                animationCaseFlag = 0;
                return super.onTouch(view, motionEvent);
            }
        });

        seagull[2].setOnTouchListener(new BlockObjListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                animationCaseFlag = 1;
                return super.onTouch(view, motionEvent);
            }
        });

    }

    int animationCaseFlag = 0;
    @Override
    public void blockAnimFunc() {
        switch (animationCaseFlag){
            case 0 :
                if (animationFlag == 0) {
                    checkedAnimation = false;
                    clickFlag=true;
                    animationFlag = 2;
                    seagullEffect = sp.load(getContext(), R.raw.effect_07_seagull, 1);
                    seagull[0].startAnimation(seagullDisappear1);
                }

                break;
            case 1 :
                if (animationFlag == 5) {
                    checkedAnimation = false;
                    animationFlag = 6;
                    seagull[2].startAnimation(seagullDisappear3);
//                    dokdo.clearAnimation();
                }

                break;

        }
        super.blockAnimFunc();
    }

    private class MyAnimationListener extends com.example.dokdofamily01.MyAnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            super.onAnimationStart(animation);
            switch (animationFlag) {
                case 1:
                    seagull[0].setVisibility(View.VISIBLE);
                    break;
                case 3:
                    seagull[1].setVisibility(View.VISIBLE);
                    seagullEffect = sp.load(getContext(), R.raw.effect_07_seagull, 1);
                    break;
                case 4:
                    seagull[2].setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            seagullEffect = sp.load(getContext(), R.raw.effect_07_seagull, 1);

                        }
                    }, 500);


                    break;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            switch (animationFlag) {
                case 1:
                    animationFlag = 0;
                    seagull[0].clearAnimation();
                    seagull[0].startAnimation(blink);
                    checkedAnimation = true;
                    break;
                case 2:
                    animationFlag = 3;
                    seagull[0].clearAnimation();
                    seagull[0].setVisibility(View.INVISIBLE);
                    seagull[1].startAnimation(seagullAppear2);
                    break;
                case 3:
                    animationFlag = 4;
                    seagull[1].clearAnimation();
                    seagull[1].setVisibility(View.INVISIBLE);
                    seagull[2].startAnimation(seagullAppear3);
                    if(clickFlag){
                        clickFlag=false;
                        dokdo.startAnimation(moveDokdo);
                    }
                    break;
                case 4:
                    animationFlag = 5;
                    checkedAnimation=true;
                    seagull[2].clearAnimation();
                    seagull[2].startAnimation(blink);
                    break;
                case 6:
                    animationFlag = 2;
                    seagull[2].setVisibility(View.INVISIBLE);
                    seagull[2].clearAnimation();
                    seagull[0].setVisibility(View.VISIBLE);
                    seagull[0].startAnimation(seagullAppear4);
                    sp.play(seagullEffect, 1, 1, 0, 0, 1);
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            super.onAnimationRepeat(animation);
        }
    }

    @Override
    public void soundPlayFunc() {
        this.isAuto = getArguments().getBoolean("isAuto");

        if(isAuto) {
            musicController = new MusicController(getActivity(), R.raw.scene_7, vp,
                    new int[]{R.drawable.sub_07_01, 5300},
                    new int[]{R.drawable.sub_07_02, 11500},
                    new int[]{R.drawable.sub_07_03, 18000},
                    new int[]{R.drawable.sub_07_04, 24500},
                    new int[]{R.drawable.sub_07_05, 29000},
                    new int[]{R.drawable.sub_07_06, 33700},
                    new int[]{R.drawable.sub_07_07, 37500},
                    new int[]{R.drawable.sub_07_08, 41500},
                    new int[]{R.drawable.sub_07_09, 99999});

        } else {
            subtitleController = new SubtitleController(vp,
                    R.drawable.sub_07_01,
                    R.drawable.sub_07_02,
                    R.drawable.sub_07_03,
                    R.drawable.sub_07_04,
                    R.drawable.sub_07_05,
                    R.drawable.sub_07_06,
                    R.drawable.sub_07_07,
                    R.drawable.sub_07_08,
                    R.drawable.sub_07_09);
        }

        seagull[0].post(new Runnable() {
            @Override
            public void run() {
                seagull[0].clearAnimation();
                seagull[0].startAnimation(blink);
            }
        });

        sl.post(new Runnable() {
            @Override
            public void run() {
                sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
                sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        sp.play(seagullEffect, 1, 1, 0, 0, 1);
                    }
                });

                seagullAppear1 = new RotateAnimation(20, 0, -(int) (seagull[0].getWidth() * 4), -(int) (seagull[0].getHeight() * 1.5));
                seagullAppear1.setDuration(1500);
                seagullAppear1.setFillAfter(true);
                seagullAppear1.setInterpolator(new AccelerateDecelerateInterpolator());
                seagullAppear1.setAnimationListener(new MyAnimationListener());

                seagullDisappear1 = new RotateAnimation(0, -40, -(int) (seagull[0].getWidth() * 4), -(int) (seagull[0].getHeight() * 1.5));
                seagullDisappear1.setDuration(1500);
                seagullDisappear1.setFillAfter(true);
                seagullDisappear1.setInterpolator(new AccelerateDecelerateInterpolator());
                seagullDisappear1.setAnimationListener(new MyAnimationListener());

                seagullAppear2 = new RotateAnimation(30, -35, 0, -(int) (seagull[1].getHeight() * 5));
                seagullAppear2.setDuration(1500);
                seagullAppear2.setFillAfter(true);
                seagullAppear2.setInterpolator(new AccelerateDecelerateInterpolator());
                seagullAppear2.setAnimationListener(new MyAnimationListener());

                seagullAppear3 = new RotateAnimation(-35, 0, -(int) (seagull[2].getWidth() * 1.2), -(int) (seagull[1].getHeight() * 2.5));
                seagullAppear3.setStartOffset(500);
                seagullAppear3.setDuration(2000);
                seagullAppear3.setFillAfter(true);
                seagullAppear3.setInterpolator(new AccelerateDecelerateInterpolator());
                seagullAppear3.setAnimationListener(new MyAnimationListener());
                seagullDisappear3 = new RotateAnimation(0, 40, -(int) (seagull[2].getWidth() * 1.2), -(int) (seagull[1].getHeight() * 2.5));
                seagullDisappear3.setDuration(2000);
                seagullDisappear3.setFillAfter(true);
                seagullDisappear3.setInterpolator(new AccelerateDecelerateInterpolator());
                seagullDisappear3.setAnimationListener(new MyAnimationListener());

                seagullAppear4 = new RotateAnimation(20, -40, -(int) (seagull[0].getWidth() * 4), -(int) (seagull[0].getHeight() * 1.5));
                seagullAppear4.setDuration(1500);
                seagullAppear4.setFillAfter(true);
                seagullAppear4.setInterpolator(new AccelerateDecelerateInterpolator());
                seagullAppear4.setAnimationListener(new MyAnimationListener());

                scaleDokdo = new ScaleAnimation(1, 0.7f, 1, 0.7f, (int) (dokdo.getWidth() * 0.5), (int) (dokdo.getHeight() * 0.5));
                scaleDokdo.setDuration(2000);
                scaleDokdo.setFillAfter(true);
                scaleDokdo.setInterpolator(new AccelerateDecelerateInterpolator());
                rotateDokdo = new RotateAnimation(0, -30, (int) (dokdo.getWidth() * 0.5), (int) (dokdo.getHeight() * 0.5));
                rotateDokdo.setDuration(2000);
                rotateDokdo.setFillAfter(true);
                rotateDokdo.setInterpolator(new AccelerateDecelerateInterpolator());
                moveDokdo = new AnimationSet(false);
                moveDokdo.setStartOffset(500);
                moveDokdo.setFillAfter(true);
                moveDokdo.addAnimation(scaleDokdo);
                moveDokdo.addAnimation(rotateDokdo);

                animationFlag = 1;
                checkedAnimation = false;
                dokdo.clearAnimation();
                seagull[1].clearAnimation();
                seagull[2].clearAnimation();
                seagull[0].setVisibility(View.INVISIBLE);
                seagull[1].setVisibility(View.INVISIBLE);
                seagull[2].setVisibility(View.INVISIBLE);
                seagull[0].startAnimation(seagullAppear1);
            }
        });
//        if (seagullAppear1 != null) {
//            animationFlag = 1;
//            dokdo.clearAnimation();
//            seagull[1].clearAnimation();
//            seagull[2].clearAnimation();
//            seagull[0].setVisibility(View.INVISIBLE);
//            seagull[1].setVisibility(View.INVISIBLE);
//            seagull[2].setVisibility(View.INVISIBLE);
//            seagull[0].startAnimation(seagullAppear1);
//        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            if(sp != null) {
                sp.release();
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
