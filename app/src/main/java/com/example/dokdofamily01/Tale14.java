package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
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
    AlphaAnimation blink;
    AlphaAnimation sqeedHandFadein;
    ScaleAnimation sqeedHandAni;

    AnimationSet bellAnimSet = new AnimationSet(false);
    AnimationSet sqeedHandAnimSet = new AnimationSet(false);

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
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_14);
                }

                mp.start();

                Timer timer = new Timer();
                timer.schedule(new MyThread(),0, 500);

                if(animationFlag == 0 && caveAppearAni != null){
                    animationFlag=1;
                    cave.startAnimation(caveAppearAni);
                    land.startAnimation(landAppearAni);
                    byul.startAnimation(byulAppearAni);
                    sqeedBody.startAnimation(byulAppearAni);
                    bell.startAnimation(bellAnimSet);
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
        xml = R.layout.tale14;

        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
                new String[]{"혹돔굴에 가까워질수록 어둡고 깜깜해져요. ","5000"},
                new String[]{"졸졸 따라오던 해님도 안보여요. ", "8500"},
                new String[]{"왠지 으스스한 느낌이 들어서 ","11500"},
                new String[]{"별이는 오징어 이모한테 찰싹~ 붙어요. ","16000"},
                new String[]{"드디어 혹돔굴이에요. ","19000"},
                new String[]{"오징어 이모가 다시 긴 다리를 쭈욱 늘여 \n" +
                        "초인종을 꾸욱 눌러요. ","25500"},
                new String[]{"딩동딩동~","28500"}
        );

        subtitleTextView.setText(null);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        cave = (ImageView)layout.findViewById(R.id.cave);
        land = (ImageView)layout.findViewById(R.id.land);
        sqeedBody = (ImageView)layout.findViewById(R.id.sqeedBody);
        sqeedHand = (ImageView)layout.findViewById(R.id.sqeedHand);
        byul = (ImageView)layout.findViewById(R.id.byul);
        bubble = (ImageView)layout.findViewById(R.id.bubble);
        bell = (ImageView)layout.findViewById(R.id.bell);
        light = (ImageView)layout.findViewById(R.id.light);
    }

    @Override
    public void setValues() {
        super.setValues();
        land.post(new Runnable() {
            @Override
            public void run() {
                caveAppearAni = new TranslateAnimation(cave.getWidth(), 0, 0, 0);
                caveAppearAni.setDuration(1500);
                caveAppearAni.setStartOffset(600);
                caveAppearAni.setInterpolator(new AccelerateDecelerateInterpolator());
                caveAppearAni.setFillAfter(true);

                byulAppearAni = new TranslateAnimation(-sqeedBody.getWidth(),0,sqeedBody.getHeight(),0);
                byulAppearAni.setDuration(2000);
                byulAppearAni.setStartOffset(1000);
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

                Log.d("123123", "msg"+sqeedHand.getWidth());
                sqeedHandAni = new ScaleAnimation(0.9f, 1, 1,1,0, sqeedHand.getHeight());
                sqeedHandAni.setInterpolator(new AccelerateDecelerateInterpolator());
                sqeedHandAni.setDuration(700);

                sqeedHandAnimSet.addAnimation(sqeedHandAni);
                sqeedHandAnimSet.addAnimation(sqeedHandFadein);

                if(animationFlag == 0){
                    animationFlag=1;
                    cave.startAnimation(caveAppearAni);
//                    bell.startAnimation(caveAppearAni);
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
        fadein.setDuration(1000);

        sqeedHandFadein = new AlphaAnimation(0, 1);
        sqeedHandFadein.setDuration(300);

        blink = new AlphaAnimation(1, 0.3f);
        blink.setDuration(1000);
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
                sqeedHand.setVisibility(View.VISIBLE);
                sqeedHand.startAnimation(sqeedHandAnimSet);
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
            sqeedHand.setVisibility(View.INVISIBLE);
            bubble.setVisibility(View.INVISIBLE);
        }

    }
}
