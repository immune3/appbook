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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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

public class Tale03 extends BaseFragment {

    private CustomViewPager vp;
    ImageView[] cloud = new ImageView[6];
    ImageView byulHand;
    ImageView[] wing = new ImageView[4];
    ImageView blinkStar;

    Animation fadein;
    AlphaAnimation blink;
    AlphaAnimation wingAppear1;
    AlphaAnimation wingAppear2;
    TranslateAnimation[] cloudAnimation = new TranslateAnimation[5];
    int animationFlag = 0;
    int clickFlag = 0;

    boolean isHint;
    MusicController musicController;
    boolean isAttached = false;
    MediaPlayer mp = null;


    ArrayList<SubTitleData> subtitleList;

    SoundPool sp;
    int soundID;

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
                vp = ((TaleActivity)getActivity()).vp;
                System.out.println("PlayByHint");
                soundPlayFunc();
                vp.setOnTouchListener(new MyChangeListener ());
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
        xml = R.layout.tale03;

        subtitleTextView.setText(null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        if (isHint && !homeKeyFlag && screenFlag) {
            soundPlayFunc();
            vp = ((TaleActivity)getActivity()).vp;
            vp.setOnTouchListener(new MyChangeListener ());
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
        cloud[0] = (ImageView) layout.findViewById(R.id.cloud0);
        cloud[1] = (ImageView) layout.findViewById(R.id.cloud1);
        cloud[2] = (ImageView) layout.findViewById(R.id.cloud2);
        cloud[3] = (ImageView) layout.findViewById(R.id.cloud3);
        cloud[4] = (ImageView) layout.findViewById(R.id.cloud4);
        cloud[5] = (ImageView) layout.findViewById(R.id.cloud5);
        byulHand = (ImageView) layout.findViewById(R.id.byulHand);
        wing[0] = (ImageView) layout.findViewById(R.id.wingLeft1);
        wing[1] = (ImageView) layout.findViewById(R.id.wingLeft2);
        wing[2] = (ImageView) layout.findViewById(R.id.wingRight1);
        wing[3] = (ImageView) layout.findViewById(R.id.wingRight2);
        blinkStar = (ImageView) layout.findViewById(R.id.blinkStar);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sp.load(getContext(), R.raw.effect_03_clouds, 1);
    }

    @Override
    public void setValues() {
        super.setValues();
        byulHand.post(new Runnable() {
            @Override
            public void run() {
                // 왼쪽 위 구름(cloud[0])
                cloudAnimation[0] = new TranslateAnimation(-cloud[0].getWidth(), 0, -cloud[0].getHeight(), 0);
                cloudAnimation[0].setDuration(3000);
                cloudAnimation[0].setFillAfter(true);
                cloudAnimation[0].setInterpolator(new AccelerateDecelerateInterpolator());

                // 왼쪽 아래 구름(cloud[1], cloud[2])
                cloudAnimation[1] = new TranslateAnimation(-cloud[2].getWidth(), 0, cloud[2].getHeight(), 0);
                cloudAnimation[1].setStartOffset(500);
                cloudAnimation[1].setDuration(2500);
                cloudAnimation[1].setFillAfter(true);
                cloudAnimation[1].setInterpolator(new AccelerateDecelerateInterpolator());

                // 중앙 위 구름(cloud[3])
                cloudAnimation[2] = new TranslateAnimation(0, 0, -cloud[3].getHeight(), 0);
                cloudAnimation[2].setStartOffset(2000);
                cloudAnimation[2].setDuration(2000);
                cloudAnimation[2].setFillAfter(true);
                cloudAnimation[2].setAnimationListener(new MyAnimationListener());
                cloudAnimation[2].setInterpolator(new AccelerateDecelerateInterpolator());

                // 오른쪽 아래 구름(cloud[4])
                cloudAnimation[3] = new TranslateAnimation(cloud[4].getWidth(), 0, 0, 0);
                cloudAnimation[3].setStartOffset(500);
                cloudAnimation[3].setDuration(2000);
                cloudAnimation[3].setFillAfter(true);
                cloudAnimation[3].setInterpolator(new AccelerateDecelerateInterpolator());

                // 오른쪽 위 구름(cloud[5])
                cloudAnimation[4] = new TranslateAnimation(cloud[5].getWidth(), 0, -cloud[5].getHeight(), 0);
                cloudAnimation[4].setStartOffset(1500);
                cloudAnimation[4].setDuration(2000);
                cloudAnimation[4].setFillAfter(true);
                cloudAnimation[4].setInterpolator(new AccelerateDecelerateInterpolator());

                byulHand.setVisibility(View.INVISIBLE);
                if (animationFlag == 0) {
                    animationFlag = 1;
                    sp.play(soundID, 1, 1, 0, 0, 1);
                    cloud[0].startAnimation(cloudAnimation[0]);
                    cloud[1].startAnimation(cloudAnimation[1]);
                    cloud[2].startAnimation(cloudAnimation[1]);
                    cloud[3].startAnimation(cloudAnimation[2]);
                    cloud[4].startAnimation(cloudAnimation[3]);
                    cloud[5].startAnimation(cloudAnimation[4]);
                }
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadein = new AlphaAnimation(0, 1);
        fadein.setDuration(1000);

        blink = new AlphaAnimation(0.3f, 1);
        blink.setDuration(500);
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);

        wingAppear1 = new AlphaAnimation(1, 0);
        wingAppear1.setDuration(400);
        wingAppear1.setRepeatCount(7);
        wingAppear1.setRepeatMode(Animation.REVERSE);
        wingAppear1.setAnimationListener(new MyAnimationListener());
        wingAppear2 = new AlphaAnimation(0, 1);
        wingAppear2.setDuration(400);
        wingAppear2.setRepeatCount(7);
        wingAppear2.setRepeatMode(Animation.REVERSE);
        wingAppear2.setAnimationListener(new MyAnimationListener());
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        blinkStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (animationFlag == 0) {
                    animationFlag = 1;
                    blinkStar.clearAnimation();
                    wing[0].clearAnimation();
                    wing[1].clearAnimation();
                    wing[2].clearAnimation();
                    wing[3].clearAnimation();
                    wing[0].startAnimation(wingAppear1);
                    wing[1].startAnimation(wingAppear2);
                    wing[2].startAnimation(wingAppear1);
                    wing[3].startAnimation(wingAppear2);
                }
            }
        });
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            if (animationFlag == 1) {
                animationFlag = 0;
                byulHand.setVisibility(View.VISIBLE);
                byulHand.startAnimation(fadein);
                blinkStar.startAnimation(blink);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            byulHand.setVisibility(View.INVISIBLE);
        }

    }

