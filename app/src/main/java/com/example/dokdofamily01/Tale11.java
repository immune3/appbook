package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.MotionEvent;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
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

public class Tale11 extends BaseFragment {
    ImageView bee1;
    ImageView bee2;
    ImageView butterfly;
    ImageView originalFlower;
    ImageView cutFlower;
    ImageView flowers;
    ImageView dokdo;
    ImageView byul;

    TranslateAnimation dokdoAnimation;
    TranslateAnimation originalFlowerAnimation;
    TranslateAnimation byulAnimation;
    TranslateAnimation beeAnimation;
    TranslateAnimation beeTranslate;
    TranslateAnimation butterflyTranslate;
    RotateAnimation beeRotate;
    RotateAnimation butterflyRotate;
    AlphaAnimation beeButterflyFadeIn;
    AlphaAnimation beeButterflyFadeOut;
    AlphaAnimation blink;
    Animation flowerAnimation;
    AnimationSet hideBeeAniSet;
    AnimationSet hideButterflyAniSet;
    int animationFlag = 0;
    int byulAppearFlag = 0;


    MediaPlayer mp = null;

    private SoundPool beeSoundPool;
    private int beeSound;


    ArrayList<SubTitleData> subtitleList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale11;

        subtitleTextView.setText(null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void bindViews() {
        super.bindViews();
        bee1 = (ImageView) layout.findViewById(R.id.bee1);
        bee2 = (ImageView) layout.findViewById(R.id.bee2);
        butterfly = (ImageView) layout.findViewById(R.id.butterfly);
        originalFlower = (ImageView) layout.findViewById(R.id.originalFlower);
        cutFlower = (ImageView) layout.findViewById(R.id.cutFlower);
        flowers = (ImageView) layout.findViewById(R.id.flowers);
        dokdo = (ImageView) layout.findViewById(R.id.dokdo);
        byul = (ImageView) layout.findViewById(R.id.byul);

        beeSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        beeSound = beeSoundPool.load(getContext(), R.raw.effect_11_bee, 1);

    }

    @Override
    public void setValues() {
        super.setValues();

    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        beeButterflyFadeIn = new AlphaAnimation(0, 1);
        beeButterflyFadeIn.setDuration(600);
        beeButterflyFadeIn.setInterpolator(new AccelerateDecelerateInterpolator());

        beeButterflyFadeOut = new AlphaAnimation(1, 0);
        beeButterflyFadeOut.setStartOffset(5000);
        beeButterflyFadeOut.setDuration(1000);
        beeButterflyFadeOut.setInterpolator(new DecelerateInterpolator());
        beeButterflyFadeOut.setAnimationListener(new MyAnimationListener(){
            @Override
            public void onAnimationEnd(Animation animation) {
                animationFlag = 0;
                bee2.startAnimation(blink);
                checkedAnimation = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
                bee2.clearAnimation();
            }
        });


        flowerAnimation = new AlphaAnimation(0, 1);
        flowerAnimation.setStartOffset(200);
        flowerAnimation.setDuration(300);

        blink = new AlphaAnimation(1, 0.3f);
        blink.setDuration(1000);
        blink.setInterpolator(new LinearInterpolator());
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        bee2.setOnTouchListener(new BlockObjListener());
    }

    @Override
    public void blockAnimFunc() {
        if(animationFlag == 0) {
            animationFlag = 1;
            checkedAnimation = false;
            if(byulAppearFlag == 0) {
                byulAppearFlag = 1;
                byul.startAnimation(byulAnimation);
            }
            else{
                bee1.startAnimation(hideBeeAniSet);
                butterfly.startAnimation(hideButterflyAniSet);
            }
            bee2.clearAnimation();
            byul.setVisibility(View.VISIBLE);
            beeSoundPool.play(beeSound, 1, 1, 0, 3, 1);


        }
        super.blockAnimFunc();
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            if(animationFlag==1) {
                animationFlag = 0;
                cutFlower.setVisibility(View.VISIBLE);
                flowers.setVisibility(View.VISIBLE);
                flowers.startAnimation(flowerAnimation);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            bee1.clearAnimation();
            butterfly.clearAnimation();
            flowers.clearAnimation();
            cutFlower.setVisibility(View.INVISIBLE);
            byul.setVisibility(View.INVISIBLE);
        }

    }

    public void animationClear() {
        cutFlower.setVisibility(View.INVISIBLE);
        flowers.setVisibility(View.INVISIBLE);
        animationFlag = 0;
        flowers.clearAnimation();
        bee1.clearAnimation();
        bee2.clearAnimation();
        butterfly.clearAnimation();
        originalFlower.clearAnimation();
        dokdo.clearAnimation();
        byul.clearAnimation();

    }

    @Override
    public void soundPlayFunc() {
        musicController = new MusicController(getActivity(), R.raw.scene_11);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"언제나 활짝 웃는 땅채송화들이 랄랄라~ 합창해요.", "6000"},
                new String[]{"큰 바다 한가운데 용감하게 혼자인데 무섭지 않아요.", "12500"},
                new String[]{"큰 바다 넓은 곳에 덩그러니 혼자인데 심심하지 않아요.", "19500"},
                new String[]{"큰 바다 깊은 곳에 수백만 년 혼자인데 쓸쓸하지 않아요.", "27000"},
                new String[]{"큰 바다 깊은 곳에 수백만 년 혼자인데 쓸쓸하지 않아요.", "32700"},
                new String[]{"우리들은 언제나 활짝 웃고 있는 걸까요?", "37000"},
                new String[]{"별이가 반짝이는 눈으로 독도의 푸른 풍경을 초롱초롱 바라봐요.", "44500"});

