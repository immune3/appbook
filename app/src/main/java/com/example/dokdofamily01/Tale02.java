package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
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

import static com.example.dokdofamily01.TaleActivity.homeKeyFlag;
import static com.example.dokdofamily01.TaleActivity.screenFlag;
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
    TranslateAnimation headUp;
    TranslateAnimation headDown;
    Animation seagullAppear;
    Animation fadeIn;
    AnimationSet animSet;
    RotateAnimation seagullClick;
    int width;
    int height;
    int animationFlag=0;

    boolean isAttached = false;
    boolean isHint;
    MediaPlayer mp = null;
    MusicController musicController;

    ArrayList<SubTitleData> subtitleList;

    SoundPool sp;
    int soundID;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        isHint = isVisibleToUser;
        super.setUserVisibleHint(isVisibleToUser);
        if(isAttached ){
            if (isVisibleToUser) {
                System.out.println("PlayByHint");
                soundPlayFunc();
            } else {
                if (musicController != null) {
                    musicController.getMp().release();
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
        xml = R.layout.tale02;

        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isAttached = true;
    }

    @Override
    public void onResume() {
        if (isHint && !homeKeyFlag && screenFlag) {
            soundPlayFunc();
        }
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (musicController != null) {
            musicController.getMp().release();
            musicController = null;
        }
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
        seagullHand.post(new Runnable() {
            @Override
            public void run() {

                width = (int)(seagullHand.getWidth()*0.85);
                height = (int)(seagullHand.getHeight()*0.8);

                seagullAppear = AnimationUtils.loadAnimation(getContext(),R.anim.anim_02_seagull_appear);
                seagullAppear.setInterpolator(new AccelerateDecelerateInterpolator());
//                seagullAppear.setAnimationListener(new MyAnimationListener());

                seagullClick = new RotateAnimation(10,-10,width,height);
                seagullClick.setDuration(500);
                seagullClick.setRepeatCount(1);
                seagullClick.setRepeatMode(Animation.REVERSE);
                seagullClick.setInterpolator(new AccelerateDecelerateInterpolator());

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

                fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
                fadeIn.setDuration(500);
                fadeIn.setRepeatCount(1);
                fadeIn.setRepeatMode(Animation.REVERSE);

                animSet = new AnimationSet(true);
                animSet.setInterpolator(new AccelerateDecelerateInterpolator());
                animSet.addAnimation(seagullClick);
                seagullHand.setVisibility(View.INVISIBLE);
                if(animationFlag==0) {
                    animationFlag=1;
                    byulhead.startAnimation(headUp);
                    seagullBody.setAnimation(seagullAppear);
                }
            }
        });
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
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(animationFlag==0) {
                    animationFlag = 2;
                    sp.play(soundID,1,1,0,0,1);
                    star.clearAnimation();
                    byulhead.startAnimation(headDown);
                    seagullHand.startAnimation(animSet);
                }
            }
        });
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag){
                case 1:
                    animationFlag=0;
                    star.startAnimation(blink);
                    break;
                case 2:
                    animationFlag=3;
                    byulhead.startAnimation(headUp);
                    break;
                case 3:
                    animationFlag=0;
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
        if (headUp != null) {
            animationFlag=1;
            byulhead.startAnimation(headUp);
            seagullBody.setAnimation(seagullAppear);
        }
    }

}
