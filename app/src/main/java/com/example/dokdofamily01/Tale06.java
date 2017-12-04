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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale06 extends BaseFragment {
    ImageView sea;
    ImageView waveshadow;
    ImageView[] smallwave = new ImageView[4];
    ImageView[] bigwave = new ImageView[3];
    ImageView[] seagull = new ImageView[2];

    TranslateAnimation waveAppear;
    Animation fadeIn;
    int animationFlag = 0;

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
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_6);
                }

                mp.start();

                Timer timer = new Timer();
                timer.schedule(new MyThread(),0, 500);

                sea.startAnimation(waveAppear);

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale06;


        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
                new String[]{"심심해서 언제나 간지럼 장난을 일삼는 투정쟁이 파도가 오늘은 넘실넘실~ 웃어요. ","8000"},
                new String[]{"언제나 마음 따뜻한 동도할머니는 별이가 좋아서 쳐다보고 또 쳐다봐요.   ", "16500"},
                new String[]{"독도는 정말 보물섬이에요? ","20500"},
                new String[]{"그럼~ 보물들로 가득하단다.","25500"},
                new String[]{"보물들은 어디에 있어요? ","29000"},
                new String[]{"하늘에도 있고","31500"},
                new String[]{"땅에도 있고, ","33000"},
                new String[]{"또 바다에도 있단다.","36500"},
                new String[]{"보물들을 보고 싶어요! ","40000"},
                new String[]{"그래~ 별이가 독도에서 보물찾기를 하면서 놀면 되겠구나!","49000"}
        );

        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();

        sea = (ImageView)layout.findViewById(R.id.sea);
        waveshadow = (ImageView)layout.findViewById(R.id.waveshadow);
        smallwave[0] = (ImageView)layout.findViewById(R.id.smallwave1);
        smallwave[1] = (ImageView)layout.findViewById(R.id.smallwave2);
        smallwave[2] = (ImageView)layout.findViewById(R.id.smallwave3);
        smallwave[3] = (ImageView)layout.findViewById(R.id.smallwave4);
        bigwave[0] = (ImageView)layout.findViewById(R.id.bigwave1);
        bigwave[1] = (ImageView)layout.findViewById(R.id.bigwave2);
        bigwave[2] = (ImageView)layout.findViewById(R.id.bigwave3);
        seagull[0] = (ImageView)layout.findViewById(R.id.seagull1);
        seagull[1] = (ImageView)layout.findViewById(R.id.seagull2);

    }

    @Override
    public void setValues() {
        super.setValues();

        sea.post(new Runnable() {
            @Override
            public void run() {
                if(animationFlag==0) {
                    animationFlag=1;
                    waveAppear = new TranslateAnimation(0, 0, (int) (sea.getHeight() * 0.8), 0);
                    waveAppear.setDuration(1000);
                    waveAppear.setFillAfter(true);
                    waveAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                    waveAppear.setAnimationListener(new MyAnimationListener());

                    sea.startAnimation(waveAppear);
                    bigwave[0].startAnimation(waveAppear);
                    bigwave[1].startAnimation(waveAppear);
                    bigwave[2].startAnimation(waveAppear);
                    Log.i("animationFlag", ""+animationFlag);
                }
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadeIn = AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new MyAnimationListener());
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
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
                subtitleIndex = subtitleIndex-1;
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

    private class MyAnimationListener extends com.example.dokdofamily01.MyAnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {
            super.onAnimationStart(animation);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            switch (animationFlag){
                case 1:
                    Log.i("animationFlag", ""+animationFlag);
                    animationFlag=2;
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
                    animationFlag=0;
//                    smallwave[0].clearAnimation();
//                    smallwave[1].clearAnimation();
//                    smallwave[2].clearAnimation();
//                    smallwave[3].clearAnimation();
//                    waveshadow.clearAnimation();
//                    seagull[0].clearAnimation();
//                    seagull[1].clearAnimation();
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            super.onAnimationRepeat(animation);
        }
    }

}
