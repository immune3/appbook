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
import android.widget.ImageView;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale17 extends BaseFragment {

    ImageView dokdo_under_sea;
    ImageView wave_shadow1;
    ImageView wave_shadow2;
    ImageView star;

    Boolean isAuto;
    AlphaAnimation fadeIn;
    AlphaAnimation blink;
    int animationFlag = 0;

    SoundPool sp;
    int soundID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale17;


        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void bindViews() {
        super.bindViews();
        dokdo_under_sea = (ImageView) layout.findViewById(R.id.dokdo_undersea);
        wave_shadow1 = (ImageView) layout.findViewById(R.id.wave_shadow_01);
        wave_shadow2 = (ImageView) layout.findViewById(R.id.wave_shadow_02);
        star = (ImageView) layout.findViewById(R.id.star);
    }

    @Override
    public void setValues() {
        super.setValues();
        star.post(new Runnable() {
            @Override
            public void run() {
                fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setDuration(3000);
                fadeIn.setFillAfter(true);
                fadeIn.setAnimationListener(new MyAnimationListener());

                blink = new AlphaAnimation(1, 0.3f);
                blink.setDuration(500);
                blink.setRepeatCount(Animation.INFINITE);
                blink.setRepeatMode(Animation.REVERSE);

                if (blink != null) {
                    animationFlag = 0;
                    star.startAnimation(blink);
                    dokdo_under_sea.clearAnimation();
                }
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        star.setOnTouchListener(new BlockObjListener());

    }

    @Override
    public void blockAnimFunc() {
        if (animationFlag == 0) {
            animationFlag = 1;
            checkedAnimation = false;
            star.clearAnimation();
            soundID = sp.load(getContext(), R.raw.effect_17_click_star, 1);
            dokdo_under_sea.startAnimation(fadeIn);
        }
        super.blockAnimFunc();
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            soundID = sp.load(getContext(), R.raw.effect_17_appear_dokdo, 2);
            checkedAnimation = true;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    @Override
    public void soundPlayFunc() {
        this.isAuto = getArguments().getBoolean("isAuto");

        checkedAnimation = false;

        if(isAuto) {
            musicController = new MusicController(((TaleActivity)getActivity()), R.raw.scene_17, vp,
                    new int[]{R.drawable.sub_17_01, 1500},
                    new int[]{R.drawable.sub_17_02, 5000},
                    new int[]{R.drawable.sub_17_03, 9500},
                    new int[]{R.drawable.sub_17_04, 15500},
                    new int[]{R.drawable.sub_17_05, 19000},
                    new int[]{R.drawable.sub_17_06, 99999});
        } else {
            subtitleController = new SubtitleController(((TaleActivity)getActivity()), vp,
                    R.drawable.sub_17_01,
                    R.drawable.sub_17_02,
                    R.drawable.sub_17_03,
                    R.drawable.sub_17_04,
                    R.drawable.sub_17_05,
                    R.drawable.sub_17_06);
        }

        sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                sp.play(soundID, 0.3f, 0.3f, 0, 0, 1);
            }
        });

        if (blink != null) {
            animationFlag = 0;
            dokdo_under_sea.clearAnimation();
            star.startAnimation(blink);
        }

        checkedAnimation = true;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            if (sp != null) {
                sp.release();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void returnMemory() {

        dokdo_under_sea = null;
        wave_shadow1 = null;
        wave_shadow2 = null;
        star = null;

        if(fadeIn != null) fadeIn.cancel();
        if(blink != null) blink.cancel();

        fadeIn = null;
        blink = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        returnMemory();
    }
}
