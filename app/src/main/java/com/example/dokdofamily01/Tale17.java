package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale17 extends BaseFragment {


    boolean isAttached = false;
    MediaPlayer mp = null;

    ArrayList<SubTitleData> subtitleList;

    ImageView dokdo_under_sea;
    ImageView wave_shadow1;
    ImageView wave_shadow2;
    ImageView star;

    Animation fadeIn;

    SoundPool sp;
    int clickStar;
    int appearDokdo;

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
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_17);
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
        xml = R.layout.tale17;


        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
                new String[]{"깜깜해지면...","1500"},
                new String[]{"이렇게나 깊고 깊은 바닷속까지도", "5000"},
                new String[]{"서로를 보살피는 독도 가족들의 소리가 잘 들리지~","9500"},
                new String[]{"동도할머니랑 서도할아버지가 \n" +
                        "동해바다에 정답게 마주 앉은 ","19000"},
                new String[]{"아~주아~주 먼 옛날부터 오늘까지","25000"},
                new String[]{"우리들은 언제나 서로 의지하고 \n" +
                        "함께 지켜주며 지내왔단다. ","25000"}
        );

        subtitleTextView.setText(null);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        dokdo_under_sea = (ImageView)layout.findViewById(R.id.dokdo_undersea);
        wave_shadow1= (ImageView)layout.findViewById(R.id.wave_shadow_01);
        wave_shadow2= (ImageView)layout.findViewById(R.id.wave_shadow_02);
        star= (ImageView)layout.findViewById(R.id.star);

        sp = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        clickStar = sp.load(getContext(),R.raw.effect_17_click_star,1);
        appearDokdo = sp.load(getContext(),R.raw.effect_17_appear_dokdo,2);

    }

    @Override
    public void setValues() {
        super.setValues();
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadeIn = AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
        fadeIn.setDuration(3000);
        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new MyAnimationListener());
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(clickStar,1,1,0,0,1);
                dokdo_under_sea.startAnimation(fadeIn);
            }
        });

    }


    private class MyAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            sp.play(appearDokdo,1,1,0,0,1);
//            dokdo_under_sea.startAnimation(fadeIn);
//            dokdo_under_sea.clearAnimation();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
