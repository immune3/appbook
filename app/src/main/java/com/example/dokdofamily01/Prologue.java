package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
    MediaPlayer musicPlayer = null;

    private int storyFlag = 0;
    private Handler fadeInHandler, fadeOutHandler;
    private Runnable fadeInRun, fadeOutRun;
    private int[] syncArray;

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
//        checkedAnimation = false;

    }

    @Override
    public void setAnimation() {
        super.setAnimation();

        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(1000);
        fadeIn.setStartOffset(3000);
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

        if(checkedAnimation) checkedAnimation = false;
        //델리케이트를 이용해서 CustomTouchListener 터치 이벤트를 가져옴
        //이렇게 하지 않으면 터치가 발생한 직후 거리값을 가져올 수가 없음.
        MyChangeListener mListener = new MyChangeListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistance) {
                // onAction 안에 CustomTouchListener onTouch() 이벤트가 끝난 후에 추가로 이벤트를 줄 수 있음.

                if (motionEvent.getAction() == MotionEvent.ACTION_UP && storyFlag < 3 && checkDistance == 1) {

                    Log.d("storyFlag", "plus");
                    storyFlag++;
                    if (storyFlag != 3 && musicPlayer != null && musicPlayer.isPlaying())
                        musicPlayer.seekTo(syncArray[storyFlag]);
                    else {
                        checkedAnimation = true;
                        vp = ((TaleActivity)getActivity()).vp;
                        vp.setCurrentItem(vp.getCurrentItem() + 1, true);
                    }

//                    if(storyFlag == syncArray.length) checkedAnimation = true;
//                    else checkedAnimation = false;

                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP && storyFlag > 0 && checkDistance == -1) {

                    checkedAnimation = false;

                    Log.d("storyFlag", "minus");
                    storyFlag--;
                    if (musicPlayer != null && musicPlayer.isPlaying())
                        musicPlayer.seekTo(syncArray[storyFlag]);
                }

                Log.d("StoryFlag", storyFlag + " " + checkDistance);

                //postDelayed로 인한 callBack 을 취소함
                destroyHandler();

                switch (storyFlag) {

                    case 0:

                        prologueTextImage.clearAnimation();
                        animationFlag = 3;
                        fadeOut.setStartOffset(0);
                        prologueTextImage.startAnimation(fadeOut);

                        fadeInHandler = new Handler();
                        fadeInRun = new Runnable() {
                            @Override
                            public void run() {
                                animationFlag = 1;
                                prologueTextImage.setVisibility(View.INVISIBLE);
                                prologueTextImage.setImageResource(R.drawable.prologue_text_01);
                                fadeIn.setStartOffset(2500);
                                prologueTextImage.startAnimation(fadeIn);
                            }
                        };

                        fadeInHandler.postDelayed(fadeInRun, 1000);

                        break;

                    case 1:

                        prologueTextImage.clearAnimation();
                        animationFlag = 3;
                        fadeOut.setStartOffset(0);
                        prologueTextImage.startAnimation(fadeOut);

                        fadeInHandler = new Handler();

                        fadeInRun = new Runnable() {
                            @Override
                            public void run() {
                                animationFlag = 2;
                                prologueTextImage.setVisibility(View.INVISIBLE);
                                prologueTextImage.setImageResource(R.drawable.prologue_text_02);

                                fadeIn.setStartOffset(0);
                                prologueTextImage.startAnimation(fadeIn);
                            }
                        };

                        fadeInHandler.postDelayed(fadeInRun, 1000);

                        break;

                    case 2:

                        prologueTextImage.clearAnimation();
                        animationFlag = 3;
                        fadeOut.setStartOffset(0);
                        prologueTextImage.startAnimation(fadeOut);

                        fadeInHandler = new Handler();

                        fadeInRun = new Runnable() {
                            @Override
                            public void run() {
                                fadeIn.setStartOffset(0);
                                prologueTextImage.setVisibility(View.INVISIBLE);
                                prologueTextImage.setImageResource(R.drawable.prologue_text_03);
                                prologueTextImage.startAnimation(fadeIn);
                            }
                        };

                        fadeInHandler.postDelayed(fadeInRun, 1000);

                        break;

                }

            }
        }) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Log.d("syncArrayLength", syncArray.length + "");

                checkedAnimation = false;
                return super.onTouch(view, motionEvent);
            }
        };

        prologueTextImage.setOnTouchListener(mListener);
        sl.setOnTouchListener(mListener);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        isHint = isVisibleToUser;
        Log.d("isHint", isHint + "");
        super.setUserVisibleHint(isVisibleToUser);
        if (isAttached) {
            if (isVisibleToUser) {
                System.out.println("PlayByHint");
                soundPlayFunc();

//                vp.setOnTouchListener(null);
//                vp.setOnTouchListener(new MyChangeListener());

            } else {
//                CheckMP checkMP = new CheckMP(musicController);
//                checkMP.execute();
                if (musicPlayer != null) {
                    musicPlayer.release();
                    musicPlayer = null;
                    System.out.println("ReleaseMusic");
                }
            }
        }
    }

    @Override
    public void soundPlayFunc() {
        super.soundPlayFunc();

        syncArray = new int[]{0, 24000, 53000};

        if(musicPlayer != null && musicPlayer.isPlaying()) {
            musicPlayer.pause();
            musicPlayer.release();
            musicPlayer = null;
        }

        musicPlayer = new MediaPlayer();
        musicPlayer = MediaPlayer.create(getContext(), R.raw.prologue);
        musicPlayer.setLooping(false);
        musicPlayer.start();

//        musicController = new MusicController(getActivity(), R.raw.prologue);
//        subtitleList = new ArrayList<>();
//        subtitleList = musicController.makeSubTitleList(
//                new String[]{"", "24000"},
//                new String[]{"", "53000"}
//        );

//        Log.d("subLength", subtitleList.size() + "");
//        musicController.excuteAsync();
//        mp = musicController.getMp();
        checkedAnimation = false;

        prologueTextImage.setVisibility(View.INVISIBLE);
        prologueTextImage.setImageResource(R.drawable.prologue_text_01);

        storyFlag = 0;
        animationFlag = 1;
        fadeIn.setStartOffset(3000);
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
        if (musicPlayer != null && musicPlayer.isPlaying()) {
            musicPlayer.release();
        }
        musicPlayer = null;
    }

    private void destroyHandler() {

        if (fadeInHandler != null && fadeInRun != null) {
            fadeInHandler.removeCallbacks(fadeInRun);
            fadeInHandler = null;
        }

        if (fadeOutHandler != null && fadeOutRun != null) {
            fadeOutHandler.removeCallbacks(fadeOutRun);
            fadeOutHandler = null;
        }

    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {

            switch (animationFlag) {
                case 1:

                    storyFlag = 0;

                    fadeOutHandler = new Handler();
                    fadeOutRun = new Runnable() {
                        @Override
                        public void run() {

                            destroyHandler();

                            animationFlag = 3;
                            fadeOut.setStartOffset(0);
                            prologueTextImage.startAnimation(fadeOut);

                            fadeInHandler = new Handler();
                            fadeInRun = new Runnable() {
                                @Override
                                public void run() {
                                    storyFlag = 1;
                                    animationFlag = 2;
                                    prologueTextImage.setVisibility(View.INVISIBLE);
                                    prologueTextImage.setImageResource(R.drawable.prologue_text_02);

                                    fadeIn.setStartOffset(0);
                                    prologueTextImage.startAnimation(fadeIn);
                                }
                            };

                            fadeInHandler.postDelayed(fadeInRun, 1000);
                        }
                    };

                    fadeOutHandler.postDelayed(fadeOutRun, 19500);

                    break;

                case 2:

                    storyFlag = 1;

                    fadeOutHandler = new Handler();
                    fadeOutRun = new Runnable() {
                        @Override
                        public void run() {

                            destroyHandler();

                            animationFlag = 3;
                            fadeOut.setStartOffset(0);
                            prologueTextImage.startAnimation(fadeOut);

                            fadeInHandler = new Handler();
                            fadeInRun = new Runnable() {
                                @Override
                                public void run() {
                                    storyFlag = 2;
                                    fadeIn.setStartOffset(0);
                                    prologueTextImage.setVisibility(View.INVISIBLE);
                                    prologueTextImage.setImageResource(R.drawable.prologue_text_03);
                                    prologueTextImage.startAnimation(fadeIn);
                                }
                            };

                            fadeInHandler.postDelayed(fadeInRun, 1000);
                        }
                    };

                    fadeOutHandler.postDelayed(fadeOutRun, 27200);

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
