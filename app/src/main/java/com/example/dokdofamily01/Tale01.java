package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ssomai.android.scalablelayout.ScalableLayout;

import static android.content.ContentValues.TAG;
import static java.lang.Integer.parseInt;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale01 extends BaseFragment{

    int animationFlag=0;
    ImageView lamp;
    ImageView lampLight;
    ImageView bedLight;
    ImageView head;
    ImageView blanket;
    ImageView byul;
    ImageView hand;
    ImageView curtain;
    ImageView light;
    Animation fadeIn;
    Animation fadeOut;

    ArrayList<SubTitleData> subtitleList;

    boolean isAttached = false;
    MediaPlayer mp = null;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isAttached = true;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isAttached) {

            if (isVisibleToUser) {
                System.out.println(1 + "Visible");
                if (mp == null) {
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_1);
                }
                mp.start();

                Timer timer = new Timer();
                timer.schedule(new MyThread(), 0, 500);


            } else {
                System.out.println(1 + "notVisible");
                if (mp != null && mp.isPlaying()) {
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
        xml = R.layout.tale01;
        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
                new String[]{"별님들이 소곤거리는 아직은 까만밤이에요", "5000"},
                new String[]{"콕콕... 콕콕콕...","7500"},
                new String[]{"누가 별이 방 창문을 가만가만 두드려요.", "12500"},
                new String[]{"별이가 꼬물꼬물 일어나 창가로 가요.", "17000"},
                new String[]{"가슴이 콩콩거려 커튼을 아주 빼꼼히 열었는데","22500"}
        );


        subtitleTextView.setText(null);
        if (mp == null && BaseFragment.firstFlag == 0) {
            mp = MediaPlayer.create(getActivity(), R.raw.scene_1);
            mp.start();

            Timer timer = new Timer();
            timer.schedule(new MyThread(), 0, 500);

            BaseFragment.firstFlag = 1;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        lamp = (ImageView)layout.findViewById(R.id.lamp);
        lampLight = (ImageView)layout.findViewById(R.id.lampLight);
        bedLight = (ImageView)layout.findViewById(R.id.bedLight);
        head = (ImageView)layout.findViewById(R.id.head);
        blanket = (ImageView)layout.findViewById(R.id.blanket);
        byul = (ImageView)layout.findViewById(R.id.byul);
        hand = (ImageView)layout.findViewById(R.id.hand);
        curtain = (ImageView)layout.findViewById(R.id.curtain);
        light = (ImageView)layout.findViewById(R.id.light);
    }

    @Override
    public void setValues() {
        super.setValues();
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadeIn = AnimationUtils.loadAnimation(getContext(),R.anim.anim_01_fadein);
        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new MyAnimationListener());

        fadeOut = AnimationUtils.loadAnimation(getContext(),R.anim.anim_01_fadeout);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new MyAnimationListener());

    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        lamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationFlag = 1;
                lampLight.startAnimation(fadeIn);
                bedLight.startAnimation(fadeIn);
                head.setVisibility(View.VISIBLE);
                blanket.setVisibility(View.VISIBLE);
                curtain.setVisibility(View.VISIBLE);
                light.setVisibility(View.INVISIBLE);
                byul.setVisibility(View.INVISIBLE);
                hand.setVisibility(View.INVISIBLE);
                
            }
        });
    }
    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag) {
                case 1:
                    animationFlag = 2;
                    lampLight.clearAnimation();
                    bedLight.clearAnimation();
                    break;
                case 2:
                    animationFlag = 3;

                    fadeOut.setStartOffset(1000);
                    head.startAnimation(fadeOut);
                    bedLight.startAnimation(fadeOut);
                    blanket.startAnimation(fadeOut);
                    break;
                case 3:
                    animationFlag = 4;
                    bedLight.setVisibility(View.INVISIBLE);
                    head.setVisibility(View.INVISIBLE);
                    blanket.setVisibility(View.INVISIBLE);
                    bedLight.clearAnimation();
                    head.clearAnimation();
                    blanket.clearAnimation();
                    break;
                case 4:
                    animationFlag = 5;
                    byul.startAnimation(fadeIn);
                    break;
                case 5:
                    animationFlag = 6;
                    byul.clearAnimation();
                    break;
                case 6:
                    animationFlag = 7;
                    fadeIn.setStartOffset(1000);
                    fadeOut.setStartOffset(1000);
                    hand.startAnimation(fadeIn);
                    curtain.startAnimation(fadeOut);
                    light.startAnimation(fadeIn);
                    break;
                case 7:
                    animationFlag = 8;
                    curtain.setVisibility(View.INVISIBLE);
//                    hand.clearAnimation();
//                    curtain.clearAnimation();
//                    light.clearAnimation();
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            if (animationFlag == 1) {
                lampLight.setVisibility(View.VISIBLE);
                bedLight.setVisibility(View.VISIBLE);
            } else if (animationFlag == 5) {
                byul.setVisibility(View.VISIBLE);
            } else if (animationFlag == 7) {
                hand.setVisibility(View.VISIBLE);
                light.setVisibility(View.VISIBLE);
            }
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println(1 + "onDestroyView");
        if (mp != null && mp.isPlaying()) {
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
