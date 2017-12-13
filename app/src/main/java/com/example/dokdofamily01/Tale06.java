package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
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

public class Tale06 extends BaseFragment {
    ImageView sea;
    ImageView waveshadow;
    ImageView momDokdo;
    ImageView[] smallwave = new ImageView[4];
    ImageView[] bigwave = new ImageView[3];
    ImageView[] seagull = new ImageView[2];

    TranslateAnimation waveAppear;
    TranslateAnimation backgroundWaving;
    TranslateAnimation momAppear;
    TranslateAnimation[] wavingTranslateAni = new TranslateAnimation[7];
    TranslateAnimation[] wavingUpperAndLower = new TranslateAnimation[3];
    AlphaAnimation blink;
    Animation fadeIn;
    AnimationSet[] wavingAniSet = new AnimationSet[3];
    int animationFlag = 0;

    boolean isAttached = false;

    boolean isHint;
    MediaPlayer mp = null;
    MusicController musicController;

    SoundPool gullSoundPool, waveSoundPool;
    int gullSound;
    int waveSound;

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
        xml = R.layout.tale06;

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

        sea = (ImageView) layout.findViewById(R.id.sea);
        waveshadow = (ImageView) layout.findViewById(R.id.waveshadow);
        smallwave[0] = (ImageView) layout.findViewById(R.id.smallwave1);
        smallwave[1] = (ImageView) layout.findViewById(R.id.smallwave2);
        smallwave[2] = (ImageView) layout.findViewById(R.id.smallwave3);
        smallwave[3] = (ImageView) layout.findViewById(R.id.smallwave4);
        bigwave[0] = (ImageView) layout.findViewById(R.id.bigwave1);
        bigwave[1] = (ImageView) layout.findViewById(R.id.bigwave2);
        bigwave[2] = (ImageView) layout.findViewById(R.id.bigwave3);
        seagull[0] = (ImageView) layout.findViewById(R.id.seagull1);
        seagull[1] = (ImageView) layout.findViewById(R.id.seagull2);
        momDokdo = (ImageView) layout.findViewById(R.id.momDokdo);
        gullSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        waveSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        gullSound = gullSoundPool.load(getContext(),R.raw.effect_06_gull,1);
        waveSound = waveSoundPool.load(getContext(),R.raw.effect_06_wave,1);
    }

    @Override
    public void setValues() {
        super.setValues();

        sea.post(new Runnable() {
            @Override
            public void run() {
                waveAppear = new TranslateAnimation(0, 0, (int) (sea.getHeight() * 0.8), 0);
                waveAppear.setDuration(1000);
                waveAppear.setFillAfter(true);
                waveAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                waveAppear.setAnimationListener(new MyAnimationListener());

                momAppear = new TranslateAnimation(-momDokdo.getWidth(), 0, 0, 0);
                momAppear.setStartOffset(500);
                momAppear.setDuration(1000);
                momAppear.setFillAfter(true);
                momAppear.setInterpolator(new AccelerateDecelerateInterpolator());

                backgroundWaving = new TranslateAnimation(0, 0, 0, waveshadow.getHeight() * 0.05f);
                backgroundWaving.setDuration(2000);
                backgroundWaving.setInterpolator(new AccelerateDecelerateInterpolator());
                backgroundWaving.setRepeatCount(Animation.INFINITE);
                backgroundWaving.setRepeatMode(Animation.REVERSE);

                int baseDuration = 2000;
                for(int iter = 0; iter < 3; iter++){
                    wavingTranslateAni[iter] = new TranslateAnimation(0, bigwave[iter].getWidth() * 0.1f, 0, 0);
                    wavingTranslateAni[iter].setDuration(baseDuration);
                    wavingTranslateAni[iter].setInterpolator(new AccelerateDecelerateInterpolator());
                    wavingTranslateAni[iter].setRepeatCount(3);
                    wavingTranslateAni[iter].setRepeatMode(Animation.REVERSE);

                    wavingUpperAndLower[iter] = new TranslateAnimation(0,0,0 , bigwave[iter].getHeight() * 0.2f);
                    wavingUpperAndLower[iter].setDuration(baseDuration);
                    wavingUpperAndLower[iter].setInterpolator(new AccelerateDecelerateInterpolator());
                    wavingUpperAndLower[iter].setRepeatCount(3);
                    wavingUpperAndLower[iter].setRepeatMode(Animation.REVERSE);

                    wavingAniSet[iter] = new AnimationSet(false);
                    wavingAniSet[iter].addAnimation(wavingTranslateAni[iter]);
                    wavingAniSet[iter].addAnimation(wavingUpperAndLower[iter]);
                }

                wavingTranslateAni[3] = new TranslateAnimation(0, 0, 0, smallwave[0].getHeight() * 0.1f);
                wavingTranslateAni[3].setDuration(2600);
                wavingTranslateAni[3].setInterpolator(new CycleInterpolator(0.5f));
                wavingTranslateAni[3].setRepeatCount(3);
                wavingTranslateAni[3].setRepeatMode(Animation.REVERSE);

                wavingTranslateAni[4] = new TranslateAnimation(0, 0, 0, smallwave[1].getHeight() * 0.1f);
                wavingTranslateAni[4].setDuration(3500);
                wavingTranslateAni[4].setInterpolator(new CycleInterpolator(1));
                wavingTranslateAni[4].setRepeatCount(2);
                wavingTranslateAni[4].setRepeatMode(Animation.REVERSE);

                wavingTranslateAni[5] = new TranslateAnimation(0, 0, 0, smallwave[2].getHeight() * 0.1f);
                wavingTranslateAni[5].setDuration(2000);
                wavingTranslateAni[5].setInterpolator(new CycleInterpolator(0.5f));
                wavingTranslateAni[5].setRepeatCount(4);
                wavingTranslateAni[5].setRepeatMode(Animation.REVERSE);
                wavingTranslateAni[5].setAnimationListener(new MyAnimationListener(){
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animationFlag = 0;
                        seagull[0].startAnimation(blink);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        seagull[0].clearAnimation();
                    }
                });

                wavingTranslateAni[6] = new TranslateAnimation(0, 0, 0, smallwave[3].getHeight() * 0.1f);
                wavingTranslateAni[6].setDuration(2600);
                wavingTranslateAni[6].setInterpolator(new CycleInterpolator(1));
                wavingTranslateAni[6].setRepeatCount(3);
                wavingTranslateAni[6].setRepeatMode(Animation.REVERSE);

                if (animationFlag == 0) {
                    animationClear();
                    animationFlag = 1;

                    sea.startAnimation(waveAppear);
                    bigwave[0].startAnimation(waveAppear);
                    bigwave[1].startAnimation(waveAppear);
                    bigwave[2].startAnimation(waveAppear);
                    momDokdo.startAnimation(momAppear);
                }
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new MyAnimationListener());

        blink = new AlphaAnimation(1, 0.3f);
        blink.setDuration(500);
        blink.setInterpolator(new LinearInterpolator());
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        seagull[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(animationFlag == 0) {
                    animationFlag = 1;
                    bigwave[0].startAnimation(wavingAniSet[0]);
                    bigwave[1].startAnimation(wavingAniSet[1]);
                    bigwave[2].startAnimation(wavingAniSet[2]);
                    smallwave[0].startAnimation(wavingTranslateAni[3]);
                    smallwave[1].startAnimation(wavingTranslateAni[4]);
                    smallwave[2].startAnimation(wavingTranslateAni[5]);
                    smallwave[3].startAnimation(wavingTranslateAni[6]);

                    gullSoundPool.play(gullSound, 1, 1, 0, 0, 1);
                    waveSoundPool.play(waveSound, 1, 1, 0, 0, 1);
                }
            }
        });
    }


    private class MyAnimationListener extends com.example.dokdofamily01.MyAnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            super.onAnimationStart(animation);
            switch (animationFlag) {
                case 1:
                    momDokdo.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    smallwave[0].setVisibility(View.VISIBLE);
                    smallwave[1].setVisibility(View.VISIBLE);
                    smallwave[2].setVisibility(View.VISIBLE);
                    smallwave[3].setVisibility(View.VISIBLE);
                    waveshadow.setVisibility(View.VISIBLE);
                    seagull[0].setVisibility(View.VISIBLE);
                    seagull[1].setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            switch (animationFlag) {
                case 1:
                    animationFlag = 2;
                    Log.i("animationFlag", "" + animationFlag);
                    sea.clearAnimation();
                    bigwave[0].clearAnimation();
                    bigwave[1].clearAnimation();
                    bigwave[2].clearAnimation();
                    smallwave[0].startAnimation(fadeIn);
                    smallwave[1].startAnimation(fadeIn);
                    smallwave[2].startAnimation(fadeIn);
                    smallwave[3].startAnimation(fadeIn);
                    waveshadow.startAnimation(fadeIn);
                    seagull[0].startAnimation(fadeIn);
                    seagull[1].startAnimation(fadeIn);
                    break;
                case 2:
                    animationFlag = 3;
                    Log.i("animationFlag", "" + animationFlag);
                    smallwave[0].clearAnimation();
                    smallwave[1].clearAnimation();
                    smallwave[2].clearAnimation();
                    smallwave[3].clearAnimation();
                    waveshadow.clearAnimation();
                    seagull[0].clearAnimation();
                    seagull[1].clearAnimation();
                    break;
                case 3:
                    animationFlag = 0;
                    sea.startAnimation(backgroundWaving);
                    waveshadow.startAnimation(backgroundWaving);
                    seagull[0].startAnimation(blink);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            super.onAnimationRepeat(animation);
        }
    }

    private void animationClear() {


        momDokdo.setVisibility(View.INVISIBLE);
        smallwave[0].setVisibility(View.INVISIBLE);
        smallwave[1].setVisibility(View.INVISIBLE);
        smallwave[2].setVisibility(View.INVISIBLE);
        smallwave[3].setVisibility(View.INVISIBLE);
        waveshadow.setVisibility(View.INVISIBLE);
        seagull[0].setVisibility(View.INVISIBLE);
        seagull[1].setVisibility(View.INVISIBLE);
        animationFlag = 0;
        sea.clearAnimation();
        waveshadow.clearAnimation();
        momDokdo.clearAnimation();
        smallwave[0].clearAnimation();
        smallwave[1].clearAnimation();
        smallwave[2].clearAnimation();
        smallwave[3].clearAnimation();
        bigwave[0].clearAnimation();
        bigwave[1].clearAnimation();
        bigwave[2].clearAnimation();
        seagull[0].clearAnimation();
        seagull[1].clearAnimation();
    }

    public void soundPlayFunc() {
        musicController = new MusicController(getActivity(), R.raw.scene_6);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"심심해서 언제나 간지럼 장난을 일삼는 투정쟁이 파도가 오늘은 넘실넘실~ 웃어요. ", "8000"},
                new String[]{"언제나 마음 따뜻한 동도할머니는 별이가 좋아서 쳐다보고 또 쳐다봐요.   ", "16500"},
                new String[]{"독도는 정말 보물섬이에요? ", "20500"},
                new String[]{"그럼~ 보물들로 가득하단다.", "25500"},
                new String[]{"보물들은 어디에 있어요? ", "29000"},
                new String[]{"하늘에도 있고", "31500"},
                new String[]{"땅에도 있고, ", "33000"},
                new String[]{"또 바다에도 있단다.", "36500"},
                new String[]{"보물들을 보고 싶어요! ", "40000"},
                new String[]{"그래~ 별이가 독도에서 보물찾기를 하면서 놀면 되겠구나!", "49000"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();

        if (momAppear != null) {
            animationClear();
            animationFlag = 1;
            sea.startAnimation(waveAppear);
            bigwave[0].startAnimation(waveAppear);
            bigwave[1].startAnimation(waveAppear);
            bigwave[2].startAnimation(waveAppear);
            momDokdo.startAnimation(momAppear);
        }
    }

}
