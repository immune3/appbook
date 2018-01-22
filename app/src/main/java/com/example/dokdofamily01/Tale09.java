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
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale09 extends BaseFragment {
    ImageView bird;
    ImageView birds1;
    ImageView birds2;
    ImageView birds3;
    ImageView birds4;
    ImageView birds5;
    ImageView birds6;
    ImageView byulBody1;
    ImageView byulBody2;
    ImageView byulBody3;
    ImageView byulBody4;
    ImageView byulBody5;
    ImageView byulHead1;
    ImageView byulHead2;
    ImageView byulHead3;
    ImageView byulHead4;
    ImageView byulHead5;
    ImageView birds;
    ImageView fatherDokdo;
    ImageView fatherDokdoHand;
    ImageView momDokdo;
    ImageView deco;
    ImageView sea;
    ImageView effect;
    Boolean isAuto;

    ScaleAnimation seagullAppear;
    AlphaAnimation appear;
    AlphaAnimation fadeIn;
    AlphaAnimation fadeOut;
    ScaleAnimation birdsAppear;
    TranslateAnimation decoAppear;
    TranslateAnimation fatherAppear;
    TranslateAnimation momAppear;
    TranslateAnimation seaAppear;
    TranslateAnimation wavingAnimation;
    AlphaAnimation blink;
    int appearFlag = 0;
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
        xml = R.layout.tale09;

        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        bird = (ImageView) layout.findViewById(R.id.bird);
        birds1 = (ImageView) layout.findViewById(R.id.birds1);
        birds2 = (ImageView) layout.findViewById(R.id.birds2);
        birds3 = (ImageView) layout.findViewById(R.id.birds3);
        birds4 = (ImageView) layout.findViewById(R.id.birds4);
        birds5 = (ImageView) layout.findViewById(R.id.birds5);
        birds6 = (ImageView) layout.findViewById(R.id.birds6);
        byulBody1 = (ImageView) layout.findViewById(R.id.byulBody1);
        byulBody2 = (ImageView) layout.findViewById(R.id.byulBody2);
        byulBody3 = (ImageView) layout.findViewById(R.id.byulBody3);
        byulBody4 = (ImageView) layout.findViewById(R.id.byulBody4);
        byulBody5 = (ImageView) layout.findViewById(R.id.byulBody5);
        byulHead1 = (ImageView) layout.findViewById(R.id.byulHead1);
        byulHead2 = (ImageView) layout.findViewById(R.id.byulHead2);
        byulHead3 = (ImageView) layout.findViewById(R.id.byulHead3);
        byulHead4 = (ImageView) layout.findViewById(R.id.byulHead4);
        byulHead5 = (ImageView) layout.findViewById(R.id.byulHead5);
        birds = (ImageView) layout.findViewById(R.id.birds);
        fatherDokdo = (ImageView) layout.findViewById(R.id.fatherDokdo);
        fatherDokdoHand = (ImageView) layout.findViewById(R.id.fatherDokdoHand);
        momDokdo = (ImageView) layout.findViewById(R.id.momDokdo);
        deco = (ImageView) layout.findViewById(R.id.deco);
        sea = (ImageView) layout.findViewById(R.id.sea);
        effect = (ImageView) layout.findViewById(R.id.effect);

    }

    @Override
    public void setValues() {
        super.setValues();

    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setStartOffset(280);
        fadeIn.setDuration(500);
//        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new MyAnimationListener());

        fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setStartOffset(-200);
        fadeOut.setDuration(800);
        fadeOut.setFillAfter(true);

        blink = new AlphaAnimation(0.3f, 1);
        blink.setDuration(500);
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);

    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        bird.setOnTouchListener(new BlockObjListener());

    }

    @Override
    public void blockAnimFunc() {
        if (animationFlag == 0) {
            animationFlag = 1;
            checkedAnimation = false;
            bird.clearAnimation();
            byulHead1.clearAnimation();
            byulHead2.clearAnimation();
            byulHead3.clearAnimation();
            byulHead4.clearAnimation();
            byulHead5.clearAnimation();
            byulBody1.clearAnimation();
            byulBody2.clearAnimation();
            byulBody3.clearAnimation();
            byulBody4.clearAnimation();
            byulBody5.clearAnimation();

            byulBody5.setVisibility(View.INVISIBLE);
            byulHead5.setVisibility(View.INVISIBLE);

            soundID = sp.load(getContext(), R.raw.effect_09_click_bird, 1);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    byulBody1.startAnimation(fadeIn);
                    byulHead1.startAnimation(fadeIn);
                }
            }, 200);

        } else {
            soundID = sp.load(getContext(), R.raw.effect_09_click_bird, 1);
        }
        super.blockAnimFunc();
    }

    private class AppearAnimationListener extends com.example.dokdofamily01.MyAnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            super.onAnimationStart(animation);
            switch (appearFlag) {
                case 1:
                    fatherDokdo.setVisibility(View.VISIBLE);
                    momDokdo.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    bird.setVisibility(View.VISIBLE);
                    fatherDokdoHand.setVisibility(View.VISIBLE);
                    birds1.setVisibility(View.VISIBLE);
                    birds2.setVisibility(View.VISIBLE);
                    birds3.setVisibility(View.VISIBLE);
                    birds4.setVisibility(View.VISIBLE);
                    birds5.setVisibility(View.VISIBLE);
                    birds6.setVisibility(View.VISIBLE);
                    effect.setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            switch (appearFlag) {
                case 1:
                    appearFlag = 2;
                    bird.startAnimation(appear);
                    fatherDokdoHand.startAnimation(appear);
                    effect.startAnimation(appear);
                    birds1.startAnimation(seagullAppear);
                    birds2.startAnimation(seagullAppear);
                    birds3.startAnimation(seagullAppear);
                    birds4.startAnimation(seagullAppear);
                    birds5.startAnimation(seagullAppear);
                    birds6.startAnimation(seagullAppear);
                    break;
                case 2:
                    appearFlag = 0;
                    bird.startAnimation(blink);
                    checkedAnimation = true;
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            super.onAnimationRepeat(animation);
        }
    }

    private class MyAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {

            switch (animationFlag) {
                case 1:
//                    byulBody1.setVisibility(View.VISIBLE);
//                    byulHead1.setVisibility(View.VISIBLE);
                    break;
                case 2:
//                    byulBody2.setVisibility(View.VISIBLE);
//                    byulHead2.setVisibility(View.VISIBLE);
                    break;
                case 3:
//                    byulBody3.setVisibility(View.VISIBLE);
//                    byulHead3.setVisibility(View.VISIBLE);
                    break;
                case 4:
//                    byulBody4.setVisibility(View.VISIBLE);
//                    byulHead4.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    byulBody5.setVisibility(View.VISIBLE);
                    byulHead5.setVisibility(View.VISIBLE);
                    break;
            }
        }
        @Override
        public void onAnimationEnd(Animation animation) {
            soundID = sp.load(getContext(), R.raw.effect_09_move_byul, 1);
            switch (animationFlag) {
                case 1:
                    animationFlag = 2;
                    byulBody1.clearAnimation();
                    byulHead1.clearAnimation();
                    byulBody2.clearAnimation();
                    byulHead2.clearAnimation();
                    byulBody1.startAnimation(fadeOut);
                    byulHead1.startAnimation(fadeOut);
                    byulBody2.startAnimation(fadeIn);
                    byulHead2.startAnimation(fadeIn);
                    break;
                case 2:
                    animationFlag = 3;
                    byulBody1.clearAnimation();
                    byulHead1.clearAnimation();
                    byulBody2.clearAnimation();
                    byulHead2.clearAnimation();
                    byulBody2.startAnimation(fadeOut);
                    byulHead2.startAnimation(fadeOut);
                    byulBody3.startAnimation(fadeIn);
                    byulHead3.startAnimation(fadeIn);
                    break;
                case 3:
                    animationFlag = 4;
                    byulBody2.clearAnimation();
                    byulHead2.clearAnimation();
                    byulBody3.clearAnimation();
                    byulHead3.clearAnimation();
                    byulBody3.startAnimation(fadeOut);
                    byulHead3.startAnimation(fadeOut);
                    byulBody4.startAnimation(fadeIn);
                    byulHead4.startAnimation(fadeIn);

                    break;
                case 4:
                    animationFlag = 5;
                    byulBody3.clearAnimation();
                    byulHead3.clearAnimation();
                    byulBody4.clearAnimation();
                    byulHead4.clearAnimation();
                    byulBody4.startAnimation(fadeOut);
                    byulHead4.startAnimation(fadeOut);
                    byulBody5.startAnimation(fadeIn);
                    byulHead5.startAnimation(fadeIn);
                    break;
                case 5:
                    animationFlag = 0;
                    byulBody4.clearAnimation();
                    byulHead4.clearAnimation();
                    byulBody5.startAnimation(fadeOut);
                    byulHead5.startAnimation(fadeOut);
                    bird.startAnimation(blink);
                    checkedAnimation = true;
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }
    private void animationClear(){
        byulBody5.setVisibility(View.INVISIBLE);
        byulHead5.setVisibility(View.INVISIBLE);
        effect.setVisibility(View.INVISIBLE);
        bird.setVisibility(View.INVISIBLE);
        fatherDokdo.setVisibility(View.INVISIBLE);
        fatherDokdoHand.setVisibility(View.INVISIBLE);
        momDokdo.setVisibility(View.INVISIBLE);
        birds.setVisibility(View.INVISIBLE);
        birds1.setVisibility(View.INVISIBLE);
        birds2.setVisibility(View.INVISIBLE);
        birds3.setVisibility(View.INVISIBLE);
        birds4.setVisibility(View.INVISIBLE);
        birds5.setVisibility(View.INVISIBLE);
        birds6.setVisibility(View.INVISIBLE);
        bird.clearAnimation();
    }

    @Override
    public void soundPlayFunc() {
        this.isAuto = getArguments().getBoolean("isAuto");

        if(isAuto) {
            musicController = new MusicController(getActivity(), R.raw.scene_9, vp,
                    new int[]{R.drawable.sub_09_01, 3000},
                    new int[]{R.drawable.sub_09_02, 5800},
                    new int[]{R.drawable.sub_09_03, 12000},
                    new int[]{R.drawable.sub_09_04, 16500},
                    new int[]{R.drawable.sub_09_05, 20500},
                    new int[]{R.drawable.sub_09_06, 26000},
                    new int[]{R.drawable.sub_09_07, 32500},
                    new int[]{R.drawable.sub_09_08, 37500},
                    new int[]{R.drawable.sub_09_09, 48000},
                    new int[]{R.drawable.sub_09_10, 99999});
        } else {
            subtitleController = new SubtitleController(vp,
                    R.drawable.sub_09_01,
                    R.drawable.sub_09_02,
                    R.drawable.sub_09_03,
                    R.drawable.sub_09_04,
                    R.drawable.sub_09_05,
                    R.drawable.sub_09_06,
                    R.drawable.sub_09_07,
                    R.drawable.sub_09_08,
                    R.drawable.sub_09_09,
                    R.drawable.sub_09_10);
        }

        birds2.post(new Runnable() {
            @Override
            public void run() {
                sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
                sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        sp.play(soundID, 0.3f, 0.3f, 0, 0, 1);
                    }
                });

                int birdsWidth = birds2.getWidth();
                int birdsHeight = (int) (birds2.getHeight() * 1.3);
                seagullAppear = new ScaleAnimation(0, 1, 0, 1, birdsWidth, birdsHeight);
                seagullAppear.setDuration(1000);
//                seagullAppear.setFillAfter(true);

//                birdsAppear = AnimationUtils.loadAnimation(getContext(), R.anim.anim_09_birds_appear);
                birdsAppear = new ScaleAnimation(3,1,3,1, birds.getWidth()*0.5f, birds.getHeight()*0.5f);
                birdsAppear.setDuration(2000);
                birdsAppear.setFillAfter(true);

                decoAppear = new TranslateAnimation(0, 0, (int) (deco.getHeight() * 0.7), 0);
                decoAppear.setDuration(1000);
                decoAppear.setFillAfter(true);
                decoAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                decoAppear.setAnimationListener(new AppearAnimationListener());

                seaAppear = new TranslateAnimation(0, 0, (int) (sea.getHeight() * 0.7), 0);
                seaAppear.setDuration(1000);
                seaAppear.setFillAfter(true);
                seaAppear.setInterpolator(new AccelerateDecelerateInterpolator());
//                seaAppear.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        sea.startAnimation(wavingAnimation);
//                        effect.startAnimation(wavingAnimation);
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });

                fatherAppear = new TranslateAnimation(-fatherDokdo.getWidth(), 0, 0, 0);
                fatherAppear.setStartOffset(200);
                fatherAppear.setDuration(1000);
                fatherAppear.setFillAfter(true);
                fatherAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                fatherAppear.setAnimationListener(new AppearAnimationListener());

                momAppear = new TranslateAnimation(momDokdo.getWidth(), 0, 0, 0);
                momAppear.setStartOffset(300);
                momAppear.setDuration(1000);
                momAppear.setFillAfter(true);
                momAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                momAppear.setAnimationListener(new AppearAnimationListener());

                wavingAnimation = new TranslateAnimation(0, 0, 0, sea.getHeight() * 0.02f);
                wavingAnimation.setDuration(2500);
                wavingAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                wavingAnimation.setRepeatCount(Animation.INFINITE);
                wavingAnimation.setRepeatMode(Animation.REVERSE);

