package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;
import com.ssomai.android.scalablelayout.ScalableLayout;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale13 extends BaseFragment {

    MediaPlayer mp = null;

    ArrayList<SubTitleData> subtitleList;

    private android.widget.ImageView ivBuyl13;
    private android.widget.ImageView ivWall13;
    private android.widget.ImageView ivBottom13;
    private android.widget.ImageView ivFishes13;
    ImageView bubble;

    private com.ssomai.android.scalablelayout.ScalableLayout sl;
    private CustomScrollView sv;

    private TranslateAnimation wallAnimation, bottomAnimation, characterAnimation, fishAnimation;
    AlphaAnimation bubbleAlpha;
    TranslateAnimation bubbleTranslate;
    AnimationSet bubbleAppear;
    AlphaAnimation blink;
    int animationFlag = 0;

    private SoundPool bubbleSoundPool, tickSoundPool;
    private int bubbleSound, tickSound;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale13;


        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override

    public void bindViews() {
        super.bindViews();

        this.sv = (CustomScrollView) layout.findViewById(R.id.sv);
        this.sl = (ScalableLayout) layout.findViewById(R.id.sl);
        this.ivBottom13 = (ImageView) layout.findViewById(R.id.ivBottom13);
        this.ivWall13 = (ImageView) layout.findViewById(R.id.ivWall13);
        this.ivBuyl13 = (ImageView) layout.findViewById(R.id.ivBuyl13);
        ivFishes13 = (ImageView) layout.findViewById(R.id.ivFishes13);
        bubble = (ImageView) layout.findViewById(R.id.bubble);
        bubbleSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        bubbleSound = bubbleSoundPool.load(getContext(), R.raw.effect_13_bubble, 1);
        tickSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        tickSound = tickSoundPool.load(getContext(), R.raw.effect_13_tick, 1);
    }

    @Override
    public void setValues() {
        super.setValues();

        ivBuyl13.post(new Runnable() {
            @Override
            public void run() {
                bottomAnimation = new TranslateAnimation(0, 0, ivBottom13.getHeight(), 0);
                bottomAnimation.setDuration(2000);
                bottomAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                wallAnimation = new TranslateAnimation(ivWall13.getWidth(), 0, 0, 0);
                wallAnimation.setStartOffset(500);
                wallAnimation.setDuration(2000);
                wallAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                characterAnimation = new TranslateAnimation(-ivBottom13.getWidth(), 0, 0, 0);
                characterAnimation.setDuration(3000);
                characterAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                characterAnimation.setAnimationListener(new MyAnimationListener());

                fishAnimation = new TranslateAnimation(ivFishes13.getWidth(), 0, 0, 0);
                fishAnimation.setStartOffset(2000);
                fishAnimation.setDuration(1000);
                fishAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                bubbleAlpha = new AlphaAnimation(0, 1);
                bubbleAlpha.setDuration(1000);
                bubbleAlpha.setRepeatCount(1);
                bubbleAlpha.setRepeatMode(Animation.REVERSE);

                bubbleTranslate = new TranslateAnimation(0, 0, 0, -bubble.getHeight() * 0.5f);
                bubbleTranslate.setDuration(2000);
                bubbleTranslate.setInterpolator(new AnticipateInterpolator());

                bubbleAppear = new AnimationSet(false);
                bubbleAppear.addAnimation(bubbleAlpha);
                bubbleAppear.addAnimation(bubbleTranslate);

                blink = new AlphaAnimation(1, 0.3f);
                blink.setDuration(500);
                blink.setRepeatCount(Animation.INFINITE);
                blink.setRepeatMode(Animation.REVERSE);

                if (bottomAnimation != null) {
                    animationClear();
                    animationFlag = 1;
                    ivBottom13.startAnimation(bottomAnimation);
                    ivWall13.startAnimation(wallAnimation);
                    ivBuyl13.startAnimation(characterAnimation);
                    ivFishes13.startAnimation(fishAnimation);
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
        ivBuyl13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivBuyl13.clearAnimation();
                bubble.startAnimation(bubbleAppear);
                tickSoundPool.play(tickSound, 1, 1, 0, 0, 1);
                bubbleSoundPool.play(bubbleSound, 1, 1, 0, 0, 1);
            }
        });
    }

    private class MyAnimationListener extends com.example.dokdofamily01.MyAnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            super.onAnimationStart(animation);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            if (animationFlag == 1) {
                animationFlag = 0;
                animationClear();
                ivBuyl13.startAnimation(blink);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            super.onAnimationRepeat(animation);
        }
    }

    private void animationClear() {
        animationFlag = 0;
        ivFishes13.clearAnimation();
        ivWall13.clearAnimation();
        ivBottom13.clearAnimation();
        ivBuyl13.clearAnimation();
    }
    @Override
    public void soundPlayFunc() {
        musicController = new MusicController(getActivity(), R.raw.scene_13);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"첨벙첨벙~ 별이가 바다 안으로 들어가요. ", "5000"},
                new String[]{"별이랑 놀고 싶은 해님도 풍덩~ 따라 들어가요. ", "10000"},
                new String[]{"맑은 바다 안이 더 환해졌어요.", "14000"},
                new String[]{"오징어 이모랑 놀고 싶은 바닷속 친구들이 몰려와서 \n" +
                        "헤엄치다 서고, 헤엄치다 멈추면서 가요.", "23000"},
                new String[]{"보들보들 감태 숲을 지나니 간질간질 모자반 숲이에요. ", "29500"},
                new String[]{"축구하던 성게 꼬마들이 축구공과 함께 데구루루 굴러 가요. ", "36500"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();
        if (bottomAnimation != null) {
            animationClear();
            animationFlag = 1;
            ivBottom13.startAnimation(bottomAnimation);
            ivWall13.startAnimation(wallAnimation);
            ivBuyl13.startAnimation(characterAnimation);
            ivFishes13.startAnimation(fishAnimation);
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
