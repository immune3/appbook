package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by mapl0 on 2017-12-28.
 */

public class Prologue extends BaseFragment {

    //    int deviceHeight;
//    int deviceWidth;
//    CustomScrollView sv;
//    ScalableLayout sl;
    private android.widget.ImageView prologueTextImage;

    AlphaAnimation fadeIn;
    AlphaAnimation fadeOut;
    int animationFlag = 1;

    ArrayList<SubTitleData> subtitleList;

    MediaPlayer mp = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        xml = R.layout.prologue;
        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();

        this.prologueTextImage = (ImageView) layout.findViewById(R.id.prologueTextImage);

    }

    @Override
    public void setValues() {
        super.setValues();

    }

    @Override
    public void setAnimation() {
        super.setAnimation();

        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(1000);
        fadeIn.setStartOffset(4000);
        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new MyAnimationListener());

        fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new MyAnimationListener());

    }

    @Override
    public void setupEvents() {
        super.setupEvents();

//        vp = ((TaleActivity)getActivity()).vp;
//
//        vp.setOnTouchListener(new MyChangeListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                customViewPager = vp;
//
//                return super.onTouch(view, motionEvent);
//            }
//        });

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
    public void soundPlayFunc() {
        super.soundPlayFunc();

        musicController = new MusicController(getActivity(), R.raw.prologue);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"", "24000"},
                new String[]{"", "53000"}
        );

        Log.d("subLength", subtitleList.size() + "");
        musicController.excuteAsync();
        mp = musicController.getMp();
        checkedAnimation = true;

        prologueTextImage.setVisibility(View.INVISIBLE);
        prologueTextImage.setImageResource(R.drawable.prologue_text_01);

        fadeIn.setStartOffset(4000);
        prologueTextImage.startAnimation(fadeIn);
    }

    @Override
    public void blockAnimFunc() {
        super.blockAnimFunc();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag) {
                case 1:

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            animationFlag = 3;
                            fadeOut.setStartOffset(0);
                            prologueTextImage.startAnimation(fadeOut);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animationFlag = 2;
                                    prologueTextImage.setVisibility(View.INVISIBLE);
                                    prologueTextImage.setImageResource(R.drawable.prologue_text_02);

                                    fadeIn.setStartOffset(0);
                                    prologueTextImage.startAnimation(fadeIn);
                                }
                            }, 1000);

                        }
                    }, 19000);

                    break;

                case 2:

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            animationFlag = 3;
                            fadeOut.setStartOffset(0);
                            prologueTextImage.startAnimation(fadeOut);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    fadeIn.setStartOffset(0);
                                    prologueTextImage.setVisibility(View.INVISIBLE);
                                    prologueTextImage.setImageResource(R.drawable.prologue_text_03);
                                    prologueTextImage.startAnimation(fadeIn);
                                }
                            }, 1000);
                        }
                    }, 27200);

                    break;

                case 3:
//                    아무 애니메이션이 없는 상태(사용중)
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
}
