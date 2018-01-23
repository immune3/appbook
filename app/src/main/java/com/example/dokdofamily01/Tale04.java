package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale04 extends BaseFragment {
    ImageView dokdo;
    ImageView sun;
    ImageView sunLight;
    TranslateAnimation sunRiseAni;
    AlphaAnimation sunLightAppear;
    AlphaAnimation blink;
    int animationFlag = 0;
    Boolean isAuto;

    int[] sunLightLocation = new int[2];

    SoundPool sp;
    int soundID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale04;

        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }




    @Override
    public void bindViews() {
        super.bindViews();
        dokdo = (ImageView) layout.findViewById(R.id.dokdo);
        sun = (ImageView) layout.findViewById(R.id.sun);
        sunLight = (ImageView) layout.findViewById(R.id.sunLight);
    }

    @Override
    public void setValues() {
        super.setValues();
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        sunLightAppear = new AlphaAnimation(0, 1);
        sunLightAppear.setDuration(3000);
        sunLightAppear.setFillAfter(true);

        blink = new AlphaAnimation(1, 0.3f);
        blink.setDuration(500);
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);


    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        dokdo.setOnTouchListener(new BlockObjListener());
    }

    @Override
    public void blockAnimFunc() {
        if(animationFlag==0){
            dokdo.clearAnimation();
            checkedAnimation = false;
            soundID = sp.load(getContext(), R.raw.effect_04_sun, 1);
            sun.startAnimation(sunRiseAni);
        }
        super.blockAnimFunc();
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            checkedAnimation = true;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            if (animationFlag == 0) {
                animationFlag = 1;
                sunLight.setVisibility(View.VISIBLE);
                sunLight.startAnimation(sunLightAppear);
            }
        }

    }

    @Override
    public void soundPlayFunc() {

        this.isAuto = getArguments().getBoolean("isAuto");

        if(isAuto) {
            musicController = new MusicController(getActivity(), R.raw.scene_4, vp,
                    new int[]{R.drawable.sub_04_01, 5000},
                    new int[]{R.drawable.sub_04_02, 10500},
                    new int[]{R.drawable.sub_04_03, 16000},
                    new int[]{R.drawable.sub_04_04, 22500},
                    new int[]{R.drawable.sub_04_05, 99999});

        } else{
            subtitleController = new SubtitleController(vp,
                    R.drawable.sub_04_01,
                    R.drawable.sub_04_02,
                    R.drawable.sub_04_03,
                    R.drawable.sub_04_04,
                    R.drawable.sub_04_05);
        }


        checkedAnimation = true;
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                sp.play(soundID, 0.6f, 0.6f, 1, 0, 1);
            }
        });
        animationFlag=0;
        sun.clearAnimation();
        sunLight.clearAnimation();
        sunLight.setVisibility(View.INVISIBLE);

        dokdo.clearAnimation();
        dokdo.post(new Runnable() {
            @Override
            public void run() {
//                sun.setTranslationY(sun.getHeight()*0.6f);

                sunRiseAni = new TranslateAnimation(0, 0, 0, -(sun.getHeight()*0.5f));
                sunRiseAni.setDuration(3000);
                sunRiseAni.setFillAfter(true);
                sunRiseAni.setAnimationListener(new MyAnimationListener());

                animationFlag=0;
                sun.clearAnimation();
                sunLight.clearAnimation();
                sunLight.setVisibility(View.INVISIBLE);

                sun.startAnimation(blink);
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





