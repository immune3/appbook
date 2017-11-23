package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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

    ScaleAnimation seagullAppear;
    Animation fadeIn;
    Animation fadeOut;
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
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_9);
                }

                mp.start();

                Timer timer = new Timer();
                timer.schedule(new MyThread(),0, 500);

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
        xml = R.layout.tale09;

        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
                new String[]{"언제나 용감한 갈매기의 친구들이 ","3000"},
                new String[]{"와글와글~ 줄지어 몰려와 ", "5800"},
                new String[]{"동도할머니와 서도할아버지를 잇는 갈매기다리를 뚝딱 만들어요. ","12000"},
                new String[]{"별이는 사뿐사뿐~ 갈매기다리를 건너, ","16500"},
                new String[]{"언제나 멋쟁이인 서도할아버지한테로 가요.","20500"},
                new String[]{"서도할아버지는 몇 살이에요?","26000"},
                new String[]{"아~주아~주 먼 옛날 일이라 나이가 가물가물하단다.","32500"},
                new String[]{"정말 한라산보다도 키가 커요? ","37500"},
                new String[]{"그럼~ 내가 더 멋있게 보이려고 바다 \n" +
                        "속에서 까치발을 번쩍 들었거든. 껄껄~ ","48000"},
                new String[]{"까르르~ 별이의 웃음에 물골의 샘물도 퐁퐁퐁~ 따라 웃어요.","57000"}
        );

        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        bird = (ImageView)layout.findViewById(R.id.bird);
        birds1 = (ImageView)layout.findViewById(R.id.birds1);
        birds2 = (ImageView)layout.findViewById(R.id.birds2);
        birds3 = (ImageView)layout.findViewById(R.id.birds3);
        birds4 = (ImageView)layout.findViewById(R.id.birds4);
        birds5 = (ImageView)layout.findViewById(R.id.birds5);
        birds6 = (ImageView)layout.findViewById(R.id.birds6);
        byulBody1 = (ImageView)layout.findViewById(R.id.byulBody1);
        byulBody2 = (ImageView)layout.findViewById(R.id.byulBody2);
        byulBody3 = (ImageView)layout.findViewById(R.id.byulBody3);
        byulBody4 = (ImageView)layout.findViewById(R.id.byulBody4);
        byulBody5 = (ImageView)layout.findViewById(R.id.byulBody5);
        byulHead1 = (ImageView)layout.findViewById(R.id.byulHead1);
        byulHead2 = (ImageView)layout.findViewById(R.id.byulHead2);
        byulHead3 = (ImageView)layout.findViewById(R.id.byulHead3);
        byulHead4 = (ImageView)layout.findViewById(R.id.byulHead4);
        byulHead5 = (ImageView)layout.findViewById(R.id.byulHead5);

    }

    @Override
    public void setValues() {
        super.setValues();
        birds2.post(new Runnable() {
            @Override
            public void run() {
                int birdsWidth = birds2.getWidth();
                int birdsHeight = (int)(birds2.getHeight()*1.3);
                seagullAppear = new ScaleAnimation(0,1,0,1,birdsWidth,birdsHeight);
                seagullAppear.setDuration(1000);
                seagullAppear.setFillAfter(true);
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadeIn = AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
        fadeIn.setDuration(1000);
        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new MyAnimationListener());
        fadeOut = AnimationUtils.loadAnimation(getContext(),R.anim.fade_out);
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(animationFlag==0) {
                    animationFlag = 1;
                    birds1.setVisibility(View.VISIBLE);
                    birds2.setVisibility(View.VISIBLE);
                    birds3.setVisibility(View.VISIBLE);
                    birds4.setVisibility(View.VISIBLE);
                    birds5.setVisibility(View.VISIBLE);
                    birds6.setVisibility(View.VISIBLE);
                    birds1.startAnimation(seagullAppear);
                    birds2.startAnimation(seagullAppear);
                    birds3.startAnimation(seagullAppear);
                    birds4.startAnimation(seagullAppear);
                    birds5.startAnimation(seagullAppear);
                    birds6.startAnimation(seagullAppear);

                    fadeIn.setStartOffset(2000);
                    fadeOut.setStartOffset(2000);

                    byulBody1.startAnimation(fadeOut);
                    byulHead1.startAnimation(fadeOut);
                    byulBody2.startAnimation(fadeIn);
                    byulHead2.startAnimation(fadeIn);
                    byulBody5.setVisibility(View.INVISIBLE);
                    byulHead5.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag){
                case 1:
                    animationFlag=2;
                    fadeIn.setStartOffset(0);
                    fadeOut.setStartOffset(0);
                    byulBody1.setVisibility(View.INVISIBLE);
                    byulHead1.setVisibility(View.INVISIBLE);
                    byulBody1.clearAnimation();
                    byulHead1.clearAnimation();
                    byulBody2.clearAnimation();
                    byulHead2.clearAnimation();
                    break;
                case 2:
                    animationFlag=3;
                    byulBody2.startAnimation(fadeOut);
                    byulHead2.startAnimation(fadeOut);
                    byulBody3.startAnimation(fadeIn);
                    byulHead3.startAnimation(fadeIn);
                    break;
                case 3:
                    animationFlag=4;
                    byulBody2.setVisibility(View.INVISIBLE);
                    byulHead2.setVisibility(View.INVISIBLE);
                    byulBody2.clearAnimation();
                    byulHead2.clearAnimation();
                    byulBody3.clearAnimation();
                    byulHead3.clearAnimation();
                    break;
                case 4:
                    animationFlag=5;
                    byulBody3.startAnimation(fadeOut);
                    byulHead3.startAnimation(fadeOut);
                    byulBody4.startAnimation(fadeIn);
                    byulHead4.startAnimation(fadeIn);
                    break;
                case 5:
                    animationFlag=6;
                    byulBody3.setVisibility(View.INVISIBLE);
                    byulHead3.setVisibility(View.INVISIBLE);
                    byulBody3.clearAnimation();
                    byulHead3.clearAnimation();
                    byulBody4.clearAnimation();
                    byulHead4.clearAnimation();
                    break;
                case 6:
                    animationFlag=7;
                    byulBody4.startAnimation(fadeOut);
                    byulHead4.startAnimation(fadeOut);
                    byulBody5.startAnimation(fadeIn);
                    byulHead5.startAnimation(fadeIn);
                    break;
                case 7:
                    animationFlag=0;
                    byulBody4.setVisibility(View.INVISIBLE);
                    byulHead4.setVisibility(View.INVISIBLE);
                    byulBody4.clearAnimation();
                    byulHead4.clearAnimation();
                    byulBody5.clearAnimation();
                    byulHead5.clearAnimation();
                    break;

            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            switch (animationFlag){
                case 1:
                    byulBody2.setVisibility(View.VISIBLE);
                    byulHead2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    byulBody3.setVisibility(View.VISIBLE);
                    byulHead3.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    byulBody4.setVisibility(View.VISIBLE);
                    byulHead4.setVisibility(View.VISIBLE);
                    break;
                case 7:
                    byulBody5.setVisibility(View.VISIBLE);
                    byulHead5.setVisibility(View.VISIBLE);
                    break;
            }
        }

    }
}
