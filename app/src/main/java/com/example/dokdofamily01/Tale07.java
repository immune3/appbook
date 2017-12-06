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
import android.view.animation.RotateAnimation;
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

public class Tale07 extends BaseFragment {
    ImageView dokdo;
    ImageView seagull[] = new ImageView[3];
    ImageView shadow[] = new ImageView[3];

    RotateAnimation seagullAppear1;
    RotateAnimation seagullDisappear1;
    RotateAnimation seagullAppear2;
    RotateAnimation seagullAppear3;
    RotateAnimation seagullDisappear3;
    RotateAnimation rotateDokdo;
    ScaleAnimation scaleDokdo;
    AnimationSet moveDokdo;

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
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_7);
                }

                mp.start();

                Timer timer = new Timer();
                timer.schedule(new MyThread(),0, 500);

                if(animationFlag==0){
                    animationFlag=1;
                    seagull[0].setVisibility(View.INVISIBLE);
                    seagull[1].setVisibility(View.INVISIBLE);
                    seagull[2].setVisibility(View.INVISIBLE);
                    seagull[0].startAnimation(seagullAppear1);
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
        xml = R.layout.tale07;

        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
                new String[]{"언제나 용감한 갈매기는 잠시 곰곰... 생각하더니","5300"},
                new String[]{"별이의 보물찾기를 위해 조금 특별하게 날아야겠다고 마음먹어요.", "11500"},
                new String[]{"끼룩끼룩~ 갈매기 롤러코스터 출발!","18000"},
                new String[]{"올라갈 때는 바다색 날개를 펄럭이며 훨훨~ ","24500"},
                new String[]{"내려올 때는 갈고리 바람을 타고 쌩쌩~  ","33700"},
                new String[]{"사철나무 아빠 집까지는 단숨에 휘익~","37500"},
                new String[]{"이야~ 까르르~ ","41500"},
                new String[]{"구경하던 해님도 신나서 하늘 높이 폴짝~ 튀어요.","47800"}
        );

        subtitleTextView.setText(null);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        dokdo = (ImageView)layout.findViewById(R.id.dokdo);
        seagull[0] = (ImageView)layout.findViewById(R.id.seagull1);
        seagull[1] = (ImageView)layout.findViewById(R.id.seagull2);
        seagull[2] = (ImageView)layout.findViewById(R.id.seagull3);
        shadow[0] = (ImageView)layout.findViewById(R.id.shadow1);
        shadow[1] = (ImageView)layout.findViewById(R.id.shadow1);
        shadow[2] = (ImageView)layout.findViewById(R.id.shadow1);
    }

    @Override
    public void setValues() {
        super.setValues();
        sl.post(new Runnable() {
            @Override
            public void run() {
                seagullAppear1 = new RotateAnimation(20,0,-(int)(seagull[0].getWidth()*4),-(int)(seagull[0].getHeight()*1.5));
                seagullAppear1.setDuration(1500);
                seagullAppear1.setFillAfter(true);
                seagullAppear1.setInterpolator(new AccelerateDecelerateInterpolator());
                seagullAppear1.setAnimationListener(new MyAnimationListener());

                seagullDisappear1 = new RotateAnimation(0,-40,-(int)(seagull[0].getWidth()*4),-(int)(seagull[0].getHeight()*1.5));
                seagullDisappear1.setDuration(2000);
                seagullDisappear1.setFillAfter(true);
                seagullDisappear1.setInterpolator(new AccelerateDecelerateInterpolator());
                seagullDisappear1.setAnimationListener(new MyAnimationListener());

                seagullAppear2 = new RotateAnimation(35,-35,0,-(int)(seagull[1].getHeight()*5));
                seagullAppear2.setDuration(4000);
                seagullAppear2.setFillAfter(true);
                seagullAppear2.setInterpolator(new AccelerateDecelerateInterpolator());
                seagullAppear2.setAnimationListener(new MyAnimationListener());

                seagullAppear3 = new RotateAnimation(-30,0,-(int)(seagull[2].getWidth()*1.2),-(int)(seagull[1].getHeight()*2.5));
                seagullAppear3.setDuration(2000);
                seagullAppear3.setFillAfter(true);
                seagullAppear3.setInterpolator(new AccelerateDecelerateInterpolator());
                seagullAppear3.setAnimationListener(new MyAnimationListener());
                seagullDisappear3 = new RotateAnimation(0,40,-(int)(seagull[2].getWidth()*1.2),-(int)(seagull[1].getHeight()*2.5));
                seagullDisappear3.setDuration(2000);
                seagullDisappear3.setFillAfter(true);
                seagullDisappear3.setInterpolator(new AccelerateDecelerateInterpolator());
                seagullDisappear3.setAnimationListener(new MyAnimationListener());

                scaleDokdo = new ScaleAnimation(1,0.7f,1,0.7f,(int)(dokdo.getWidth()*0.5),(int)(dokdo.getHeight()*0.5));
                scaleDokdo.setDuration(4000);
                scaleDokdo.setFillAfter(true);
                scaleDokdo.setInterpolator(new AccelerateDecelerateInterpolator());
                rotateDokdo = new RotateAnimation(0,-30,(int)(dokdo.getWidth()*0.5),(int)(dokdo.getHeight()*0.5));
                rotateDokdo.setDuration(4000);
                rotateDokdo.setFillAfter(true);
                rotateDokdo.setInterpolator(new AccelerateDecelerateInterpolator());
                moveDokdo = new AnimationSet(false);
                moveDokdo.setFillAfter(true);
                moveDokdo.addAnimation(scaleDokdo);
                moveDokdo.addAnimation(rotateDokdo);



                if(animationFlag==0){
                    animationFlag=1;
                    seagull[0].setVisibility(View.INVISIBLE);
                    seagull[1].setVisibility(View.INVISIBLE);
                    seagull[2].setVisibility(View.INVISIBLE);
                    seagull[0].startAnimation(seagullAppear1);

                }
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
        seagull[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(animationFlag==0){
                    animationFlag=2;
                    seagull[0].startAnimation(seagullDisappear1);
                    dokdo.startAnimation(moveDokdo);
                }
            }
        });
        seagull[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(animationFlag==5){
                    animationFlag=6;
                    seagull[2].startAnimation(seagullDisappear3);
                    dokdo.clearAnimation();
                }
            }
        });
    }

    private class MyAnimationListener extends com.example.dokdofamily01.MyAnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {
            super.onAnimationStart(animation);
            switch (animationFlag){
                case 1:
                    seagull[0].setVisibility(View.VISIBLE);
                    break;
                case 3:
                    seagull[1].setVisibility(View.VISIBLE);
                    break;
                case 4:
                    seagull[2].setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            switch (animationFlag){
                case 1:
                    animationFlag=0;
                    seagull[0].clearAnimation();
                    break;
                case 2:
                    animationFlag=3;
                    seagull[0].clearAnimation();
                    seagull[0].setVisibility(View.INVISIBLE);
                    seagull[1].startAnimation(seagullAppear2);
                    break;
                case 3:
                    animationFlag=4;
                    seagull[1].clearAnimation();
                    seagull[1].setVisibility(View.INVISIBLE);
                    seagull[2].startAnimation(seagullAppear3);
                    break;
                case 4:
                    animationFlag=5;
                    seagull[2].clearAnimation();
                    break;
                case 6:
                    animationFlag=1;
                    seagull[2].setVisibility(View.INVISIBLE);
                    seagull[2].clearAnimation();
                    seagull[0].startAnimation(seagullAppear1);
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            super.onAnimationRepeat(animation);
        }
    }
}
