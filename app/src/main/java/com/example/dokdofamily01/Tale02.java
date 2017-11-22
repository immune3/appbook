package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.ssomai.android.scalablelayout.ScalableLayout;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale02 extends BaseFragment {
    ImageView byulhead;
    ImageView seagullHand;
    ImageView seagullBody;
    ImageView star;
    TranslateAnimation ani;
    Animation seagullAppear;
    RotateAnimation seagullClick;
    int width;
    int height;


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
                new String[]{"진짜 꿈이야...", "35000"}
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
                seagullClick = new RotateAnimation(15,-15,width,height);
                seagullClick.setDuration(500);
                seagullClick.setRepeatCount(7);
                seagullClick.setRepeatMode(Animation.REVERSE);
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        ani = new TranslateAnimation(0,0,100,0);
        ani.setDuration(1000);
        ani.setFillAfter(true);
        ani.setAnimationListener(new MyAnimationListener());
        seagullAppear = AnimationUtils.loadAnimation(getContext(),R.anim.anim_02_seagull_appear);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        byulhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byulhead.startAnimation(ani);
                seagullHand.startAnimation(seagullAppear);
                seagullBody.setAnimation(seagullAppear);
            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byulhead.startAnimation(ani);
                seagullHand.startAnimation(seagullClick);
            }
        });
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
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
