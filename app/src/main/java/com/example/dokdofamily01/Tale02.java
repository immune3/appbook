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
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
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

public class Tale02 extends BaseFragment {
    ImageView byulhead;
    ImageView seagullHand;
    ImageView seagullBody;
    ImageView star;
    TranslateAnimation headUp;
    TranslateAnimation headDown;
    Animation seagullAppear;
    RotateAnimation seagullClick;
    int width;
    int height;
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
                System.out.println(2+"Visible");
                if(mp == null){
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_2);
                }

                mp.start();

                Timer timer = new Timer();
                timer.schedule(new MyThread(),0, 500);
                byulhead.startAnimation(headUp);
//                seagullHand.startAnimation(seagullAppear);
                seagullBody.setAnimation(seagullAppear);


            } else {
                System.out.println(2+"notVisible");
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
        xml = R.layout.tale02;

        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
                new String[]{"갈매기에요","1600"},
                new String[]{"하얀 깃털 옷을 새하얗게 차려입은 갈매기가 \n" +
                        "생긋~ 웃더니 말을 해요.", "8500"},
                new String[]{"별아 창문을 열어! ","12500"},
                new String[]{"꿈이야...","16000"},
                new String[]{"별이가 창문을 열어요.","19000"},
                new String[]{"폴짝 뛰어든 바다냄새가 시원해요.", "23500"},
                new String[]{"갈매기가 또 말을 해요.","27000"},
                new String[]{"별아 내 등에 앉아!","31500"},
                new String[]{"진짜 꿈이야...", "35500"}
        );
        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        byulhead = (ImageView)layout.findViewById(R.id.byulhead);
        seagullHand = (ImageView)layout.findViewById(R.id.seagullHand);
        seagullBody = (ImageView) layout.findViewById(R.id.seagullBody);
        star = (ImageView)layout.findViewById(R.id.star);
    }

    @Override
    public void setValues() {
        super.setValues();
        seagullHand.post(new Runnable() {
            @Override
            public void run() {

                width = (int)(seagullHand.getWidth()*0.85);
                height = (int)(seagullHand.getHeight()*0.8);

                seagullAppear = AnimationUtils.loadAnimation(getContext(),R.anim.anim_02_seagull_appear);
                seagullAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                seagullAppear.setAnimationListener(new MyAnimationListener());

                seagullClick = new RotateAnimation(10,-10,width,height);
                seagullClick.setDuration(300);
                seagullClick.setRepeatCount(1);
                seagullClick.setRepeatMode(Animation.REVERSE);
                seagullClick.setFillAfter(true);
                seagullClick.setInterpolator(new AccelerateDecelerateInterpolator());
                headUp = new TranslateAnimation(0,0,byulhead.getHeight(),0);
                headUp.setDuration(3000);
                headUp.setFillAfter(true);
                headUp.setInterpolator(new AccelerateDecelerateInterpolator());
                headUp.setAnimationListener(new MyAnimationListener());
                headDown= new TranslateAnimation(0,0,0,byulhead.getHeight());
                headDown.setDuration(300);
                headDown.setFillAfter(true);
                headDown.setInterpolator(new AccelerateDecelerateInterpolator());
                headDown.setAnimationListener(new MyAnimationListener());

                byulhead.startAnimation(headUp);
//                seagullHand.startAnimation(seagullAppear);
                seagullBody.setAnimation(seagullAppear);
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
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(animationFlag==1) {
                    animationFlag = 2;
                    seagullHand.setVisibility(View.VISIBLE);
                    byulhead.startAnimation(headDown);
                    seagullHand.startAnimation(seagullClick);
                }
            }
        });
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag){
                case 0:
                    animationFlag=1;
//                    byulhead.clearAnimation();
                    break;
                case 2:
                    animationFlag=3;
                    byulhead.startAnimation(headUp);
                    break;
                case 3:
                    animationFlag=1;
                    seagullHand.clearAnimation();
                    seagullHand.setVisibility(View.INVISIBLE);
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println(2+"onDestroyView");
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
