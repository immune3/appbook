package com.example.dokdofamily01;

import android.media.AudioManager;
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
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
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
    Boolean isAuto;

    TranslateAnimation caveAppearAni;
    TranslateAnimation byulAppearAni;
    TranslateAnimation landAppearAni;
    TranslateAnimation bellAppearAni;
    AlphaAnimation fadein;
    AlphaAnimation lightFadein;
    AlphaAnimation blink;
    AlphaAnimation sqeedHandFadein;
    ScaleAnimation sqeedHandScaleAni;
    ScaleAnimation sqeedHandAfterClinkAni;
    ScaleAnimation sqeedHandAfterClinkAni2;
    RotateAnimation sqeedHandRotateAni;

    AnimationSet sqeedHandScaleAnimSet;
    AnimationSet sqeedHandAfterClinkAnimSet;

    int bellClickFlag = 0;
    int animationFlag = 0;

    private SoundPool bubbleSoundPool;
    private int soundID;


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
        bell.setOnTouchListener(new BlockObjListener());
    }

    @Override
    public void blockAnimFunc() {
        if(animationFlag == 0) {
            checkedAnimation=false;
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
            soundID = bubbleSoundPool.load(getContext(), R.raw.effect_14_bubble, 1);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    soundID = bubbleSoundPool.load(getContext(), R.raw.effect_14_bell, 2);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(bellClickFlag==1) {
                                bellClickFlag=2;
                                soundID = bubbleSoundPool.load(getContext(), R.raw.effect_14_light, 3);
                            }
                        }
                    }, 1500);
                }
            }, 1200);
        }
        super.blockAnimFunc();
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            if(animationFlag==1){
                animationFlag = 0;
                bubble.setVisibility(View.VISIBLE);
                bubble.startAnimation(fadein);
                checkedAnimation = true;
            }
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
//        cave.setVisibility(View.INVISIBLE);
        animationFlag = 0;
        bellClickFlag = 0;
        cave.clearAnimation();
        bell.clearAnimation();
        land.clearAnimation();
        sqeedBody.clearAnimation();
        sqeedHand.clearAnimation();
        byul.clearAnimation();
        bubble.clearAnimation();
        light.clearAnimation();
    }

    @Override
    public void soundPlayFunc() {
        this.isAuto = getArguments().getBoolean("isAuto");

        checkedAnimation = false;

        if(isAuto) {
            musicController = new MusicController(((TaleActivity)getActivity()), R.raw.scene_14, vp,
                    new int[]{R.drawable.sub_14_01, 5000},
                    new int[]{R.drawable.sub_14_02, 8500},
                    new int[]{R.drawable.sub_14_03, 11500},
                    new int[]{R.drawable.sub_14_04, 16000},
                    new int[]{R.drawable.sub_14_05, 19000},
                    new int[]{R.drawable.sub_14_06, 25500},
                    new int[]{R.drawable.sub_14_07, 99999});
        } else {
            subtitleController = new SubtitleController(((TaleActivity)getActivity()), vp,
                    R.drawable.sub_14_01,
                    R.drawable.sub_14_02,
                    R.drawable.sub_14_03,
                    R.drawable.sub_14_04,
                    R.drawable.sub_14_05,
                    R.drawable.sub_14_06,
                    R.drawable.sub_14_07);
        }

        land.post(new Runnable() {
            @Override
            public void run() {
                bubbleSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 1);
                bubbleSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        bubbleSoundPool.play(soundID, 1, 1, 0, 0, 1);
                    }
                });

                fadein = new AlphaAnimation(0, 1);
                fadein.setDuration(1500);

                lightFadein = new AlphaAnimation(0, 1);
                lightFadein.setStartOffset(700);
                lightFadein.setDuration(1500);
                lightFadein.setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        bell.startAnimation(blink);
                        checkedAnimation = true;
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
                blink.setDuration(600);
                blink.setRepeatCount(Animation.INFINITE);
                blink.setRepeatMode(Animation.REVERSE);

                sqeedHandScaleAnimSet = new AnimationSet(false);
                sqeedHandAfterClinkAnimSet = new AnimationSet(false);

                landAppearAni = new TranslateAnimation(0, 0, land.getHeight(), 0);
                landAppearAni.setDuration(2000);
                landAppearAni.setInterpolator(new AccelerateDecelerateInterpolator());
                landAppearAni.setFillAfter(true);

                caveAppearAni = new TranslateAnimation(cave.getWidth(), 0, 0, 0);
                caveAppearAni.setStartOffset(500);
                caveAppearAni.setDuration(2500);
                caveAppearAni.setInterpolator(new AccelerateDecelerateInterpolator());

                bellAppearAni = new TranslateAnimation(cave.getWidth(), 0, 0, 0);
                bellAppearAni.setStartOffset(500);
                bellAppearAni.setDuration(2500);
                bellAppearAni.setInterpolator(new AccelerateDecelerateInterpolator());
                bellAppearAni.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        bell.startAnimation(blink);
                        checkedAnimation = true;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
//                caveAppearAni.setFillAfter(true);

                byulAppearAni = new TranslateAnimation(-sqeedBody.getWidth(), 0, sqeedBody.getHeight(), 0);
                byulAppearAni.setDuration(2000);
                byulAppearAni.setStartOffset(1400);
                byulAppearAni.setInterpolator(new AccelerateDecelerateInterpolator());
                byulAppearAni.setFillAfter(true);
                byulAppearAni.setAnimationListener(new MyAnimationListener());

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
                        checkedAnimation = true;
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

                animationClear();
                checkedAnimation = false;
                animationFlag = 1;
                land.startAnimation(landAppearAni);
                byul.startAnimation(byulAppearAni);
                sqeedBody.startAnimation(byulAppearAni);
                cave.startAnimation(caveAppearAni);
                bell.startAnimation(bellAppearAni);
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
            if(bubbleSoundPool != null) {
                bubbleSoundPool.release();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void returnMemory() {

        cave = null;
        land = null;
        sqeedBody = null;
        sqeedHand = null;
        byul = null;
        bubble = null;
        bell = null;
        light = null;

        if(caveAppearAni != null) caveAppearAni.cancel();
        if(byulAppearAni != null) byulAppearAni.cancel();
        if(landAppearAni != null) landAppearAni.cancel();
        if(bellAppearAni != null) bellAppearAni.cancel();
        if(fadein != null) fadein.cancel();
        if(lightFadein != null) lightFadein.cancel();
        if(blink != null) blink.cancel();
        if(sqeedHandFadein != null) sqeedHandFadein.cancel();
        if(sqeedHandScaleAni != null) sqeedHandScaleAni.cancel();
        if(sqeedHandAfterClinkAni != null) sqeedHandAfterClinkAni.cancel();
        if(sqeedHandAfterClinkAni2 != null) sqeedHandAfterClinkAni2.cancel();
        if(sqeedHandRotateAni != null) sqeedHandRotateAni.cancel();

        caveAppearAni = null;
        byulAppearAni = null;
        landAppearAni = null;
        bellAppearAni = null;
        fadein = null;
        lightFadein = null;
        blink = null;
        sqeedHandFadein = null;
        sqeedHandScaleAni = null;
        sqeedHandAfterClinkAni = null;
        sqeedHandAfterClinkAni2 = null;
        sqeedHandRotateAni = null;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        returnMemory();
    }
}
