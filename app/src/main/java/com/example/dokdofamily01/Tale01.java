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
import android.widget.RelativeLayout;

import com.ssomai.android.scalablelayout.ScalableLayout;

import static android.content.ContentValues.TAG;
import static java.lang.Integer.parseInt;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale01 extends BaseFragment{



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


        subtitleTextView.setText(subtitleList.get(0).getSubTitle());
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
    }

    @Override
    public void setValues() {
        super.setValues();
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
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
