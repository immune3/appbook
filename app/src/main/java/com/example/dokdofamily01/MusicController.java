package com.example.dokdofamily01;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.AlphaAnimation;

import com.example.dokdofamily01.Data.SubTitleData;
import com.example.dokdofamily01.Data.SubTitleDataById;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.homeKeyFlag;
import static com.example.dokdofamily01.TaleActivity.screenFlag;
import static com.example.dokdofamily01.TaleActivity.subtitleImageVIew;

//import static com.example.dokdofamily01.Tale20.cutainText;
//import static com.example.dokdofamily01.Tale20.endFlag;

/**
 * Created by hero on 2017-12-04.
 */

public class MusicController {

    private Context mContext;
    private int resID;
    static ArrayList<SubTitleData> subtitleList1;
    static ArrayList<SubTitleDataById> subtitleList;
    private MyAsynTask createMP;
    private MyThread subtitleThread;
    private CustomViewPager vp;
    int subtitleIndex = 0;

    AlphaAnimation fadein;
    int animFlag;

    Handler delayedPagingHandler;
    Runnable delayedPagingRunnable;

    public MusicController(Context context, int audioID) {
        this.mContext = context;
        this.resID = audioID;
        animFlag = 0;
    }

    public MusicController(Context context, int audioID, CustomViewPager viewPager, int[]... sub) {
        this.mContext = context;
        this.resID = audioID;
        vp = viewPager;
        fadein = new AlphaAnimation(0,1);
        fadein.setDuration(500);
        fadein.setFillAfter(true);
        animFlag = 0;
        makeSubTitleList(sub);
        excuteAsync();

    }

    public void setVP(CustomViewPager vp) {
        this.vp = vp;
    }

    public MediaPlayer getMp() {

        return createMP.getMp();
    }

    public boolean nextPart() {
        if (subtitleThread != null) {
            if (subtitleThread.increaseSubtitleMusic()) { // 자막 넘기는 상태

                return true;// 자막만 넘기는
            } else {
                destroyPaging();
                return false; // 자막이 끝났으니까 다음 페이지로 넘겨라
            }
        } else {
            return false;
        }

    }

    public boolean previousPart() {
        if (subtitleThread != null) {
            if (subtitleThread.decreaseSubtitleMusic()) {
                return true;
            } else {
                destroyPaging();
                return false;
            }
        } else {
            return false;
        }
    }

    public void delayedPaging() {
        delayedPagingRunnable = new Runnable() {
            @Override
            public void run() {
                vp.setCurrentItem(vp.getCurrentItem() + 1, false);
            }
        };

        delayedPagingHandler = new Handler();
        delayedPagingHandler.postDelayed(delayedPagingRunnable, 3000);
    }

    public void destroyPaging() {
        if(delayedPagingHandler != null && delayedPagingRunnable != null) {
            delayedPagingHandler.removeCallbacks(delayedPagingRunnable);
            delayedPagingHandler = null;
            delayedPagingRunnable = null;
        }
    }

    public void excuteAsync() {
        createMP = new MyAsynTask();
        createMP.execute();
    }

    public void makeSubTitleList(int[]... params) {
        subtitleList = new ArrayList<>();
        for (int[] s : params) {
            SubTitleDataById subTitleData = new SubTitleDataById(
                    s[0], s[1]
            );
            subtitleList.add(subTitleData);
        }
    }


    public ArrayList<SubTitleData> makeSubTitleList(String[]... params) {
        subtitleList1 = new ArrayList<>();

        for (String[] s : params) {
            SubTitleData subTitleData = new SubTitleData(
                    s[0], Integer.parseInt(s[1])
            );
            subtitleList1.add(subTitleData);
        }


        return subtitleList1;
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
                    subtitleIndex = 0;
                    subtitleThread = new MyThread();
                    subtitleThread.setMP(mediaPlayer);
                    timer.schedule(subtitleThread, 0, 500);
                    Log.d("mpLength", mediaPlayer.getDuration() + "");
                    if (vp != null) {
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            // 고쳐야 함 //
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                System.out.println("PageMove Auto");
                                new AsyncTask<Void, Void, Void>() {
                                    Boolean animFlag = false;

                                    @Override
                                    protected void onPostExecute(Void aVoid) {
                                        super.onPostExecute(aVoid);
                                        if (animFlag) {
                                            delayedPaging();
                                        }

                                    }

                                    @Override
                                    protected Void doInBackground(Void... voids) {

                                        //Log.d("checkedAnimation", checkedAnimation + "/");
                                        while (!checkedAnimation) {
                                            // 1초마다 검사
                                            try {
                                                Thread.sleep(300);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();

                                            }

                                        }
                                        animFlag = true;
                                        return null;
                                    }
                                }.execute();

                            }
                        });
                    }
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
                Log.d("subtitleIndex ", subtitleIndex + "");
                if (mp!= null && subtitleIndex < subtitleList.size() - 1 && mp.isPlaying()) {
                    subtitleIndex++;
                    mp.seekTo(subtitleList.get(subtitleIndex - 1).getFinishTime());
                    return true;
                } else {
                    return false;
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean decreaseSubtitleMusic() {
//            subtitleImageVIew.setVisibility(View.VISIBLE);
            try {
                if (mp!=null && mp.isPlaying()) {
                    if (subtitleIndex > 1) {
//                        원래 값 -= 2 일 경우 대사가 3파트일 때 subtitleIndex가 2 일 경우 0이 되버려서
//                        else if 조건은 1일 경우로 넘어가지 못하고 두번째 대사에서 첫번째 대사로 이동하지 않는 문제가 있음
//                        인덱스는 -1 씩 이동하되 재생은 그보다 -1 한 인덱스로 재생함으로써 조건을 충족하도록 수정
                        subtitleIndex -= 1;
                        mp.seekTo(subtitleList.get(subtitleIndex - 1).getFinishTime());
                        return true;
                    } else if (subtitleIndex == 1) {
                        subtitleIndex = 0;
                        mp.seekTo(0);
                        return true;
                    } else {
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
//        public int getSubtitleIndex(){
//            return subtitleIndex;
//        }
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            if (msg.what >= 0) {
//                subtitleTextView.setText(subtitleList1.get(msg.what).getSubTitle());
                subtitleImageVIew.setImageDrawable(null);
                try {
//                    if(endFlag == 1 && msg.what == 8) {
//                        if(animFlag == 0) {
//                            animFlag = 1;
//                            cutainText.startAnimation(fadein);
//                        }
//                    }
//                    else{
                        subtitleImageVIew.setImageResource(subtitleList.get(msg.what).getSubTitle());
//                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
//                subtitleImageVIew.setTag((Integer)msg.what);
//                if(subtitleImageVIew.getVisibility() == View.INVISIBLE || subtitleImageVIew.getVisibility() == View.GONE) subtitleImageVIew.setVisibility(View.VISIBLE);
            } else
                subtitleImageVIew.setImageDrawable(null);

        }
    };

}
