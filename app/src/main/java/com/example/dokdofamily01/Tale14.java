package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;

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
    ScaleAnimation sqeedHandAfterClinkAni;
    ScaleAnimation sqeedHandAfterClinkAni2;
    RotateAnimation sqeedHandRotateAni;

    AnimationSet bellAnimSet = new AnimationSet(false);
    AnimationSet sqeedHandScaleAnimSet = new AnimationSet(false);
    AnimationSet sqeedHandAfterClinkAnimSet = new AnimationSet(false);

    int bellClickFlag = 0;
    int animationFlag = 0;


    MediaPlayer mp = null;
    ArrayList<SubTitleData> subtitleList;

    private SoundPool bubbleSoundPool, lightSoundPool, bellSoundPool;
    private int bubbleSound, lightSound, bellSound;


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
        bubbleSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        bellSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        lightSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        bubbleSound = bubbleSoundPool.load(getContext(), R.raw.effect_14_bubble, 1);
        bellSound = bellSoundPool.load(getContext(), R.raw.effect_14_bell, 1);
        lightSound = lightSoundPool.load(getContext(), R.raw.effect_14_light, 1);
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
                sqeedHandScaleAni.setInterpolator(new AnticipateOvershootInterpolator());
                sqeedHandScaleAni.setStartOffset(700);
                sqeedHandScaleAni.setDuration(600);

                sqeedHandRotateAni = new RotateAnimation(10, 0, 0, sqeedHand.getHeight());
                sqeedHandRotateAni.setInterpolator(new AccelerateDecelerateInterpolator());
                sqeedHandRotateAni.setDuration(700);

                sqeedHandAfterClinkAni2 = new ScaleAnimation(0.95f, 1, 1, 1, 0, sqeedHand.getHeight());
                sqeedHandAfterClinkAni2.setInterpolator(new AnticipateOvershootInterpolator());
                sqeedHandScaleAni.setStartOffset(700);
                sqeedHandAfterClinkAni2.setDuration(700);

                sqeedHandAfterClinkAni = new ScaleAnimation(1, 0.95f, 1, 1, 0, sqeedHand.getHeight());
                sqeedHandAfterClinkAni.setInterpolator(new AccelerateDecelerateInterpolator());
                sqeedHandAfterClinkAni.setStartOffset(1400);
                sqeedHandAfterClinkAni.setDuration(1000);

                sqeedHandScaleAnimSet.addAnimation(sqeedHandAfterClinkAni);
                sqeedHandScaleAnimSet.addAnimation(sqeedHandScaleAni);
                sqeedHandScaleAnimSet.addAnimation(sqeedHandRotateAni);
                sqeedHandScaleAnimSet.addAnimation(sqeedHandFadein);
                sqeedHandScaleAnimSet.setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animationFlag = 0;
                        light.setVisibility(View.VISIBLE);
                        light.startAnimation(lightFadein);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        animationFlag = 1;
                        light.setVisibility(View.INVISIBLE);
                        bell.clearAnimation();
                    }
                });
                sqeedHandScaleAnimSet.setFillAfter(true);

                sqeedHandAfterClinkAnimSet.addAnimation(sqeedHandAfterClinkAni2);
                sqeedHandAfterClinkAnimSet.addAnimation(sqeedHandAfterClinkAni);
                sqeedHandAfterClinkAnimSet.setFillAfter(true);
                sqeedHandAfterClinkAnimSet.setAnimationListener(new MyAnimationListener(){
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animationFlag = 0;
                        bell.startAnimation(blink);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        animationFlag = 1;
                        bell.clearAnimation();
                    }
                });

                if (animationFlag == 0) {
                    animationClear();
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
        lightFadein.setStartOffset(700);
        lightFadein.setDuration(1500);
        lightFadein.setAnimationListener(new MyAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                bell.startAnimation(blink);
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
        blink.setDuration(500);
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
                if(animationFlag == 0) {
                    animationFlag = 1;
                    if(bellClickFlag == 0) {
                        bellClickFlag = 1;
                        sqeedHand.setVisibility(View.VISIBLE);
                        sqeedHand.startAnimation(sqeedHandScaleAnimSet);
                    }
                    else {
                        sqeedHand.clearAnimation();
                        sqeedHand.startAnimation(sqeedHandAfterClinkAnimSet);
                    }
                    bubbleSoundPool.play(bubbleSound, 1, 1, 0, 0, 1);
                    new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                    bellSoundPool.play(bellSound, 1, 1, 0, 0, 1);
                    new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    lightSoundPool.play(lightSound, 1, 1, 0, 0, 1);
                                }
                            }, 1100);
                        }
                    }, 1200);
                }
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

    private void animationClear() {
        cave.setVisibility(View.INVISIBLE);
        animationFlag = 0;
        bellClickFlag = 0;
        cave.clearAnimation();
        land.clearAnimation();
        sqeedBody.clearAnimation();
        sqeedHand.clearAnimation();
        byul.clearAnimation();
        bubble.clearAnimation();
        bell.clearAnimation();
        light.clearAnimation();
    }

    @Override
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

        if (caveAppearAni != null) {
            animationClear();
            animationFlag = 1;
            cave.startAnimation(caveAppearAni);
            land.startAnimation(landAppearAni);
            byul.startAnimation(byulAppearAni);
            sqeedBody.startAnimation(byulAppearAni);
            bell.startAnimation(bellAnimSet);
        }

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
