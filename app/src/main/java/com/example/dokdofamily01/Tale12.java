package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
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

public class Tale12 extends BaseFragment {
    ImageView sea1;
    ImageView sea2;
    ImageView dokdo;
    ImageView byul;
    ImageView byulHand;
    ImageView seagull;
    ImageView smallsqeed;
    ImageView sqeedLeftHand;
    ImageView sqeedRightHand;
    ImageView sqeedBody;
    ImageView sqeedHead;
    ImageView hairpin;

    TranslateAnimation seaAppear;
    TranslateAnimation dokdoAppear;
    TranslateAnimation smallSqeedAppear;
    TranslateAnimation seagullAppear;
    TranslateAnimation sqeedAppear;
    TranslateAnimation sqeedClinkAni;
    AlphaAnimation blink;
    AlphaAnimation sqeedHandFadein;
    AlphaAnimation sqeedHandFadeout;
    int animationFlag = 0;
    int clickedFlag = 0;

    boolean isAttached = false;
    boolean isHint;
    MediaPlayer mp = null;
    MusicController musicController;


    ArrayList<SubTitleData> subtitleList;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isAttached = true;
    }

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
        xml = R.layout.tale12;

        subtitleTextView.setText(null);


        return super.onCreateView(inflater, container, savedInstanceState);
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

    public void bindViews() {
        super.bindViews();
        sea1 = (ImageView)layout.findViewById(R.id.sea1);
        sea2 = (ImageView)layout.findViewById(R.id.sea2);
        dokdo = (ImageView)layout.findViewById(R.id.dokdo);
        byul = (ImageView)layout.findViewById(R.id.byul);
        byulHand = (ImageView)layout.findViewById(R.id.byulHand);
        seagull = (ImageView)layout.findViewById(R.id.seagull);
        smallsqeed = (ImageView)layout.findViewById(R.id.smallSqeed);
        sqeedLeftHand = (ImageView)layout.findViewById(R.id.sqeedLeftHand);
        sqeedRightHand = (ImageView)layout.findViewById(R.id.sqeedRightHand);
        sqeedBody = (ImageView)layout.findViewById(R.id.sqeedBody);
        sqeedHead = (ImageView)layout.findViewById(R.id.sqeedHead);
        hairpin = (ImageView)layout.findViewById(R.id.hairpin);
    }

    @Override
    public void setValues() {
        super.setValues();
        sea1.post(new Runnable() {
            @Override
            public void run() {
                seaAppear = new TranslateAnimation(0,0, sea1.getHeight(), 0);
                seaAppear.setDuration(1000);
                seaAppear.setInterpolator(new AccelerateDecelerateInterpolator());

                dokdoAppear = new TranslateAnimation(-dokdo.getWidth(),0, 0, 0);
                dokdoAppear.setStartOffset(800);
                dokdoAppear.setDuration(1500);
                dokdoAppear.setInterpolator(new AccelerateDecelerateInterpolator());

                seagullAppear = new TranslateAnimation(-(seagull.getWidth()*2),0, -seagull.getHeight(), 0);
                seagullAppear.setStartOffset(800);
                seagullAppear.setDuration(1000);
                seagullAppear.setInterpolator(new AccelerateDecelerateInterpolator());

                smallSqeedAppear = new TranslateAnimation(0,0, sea1.getHeight()*0.5f, 0);
                smallSqeedAppear.setStartOffset(500);
                smallSqeedAppear.setDuration(2400);
                smallSqeedAppear.setInterpolator(new OvershootInterpolator());

                sqeedAppear = new TranslateAnimation(0,0, sqeedHead.getHeight()*1.5f, 0);
                sqeedAppear.setStartOffset(1000);
                sqeedAppear.setDuration(2000);
                sqeedAppear.setInterpolator(new DecelerateInterpolator());
                sqeedAppear.setFillAfter(true);
                sqeedAppear.setAnimationListener(new MyAnimationListener(){
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animationFlag = 0;
                        hairpin.startAnimation(blink);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        sqeedRightHand.setVisibility(View.INVISIBLE);
                        sqeedLeftHand.setVisibility(View.INVISIBLE);
                        byulHand.setVisibility(View.INVISIBLE);
                        sea2.bringToFront();
                    }
                });

                sqeedClinkAni = new TranslateAnimation(0, 0, 0, -(sqeedHead.getHeight()*0.48f));
                sqeedClinkAni.setDuration(2000);
                sqeedClinkAni.setInterpolator(new AccelerateDecelerateInterpolator());
                sqeedClinkAni.setFillAfter(true);
                sqeedClinkAni.setAnimationListener(new MyAnimationListener(){
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        sqeedRightHand.setVisibility(View.VISIBLE);
                        sqeedLeftHand.setVisibility(View.VISIBLE);
                        byulHand.setVisibility(View.VISIBLE);
                        sqeedLeftHand.bringToFront();
                        sqeedHead.bringToFront();
                        hairpin.bringToFront();
                        byulHand.bringToFront();
                        sqeedRightHand.startAnimation(sqeedHandFadein);
                        sqeedLeftHand.startAnimation(sqeedHandFadein);
                        byulHand.startAnimation(sqeedHandFadein);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                    }
                });


                if(animationFlag == 0){
                    animationFlag = 1;
                    sea1.startAnimation(seaAppear);
                    sea2.startAnimation(seaAppear);
                    dokdo.startAnimation(dokdoAppear);
                    byul.startAnimation(dokdoAppear);
                    smallsqeed.startAnimation(smallSqeedAppear);
                    seagull.startAnimation(seagullAppear);
                    sqeedBody.startAnimation(sqeedAppear);
                    sqeedHead.startAnimation(sqeedAppear);
                    hairpin.startAnimation(sqeedAppear);
                }
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        sqeedHandFadein = new AlphaAnimation(0, 1);
        sqeedHandFadein.setDuration(800);

        sqeedHandFadeout = new AlphaAnimation(1, 0);
        sqeedHandFadeout.setDuration(800);

        blink = new AlphaAnimation(1, 0.3f);
        blink.setDuration(600);
        blink.setInterpolator(new LinearInterpolator());
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        hairpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqeedBody.startAnimation(sqeedClinkAni);
                sqeedHead.startAnimation(sqeedClinkAni);
                hairpin.startAnimation(sqeedClinkAni);
            }
        });
    }
    public void soundPlayFunc(){
        musicController = new MusicController(getActivity(), R.raw.scene_12);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"찰랑찰랑~ ","1500"},
                new String[]{"언제나 부지런한 오징어 이모의 세모난 머리가 바다 위로 쑤욱~ 올라와요.", "8000"},
                new String[]{"별아 나랑 혹돔굴에 가보자!","12000"},
                new String[]{"우와~ 혹돔 삼촌도 만나요?","16500"},
                new String[]{"오징어 이모는 쭉쭉 긴 다리로 별이의 팔과 다리를 잡고서 ","23700"},
                new String[]{"하나 둘~ 하나 둘~ 준비운동을 시켜요. ","28600"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();

        if(seaAppear != null){
            animationFlag = 1;
            sea1.startAnimation(seaAppear);
            sea2.startAnimation(seaAppear);
            dokdo.startAnimation(dokdoAppear);
            byul.startAnimation(dokdoAppear);
            smallsqeed.startAnimation(smallSqeedAppear);
            seagull.startAnimation(seagullAppear);
            sqeedBody.startAnimation(sqeedAppear);
            sqeedHead.startAnimation(sqeedAppear);
            hairpin.startAnimation(sqeedAppear);
        }

    }

}

