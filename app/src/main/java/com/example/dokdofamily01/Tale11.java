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
import android.view.animation.BounceInterpolator;
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

public class Tale11 extends BaseFragment {
    ImageView bee1;
    ImageView bee2;
    ImageView butterfly;
    ImageView originalFlower;
    ImageView cutFlower;
    ImageView flowers;
    ImageView dokdo;
    ImageView byul;

    TranslateAnimation dokdoAnimation;
    TranslateAnimation originalFlowerAnimation;
    TranslateAnimation byulAnimation;
    Animation flowerAnimation;
    int animationFlag = 0;

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
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_11);
                }

                mp.start();

                Timer timer = new Timer();
                timer.schedule(new MyThread(),0, 500);


                if(animationFlag == 0){
                    animationFlag = 1;
                    dokdo.startAnimation(dokdoAnimation);
                    originalFlower.startAnimation(originalFlowerAnimation);
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
        xml = R.layout.tale11;


        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
                new String[]{"언제나 활짝 웃는 땅채송화들이 랄랄라~ 합창해요.","6000"},
                new String[]{"큰 바다 한가운데 용감하게 혼자인데 무섭지 않아요.", "12500"},
                new String[]{"큰 바다 넓은 곳에 덩그러니 혼자인데 심심하지 않아요.","19500"},
                new String[]{"큰 바다 깊은 곳에 수백만 년 혼자인데 쓸쓸하지 않아요.","27000"},
                new String[]{"큰 바다 깊은 곳에 수백만 년 혼자인데 쓸쓸하지 않아요.","32700"},
                new String[]{"우리들은 언제나 활짝 웃고 있는 걸까요?","37000"},
                new String[]{"별이가 반짝이는 눈으로 독도의 푸른 풍경을 초롱초롱 바라봐요.","44500"}
        );

        subtitleTextView.setText(null);



        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        bee1 = (ImageView)layout.findViewById(R.id.bee1);
        bee2 = (ImageView)layout.findViewById(R.id.bee2);
        butterfly = (ImageView)layout.findViewById(R.id.butterfly);
        originalFlower = (ImageView)layout.findViewById(R.id.originalFlower);
        cutFlower = (ImageView)layout.findViewById(R.id.cutFlower);
        flowers = (ImageView)layout.findViewById(R.id.flowers);
        dokdo = (ImageView)layout.findViewById(R.id.dokdo);
        byul = (ImageView)layout.findViewById(R.id.byul);
    }

    @Override
    public void setValues() {
        super.setValues();
        originalFlower.post(new Runnable() {
            @Override
            public void run() {
                originalFlowerAnimation = new TranslateAnimation(originalFlower.getWidth(), 0, originalFlower.getHeight(), 0);
                originalFlowerAnimation.setDuration(2000);
                originalFlowerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                originalFlowerAnimation.setAnimationListener(new MyAnimationListener());

                dokdoAnimation = new TranslateAnimation(-dokdo.getWidth(), 0, 0, 0);
                dokdoAnimation.setDuration(2000);
                dokdoAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                byulAnimation = new TranslateAnimation(byul.getWidth(), 0, byul.getHeight(), 0);
                byulAnimation.setDuration(1000);
                byulAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                byulAnimation.setInterpolator(new BounceInterpolator());


                if(animationFlag == 0){
                    animationFlag = 1;
                    dokdo.startAnimation(dokdoAnimation);
                    originalFlower.startAnimation(originalFlowerAnimation);
                }

            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        flowerAnimation = new AlphaAnimation(0, 1);
        flowerAnimation.setStartOffset(200);
        flowerAnimation.setDuration(300);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            animationFlag = 0;
            byul.setVisibility(View.VISIBLE);
            cutFlower.setVisibility(View.VISIBLE);
            flowers.setVisibility(View.VISIBLE);
            byul.startAnimation(byulAnimation);
            flowers.startAnimation(flowerAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            byul.setVisibility(View.INVISIBLE);
            cutFlower.setVisibility(View.INVISIBLE);
            flowers.setVisibility(View.INVISIBLE);
        }

    }
}
