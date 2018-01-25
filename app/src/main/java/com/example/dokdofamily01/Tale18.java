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
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale18 extends BaseFragment {

    ImageView father18;
    ImageView mom18;
    ImageView stars18;
    ImageView flower18;
    ImageView man18;
    ImageView sqeed18;
    ImageView post18;
    ImageView tree18;
    Boolean isAuto;

    Handler handler;
    Runnable run;

    TranslateAnimation fatherAppear;
    TranslateAnimation momAppear;
    TranslateAnimation starsAppear;
    TranslateAnimation flowerAppear;
    TranslateAnimation sqeedAppear;
    TranslateAnimation manAppear;
    TranslateAnimation postAppear;
    TranslateAnimation treeAppear;
    AlphaAnimation blink;
    AlphaAnimation postBlink;
    AlphaAnimation treeBlink;
    AlphaAnimation sqeedBlink;
    AlphaAnimation manBlink;
    AlphaAnimation fatherBlink;
    AlphaAnimation momBlink;
    RotateAnimation postRotate[] = new RotateAnimation[2];
    RotateAnimation treeRotate[] = new RotateAnimation[2];
    RotateAnimation sqeedRotate[] = new RotateAnimation[2];
    RotateAnimation manRotate[] = new RotateAnimation[2];

    int animationFlag = 0;
    int rotateFlag[] = new int[4];
    boolean clickFlag = false;
    boolean delayFlag = false;

    SoundPool[] soundsPool;
    SoundPool sp;
    int[] soundsID;
    int soundID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale18;

        subtitleTextView.setText(null);


        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void bindViews() {
        super.bindViews();
        father18 = (ImageView) layout.findViewById(R.id.father18);
        mom18 = (ImageView) layout.findViewById(R.id.mom18);
        stars18 = (ImageView) layout.findViewById(R.id.stars18);
        flower18 = (ImageView) layout.findViewById(R.id.flower18);
        post18 = (ImageView) layout.findViewById(R.id.post18);
        tree18 = (ImageView) layout.findViewById(R.id.tree18);
        sqeed18 = (ImageView) layout.findViewById(R.id.sqeed18);
        man18 = (ImageView) layout.findViewById(R.id.man18);
        soundsPool = new SoundPool[6];
        soundsID = new int[6];

    }

    @Override
    public void setValues() {
        super.setValues();

    }

    @Override
    public void setAnimation() {
        super.setAnimation();
    }

    public void delay(final int delay) {
        handler = new Handler();

        run = new Runnable() {
            @Override
            public void run() {
                delayFlag = false;
            }
        };

        handler.postDelayed(run, delay);
    }


    @Override
    public void setupEvents() {
        super.setupEvents();

        flower18.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                animationCaseFlag = 0;
                return super.onTouch(view, motionEvent);
            }
        });

        sqeed18.setOnTouchListener(new BlockObjListener() {
           @Override
           public boolean onTouch(View view, MotionEvent motionEvent) {
               animationCaseFlag = 1;
               return super.onTouch(view, motionEvent);
           }
        });

        man18.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                animationCaseFlag = 2;
                return super.onTouch(view , motionEvent);
            }
        });

        post18.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                animationCaseFlag = 3;
                return super.onTouch(view , motionEvent);
            }
        });

        tree18.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                animationCaseFlag = 4;
                return super.onTouch(view , motionEvent);
            }
        });

        father18.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(rotateFlag[3]==0) animationCaseFlag = 5;
                return super.onTouch(view , motionEvent);
            }
        });

        mom18.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(rotateFlag[3]==0) animationCaseFlag = 6;
                return super.onTouch(view , motionEvent);
            }
        });
    }

    int animationCaseFlag = 0;

    @Override
    public void blockAnimFunc() {

        if(sp != null && !delayFlag) {

            switch (animationCaseFlag) {
                case 0:
                    soundID = sp.load(getContext(), R.raw.effect_18_star, 2);
//                soundsPool.play(starEffect, 1, 1, 1, 0, 1);
                    if (animationFlag == 0) {
                        checkedAnimation = false;
                        animationFlag = 2;
                        animationClear();
                        flower18.clearAnimation();

                        rotateFlag[0] = 1;
                        rotateFlag[1] = 1;
                        rotateFlag[2] = 1;
                        rotateFlag[3] = 1;
                        post18.setVisibility(View.VISIBLE);
                        tree18.setVisibility(View.VISIBLE);
                        sqeed18.setVisibility(View.VISIBLE);
                        man18.setVisibility(View.VISIBLE);
                        post18.startAnimation(postAppear);
                        tree18.startAnimation(treeAppear);
                        sqeed18.startAnimation(sqeedAppear);
                        man18.startAnimation(manAppear);
                    }
                    break;
                case 1:
                    if (clickFlag) soundID = sp.load(getContext(), R.raw.effect_18_do, 4);
                    break;
                case 2:
                    if (clickFlag) soundID = sp.load(getContext(), R.raw.effect_18_re, 4);
                    break;
                case 3 :
                    if (clickFlag) soundID = sp.load(getContext(), R.raw.effect_18_mi, 5);
                    break;
                case 4 :
                    if (clickFlag) soundID = sp.load(getContext(), R.raw.effect_18_fa, 6);
                    break;
                case 5 :
                    if (clickFlag) soundID = sp.load(getContext(), R.raw.effect_18_so, 7);
                    break;
                case 6 :
                    if (clickFlag) soundID = sp.load(getContext(), R.raw.effect_18_la, 8);
                    break;
            }

        }
        delayFlag = true;
        delay(300);

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
            switch (animationFlag) {
                case 1:
                    animationFlag = 0;
                    flower18.startAnimation(blink);
                    checkedAnimation = true;
                    break;
                case 2:
                    animationFlag = 3;
                    post18.startAnimation(postRotate[0]);
                    break;
                case 3:
                    animationFlag = 4;
                    AnimationSet postSet = new AnimationSet(false);
                    postSet.addAnimation(postBlink);
                    postSet.addAnimation(postRotate[1]);
                    post18.startAnimation(postSet);
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            super.onAnimationRepeat(animation);
        }
    }

    private class MyAnimationListener1 extends com.example.dokdofamily01.MyAnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            super.onAnimationStart(animation);
            if (rotateFlag[1] == 1) {
                soundID = sp.load(getContext(), R.raw.effect_18_appear, 1);
//                soundsPool.play(appear, 1, 1, 1, 0, 1);
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            switch (rotateFlag[1]) {
                case 1:
                    rotateFlag[1] = 2;
                    tree18.startAnimation(treeRotate[0]);
                    break;
                case 2:
                    rotateFlag[1] = 0;
                    AnimationSet treeSet = new AnimationSet(false);
                    treeSet.addAnimation(treeBlink);
                    treeSet.addAnimation(treeRotate[1]);
                    tree18.startAnimation(treeSet);
                    break;
            }
        }
    }

    private class MyAnimationListener2 extends com.example.dokdofamily01.MyAnimationListener {
        @Override
        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            switch (rotateFlag[2]) {
                case 1:
                    rotateFlag[2] = 2;
                    sqeed18.startAnimation(sqeedRotate[0]);
                    break;
                case 2:
                    rotateFlag[2] = 0;
                    AnimationSet sqeedSet = new AnimationSet(false);
                    sqeedSet.addAnimation(sqeedBlink);
                    sqeedSet.addAnimation(sqeedRotate[1]);
                    sqeed18.startAnimation(sqeedSet);
                    father18.startAnimation(fatherBlink);
//                    sqeed18.startAnimation(sqeedRotate[1]);
                    break;
            }
        }
    }

    private class MyAnimationListener3 extends com.example.dokdofamily01.MyAnimationListener {
        @Override
        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            switch (rotateFlag[3]) {
                case 1:
                    rotateFlag[3] = 2;
                    man18.startAnimation(manRotate[0]);
                    checkedAnimation = true;
                    clickFlag = true;
                    break;
                case 2:
                    rotateFlag[3] = 0;
                    AnimationSet manSet = new AnimationSet(false);
                    manSet.addAnimation(manBlink);
                    manSet.addAnimation(manRotate[1]);
                    man18.startAnimation(manSet);
                    mom18.startAnimation(momBlink);
                    break;
            }
        }
    }

    private void animationClear() {
        post18.setVisibility(View.INVISIBLE);
        tree18.setVisibility(View.INVISIBLE);
        sqeed18.setVisibility(View.INVISIBLE);
        man18.setVisibility(View.INVISIBLE);
        post18.clearAnimation();
        tree18.clearAnimation();
        sqeed18.clearAnimation();
        man18.clearAnimation();
        father18.clearAnimation();
        mom18.clearAnimation();
    }


    public void soundPlayFunc() {
        this.isAuto = getArguments().getBoolean("isAuto");

        if(isAuto) {
            musicController = new MusicController(getActivity(), R.raw.scene_18, vp,
                    new int[]{R.drawable.sub_18_01, 3000},
                    new int[]{R.drawable.sub_18_02, 11000},
                    new int[]{R.drawable.sub_18_03, 17000},
                    new int[]{R.drawable.sub_18_04, 26500},
                    new int[]{R.drawable.sub_18_05, 32500},
                    new int[]{R.drawable.sub_18_06, 41500});
        } else {
            subtitleController = new SubtitleController(vp,
                    R.drawable.sub_18_01,
                    R.drawable.sub_18_02,
                    R.drawable.sub_18_03,
                    R.drawable.sub_18_04,
                    R.drawable.sub_18_05,
                    R.drawable.sub_18_06);
        }

        father18.post(new Runnable() {
            @Override
            public void run() {
                sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
                sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        sp.play(soundID, 1, 1, 1, 0, 1);
                    }
                });

                blink = new AlphaAnimation(1, 0.3f);
                blink.setDuration(500);
                blink.setRepeatCount(Animation.INFINITE);
                blink.setRepeatMode(Animation.REVERSE);

                postBlink = new AlphaAnimation(1, 0.3f);
                postBlink.setDuration(500);
                postBlink.setRepeatCount(Animation.INFINITE);
                postBlink.setRepeatMode(Animation.REVERSE);

                treeBlink = new AlphaAnimation(1, 0.3f);
                treeBlink.setDuration(500);
                treeBlink.setRepeatCount(Animation.INFINITE);
                treeBlink.setRepeatMode(Animation.REVERSE);

                sqeedBlink = new AlphaAnimation(1, 0.3f);
                sqeedBlink.setDuration(500);
                sqeedBlink.setRepeatCount(Animation.INFINITE);
                sqeedBlink.setRepeatMode(Animation.REVERSE);

                manBlink = new AlphaAnimation(1, 0.3f);
                manBlink.setDuration(500);
                manBlink.setRepeatCount(Animation.INFINITE);
                manBlink.setRepeatMode(Animation.REVERSE);

                fatherBlink = new AlphaAnimation(1, 0.3f);
                fatherBlink.setDuration(500);
                fatherBlink.setRepeatCount(Animation.INFINITE);
                fatherBlink.setRepeatMode(Animation.REVERSE);

                momBlink = new AlphaAnimation(1, 0.3f);
                momBlink.setDuration(500);
                momBlink.setRepeatCount(Animation.INFINITE);
                momBlink.setRepeatMode(Animation.REVERSE);

                fatherAppear = new TranslateAnimation(0, 0, father18.getHeight(), 0);
                fatherAppear.setDuration(1500);
                fatherAppear.setInterpolator(new AccelerateDecelerateInterpolator());

                momAppear = new TranslateAnimation(0, 0, mom18.getHeight(), 0);
                momAppear.setStartOffset(300);
                momAppear.setDuration(1500);
                momAppear.setInterpolator(new AccelerateDecelerateInterpolator());

                starsAppear = new TranslateAnimation(0, 0, -stars18.getHeight(), 0);
                starsAppear.setStartOffset(500);
                starsAppear.setDuration(1500);
                starsAppear.setInterpolator(new AccelerateDecelerateInterpolator());

                flowerAppear = new TranslateAnimation(0, 0, -flower18.getHeight(), 0);
                flowerAppear.setStartOffset(500);
                flowerAppear.setDuration(2000);
                flowerAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                flowerAppear.setAnimationListener(new MyAnimationListener());

                postAppear = new TranslateAnimation(0, 0, -post18.getHeight(), 0);
                postAppear.setDuration(1500);
                postAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                postAppear.setAnimationListener(new MyAnimationListener());

                treeAppear = new TranslateAnimation(0, 0, -tree18.getHeight(), 0);
                treeAppear.setStartOffset(500);
                treeAppear.setDuration(1500);
                treeAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                treeAppear.setAnimationListener(new MyAnimationListener1());

                sqeedAppear = new TranslateAnimation(0, 0, -sqeed18.getHeight(), 0);
                sqeedAppear.setStartOffset(1000);
                sqeedAppear.setDuration(1500);
                sqeedAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                sqeedAppear.setAnimationListener(new MyAnimationListener2());
                manAppear = new TranslateAnimation(0, 0, -man18.getHeight(), 0);
                manAppear.setStartOffset(1500);
                manAppear.setDuration(1500);
                manAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                manAppear.setAnimationListener(new MyAnimationListener3());

                postRotate[0] = new RotateAnimation(0, -3, (int) (post18.getWidth() * 0.5), -post18.getHeight());
                postRotate[0].setDuration(1000);
                postRotate[0].setInterpolator(new AccelerateDecelerateInterpolator());
                postRotate[0].setAnimationListener(new MyAnimationListener());
                postRotate[1] = new RotateAnimation(-3, 3, (int) (post18.getWidth() * 0.5), -post18.getHeight());
                postRotate[1].setDuration(1000);
                postRotate[1].setInterpolator(new AccelerateDecelerateInterpolator());
                postRotate[1].setRepeatCount(Animation.INFINITE);
                postRotate[1].setRepeatMode(Animation.REVERSE);

                treeRotate[0] = new RotateAnimation(0, -3, (int) (tree18.getWidth() * 0.5), -tree18.getHeight());
                treeRotate[0].setDuration(1000);
                treeRotate[0].setInterpolator(new AccelerateDecelerateInterpolator());
                treeRotate[0].setAnimationListener(new MyAnimationListener1());
                treeRotate[1] = new RotateAnimation(-3, 3, (int) (tree18.getWidth() * 0.5), -tree18.getHeight());
                treeRotate[1].setDuration(1000);
                treeRotate[1].setInterpolator(new AccelerateDecelerateInterpolator());
                treeRotate[1].setRepeatCount(Animation.INFINITE);
                treeRotate[1].setRepeatMode(Animation.REVERSE);

                sqeedRotate[0] = new RotateAnimation(0, -3, (int) (sqeed18.getWidth() * 0.5), -sqeed18.getHeight());
                sqeedRotate[0].setDuration(1000);
                sqeedRotate[0].setInterpolator(new AccelerateDecelerateInterpolator());
                sqeedRotate[0].setAnimationListener(new MyAnimationListener2());
                sqeedRotate[1] = new RotateAnimation(-3, 3, (int) (sqeed18.getWidth() * 0.5), -sqeed18.getHeight());
                sqeedRotate[1].setDuration(1000);
                sqeedRotate[1].setInterpolator(new AccelerateDecelerateInterpolator());
                sqeedRotate[1].setRepeatCount(Animation.INFINITE);
                sqeedRotate[1].setRepeatMode(Animation.REVERSE);
                manRotate[0] = new RotateAnimation(0, -3, (int) (man18.getWidth() * 0.5), -man18.getHeight());
                manRotate[0].setDuration(1000);
                manRotate[0].setInterpolator(new AccelerateDecelerateInterpolator());
                manRotate[0].setAnimationListener(new MyAnimationListener3());
                manRotate[1] = new RotateAnimation(-3, 3, (int) (man18.getWidth() * 0.5), -man18.getHeight());
                manRotate[1].setDuration(1000);
                manRotate[1].setInterpolator(new AccelerateDecelerateInterpolator());
                manRotate[1].setRepeatCount(Animation.INFINITE);
                manRotate[1].setRepeatMode(Animation.REVERSE);


                animationClear();
                checkedAnimation = false;
                animationFlag = 1;
                father18.startAnimation(fatherAppear);
                mom18.startAnimation(momAppear);
                stars18.startAnimation(starsAppear);
                flower18.startAnimation(flowerAppear);

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
