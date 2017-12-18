package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;

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

    TranslateAnimation fatherAppear;
    TranslateAnimation momAppear;
    TranslateAnimation starsAppear;
    TranslateAnimation flowerAppear;
    TranslateAnimation sqeedAppear;
    TranslateAnimation manAppear;
    TranslateAnimation postAppear;
    TranslateAnimation treeAppear;
    AlphaAnimation blink;
    RotateAnimation postRotate[] = new RotateAnimation[2];
    RotateAnimation treeRotate[] = new RotateAnimation[2];
    RotateAnimation sqeedRotate[] = new RotateAnimation[2];
    RotateAnimation manRotate[] = new RotateAnimation[2];


    int animationFlag = 0;
    int rotateFlag[] = new int[4];
    boolean clickFlag=false;


    MediaPlayer mp = null;

    ArrayList<SubTitleData> subtitleList;

    SoundPool sp;
    int appear;
    int starEffect;
    int scale[] = new int[6];



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

        sp = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
        appear = sp.load(getContext(), R.raw.effect_18_appear, 1);
        starEffect = sp.load(getContext(), R.raw.effect_18_star, 2);
        scale[0] = sp.load(getContext(), R.raw.effect_18_do, 3);
        scale[1] = sp.load(getContext(), R.raw.effect_18_re, 4);
        scale[2] = sp.load(getContext(), R.raw.effect_18_mi, 5);
        scale[3] = sp.load(getContext(), R.raw.effect_18_fa, 6);
        scale[4] = sp.load(getContext(), R.raw.effect_18_so, 7);
        scale[5] = sp.load(getContext(), R.raw.effect_18_la, 8);
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

        flower18.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch(motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        sp.play(starEffect,1,1,1,0,1);
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

                }

                return false;
            }
        });

        sqeed18.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch(motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        if(clickFlag) sp.play(scale[0],1,1,1,0,1);
                        break;

                }

                return false;
            }
        });

        man18.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch(motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        if(clickFlag) sp.play(scale[1],1,1,1,0,1);
                        break;

                }

                return false;
            }
        });

        post18.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch(motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        if(clickFlag) sp.play(scale[2],1,1,1,0,1);
                        break;

                }

                return false;
            }
        });

        tree18.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch(motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        if(clickFlag) sp.play(scale[3],1,1,1,0,1);
                        break;

                }

                return false;
            }
        });

        father18.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch(motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        if(clickFlag) sp.play(scale[4],1,1,1,0,1);
                        break;

                }

                return false;
            }
        });

        mom18.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch(motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        if(clickFlag) sp.play(scale[5],1,1,1,0,1);
                        break;

                }

                return false;
            }
        });
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
                    post18.startAnimation(postRotate[1]);
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
            if(rotateFlag[1]==1){
                sp.play(appear,1,1,1,0,1);
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
                    tree18.startAnimation(treeRotate[1]);
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
                    sqeed18.startAnimation(sqeedRotate[1]);
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
                    clickFlag=true;
                    break;
                case 2:
                    rotateFlag[3] = 0;
                    man18.startAnimation(manRotate[1]);
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
    }


    public void soundPlayFunc() {
        musicController = new MusicController(getActivity(), R.raw.scene_18);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(

                new String[]{"별이도 자꾸 하품이 나요.", "3000"},
                new String[]{"오징어 이모가 보드라운 감태침대에 \n" +
                        "별이를 눕히고 토닥토닥~ 해주어요.", "11000"},
                new String[]{"하늘에 금가루를 뿌린 것처럼 별님들이 많아요.", "17000"},
                new String[]{"별님들은 아~주아~주 먼 옛날부터 \n" +
                        "보물섬 독도를 밝혀주고 지켜주었지.", "26500"},
                new String[]{"저 많은 별님들이 다 독도를 지켜주는 거예요? ", "32500"},
                new String[]{"그럼~ 그래서 독도 가족들한테는 \n" +
                        "자기를 지켜주는 별님이 모두 하나씩 있단다.", "41500"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();

        father18.post(new Runnable() {
            @Override
            public void run() {
                blink = new AlphaAnimation(1, 0.3f);
                blink.setDuration(500);
                blink.setRepeatCount(Animation.INFINITE);
                blink.setRepeatMode(Animation.REVERSE);

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
