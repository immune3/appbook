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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.subtitleTextView;
import static com.example.dokdofamily01.TaleActivity.checkedAnimation;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale01 extends BaseFragment {

    int animationFlag = 0;
    ImageView lamp;
    ImageView lampLight;
    ImageView bedLight;
    ImageView head;
    ImageView blanket;
    ImageView byul;
    ImageView hand;
    ImageView curtain;
    ImageView light;
    AlphaAnimation fadeIn;
    AlphaAnimation fadeOut;

    ArrayList<SubTitleData> subtitleList;


    MediaPlayer mp = null;


    SoundPool sp;
    int soundID;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale01;
        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        lamp = (ImageView) layout.findViewById(R.id.lamp);
        lampLight = (ImageView) layout.findViewById(R.id.lampLight);
        bedLight = (ImageView) layout.findViewById(R.id.bedLight);
        head = (ImageView) layout.findViewById(R.id.head);
        blanket = (ImageView) layout.findViewById(R.id.blanket);
        byul = (ImageView) layout.findViewById(R.id.byul);
        hand = (ImageView) layout.findViewById(R.id.hand);
        curtain = (ImageView) layout.findViewById(R.id.curtain);
        light = (ImageView) layout.findViewById(R.id.light);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sp.load(getContext(), R.raw.effect_01, 1);
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
        fadeIn.setAnimationListener(new MyAnimationListener());

        fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new MyAnimationListener());

    }

    @Override
    public void setupEvents() {
        super.setupEvents();

//        CustomViewPager.isPageScrollEnabled = true;
        lamp.setOnTouchListener(new BlockObjListener());

    }

    @Override
    public void blockAnimFunc() {
        if (animationFlag == 0) {
            checkedAnimation = false;
            animationFlag = 1;
            sp.play(soundID, 4, 4, 0, 0, 1);
            lampLight.startAnimation(fadeIn);
            bedLight.startAnimation(fadeIn);
            head.setVisibility(View.VISIBLE);
            blanket.setVisibility(View.VISIBLE);
            curtain.setVisibility(View.VISIBLE);
            light.setVisibility(View.INVISIBLE);
            byul.setVisibility(View.INVISIBLE);
            hand.setVisibility(View.INVISIBLE);
        } else if (animationFlag == 5) {
            fadeIn.setStartOffset(0);
            fadeOut.setStartOffset(0);
            sp.play(soundID, 4, 4, 0, 0, 1);
            lampLight.startAnimation(fadeOut);
        } else if (animationFlag == 7) {
            sp.play(soundID, 4, 4, 0, 0, 1);
            lampLight.startAnimation(fadeIn);
        }
        super.blockAnimFunc();
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag) {
                case 1:
                    animationFlag = 2;
                    lampLight.clearAnimation();
                    bedLight.clearAnimation();
                    fadeOut.setStartOffset(1000);
                    head.startAnimation(fadeOut);
                    bedLight.startAnimation(fadeOut);
                    blanket.startAnimation(fadeOut);
                    break;
                case 2:
                    animationFlag = 3;
                    head.clearAnimation();
                    bedLight.clearAnimation();
                    blanket.clearAnimation();
                    bedLight.setVisibility(View.INVISIBLE);
                    head.setVisibility(View.INVISIBLE);
                    blanket.setVisibility(View.INVISIBLE);
                    byul.startAnimation(fadeIn);
                    break;
                case 3:
                    animationFlag = 4;
                    fadeIn.setStartOffset(1000);
                    fadeOut.setStartOffset(1000);
                    byul.clearAnimation();
                    hand.startAnimation(fadeIn);
                    curtain.startAnimation(fadeOut);
                    light.startAnimation(fadeIn);
                    break;
                case 4:
                    animationFlag = 5;
                    checkedAnimation = true;
                    curtain.setVisibility(View.INVISIBLE);
                    hand.clearAnimation();
                    curtain.clearAnimation();
                    light.clearAnimation();
                    break;
                case 6:
                    animationFlag = 7;
                    break;
                case 8:
                    animationFlag = 5;
                    break;

            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            if (animationFlag == 1) {
                lampLight.setVisibility(View.VISIBLE);
                bedLight.setVisibility(View.VISIBLE);
            } else if (animationFlag == 3) {
                byul.setVisibility(View.VISIBLE);
            } else if (animationFlag == 4) {
                hand.setVisibility(View.VISIBLE);
                light.setVisibility(View.VISIBLE);
            } else if (animationFlag == 5) {
                animationFlag = 6;
            } else if (animationFlag == 7) {
                animationFlag = 8;
            }
        }
    }


    @Override
    public void soundPlayFunc() {

        musicController = new MusicController(getActivity(), R.raw.scene_1);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"별님들이 소곤거리는 아직은 까만밤이에요", "5000"},
                new String[]{"콕콕... 콕콕콕...", "7500"},
                new String[]{"누가 별이 방 창문을 가만가만 두드려요.", "12500"},
                new String[]{"별이가 꼬물꼬물 일어나 창가로 가요.", "17000"},
                new String[]{"가슴이 콩콩거려 커튼을 아주 빼꼼히 열었는데", "22500"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();
        checkedAnimation = true;
        lampLight.clearAnimation();
        byul.clearAnimation();
        bedLight.clearAnimation();
        curtain.clearAnimation();
        light.clearAnimation();
        hand.clearAnimation();
        blanket.clearAnimation();
        head.clearAnimation();
        head.setVisibility(View.VISIBLE);
        blanket.setVisibility(View.VISIBLE);
        curtain.setVisibility(View.VISIBLE);
        light.setVisibility(View.INVISIBLE);
        lampLight.setVisibility(View.INVISIBLE);
        hand.setVisibility(View.INVISIBLE);
        bedLight.setVisibility(View.INVISIBLE);
        byul.setVisibility(View.INVISIBLE);
        animationFlag = 0;


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
