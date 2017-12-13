package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.subtitleTextView;


/**
 * Created by heronation on 2017-11-06.
 */

public class Tale19 extends BaseFragment {

    private CustomViewPager vp;
    ImageView byul;
    ImageView starLight;
    ImageView star1;
    ImageView star2;
    ImageView star3;
    ImageView star4;
    ImageView star5;
    ImageView star6;
    ImageView light;

    TranslateAnimation starAppear;
    TranslateAnimation[] starFall = new TranslateAnimation[5];
    AlphaAnimation fadein;
    AlphaAnimation fadeout;
    AlphaAnimation[] starFadein = new AlphaAnimation[5];
    AlphaAnimation[] starFadeout = new AlphaAnimation[5];
    AlphaAnimation blink;
    AnimationSet starAppearAniSet = new AnimationSet(false);
    AnimationSet starLightAniSet = new AnimationSet(false);
    AnimationSet[] starFallAniSet = new AnimationSet[5];
    int animationFlag = 0;
    int starFallCount = 0;

    MediaPlayer mp = null;

    ArrayList<SubTitleData> subtitleList;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale19;
        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public void bindViews() {
        super.bindViews();
        byul = (ImageView) layout.findViewById(R.id.byul);
        starLight = (ImageView) layout.findViewById(R.id.starLight);
        star1 = (ImageView) layout.findViewById(R.id.star1);
        star2 = (ImageView) layout.findViewById(R.id.star2);
        star3 = (ImageView) layout.findViewById(R.id.star3);
        star4 = (ImageView) layout.findViewById(R.id.star4);
        star5 = (ImageView) layout.findViewById(R.id.star5);
        star6 = (ImageView) layout.findViewById(R.id.star6);
        light = (ImageView) layout.findViewById(R.id.light);


    }

    @Override
    public void setValues() {
        super.setValues();
        light.post(new Runnable() {
            @Override
            public void run() {
                starAppear = new TranslateAnimation(0, 0, -star1.getHeight(), 0);
                starAppear.setDuration(3000);
                starAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                starAppear.setInterpolator(new BounceInterpolator());

                starAppear.setAnimationListener(new MyAnimationListener());

                starAppearAniSet.addAnimation(starAppear);
                starAppearAniSet.addAnimation(fadein);

                float ratio = 0.6f;

                for (int iter = 0; iter < 5; iter++) {
                    starFadein[iter] = new AlphaAnimation(0, 1);
                    starFadein[iter].setStartOffset(iter * 300);
                    starFadein[iter].setDuration(500);
                    starFadein[iter].setFillAfter(true);

                    starFall[iter] = new TranslateAnimation(0, 0, 0, star2.getHeight() * ratio);
                    starFall[iter].setDuration(800);
                    starFall[iter].setStartOffset(iter * 300 + 500);
                    starFall[iter].setInterpolator(new AnticipateInterpolator());
                    starFall[iter].setFillAfter(true);

                    starFadeout[iter] = new AlphaAnimation(1, 0);
                    starFadeout[iter].setStartOffset(iter * 300 + 1300);
                    starFadeout[iter].setDuration(500);
                    starFadeout[iter].setFillAfter(true);

                    starFallAniSet[iter] = new AnimationSet(false);
                    starFallAniSet[iter].addAnimation(starFadein[iter]);
                    starFallAniSet[iter].addAnimation(starFall[iter]);
                    starFallAniSet[iter].addAnimation(starFadeout[iter]);
                    starFallAniSet[iter].setFillAfter(true);

                    ratio *= 1.7f;
//                    starFallAniSet[iter].setStartOffset(1000*iter);
                }

                starFadeout[4].setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        star6.clearAnimation();
//                        star2.clearAnimation();
//                        star3.clearAnimation();
//                        star4.clearAnimation();
//                        star5.clearAnimation();
                        if (starFallCount < 4) {
                            star6.setVisibility(View.INVISIBLE);
                            star2.startAnimation(starFallAniSet[0]);
                            star3.startAnimation(starFallAniSet[1]);
                            star4.startAnimation(starFallAniSet[2]);
                            star5.startAnimation(starFallAniSet[3]);
                            star6.startAnimation(starFallAniSet[4]);
                        } else {

                            starLight.setVisibility(View.INVISIBLE);
                            light.setVisibility(View.INVISIBLE);
                            star1.startAnimation(blink);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        starFallCount++;
                        if (starFallCount < 4) {
//                            star6.setVisibility(View.INVISIBLE);
                        } else {
                            starLight.startAnimation(fadeout);
                            light.startAnimation(fadeout);
                        }
                    }
                });

                if (animationFlag == 0) {
                    star1.setVisibility(View.INVISIBLE);
                    starLight.clearAnimation();
                    light.clearAnimation();
                    star2.clearAnimation();
                    star3.clearAnimation();
                    star4.clearAnimation();
                    star5.clearAnimation();
                    star6.clearAnimation();
                    animationFlag = 1;
                    star1.startAnimation(starAppearAniSet);
                }
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadein = new AlphaAnimation(0, 1);
        fadein.setDuration(2000);
        fadein.setFillAfter(true);

        fadeout = new AlphaAnimation(1, 0);
        fadeout.setStartOffset(500);
        fadeout.setDuration(2500);
        fadeout.setFillAfter(true);

        blink = new AlphaAnimation(1, 0.3f);
        blink.setDuration(700);
        blink.setInterpolator(new LinearInterpolator());
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starFallCount = 0;
                animationFlag = 0;
                star1.clearAnimation();
//                starLight.setVisibility(View.VISIBLE);
//                light.setVisibility(View.VISIBLE);
                starLight.startAnimation(fadein);
                light.startAnimation(fadein);

                star2.startAnimation(starFallAniSet[0]);
                star3.startAnimation(starFallAniSet[1]);
                star4.startAnimation(starFallAniSet[2]);
                star5.startAnimation(starFallAniSet[3]);
                star6.startAnimation(starFallAniSet[4]);

            }
        });
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            animationFlag = 0;
            star1.startAnimation(blink);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            starLight.setVisibility(View.INVISIBLE);
            light.setVisibility(View.INVISIBLE);
            star1.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void soundPlayFunc() {
        musicController = new MusicController(getActivity(), R.raw.scene_19);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(

                new String[]{"아함~ 하품하는 별이의 입속으로 \n" +
                        "총총 별님 하나가 톡! 떨어져요. ", "9000"},
                new String[]{"이어 또 하나가 톡! 톡톡... 톡톡톡... 톡톡톡톡... ", "15000"},
                new String[]{"별님들이 앞 다투어 와르르 와르르 쏟아져 내려요.", "20500"},
                new String[]{"엄마야~ 별님들이 쏟아진다!", "25500"},
                new String[]{"콕콕... 콕콕콕...", "28000"},
                new String[]{"누가 별이 방 창문을 가만가만 두드려요.", "33000"},
                new String[]{"벌떡 일어난 별이가 창문을 활짝 열며 외쳐요.", "38000"},
                new String[]{"안녕~ 갈매기야! ", "41500"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();
        if (animationFlag == 0 && starAppearAniSet != null) {
            star1.setVisibility(View.INVISIBLE);
//                    starLight.setVisibility(View.INVISIBLE);
//                    light.setVisibility(View.INVISIBLE);
            starLight.clearAnimation();
            light.clearAnimation();
            star2.clearAnimation();
            star3.clearAnimation();
            star4.clearAnimation();
            star5.clearAnimation();
            star6.clearAnimation();
            animationFlag = 1;
            star1.startAnimation(starAppearAniSet);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
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
