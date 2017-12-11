package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.homeKeyFlag;
import static com.example.dokdofamily01.TaleActivity.screenFlag;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale14 extends BaseFragment {
    ImageView cave;
    ImageView land;
    ImageView sqeedBody;
    ImageView sqeedHand;
    ImageView byul;
    ImageView bubble;
    ImageView bell;
    ImageView light;

    TranslateAnimation caveAppearAni;
    TranslateAnimation byulAppearAni;
    TranslateAnimation landAppearAni;
    AlphaAnimation fadein;
    AlphaAnimation lightFadein;
    AlphaAnimation blink;
    AlphaAnimation sqeedHandFadein;
    ScaleAnimation sqeedHandScaleAni;
    RotateAnimation sqeedHandRotateAni;

    AnimationSet bellAnimSet = new AnimationSet(false);
    AnimationSet sqeedHandScaleAnimSet = new AnimationSet(false);

    int animationFlag = 0;


    boolean isAttached = false;
    boolean isHint;
    MediaPlayer mp = null;
    MusicController musicController;
    ArrayList<SubTitleData> subtitleList;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isAttached = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        isHint = isVisibleToUser;
        super.setUserVisibleHint(isVisibleToUser);
        if (isAttached) {
            if (isVisibleToUser) {
                System.out.println("PlayByHint");
                soundPlayFunc();
            } else {
                CheckMP checkMP = new CheckMP(musicController);
          checkMP.execute();
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale14;
        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        if (isHint && !homeKeyFlag && screenFlag) {
            soundPlayFunc();
        }
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (musicController != null) {
            CheckMP checkMP = new CheckMP(musicController);
          checkMP.execute();
        }
    }


    @Override
    public void bindViews() {
        super.bindViews();
        cave = (ImageView) layout.findViewById(R.id.cave);
        land = (ImageView) layout.findViewById(R.id.land);
        sqeedBody = (ImageView) layout.findViewById(R.id.sqeedBody);
        sqeedHand = (ImageView) layout.findViewById(R.id.sqeedHand);
        byul = (ImageView) layout.findViewById(R.id.byul);
        bubble = (ImageView) layout.findViewById(R.id.bubble);
        bell = (ImageView) layout.findViewById(R.id.bell);
        light = (ImageView) layout.findViewById(R.id.light);
    }

    @Override
    public void setValues() {
        super.setValues();
        land.post(new Runnable() {
            @Override
            public void run() {
                caveAppearAni = new TranslateAnimation(cave.getWidth(), 0, 0, 0);
                caveAppearAni.setDuration(1500);
                caveAppearAni.setStartOffset(1000);
                caveAppearAni.setInterpolator(new AccelerateDecelerateInterpolator());
                caveAppearAni.setFillAfter(true);

                byulAppearAni = new TranslateAnimation(-sqeedBody.getWidth(), 0, sqeedBody.getHeight(), 0);
                byulAppearAni.setDuration(2000);
                byulAppearAni.setStartOffset(1400);
                byulAppearAni.setInterpolator(new AccelerateDecelerateInterpolator());
                byulAppearAni.setFillAfter(true);
                byulAppearAni.setAnimationListener(new MyAnimationListener());

                landAppearAni = new TranslateAnimation(0, 0, land.getHeight(), 0);
                landAppearAni.setDuration(2000);
                landAppearAni.setInterpolator(new AccelerateDecelerateInterpolator());
                landAppearAni.setFillAfter(true);
//                landAppearAni.setAnimationListener(new MyAnimationListener());

                bellAnimSet.addAnimation(caveAppearAni);
                bellAnimSet.addAnimation(blink);

//                Log.d("123123", "msg"+sqeedHand.getWidth());
                sqeedHandScaleAni = new ScaleAnimation(0.95f, 1, 1, 1, 0, sqeedHand.getHeight());
                sqeedHandScaleAni.setInterpolator(new AccelerateDecelerateInterpolator());
                sqeedHandScaleAni.setInterpolator(new AnticipateOvershootInterpolator());
                sqeedHandScaleAni.setStartOffset(700);
                sqeedHandScaleAni.setDuration(700);

                sqeedHandRotateAni = new RotateAnimation(10, 0, 0, sqeedHand.getHeight());
                sqeedHandRotateAni.setInterpolator(new AccelerateDecelerateInterpolator());
                sqeedHandRotateAni.setDuration(700);

                sqeedHandScaleAnimSet.addAnimation(sqeedHandScaleAni);
                sqeedHandScaleAnimSet.addAnimation(sqeedHandRotateAni);
                sqeedHandScaleAnimSet.addAnimation(sqeedHandFadein);
                sqeedHandScaleAnimSet.setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        light.setVisibility(View.VISIBLE);
                        light.startAnimation(lightFadein);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        light.setVisibility(View.INVISIBLE);
                        bell.clearAnimation();
                    }
                });

                if (animationFlag == 0) {
                    animationFlag = 1;
                    cave.startAnimation(caveAppearAni);
                    land.startAnimation(landAppearAni);
                    byul.startAnimation(byulAppearAni);
                    sqeedBody.startAnimation(byulAppearAni);
                    bell.startAnimation(bellAnimSet);
                }
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadein = new AlphaAnimation(0, 1);
        fadein.setDuration(1500);

        lightFadein = new AlphaAnimation(0, 1);
        lightFadein.setStartOffset(1000);
        lightFadein.setDuration(1500);
        lightFadein.setAnimationListener(new MyAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                //bell.startAnimation(blink);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });

        sqeedHandFadein = new AlphaAnimation(0, 1);
        sqeedHandFadein.setDuration(300);

        blink = new AlphaAnimation(1, 0.3f);
        blink.setDuration(1000);
        blink.setInterpolator(new LinearInterpolator());
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);

    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqeedHand.setVisibility(View.VISIBLE);
                sqeedHand.startAnimation(sqeedHandScaleAnimSet);
            }
        });

    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            animationFlag = 0;
            bubble.setVisibility(View.VISIBLE);
            bubble.startAnimation(fadein);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            light.setVisibility(View.INVISIBLE);
            sqeedHand.setVisibility(View.INVISIBLE);
            bubble.setVisibility(View.INVISIBLE);
        }

    }

    public void soundPlayFunc() {
        musicController = new MusicController(getActivity(), R.raw.scene_14);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"혹돔굴에 가까워질수록 어둡고 깜깜해져요. ", "5000"},
                new String[]{"졸졸 따라오던 해님도 안보여요. ", "8500"},
                new String[]{"왠지 으스스한 느낌이 들어서 ", "11500"},
                new String[]{"별이는 오징어 이모한테 찰싹~ 붙어요. ", "16000"},
                new String[]{"드디어 혹돔굴이에요. ", "19000"},
                new String[]{"오징어 이모가 다시 긴 다리를 쭈욱 늘여 \n" +
                        "초인종을 꾸욱 눌러요. ", "25500"},
                new String[]{"딩동딩동~", "28500"}

        );
        musicController.excuteAsync();
        mp = musicController.getMp();

        if (animationFlag == 0 && caveAppearAni != null) {
            animationFlag = 1;
            cave.startAnimation(caveAppearAni);
            land.startAnimation(landAppearAni);
            byul.startAnimation(byulAppearAni);
            sqeedBody.startAnimation(byulAppearAni);
            bell.startAnimation(bellAnimSet);
        }

    }

}
