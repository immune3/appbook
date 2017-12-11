package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

    ScaleAnimation seagullAppear;
    Animation appear;
    Animation fadeIn;
    Animation fadeOut;
    Animation birdsAppear;
    TranslateAnimation decoAppear;
    TranslateAnimation fatherAppear;
    TranslateAnimation momAppear;
    TranslateAnimation seaAppear;
    AlphaAnimation blink;
    int appearFlag = 0;
    int animationFlag = 0;
    boolean isAttached = false;
    boolean isHint;
    MediaPlayer mp = null;
    MusicController musicController;

    ArrayList<SubTitleData> subtitleList;

    SoundPool sp;
    int moveByul;
    int clickBird;


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
        xml = R.layout.tale09;

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

        sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        moveByul = sp.load(getContext(), R.raw.effect_09_move_byul, 2);
        clickBird = sp.load(getContext(), R.raw.effect_09_click_bird, 1);

    }

    @Override
    public void setValues() {
        super.setValues();
        birds2.post(new Runnable() {
            @Override
            public void run() {
                int birdsWidth = birds2.getWidth();
                int birdsHeight = (int) (birds2.getHeight() * 1.3);
                seagullAppear = new ScaleAnimation(0, 1, 0, 1, birdsWidth, birdsHeight);
                seagullAppear.setDuration(1000);
                seagullAppear.setFillAfter(true);

                birdsAppear = AnimationUtils.loadAnimation(getContext(), R.anim.anim_09_birds_appear);
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
//                seaAppear.setAnimationListener(new AppearAnimationListener());

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

                appear = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
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
                    appearFlag = 1;
                    sea.startAnimation(seaAppear);
                    birds.startAnimation(birdsAppear);
                    deco.startAnimation(decoAppear);
                    fatherDokdo.startAnimation(fatherAppear);
                    momDokdo.startAnimation(momAppear);
                }
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeIn.setDuration(1000);
        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new MyAnimationListener());
        fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);

        blink = new AlphaAnimation(1, 0.3f);
        blink.setDuration(500);
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);

    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (animationFlag == 0) {
                    animationFlag = 1;
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

                    sp.play(clickBird, 1, 1, 0, 0, 1);
                    byulBody1.startAnimation(fadeIn);
                    byulHead1.startAnimation(fadeIn);
                } else {
                    sp.play(clickBird, 1, 1, 0, 0, 1);
                }
            }
        });
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
                    birds1.startAnimation(appear);
                    birds2.startAnimation(appear);
                    birds3.startAnimation(appear);
                    birds4.startAnimation(appear);
                    birds5.startAnimation(appear);
                    birds6.startAnimation(appear);
                    effect.startAnimation(appear);
                    break;
                case 2:
                    appearFlag = 0;
                    bird.startAnimation(blink);
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
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag) {
                case 1:
                    animationFlag = 2;
                    byulBody1.clearAnimation();
                    byulHead1.clearAnimation();
                    break;
                case 2:
                    animationFlag = 3;
                    sp.play(moveByul, 1, 1, 0, 0, 1);
                    byulBody1.startAnimation(fadeOut);
                    byulHead1.startAnimation(fadeOut);
                    byulBody2.startAnimation(fadeIn);
                    byulHead2.startAnimation(fadeIn);
                    break;
                case 3:
                    animationFlag = 4;
                    byulBody1.setVisibility(View.INVISIBLE);
                    byulHead1.setVisibility(View.INVISIBLE);
                    byulBody1.clearAnimation();
                    byulHead1.clearAnimation();
                    byulBody2.clearAnimation();
                    byulHead2.clearAnimation();
                    break;
                case 4:
                    animationFlag = 5;
                    sp.play(moveByul, 1, 1, 0, 0, 1);
                    byulBody2.startAnimation(fadeOut);
                    byulHead2.startAnimation(fadeOut);
                    byulBody3.startAnimation(fadeIn);
                    byulHead3.startAnimation(fadeIn);
                    break;
                case 5:
                    animationFlag = 6;
                    byulBody2.setVisibility(View.INVISIBLE);
                    byulHead2.setVisibility(View.INVISIBLE);
                    byulBody2.clearAnimation();
                    byulHead2.clearAnimation();
                    byulBody3.clearAnimation();
                    byulHead3.clearAnimation();
                    break;
                case 6:
                    animationFlag = 7;
                    sp.play(moveByul, 1, 1, 0, 0, 1);
                    byulBody3.startAnimation(fadeOut);
                    byulHead3.startAnimation(fadeOut);
                    byulBody4.startAnimation(fadeIn);
                    byulHead4.startAnimation(fadeIn);
                    break;
                case 7:
                    animationFlag = 8;
                    byulBody3.setVisibility(View.INVISIBLE);
                    byulHead3.setVisibility(View.INVISIBLE);
                    byulBody3.clearAnimation();
                    byulHead3.clearAnimation();
                    byulBody4.clearAnimation();
                    byulHead4.clearAnimation();
                    break;
                case 8:
                    animationFlag = 9;
                    sp.play(moveByul, 1, 1, 0, 0, 1);
                    byulBody4.startAnimation(fadeOut);
                    byulHead4.startAnimation(fadeOut);
                    byulBody5.startAnimation(fadeIn);
                    byulHead5.startAnimation(fadeIn);
                    break;
                case 9:
                    animationFlag = 10;
                    byulBody4.setVisibility(View.INVISIBLE);
                    byulHead4.setVisibility(View.INVISIBLE);
                    byulBody4.clearAnimation();
                    byulHead4.clearAnimation();
                    byulBody5.clearAnimation();
                    byulHead5.clearAnimation();
                    break;
                case 10:
                    animationFlag = 0;
                    byulBody5.startAnimation(fadeOut);
                    byulHead5.startAnimation(fadeOut);
                    bird.startAnimation(blink);
                    Log.i("animationFlag", "" + animationFlag);
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            switch (animationFlag) {
                case 1:
                    byulBody1.setVisibility(View.VISIBLE);
                    byulHead1.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    byulBody2.setVisibility(View.VISIBLE);
                    byulHead2.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    byulBody3.setVisibility(View.VISIBLE);
                    byulHead3.setVisibility(View.VISIBLE);
                    break;
                case 7:
                    byulBody4.setVisibility(View.VISIBLE);
                    byulHead4.setVisibility(View.VISIBLE);
                    break;
                case 9:
                    byulBody5.setVisibility(View.VISIBLE);
                    byulHead5.setVisibility(View.VISIBLE);
                    break;
            }
        }

    }

    public void soundPlayFunc() {
        musicController = new MusicController(getActivity(), R.raw.scene_9);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"언제나 용감한 갈매기의 친구들이 ", "3000"},
                new String[]{"와글와글~ 줄지어 몰려와 ", "5800"},
                new String[]{"동도할머니와 서도할아버지를 잇는 갈매기다리를 뚝딱 만들어요. ", "12000"},
                new String[]{"별이는 사뿐사뿐~ 갈매기다리를 건너, ", "16500"},
                new String[]{"언제나 멋쟁이인 서도할아버지한테로 가요.", "20500"},
                new String[]{"서도할아버지는 몇 살이에요?", "26000"},
                new String[]{"아~주아~주 먼 옛날 일이라 나이가 가물가물하단다.", "32500"},
                new String[]{"정말 한라산보다도 키가 커요? ", "37500"},
                new String[]{"그럼~ 내가 더 멋있게 보이려고 바다 \n" +
                        "속에서 까치발을 번쩍 들었거든. 껄껄~ ", "48000"},
                new String[]{"까르르~ 별이의 웃음에 물골의 샘물도 퐁퐁퐁~ 따라 웃어요.", "57000"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();

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
        if (birdsAppear != null) {
            appearFlag = 1;
            birds.startAnimation(birdsAppear);
            deco.startAnimation(decoAppear);
            fatherDokdo.startAnimation(fatherAppear);
            momDokdo.startAnimation(momAppear);
            sea.startAnimation(seaAppear);
        }
    }

}
