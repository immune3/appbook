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

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale17 extends BaseFragment {


    MediaPlayer mp = null;

    ArrayList<SubTitleData> subtitleList;

    ImageView dokdo_under_sea;
    ImageView wave_shadow1;
    ImageView wave_shadow2;
    ImageView star;

    AlphaAnimation fadeIn;
    AlphaAnimation blink;
    int animationFlag = 0;

    SoundPool sp;
    int soundID;
//    int clickStar;
//    int appearDokdo;


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
        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(3000);
        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new MyAnimationListener());

        blink = new AlphaAnimation(1, 0.3f);
        blink.setDuration(500);
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);
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
        musicController = new MusicController(getActivity(), R.raw.scene_17);
//        subtitleList = new ArrayList<>();
//        subtitleList = musicController.makeSubTitleList(
//                new String[]{"깜깜해지면...", "1500"},
//                new String[]{"이렇게나 깊고 깊은 바닷속까지도", "5000"},
//                new String[]{"서로를 보살피는 독도 가족들의 소리가 잘 들리지~", "9500"},
//                new String[]{"동도할머니랑 서도할아버지가 \n" +
//                        "동해바다에 정답게 마주 앉은 ", "15500"},
//                new String[]{"아~주아~주 먼 옛날부터 오늘까지", "19000"},
//                new String[]{"우리들은 언제나 서로 의지하고 \n" +
//                        "함께 지켜주며 지내왔단다. ", "25000"}
//        );

        musicController.makeSubTitleList(
                new int[]{R.drawable.sub_17_01, 1500},
                new int[]{R.drawable.sub_17_02, 5000},
                new int[]{R.drawable.sub_17_03, 9500},
                new int[]{R.drawable.sub_17_04, 15500},
                new int[]{R.drawable.sub_17_05, 19000},
                new int[]{R.drawable.sub_17_06, 99999}
        );


        musicController.setVP(vp);
        musicController.excuteAsync();
        mp = musicController.getMp();
        checkedAnimation = true;

        sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                sp.play(soundID, 1, 1, 0, 0, 1);
            }
        });

        if (blink != null) {
            animationFlag = 0;
            dokdo_under_sea.clearAnimation();
            star.startAnimation(blink);
        }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
