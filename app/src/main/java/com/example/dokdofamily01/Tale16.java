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
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale16 extends BaseFragment {
    ImageView moon;
    ImageView bubble;
    ImageView bomb;
    ImageView dokdo_father;
    ImageView dokdo_mom;
    ImageView wave;

    TranslateAnimation moonAppearAnimation;
    TranslateAnimation dokdoFatherAppearAnimation;
    TranslateAnimation dokdoMomAppearAnimation;
    TranslateAnimation waveAppearAnimation;
    TranslateAnimation wavingAnimation;
    ScaleAnimation bubbleScaleAni;
    ScaleAnimation bubbleBombScaleAni;
    AlphaAnimation blink;
    AlphaAnimation fadein;
    AlphaAnimation fadeout;
    AnimationSet bubbleAniSet = new AnimationSet(false);
    AnimationSet bubbleBombAniSet = new AnimationSet(false);

    int animationFlag=0;

    boolean isAttached = false;
    MediaPlayer mp = null;

    ArrayList<SubTitleData> subtitleList;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isAttached = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isAttached ){
            if (isVisibleToUser) {
//                System.out.println(32+"Visible");
                if(mp == null){
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_16);
                }

                mp.start();

                Timer timer = new Timer();
                timer.schedule(new MyThread(),0, 500);

                if(animationFlag == 0 && moonAppearAnimation != null){
                    animationFlag = 1;
                    moon.startAnimation(moonAppearAnimation);
                    bubble.startAnimation(moonAppearAnimation);
                    dokdo_father.startAnimation(dokdoFatherAppearAnimation);
                    dokdo_mom.startAnimation(dokdoMomAppearAnimation);
                    wave.startAnimation(waveAppearAnimation);
                }

            } else {
//                System.out.println(2+"notVisible");
                if(mp!=null && mp.isPlaying()){
                    mp.pause();
                    mp.stop();
                    mp.release();
                    mp = null;
                }

            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        System.out.println(2+"onDestroyView");
        if(mp!=null && mp.isPlaying()){
            mp.pause();
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    private ArrayList<SubTitleData> makeSubTitleList(String[]... params) {
        ArrayList<SubTitleData> list = new ArrayList<>();

        for(String[] s : params){
            SubTitleData subTitleData = new SubTitleData(
                    s[0],Integer.parseInt(s[1])
            );
            list.add(subTitleData);
        }

        return list;
    }


    class MyThread extends TimerTask {
        int finishTime = 0;
        int subtitleIndex = 0;
        @Override
        public void run() {
            if (mp != null && mp.isPlaying()) {

                int playingTime = mp.getCurrentPosition();
                Message msg = new Message();

                finishTime = subtitleList.get(subtitleIndex).getFinishTime();

                if(playingTime <= finishTime){
                    msg.what = subtitleIndex;
                }else{
                    increaseIndex();
                    if(playingTime > finishTime){
                        increaseIndex();
                    }else{
                        msg.what = subtitleIndex;
                    }
                }
                mHandler.sendMessage(msg);

            } else {
                Message msg = new Message();
                msg.what = -1;
                mHandler.sendMessage(msg);
                cancel();
            }
        }

        private void increaseIndex(){
            subtitleIndex++;
            try {
                finishTime = subtitleList.get(subtitleIndex).getFinishTime();
            }catch (IndexOutOfBoundsException e){
//                finishTime = subtitleList.get(subtitleIndex-1).getFinishTime();
            }

        }
    }
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            if(msg.what>=0)
                subtitleTextView.setText(subtitleList.get(msg.what).getSubTitle());
            else
                subtitleTextView.setText(null);


        }
    };



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale16;

        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
                new String[]{"달님과 별님이 깨어나는 시간이 되면 오늘도...","4000"},
                new String[]{"빨간 우체통 엄마는 부스럭부스럭 \n" +
                        "편지를 꺼내 상냥하게 읽어주지.  ", "10000"},
                new String[]{"동도할머니는 옛날 옛날에~하며 \n" +
                        "마음 따뜻한 이야기를 시작하고...  ","16500"},
                new String[]{"든든한 사철나무 아빠와 함께 \n" +
                        "독도가족들의 꿈나라를 지킨단다. ","25000"},
                new String[]{"심심한 파도가 투정부리며 철썩철썩~","29500"},
                new String[]{"멋쟁이 서도할아버지한테 간지럼장난을 치는 동안","33700"},
                new String[]{"바다제비 친구들은 포근한 이불 속에서 \n" +
                        "사이좋게 재잘재잘... 재잘.","41000"},
                new String[]{"활짝 웃는 땅채송화들이 불러주는 자장자장~ \n" +
                        "자장가는 하품을 데려오고 ","47500"},
                new String[]{"부지런히 이불을 덮어주는 오징어 이모의 긴 다리들은 \n" +
                        "꿈나라의 문을 열어준단다.","55000"},
                new String[]{"우리들은 오늘도 그렇게 \n" +
                        "꿀잠 속으로 스르르 빠져들지.","62000"}
        );

        subtitleTextView.setText(null);



        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        moon = (ImageView)layout.findViewById(R.id.moon);
        bubble = (ImageView)layout.findViewById(R.id.bubble);
        bomb = (ImageView)layout.findViewById(R.id.bomb);
        dokdo_father = (ImageView)layout.findViewById(R.id.dokdo_father);
        dokdo_mom = (ImageView)layout.findViewById(R.id.dokdo_mom);
        wave = (ImageView)layout.findViewById(R.id.wave);
    }

    @Override
    public void setValues() {
        super.setValues();
        wave.post(new Runnable() {
            @Override
            public void run() {
                moonAppearAnimation = new TranslateAnimation(0, 0, -moon.getHeight(), 0);
                moonAppearAnimation.setDuration(2000);
                moonAppearAnimation.setStartOffset(1000);
                moonAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                moonAppearAnimation.setFillAfter(true);
                moonAppearAnimation.setAnimationListener(new MyAnimationListener());

                dokdoFatherAppearAnimation = new TranslateAnimation(-dokdo_father.getWidth(), 0, 0, 0);
                dokdoFatherAppearAnimation.setDuration(1200);
                dokdoFatherAppearAnimation.setStartOffset(1000);
                dokdoFatherAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                dokdoFatherAppearAnimation.setFillAfter(true);

                dokdoMomAppearAnimation = new TranslateAnimation(dokdo_mom.getWidth(), 0, 0, 0);
                dokdoMomAppearAnimation.setDuration(1200);
                dokdoMomAppearAnimation.setStartOffset(800);
                dokdoMomAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                dokdoMomAppearAnimation.setFillAfter(true);

                wavingAnimation = new TranslateAnimation(0, 0, 0, wave.getHeight()*0.05f);
                wavingAnimation.setDuration(2000);
                wavingAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                wavingAnimation.setRepeatCount(Animation.INFINITE);
                wavingAnimation.setRepeatMode(Animation.REVERSE);

                waveAppearAnimation = new TranslateAnimation(0, 0, wave.getHeight(), 0);
                waveAppearAnimation.setDuration(1500);
                waveAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                waveAppearAnimation.setFillAfter(true);
                waveAppearAnimation.setAnimationListener(new MyAnimationListener(){
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        wave.startAnimation(wavingAnimation);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {

                    }
                });

                bubbleScaleAni = new ScaleAnimation(1,0.7f,1,0.7f,0,0);
                bubbleScaleAni.setDuration(800);
                bubbleScaleAni.setInterpolator(new AccelerateDecelerateInterpolator());
                bubbleScaleAni.setRepeatCount(Animation.INFINITE);
                bubbleScaleAni.setRepeatMode(Animation.REVERSE);

                bubbleAniSet.addAnimation(bubbleScaleAni);
//                bubbleAniSet.addAnimation(blink);

                bubbleBombScaleAni = new ScaleAnimation(1,0.7f,1,0.7f,0,0);
                bubbleBombScaleAni.setDuration(800);
                bubbleBombScaleAni.setInterpolator(new AccelerateDecelerateInterpolator());
                bubbleBombScaleAni.setRepeatCount(4);
                bubbleBombScaleAni.setRepeatMode(Animation.REVERSE);
                bubbleBombScaleAni.setAnimationListener(new MyAnimationListener(){
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        bubble.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        bomb.setVisibility(View.VISIBLE);
                        bomb.startAnimation(fadein);
                    }
                });

                bubbleBombAniSet.addAnimation(bubbleBombScaleAni);
                bubbleBombAniSet.addAnimation(fadeout);

                if(animationFlag == 0){
                    animationFlag = 1;
                    moon.startAnimation(moonAppearAnimation);
                    bubble.startAnimation(moonAppearAnimation);
                    dokdo_father.startAnimation(dokdoFatherAppearAnimation);
                    dokdo_mom.startAnimation(dokdoMomAppearAnimation);
                    wave.startAnimation(waveAppearAnimation);
                }
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadein = new AlphaAnimation(0, 1);
        fadein.setStartOffset(4000);
        fadein.setDuration(300);

        fadeout = new AlphaAnimation(1, 0);
        fadeout.setDuration(300);
        fadeout.setStartOffset(4000);

        blink = new AlphaAnimation(1, 0.5f);
        blink.setDuration(1000);
        blink.setInterpolator(new LinearInterpolator());
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);

    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        moon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bubble.clearAnimation();
                moon.clearAnimation();
                bubble.startAnimation(bubbleBombAniSet);
            }
        });

        bubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bubble.clearAnimation();
                moon.clearAnimation();
                bubble.startAnimation(bubbleBombAniSet);
            }
        });
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            animationFlag = 0;
            bubble.startAnimation(bubbleAniSet);
//            moon.startAnimation(blink);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            bomb.setVisibility(View.INVISIBLE);
        }

    }
}
