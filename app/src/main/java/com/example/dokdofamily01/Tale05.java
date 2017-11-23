package com.example.dokdofamily01;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
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

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale05 extends BaseFragment {
    ImageView[] letter = new ImageView[6];
    Animation[] letterAppear = new Animation[6];
    Animation[] letterDisappear = new Animation[6];
    int letterIter = 0;


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
        if (isAttached) {
            if (isVisibleToUser) {
                System.out.println(32 + "Visible");
                if (mp == null) {
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_5);
                }

                mp.start();

                Timer timer = new Timer();
                timer.schedule(new MyThread(), 0, 500);

            } else {
                System.out.println(2 + "notVisible");
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
        xml = R.layout.tale05;

        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
                new String[]{"덜컹덜컹 쿵쿵쿵~ ", "2500"},
                new String[]{"언제나 상냥한 빨간 우체통 엄마가 호들갑스럽게 달려와 별이를 덥석 끌어안아요.", "11000"},
                new String[]{"어서 오렴~ 나는 편지를 아주 재미나게 읽어주는 빨간 우체통 엄마란다.", "17500"},
                new String[]{"별이의 편지도 내가 읽어주었지~ 호호호~ ", "21700"},
                new String[]{"독도를 생각하는 별이의 마음이 어찌나 예쁘던지... 쪽~ 쪽~ 쪼오옥~ ", "30000"},
                new String[]{"아줌마 너무 간지러워요. 까르르~", "40000"}
        );

        subtitleTextView.setText(null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        letter[0] = (ImageView) layout.findViewById(R.id.letter0);
        letter[1] = (ImageView) layout.findViewById(R.id.letter1);
        letter[2] = (ImageView) layout.findViewById(R.id.letter2);
        letter[3] = (ImageView) layout.findViewById(R.id.letter3);
        letter[4] = (ImageView) layout.findViewById(R.id.letter4);
        letter[5] = (ImageView) layout.findViewById(R.id.letter5);
    }

    @Override
    public void setValues() {
        super.setValues();
        letter[5].post(new Runnable() {
            @Override
            public void run() {
//                letterAnimation.setAnimationListener(new MyAnimationListener());
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();



        for(int iter=0; iter<6 ; iter++) {

            letterAppear[iter] = new AlphaAnimation(0, 1);
            letterAppear[iter].setDuration(1000);
            letterAppear[iter].setFillAfter(true);
            letterAppear[iter].setStartOffset(iter*1000);


            letterDisappear[iter] = new AlphaAnimation(1, 0);
            letterDisappear[iter].setDuration(1000);
            letterDisappear[iter].setFillAfter(true);
            letterDisappear[iter].setStartOffset(iter*1000);
        }

    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        letter[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int iter = 0; iter < 5 ; iter++) {
                    letter[iter].startAnimation(letterDisappear[iter]);
                    letter[iter+1].startAnimation(letterAppear[iter]);
                }
//                letter[1].startAnimation(letterAppear);
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