    public void soundPlayFunc() {
        musicController = new MusicController(getActivity(), R.raw.scene_3);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"별이가 갈매기의 등에 수줍게 앉아요. ", "4000"},
                new String[]{"갈매기는 푸르르 날아올라 별님들이 \n" +
                        "하품하는 새벽하늘을 너울너울 날아요. ", "12500"},
                new String[]{"별아 동도할머니~ 서도할아버지를 만나러 \n" +
                        "보물섬 독도에 가는 거야~ ", "20000"},
                new String[]{"정말? 이렇게 날아서?", "23500"},
                new String[]{"그래~ 팔을 뻗어 말랑말랑 솜사탕 구름을 만져보렴~", "31000"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();

        byulHand.setVisibility(View.INVISIBLE);

        if (cloudAnimation[0] != null) {
            animationFlag = 1;
            sp.play(soundID, 1, 1, 0, 0, 1);
            cloud[0].startAnimation(cloudAnimation[0]);
            cloud[1].startAnimation(cloudAnimation[1]);
            cloud[2].startAnimation(cloudAnimation[1]);
            cloud[3].startAnimation(cloudAnimation[2]);
            cloud[4].startAnimation(cloudAnimation[3]);
            cloud[5].startAnimation(cloudAnimation[4]);
        }
    }

    class MyChangeListener extends CustomTouchListener{

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            customViewPager = vp;
            return super.onTouch(view, motionEvent);
        }

        @Override
        public void decreaseFunc() {
            if(musicController != null){
                if(musicController.previousPart()){

                }else{
                    super.decreaseFunc();
                }
            }
        }

        @Override
        public void increaseFunc() {
            if(musicController!=null){
                if(musicController.nextPart()){

                }else{
                    super.increaseFunc();
                }
            }
        }
    }

}
