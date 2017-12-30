package com.example.dokdofamily01;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.dokdofamily01.TaleActivity.homeKeyFlag;
import static com.example.dokdofamily01.TaleActivity.screenFlag;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by hero on 2017-12-04.
 */

public class MusicController {

    private Context mContext;
    private int resID;
    static ArrayList<SubTitleData> subtitleList;
    private MyAsynTask createMP;
    private MyThread subtitleThread;

    private int checkPage;

    public MusicController(Context context, int resID) {
        this.mContext = context;
        this.resID = resID;

        checkPage = 0;
    }

    public MediaPlayer getMp() {

        return createMP.getMp();
    }

    public boolean nextPart() {
        if (subtitleThread != null) {
            if (subtitleThread.increaseSubtitleMusic()) { // 자막 넘기는 상태
                checkPage = 1;
                return true;
            } else {
                checkPage = 0;
                return false;
            }
        } else {
            checkPage = 0;
            return false;
        }

    }

    public boolean previousPart() {
        if (subtitleThread != null) {
            if (subtitleThread.decreaseSubtitleMusic()) {
                checkPage = -1;
                return true;
            } else {
                checkPage = 0;
                return false;
            }
        } else {
            checkPage = 0;
            return false;
        }
    }

    public int checkPage() {
        return checkPage;
    }


    public void excuteAsync() {
        createMP = new MyAsynTask();
        createMP.execute();
    }


    public ArrayList<SubTitleData> makeSubTitleList(String[]... params) {
        subtitleList = new ArrayList<>();

        for (String[] s : params) {
            SubTitleData subTitleData = new SubTitleData(
                    s[0], Integer.parseInt(s[1])
            );
            subtitleList.add(subTitleData);
        }


        return subtitleList;
    }


    class MyAsynTask extends AsyncTask<Void, Void, MediaPlayer> {
        MediaPlayer mp;

        @Override
        protected void onPostExecute(MediaPlayer mediaPlayer) {
            super.onPostExecute(mediaPlayer);
            try {
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                    Timer timer = new Timer();
                    subtitleThread = new MyThread();
                    subtitleThread.setMP(mediaPlayer);
                    timer.schedule(subtitleThread, 0, 500);
                    Log.d("mpLength", mediaPlayer.getDuration() + "");
                }
            } catch (Exception e) {
                e.printStackTrace();
                doInBackground();
            }

        }

        @Override
        protected MediaPlayer doInBackground(Void... voids) {
            mp = MediaPlayer.create(mContext, resID);
            return mp;
        }

        public MediaPlayer getMp() {
            return mp;
        }


    }


    class MyThread extends TimerTask {

        int finishTime = 0;
        int subtitleIndex = 0;
        MediaPlayer mp;

        public void setMP(MediaPlayer mediaPlayer) {
            this.mp = mediaPlayer;
        }

        @Override
        public void run() {

            Log.d("scrFlagInMC", screenFlag + "");
            if (!homeKeyFlag && screenFlag) {
                try {
                    if (mp != null && mp.isPlaying()) {

                        int playingTime = mp.getCurrentPosition();
                        Message msg = new Message();

                        finishTime = subtitleList.get(subtitleIndex).getFinishTime();

                        if (playingTime <= finishTime) {
                            msg.what = subtitleIndex;
                        } else {
                            increaseIndexAuto();
                            if (playingTime > finishTime) {
                                increaseIndexAuto();
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
                } catch (Exception e) {
                    cancel();
                }

            } else {
                mp.release();
                cancel();
            }

        }

        private void increaseIndexAuto() {
            if (subtitleIndex != subtitleList.size()) {
                subtitleIndex++;
                finishTime = subtitleList.get(subtitleIndex).getFinishTime();
            }
        }

        public boolean increaseSubtitleMusic() {
            try {
                if (subtitleIndex < subtitleList.size() && mp.isPlaying()) {
                    subtitleIndex++;
                    checkPage  = 1;
                    mp.seekTo(subtitleList.get(subtitleIndex - 1).getFinishTime());
                    return true;
                } else {
                    checkPage = 0;
                    return false;
                }
            }catch (IllegalStateException e){
                e.printStackTrace();
                return false;
            }
        }

        public boolean decreaseSubtitleMusic() {

            try {
                if (mp.isPlaying()) {
                    if (subtitleIndex > 1) {
//                        원래 값 -= 2 일 경우 대사가 3파트일 때 subtitleIndex가 2 일 경우 0이 되버려서
//                        else if 조건은 1일 경우로 넘어가지 못하고 두번째 대사에서 첫번째 대사로 이동하지 않는 문제가 있음
//                        인덱스는 -1 씩 이동하되 재생은 그보다 -1 한 인덱스로 재생함으로써 조건을 충족하도록 수정
                        subtitleIndex -= 1;
                        checkPage = -1;
                        mp.seekTo(subtitleList.get(subtitleIndex-1).getFinishTime());
                        Log.d("subtitleIndex1 ", subtitleIndex + "");
                        return true;
                    } else if (subtitleIndex == 1) {
                        subtitleIndex = 0;
                        checkPage = -1;
                        mp.seekTo(0);
                        Log.d("subtitleIndex2 ", subtitleIndex + "");
                        return true;
                    } else {
                        checkPage = 0;
                        Log.d("subtitleIndex3 ", subtitleIndex + "");
                        return false;
                    }
                } else {
                    return false;
                }


            } catch (IllegalStateException e) {
                e.printStackTrace();
                return false;
            }

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
