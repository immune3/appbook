package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
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
    AlphaAnimation blink;
    Animation flowerAnimation;
    AnimationSet hideBeeAniSet = new AnimationSet(false);
    AnimationSet hideButterflyAniSet = new AnimationSet(false);
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
        if(isAttached ){
            if (isVisibleToUser) {
                System.out.println("PlayByHint");
                soundPlayFunc();
            } else {
                if (musicController != null) {
                    musicController.getMp().release();
                }
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
        xml = R.layout.tale11;

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
            musicController.getMp().release();
            musicController = null;
        }
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

    }

    @Override
    public void setValues() {
        super.setValues();
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

                byulAnimation = new TranslateAnimation(byul.getWidth(), 0, byul.getHeight(), 0);
                byulAnimation.setDuration(1000);
                byulAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                byulAnimation.setInterpolator(new BounceInterpolator());

                beeAnimation = new TranslateAnimation(bee2.getWidth(), 0, -(bee2.getHeight() * 2), 0);
                beeAnimation.setDuration(1500);
                beeAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                beeAnimation.setStartOffset(500);
                beeAnimation.setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        bee2.startAnimation(blink);
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
                beeRotate.setRepeatCount(Animation.INFINITE);
                beeRotate.setRepeatMode(Animation.REVERSE);

                beeTranslate = new TranslateAnimation(0, 0,bee1.getHeight()/3,-(bee1.getHeight()/2));
                beeTranslate.setDuration(2000);
                beeTranslate.setStartOffset(600);
                beeTranslate.setInterpolator(new AccelerateDecelerateInterpolator());
                beeTranslate.setRepeatCount(Animation.INFINITE);
                beeTranslate.setRepeatMode(Animation.REVERSE);


                butterflyRotate  = new RotateAnimation(50,10,butterfly.getWidth()/1.5f,butterfly.getHeight()*0.5f);
                butterflyRotate.setDuration(1000);
                butterflyRotate.setStartOffset(500);
                butterflyRotate.setInterpolator(new AccelerateDecelerateInterpolator());
//                butterflyRotate.setInterpolator(new AnticipateOvershootInterpolator());
                butterflyRotate.setRepeatCount(Animation.INFINITE);
                butterflyRotate.setRepeatMode(Animation.REVERSE);

                butterflyTranslate = new TranslateAnimation(0, 0,butterfly.getHeight()/6,0);
                butterflyTranslate.setDuration(3000);
                butterflyTranslate.setStartOffset(600);
                butterflyTranslate.setInterpolator(new AccelerateDecelerateInterpolator());
                butterflyTranslate.setRepeatCount(Animation.INFINITE);
                butterflyTranslate.setRepeatMode(Animation.REVERSE);

                hideBeeAniSet.addAnimation(beeButterflyFadeIn);
                hideBeeAniSet.addAnimation(beeRotate);
                hideBeeAniSet.addAnimation(beeTranslate);

                hideButterflyAniSet.addAnimation(beeButterflyFadeIn);
                hideButterflyAniSet.addAnimation(butterflyRotate);
                hideButterflyAniSet.addAnimation(butterflyTranslate);


                if(animationFlag == 0){
                    animationFlag = 1;

                    dokdo.startAnimation(dokdoAnimation);
                    originalFlower.startAnimation(originalFlowerAnimation);
                    bee2.startAnimation(beeAnimation);
                }

            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        beeButterflyFadeIn = new AlphaAnimation(0, 1);
        beeButterflyFadeIn.setDuration(600);
        beeButterflyFadeIn.setInterpolator(new AccelerateDecelerateInterpolator());
        beeButterflyFadeIn.setAnimationListener(new MyAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                byul.setVisibility(View.VISIBLE);
                byul.startAnimation(byulAnimation);
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
        bee2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byul.setVisibility(View.INVISIBLE);
                bee1.setVisibility(View.VISIBLE);
                butterfly.setVisibility(View.VISIBLE);
                bee1.startAnimation(hideBeeAniSet);
                butterfly.startAnimation(hideButterflyAniSet);
            }
        });
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            animationFlag = 0;
            cutFlower.setVisibility(View.VISIBLE);
            flowers.setVisibility(View.VISIBLE);
            flowers.startAnimation(flowerAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            bee1.clearAnimation();
            butterfly.clearAnimation();
            cutFlower.setVisibility(View.INVISIBLE);
            flowers.setVisibility(View.INVISIBLE);
            byul.setVisibility(View.INVISIBLE);
            bee1.setVisibility(View.INVISIBLE);
            butterfly.setVisibility(View.INVISIBLE);
        }

    }


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

        if (animationFlag == 0 && originalFlowerAnimation != null) {
            animationFlag = 1;
            dokdo.startAnimation(dokdoAnimation);
            originalFlower.startAnimation(originalFlowerAnimation);
            bee2.startAnimation(beeAnimation);
        }
    }


}
