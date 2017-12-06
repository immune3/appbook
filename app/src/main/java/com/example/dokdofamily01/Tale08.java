package com.example.dokdofamily01;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class Tale08 extends BaseFragment {
    ImageView treeBody;
    ImageView treeHand;
    ImageView leaves;
    ImageView smile;
    ImageView eyeBlack;
    ImageView eyeWhite;
    ImageView plant;
    ImageView dokdo;
    ImageView land;
    ImageView seagull;
    ImageView byul;
    TranslateAnimation plantAnimation;
    TranslateAnimation dokdoAnimation;
    TranslateAnimation landAnimation;
    TranslateAnimation seagullAnimation;
    TranslateAnimation byulAnimation;
    TranslateAnimation treeAnimation;
    Animation fadeIn;
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
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_8);
                }

                mp.start();

                Timer timer = new Timer();
                timer.schedule(new MyThread(),0, 500);

                byul.setVisibility(View.INVISIBLE);
                treeBody.setVisibility(View.INVISIBLE);
                treeHand.setVisibility(View.INVISIBLE);
                smile.setVisibility(View.INVISIBLE);
                leaves.setVisibility(View.INVISIBLE);
                eyeBlack.setVisibility(View.INVISIBLE);
                eyeWhite.setVisibility(View.INVISIBLE);
                if(animationFlag == 0){
                    animationFlag = 1;
                    plant.startAnimation(plantAnimation);
                    dokdo.startAnimation(dokdoAnimation);
                    seagull.startAnimation(seagullAnimation);
                    land.startAnimation(landAnimation);

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
        xml = R.layout.tale08;

        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
                new String[]{"별이랑 언제나 든든한 사철나무 아빠가 도란도란 얘기해요.","6000"},
                new String[]{"사철나무 아빠는 왜 자꾸 두리번두리번 해요?", "12000"},
                new String[]{"나는 가족을 지키는 아빠니까 이렇게 잘 살펴봐야 한단다. ","18500"},
                new String[]{"뾰족한 절벽에서 살면 엉덩이가 안 아파요?","24500"},
                new String[]{"100년도 넘어서 이제 아무렇지도 않단다.","29000"},
                new String[]{"100년? 그럼 사철나무 할아버지예요?","35000"},
                new String[]{"하하하~ 우리 독도에는 아~주아~주 먼 옛날에 \n" +
                        "태어나신 서도할아버지가 계신 걸~ ","43500"}
        );

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        plant = (ImageView)layout.findViewById(R.id.plant);
        dokdo = (ImageView)layout.findViewById(R.id.dokdo);
        land = (ImageView)layout.findViewById(R.id.land);
        seagull = (ImageView)layout.findViewById(R.id.seagull);
        byul = (ImageView)layout.findViewById(R.id.byul);
        treeBody = (ImageView)layout.findViewById(R.id.treeBody);
        treeHand = (ImageView)layout.findViewById(R.id.treeHand);
        leaves = (ImageView)layout.findViewById(R.id.leaves);
        smile = (ImageView)layout.findViewById(R.id.smile);
        eyeBlack = (ImageView)layout.findViewById(R.id.eyeBlack);
        eyeWhite = (ImageView)layout.findViewById(R.id.eyeWhite);
    }

    @Override
    public void setValues() {
        super.setValues();
        land.post(new Runnable() {
            @Override
            public void run() {
                plantAnimation = new TranslateAnimation(-plant.getWidth(), 0, 0, 0);
                plantAnimation.setDuration(2500);
                plantAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                dokdoAnimation = new TranslateAnimation(-dokdo.getWidth(), 0, 0, 0);
                dokdoAnimation.setDuration(2000);
                dokdoAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                landAnimation = new TranslateAnimation(0, 0, land.getHeight(), 0);
                landAnimation.setStartOffset(500);
                landAnimation.setDuration(2000);
                landAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                landAnimation.setAnimationListener(new MyAnimationListener());

                seagullAnimation = new TranslateAnimation(seagull.getWidth(), 0, -seagull.getHeight(), 0);
                seagullAnimation.setStartOffset(500);
                seagullAnimation.setDuration(1500);
                seagullAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                byulAnimation = new TranslateAnimation(byul.getWidth(), 0, -byul.getHeight(), 0);
                byulAnimation.setDuration(1000);
                byulAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                byulAnimation.setInterpolator(new BounceInterpolator());

                treeAnimation = new TranslateAnimation((int)(treeBody.getWidth()*0.3),0,treeBody.getHeight(),0);
                treeAnimation.setStartOffset(500);
                treeAnimation.setDuration(500);
                treeAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                treeAnimation.setAnimationListener(new MyAnimationListener());

                fadeIn = AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
                fadeIn.setAnimationListener(new MyAnimationListener());

                treeBody.setVisibility(View.INVISIBLE);
                treeHand.setVisibility(View.INVISIBLE);
                smile.setVisibility(View.INVISIBLE);
                leaves.setVisibility(View.INVISIBLE);
                eyeBlack.setVisibility(View.INVISIBLE);
                eyeWhite.setVisibility(View.INVISIBLE);
                byul.setVisibility(View.INVISIBLE);

//                byul.setVisibility(View.INVISIBLE);

                if(animationFlag == 0){
                    animationFlag = 1;
                    plant.startAnimation(plantAnimation);
                    dokdo.startAnimation(dokdoAnimation);
                    seagull.startAnimation(seagullAnimation);
                    land.startAnimation(landAnimation);

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
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag){
                case 1:
                    animationFlag = 2;
                    byul.startAnimation(byulAnimation);
                    treeBody.startAnimation(treeAnimation);
                    eyeBlack.startAnimation(treeAnimation);
                    eyeWhite.startAnimation(treeAnimation);

                    break;
                case 2:
                    animationFlag = 3;
                    treeHand.startAnimation(fadeIn);
                    smile.startAnimation(fadeIn);
                    leaves.startAnimation(fadeIn);
                    break;
                case 3:
                    animationFlag = 0;
                    treeHand.clearAnimation();
                    smile.clearAnimation();
                    leaves.clearAnimation();
                    break;

            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            switch (animationFlag){
                case 2:
                    byul.setVisibility(View.VISIBLE);
                    treeBody.setVisibility(View.VISIBLE);
                    eyeBlack.setVisibility(View.VISIBLE);
                    eyeWhite.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    treeHand.setVisibility(View.VISIBLE);
                    smile.setVisibility(View.VISIBLE);
                    leaves.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
}
