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
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
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

public class Tale10 extends BaseFragment {

    ImageView birds;
    ImageView mountain;
    ImageView rock;
    ImageView seagull;
    ImageView byulHead;
    ImageView byulBody;
    ImageView byulHand;

    TranslateAnimation mountainAppear;
    TranslateAnimation rockAppear;
    TranslateAnimation birdsAppear;
    Animation fadeIn;

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
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_10);
                }

                mp.start();

                Timer timer = new Timer();
                timer.schedule(new MyThread(),0, 500);
                if(animationFlag==0){
                    animationFlag=1;
                    mountain.startAnimation(mountainAppear);
                    rock.startAnimation(rockAppear);
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
        xml = R.layout.tale10;

        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
                new String[]{"보물들은 다 어디에 있을까?","4000"},
                new String[]{"풀죽은 별이가 쪼그리고 앉아요.", "7500"},
                new String[]{"언제나 사이좋은 바다제비들이 조잘대기 시작해요.","12500"},
                new String[]{"별이가 아직 보물을 못 찾았대~ ","15500"},
                new String[]{"... 어떡해? ... \n" +
                        "걱정 마. 금방 찾을 거야~ ","18700"},
                new String[]{"... 맞아! 하늘이랑 땅이랑 바다랑 다 이렇게 맑고 푸른 걸~ ","23700"},
                new String[]{"... 오래 날아 날개가 막 아파도 밥 먹고 코~ 자고 나면 \n" +
                        "다시 힘이 펄펄 솟는 섬이잖아~ ","32300"},
                new String[]{"... 그럼 또 씩씩하게 날 수 있지! ","35200"},
                new String[]{"...우리들은 언제나 튼튼하지! ","37700"},
                new String[]{"... 별이가 빨리 보물을 찾았으면~ ","40500"},
                new String[]{"... 우리가 알려줄까?","43000"}
        );

        subtitleTextView.setText(null);



        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        birds = (ImageView)layout.findViewById(R.id.birds);
        mountain = (ImageView)layout.findViewById(R.id.mountain);
        rock = (ImageView)layout.findViewById(R.id.rock);
        seagull = (ImageView)layout.findViewById(R.id.seagull);
        byulHead = (ImageView)layout.findViewById(R.id.byulHead);
        byulBody = (ImageView)layout.findViewById(R.id.byulBody);
        byulHand = (ImageView)layout.findViewById(R.id.byulHand);
    }

    @Override
    public void setValues() {
        super.setValues();
        mountain.post(new Runnable() {
            @Override
            public void run() {
                mountainAppear = new TranslateAnimation(0,0,mountain.getHeight(),0);
                mountainAppear.setDuration(2000);
//                mountainAppear.setFillAfter(true);
                mountainAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                mountainAppear.setAnimationListener(new MyAnimationListener());

                birdsAppear = new TranslateAnimation(0,0,birds.getHeight(),0);
                birdsAppear.setDuration(1000);
//                birdsAppear.setFillAfter(true);
                birdsAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                birdsAppear.setAnimationListener(new MyAnimationListener());

                rockAppear = new TranslateAnimation(-rock.getWidth(),0,0,0);
                rockAppear.setStartOffset(500);
                rockAppear.setDuration(1500);
//                birdsAppear.setFillAfter(true);
                rockAppear.setInterpolator(new AccelerateDecelerateInterpolator());
//                rockAppear.setAnimationListener(new MyAnimationListener());


                if(animationFlag==0){
                    animationFlag=1;
                    mountain.startAnimation(mountainAppear);
                    rock.startAnimation(rockAppear);
                }
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeIn.setDuration(1000);
        fadeIn.setAnimationListener(new MyAnimationListener());
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
    }

    private class MyAnimationListener extends com.example.dokdofamily01.MyAnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {
            super.onAnimationStart(animation);
            switch (animationFlag){
                case 1:
                    byulHead.setVisibility(View.INVISIBLE);
                    byulHand.setVisibility(View.INVISIBLE);
                    byulBody.setVisibility(View.INVISIBLE);
                    seagull.setVisibility(View.INVISIBLE);
                    birds.setVisibility(View.INVISIBLE);
                    mountain.setVisibility(View.VISIBLE);
                    rock.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    byulHead.setVisibility(View.VISIBLE);
                    byulHand.setVisibility(View.VISIBLE);
                    byulBody.setVisibility(View.VISIBLE);
                    seagull.setVisibility(View.VISIBLE);
                    birds.setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            switch (animationFlag){
                case 1:
                    animationFlag=2;
                    mountain.clearAnimation();
                    rock.clearAnimation();
                    birds.startAnimation(birdsAppear);
                    byulBody.startAnimation(fadeIn);
                    byulHand.startAnimation(fadeIn);
                    byulHead.startAnimation(fadeIn);
                    seagull.startAnimation(fadeIn);
                    break;
                case 2:
                    animationFlag=0;
                    birds.clearAnimation();
                    byulBody.clearAnimation();
                    byulHand.clearAnimation();
                    byulHead.clearAnimation();
                    seagull.clearAnimation();
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            super.onAnimationRepeat(animation);
        }
    }
}
