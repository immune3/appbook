package com.example.dokdofamily01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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

public class Tale04 extends BaseFragment {
    ImageView dokdo;
    ImageView sun;
    ImageView sunLight;
    TranslateAnimation sunRiseAni;
    Animation sunLightAppear;

    int[] sunLightLocation = new int[2];
    int[] sunLocation = new int[2];

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
                System.out.println(4 + "Visible");
                if (mp == null) {
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_4);
                }

                mp.start();

                Timer timer = new Timer();
                timer.schedule(new MyThread(), 0, 500);

            } else {
                System.out.println(3 + "notVisible");
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
        xml = R.layout.tale04;

        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
                new String[]{"저기~ 해님이 앉아있는 섬이 보물섬 독도야.", "5000"},
                new String[]{"정답게 마주앉은 동도할머니와 서도할아버지 사이에서", "10500"},
                new String[]{"잘 자고 일어난 뽀얀 얼굴의 해님이 쭉쭉 기지개를 켜요. ", "16000"},
                new String[]{"쌩~ 지나가던 바람이 되돌아와 아주아주 큰소리로 외쳐요", "22500"},
                new String[]{"별이가 왔어요! 별이가 왔다구요!", "26500"}
        );

        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        dokdo = (ImageView) layout.findViewById(R.id.dokdo);
        sun = (ImageView) layout.findViewById(R.id.sun);
        sunLight = (ImageView) layout.findViewById(R.id.sunLight);
    }

    @Override
    public void setValues() {
        super.setValues();
        dokdo.post(new Runnable() {
            @Override
            public void run() {
                sunLight.getLocationOnScreen(sunLightLocation);
                sun.getLocationOnScreen(sunLocation);

                sun.setY(sunLightLocation[1]);
//                Log.d("SunLightLocation:", "LocationX"+sunLightLocation[0]);
//                Log.d("SunLightLocation:", "LocationY"+sunLightLocation[1]);

                sunRiseAni = new TranslateAnimation(0, 0, 0, sunLocation[1]-200);
                sunRiseAni.setDuration(3000);
                sunRiseAni.setFillAfter(true);
                sunRiseAni.setAnimationListener(new MyAnimationListener());


            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        sunLightAppear = AnimationUtils.loadAnimation(getContext(), R.anim.anim_04_sunlight_appear);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        dokdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sun.startAnimation(sunRiseAni);
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
            sunLight.setVisibility(View.VISIBLE);
            sunLight.startAnimation(sunLightAppear);
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println(2 + "onDestroyView");
        if (mp != null && mp.isPlaying()) {
            mp.pause();
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    private ArrayList<SubTitleData> makeSubTitleList(String[]... params) {
        ArrayList<SubTitleData> list = new ArrayList<>();

        for (String[] s : params) {
            SubTitleData subTitleData = new SubTitleData(
                    s[0], Integer.parseInt(s[1])
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

                if (playingTime <= finishTime) {
                    msg.what = subtitleIndex;
                } else {
                    increaseIndex();
                    if (playingTime > finishTime) {
                        increaseIndex();
                    } else {
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

        private void increaseIndex() {
            subtitleIndex++;
            finishTime = subtitleList.get(subtitleIndex).getFinishTime();
        }
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            if (msg.what >= 0)
                subtitleTextView.setText(subtitleList.get(msg.what).getSubTitle());
            else
                subtitleTextView.setText(null);


        }
    };

}





