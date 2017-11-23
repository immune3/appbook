package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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

public class Tale03 extends BaseFragment{



    ImageView[] cloud = new ImageView[6];
    ImageView byulHand;
    Animation fadein;
    TranslateAnimation[] cloudAnimation = new TranslateAnimation[5];
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
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_3);
                }

                mp.start();

                Timer timer = new Timer();
                timer.schedule(new MyThread(),0, 500);

                byulHand.setVisibility(View.INVISIBLE);

                if(animationFlag == 0) {
                    animationFlag = 1;
                    cloud[0].startAnimation(cloudAnimation[0]);
                    cloud[1].startAnimation(cloudAnimation[1]);
                    cloud[2].startAnimation(cloudAnimation[1]);
                    cloud[3].startAnimation(cloudAnimation[2]);
                    cloud[4].startAnimation(cloudAnimation[3]);
                    cloud[5].startAnimation(cloudAnimation[4]);
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale03;

        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
                new String[]{"별이가 갈매기의 등에 수줍게 앉아요. ","4000"},
                new String[]{"갈매기는 푸르르 날아올라 별님들이 \n" +
                        "하품하는 새벽하늘을 너울너울 날아요. ", "12500"},
                new String[]{"별아 동도할머니~ 서도할아버지를 만나러 \n" +
                        "보물섬 독도에 가는 거야~ ","20000"},
                new String[]{"정말? 이렇게 날아서?","23500"},
                new String[]{"그래~ 팔을 뻗어 말랑말랑 솜사탕 구름을 만져보렴~","31000"}
        );

        subtitleTextView.setText(null);
        return super.onCreateView(inflater, container, savedInstanceState);
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

                // 왼쪽 아래 구름(cloud[1], cloud[2])
                cloudAnimation[1] = new TranslateAnimation(-cloud[2].getWidth(), 0, cloud[2].getHeight(), 0);
                cloudAnimation[1].setStartOffset(500);
                cloudAnimation[1].setDuration(2500);
                cloudAnimation[1].setFillAfter(true);

                // 중앙 위 구름(cloud[3])
                cloudAnimation[2] = new TranslateAnimation(0, 0, -cloud[3].getHeight(), 0);
                cloudAnimation[2].setStartOffset(2000);
                cloudAnimation[2].setDuration(2000);
                cloudAnimation[2].setFillAfter(true);
                cloudAnimation[2].setAnimationListener(new MyAnimationListener());

                // 오른쪽 아래 구름(cloud[4])
                cloudAnimation[3] = new TranslateAnimation(cloud[4].getWidth(), 0, 0, 0);
                cloudAnimation[3].setStartOffset(500);
                cloudAnimation[3].setDuration(2000);
                cloudAnimation[3].setFillAfter(true);

                // 오른쪽 위 구름(cloud[5])
                cloudAnimation[4] = new TranslateAnimation(cloud[5].getWidth(), 0, -cloud[5].getHeight(), 0);
                cloudAnimation[4].setStartOffset(1500);
                cloudAnimation[4].setDuration(2000);
                cloudAnimation[4].setFillAfter(true);

                byulHand.setVisibility(View.INVISIBLE);
                if(animationFlag == 0) {
                    animationFlag = 1;
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
    }

    @Override
    public void setupEvents() { super.setupEvents(); }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            animationFlag = 0;
            byulHand.setVisibility(View.VISIBLE);
            byulHand.startAnimation(fadein);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            byulHand.setVisibility(View.INVISIBLE);
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
            finishTime = subtitleList.get(subtitleIndex).getFinishTime();
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



}