        musicController.excuteAsync();
        mp = musicController.getMp();

        originalFlower.post(new Runnable() {
            @Override
            public void run() {
                originalFlowerAnimation = new TranslateAnimation(originalFlower.getWidth(), 0, originalFlower.getHeight(), 0);
                originalFlowerAnimation.setDuration(2000);
                originalFlowerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                originalFlowerAnimation.setAnimationListener(new MyAnimationListener());

                dokdoAnimation = new TranslateAnimation(-dokdo.getWidth(), 0, 0, 0);
                dokdoAnimation.setDuration(2000);
                dokdoAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                dokdoAnimation.setAnimationListener(new MyAnimationListener(){
                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        flowers.setVisibility(View.INVISIBLE);
                    }
                });

                byulAnimation = new TranslateAnimation(byul.getWidth(), 0, byul.getHeight(), 0);
                byulAnimation.setDuration(1000);
                byulAnimation.setInterpolator(new AccelerateInterpolator());
                byulAnimation.setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        bee1.startAnimation(hideBeeAniSet);
                        butterfly.startAnimation(hideButterflyAniSet);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                    }
                });

                beeAnimation = new TranslateAnimation(bee2.getWidth(), 0, -(bee2.getHeight() * 2), 0);
                beeAnimation.setDuration(1500);
                beeAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                beeAnimation.setStartOffset(500);
                beeAnimation.setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        bee2.startAnimation(blink);
                        checkedAnimation = true;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                    }
                });

                beeRotate = new RotateAnimation(-20, 20, bee1.getWidth() / 2, bee1.getHeight() / 2);
                beeRotate.setDuration(1500);
                beeRotate.setStartOffset(500);
                beeRotate.setInterpolator(new AccelerateDecelerateInterpolator());
//                beeRotate.setInterpolator(new AnticipateOvershootInterpolator());
                beeRotate.setRepeatCount(3);
                beeRotate.setRepeatMode(Animation.REVERSE);

                beeTranslate = new TranslateAnimation(0, 0, bee1.getHeight() / 3, -(bee1.getHeight() / 2));
                beeTranslate.setDuration(2000);
                beeTranslate.setStartOffset(600);
                beeTranslate.setInterpolator(new AccelerateDecelerateInterpolator());
                beeTranslate.setRepeatCount(2);
                beeTranslate.setRepeatMode(Animation.REVERSE);


                Log.d("butterWidth", "butter"+butterfly.getWidth());
                Log.d("butterHeight", "butter"+butterfly.getHeight());
                butterflyRotate = new RotateAnimation(50, 10, butterfly.getWidth() / 1.5f, butterfly.getHeight() * 0.5f);
                butterflyRotate.setDuration(1000);
                butterflyRotate.setStartOffset(500);
                butterflyRotate.setInterpolator(new AccelerateDecelerateInterpolator());
                butterflyRotate.setRepeatCount(3);
                butterflyRotate.setRepeatMode(Animation.REVERSE);

                butterflyTranslate = new TranslateAnimation(0, 0, butterfly.getHeight() / 6, 0);
                butterflyTranslate.setDuration(3000);
                butterflyTranslate.setStartOffset(600);
                butterflyTranslate.setInterpolator(new AccelerateDecelerateInterpolator());
                butterflyTranslate.setRepeatCount(2);
                butterflyTranslate.setRepeatMode(Animation.REVERSE);

                hideBeeAniSet = new AnimationSet(false);
                hideButterflyAniSet = new AnimationSet(false);

                hideBeeAniSet.addAnimation(beeButterflyFadeIn);
                hideBeeAniSet.addAnimation(beeRotate);
                hideBeeAniSet.addAnimation(beeTranslate);
                hideBeeAniSet.addAnimation(beeButterflyFadeOut);

                hideButterflyAniSet.addAnimation(beeButterflyFadeIn);
                hideButterflyAniSet.addAnimation(butterflyRotate);
                hideButterflyAniSet.addAnimation(butterflyTranslate);
                hideButterflyAniSet.addAnimation(beeButterflyFadeOut);

                if (animationFlag == 0) {
                    animationClear();
                    checkedAnimation = false;
                    animationFlag = 1;
                    byulAppearFlag = 0;
                    dokdo.startAnimation(dokdoAnimation);
                    originalFlower.startAnimation(originalFlowerAnimation);
                    bee2.startAnimation(beeAnimation);
                }
            }
        });
////        setValues();
//        if (originalFlowerAnimation != null) {
//            animationClear();
//            animationFlag = 1;
//            byulAppearFlag = 0;
//            dokdo.startAnimation(dokdoAnimation);
//            originalFlower.startAnimation(originalFlowerAnimation);
//            bee2.startAnimation(beeAnimation);
//        }
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
