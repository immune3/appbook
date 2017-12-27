package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale02 extends BaseFragment {

    ImageView byulhead;
    ImageView seagullHand;
    ImageView seagullBody;
    ImageView star;

    AlphaAnimation blink;
    AlphaAnimation seagullAlpha;
    TranslateAnimation headUp;
    TranslateAnimation headDown;
    TranslateAnimation seagullTrans;
    AnimationSet handAppear;
    AnimationSet handDisappear;
    AnimationSet seagullAniSet;
    RotateAnimation seagullClick;
    RotateAnimation handUp;
    RotateAnimation handDown;
    AlphaAnimation fadeIn;
    AlphaAnimation fadeOut;
    int width;
    int height;
    int animationFlag=0;

    MediaPlayer mp = null;

    ArrayList<SubTitleData> subtitleList;

    SoundPool sp;
    int soundID;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale02;

        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void bindViews() {
        super.bindViews();
        byulhead = (ImageView)layout.findViewById(R.id.byulhead);
        seagullHand = (ImageView)layout.findViewById(R.id.seagullHand);
        seagullBody = (ImageView) layout.findViewById(R.id.seagullBody);
        star = (ImageView)layout.findViewById(R.id.star);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        soundID = sp.load(getContext(),R.raw.effect_02,1);
    }

    @Override
    public void setValues() {
        super.setValues();

    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        blink = new AlphaAnimation(1,0.3f);
        blink.setDuration(500);
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        star.setOnTouchListener(new BlockObjListener());
    }

    @Override
    public void blockAnimFunc() {
        if(animationFlag==0) {
            checkedAnimation = false;
            animationFlag = 2;
            sp.play(soundID,1,1,0,0,1);
            star.clearAnimation();
            byulhead.startAnimation(headDown);
            seagullHand.startAnimation(handAppear);
        }

        super.blockAnimFunc();
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag){
                case 1:
                    animationFlag=0;
                    checkedAnimation = true;
                    star.startAnimation(blink);
                    break;
                case 2:
                    animationFlag=3;
                    byulhead.startAnimation(headUp);
                    break;
                case 3:
                    animationFlag=0;
                    checkedAnimation = true;
                    star.startAnimation(blink);
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

    }

    public void soundPlayFunc(){
        musicController = new MusicController(getActivity(), R.raw.scene_2);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"갈매기에요", "1600"},
                new String[]{"하얀 깃털 옷을 새하얗게 차려입은 갈매기가 \n" +
                        "생긋~ 웃더니 말을 해요.", "8500"},
                new String[]{"별아 창문을 열어! ", "12500"},
                new String[]{"꿈이야...", "16000"},
                new String[]{"별이가 창문을 열어요.", "19000"},
                new String[]{"폴짝 뛰어든 바다냄새가 시원해요.", "23500"},
                new String[]{"갈매기가 또 말을 해요.", "27000"},
                new String[]{"별아 내 등에 앉아!", "31500"},
                new String[]{"진짜 꿈이야...", "35000"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();

        seagullHand.post(new Runnable() {
            @Override
            public void run() {

                width = (int)(seagullHand.getWidth()*0.85);
                height = (int)(seagullHand.getHeight()*0.8);

                seagullAlpha = new AlphaAnimation(0, 1);
                seagullAlpha.setDuration(500);

                seagullTrans = new TranslateAnimation(0, 0, 0, -100);
                seagullTrans.setDuration(250);
                seagullTrans.setRepeatCount(1);
                seagullTrans.setInterpolator(new AccelerateDecelerateInterpolator());
                seagullTrans.setRepeatMode(Animation.REVERSE);

                seagullAniSet = new AnimationSet(false);
                seagullAniSet.addAnimation(seagullAlpha);
                seagullAniSet.addAnimation(seagullTrans);
                seagullAniSet.setFillAfter(true);


                seagullClick = new RotateAnimation(10,-10,width,height);
                seagullClick.setDuration(500);
                seagullClick.setRepeatCount(1);
                seagullClick.setRepeatMode(Animation.REVERSE);
                seagullClick.setInterpolator(new AccelerateDecelerateInterpolator());
                seagullClick.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        seagullHand.startAnimation(handDisappear);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                handUp = new RotateAnimation(-10,10,width,height);
                handUp.setDuration(500);
                handUp.setInterpolator(new AccelerateDecelerateInterpolator());
                handUp.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        seagullHand.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        seagullHand.startAnimation(seagullClick);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                handDown = new RotateAnimation(10,-10,width,height);
                handDown.setDuration(500);
                handDown.setInterpolator(new AccelerateDecelerateInterpolator());
                handDown.setAnimationListener(new MyAnimationListener(){
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        seagullHand.setVisibility(View.INVISIBLE);
                    }
                });

                headUp = new TranslateAnimation(0,0,byulhead.getHeight(),0);
                headUp.setDuration(3000);
                headUp.setFillAfter(true);
                headUp.setInterpolator(new AccelerateDecelerateInterpolator());
                headUp.setAnimationListener(new MyAnimationListener());

                headDown= new TranslateAnimation(0,0,0,byulhead.getHeight());
                headDown.setDuration(300);
                headDown.setFillAfter(true);
                headDown.setInterpolator(new AccelerateDecelerateInterpolator());
                headDown.setAnimationListener(new MyAnimationListener());

                fadeIn = new AlphaAnimation(0,1);
                fadeIn.setDuration(500);
                fadeOut = new AlphaAnimation(1,0);
                fadeOut.setDuration(500);

                handAppear = new AnimationSet(true);
                handAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                handAppear.addAnimation(handUp);
                handAppear.addAnimation(fadeIn);

                handDisappear = new AnimationSet(true);
                handDisappear.setInterpolator(new AccelerateDecelerateInterpolator());
                handDisappear.addAnimation(handDown);
                handDisappear.addAnimation(fadeOut);

                animationFlag=1;
                seagullHand.setVisibility(View.INVISIBLE);
                checkedAnimation = false;
                star.clearAnimation();
                byulhead.startAnimation(headUp);
                seagullBody.setAnimation(seagullAniSet);
            }
        });

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}