//                appear = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
                appear = new AlphaAnimation(0, 1);
                appear.setDuration(1000);
                appear.setAnimationListener(new AppearAnimationListener());

                effect.setVisibility(View.INVISIBLE);
                bird.setVisibility(View.INVISIBLE);
                fatherDokdo.setVisibility(View.INVISIBLE);
                fatherDokdoHand.setVisibility(View.INVISIBLE);
                momDokdo.setVisibility(View.INVISIBLE);
                birds.setVisibility(View.INVISIBLE);
                birds1.setVisibility(View.INVISIBLE);
                birds2.setVisibility(View.INVISIBLE);
                birds3.setVisibility(View.INVISIBLE);
                birds4.setVisibility(View.INVISIBLE);
                birds5.setVisibility(View.INVISIBLE);
                birds6.setVisibility(View.INVISIBLE);

                if (appearFlag == 0 && animationFlag == 0) {
                    animationClear();
                    checkedAnimation = false;
                    appearFlag = 1;
                    sea.startAnimation(seaAppear);
//                    effect.startAnimation(seaAppear);
                    birds.startAnimation(birdsAppear);
                    deco.startAnimation(decoAppear);
                    fatherDokdo.startAnimation(fatherAppear);
                    momDokdo.startAnimation(momAppear);
                }
            }
        });

//
//        if (birdsAppear != null) {
//            appearFlag = 1;
//            birds.startAnimation(birdsAppear);
//            deco.startAnimation(decoAppear);
//            fatherDokdo.startAnimation(fatherAppear);
//            momDokdo.startAnimation(momAppear);
//            sea.startAnimation(seaAppear);
//            effect.startAnimation(seaAppear);
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
