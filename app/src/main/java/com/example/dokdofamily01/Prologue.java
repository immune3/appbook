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
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleImageVIew;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by mapl0 on 2017-12-28.
 */

public class Prologue extends BaseFragment {

    private android.widget.ImageView prologueTextImage;

    private AnimationSet animInSet, animOutSet, animTouchOutSet;
    private TranslateAnimation transIn;
    private TranslateAnimation transOut, transTouchOut;
    private AlphaAnimation fadeIn;
    private AlphaAnimation fadeOut, fadeTouchOut;
    private int animationFlag = 1;

    private ArrayList<SubTitleData> subtitleList;

    private MediaPlayer mp = null;
    private MediaPlayer musicPlayer = null;

    private int storyFlag = 0;
    private Handler fadeInHandler, fadeOutHandler, animHandler;
    private Runnable fadeInRun, fadeOutRun, animRun;
    private int[] syncArray;
    private boolean checkAnim = false;


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

//        prologueTextImage.setTranslationY();

    }

    @Override
    public void setupEvents() {
        super.setupEvents();

//        if(checkedAnimation) checkedAnimation = false;
//        델리케이트를 이용해서 CustomTouchListener 터치 이벤트를 가져옴
//        이렇게 하지 않으면 터치가 발생한 직후 거리값을 가져올 수가 없음.
        MyChangeListener mListener = new MyChangeListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistance) {
                // onAction 안에 CustomTouchListener onTouch() 이벤트가 끝난 후에 추가로 이벤트를 줄 수 있음.

                if (motionEvent.getAction() == MotionEvent.ACTION_UP && storyFlag < 3 && checkDistance == 1 && checkAnim) {

                    Log.d("storyFlag", "plus");
                    storyFlag++;

                    if (storyFlag == 0) storyFlag++;

                    if (storyFlag < 3 && musicPlayer != null && musicPlayer.isPlaying())
                        musicPlayer.seekTo(syncArray[storyFlag]);
                    else if(storyFlag == 3){
                        vp = ((TaleActivity) getActivity()).vp;
                        vp.setCurrentItem(vp.getCurrentItem() + 1, true);
                    }

//                    if(storyFlag == syncArray.length) checkedAnimation = true;
//                    else checkedAnimation = false;

                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP && storyFlag >= 0 && checkDistance == -1 && checkAnim) {

                    Log.d("storyFlag", "minus");
                    storyFlag--;
                    if (storyFlag >= 0 && musicPlayer != null && musicPlayer.isPlaying())
                        musicPlayer.seekTo(syncArray[storyFlag]);
                }

                Log.d("StoryFlag", storyFlag + " " + checkDistance);

                if (motionEvent.getAction() == MotionEvent.ACTION_UP && Math.abs(checkDistance) == 1 && checkAnim) {

                    switch (storyFlag) {

                        case 0:
                            //postDelayed로 인한 callBack 을 취소함
                            destroyHandler();
                            checkAnim = false;
                            prologueTextImage.clearAnimation();
                            animationFlag = 4;
                            prologueTextImage.startAnimation(animTouchOutSet);

                            fadeInHandler = new Handler();
                            fadeInRun = new Runnable() {
                                @Override
                                public void run() {
                                    animationFlag = 1;
                                    checkAnim = false;
                                    prologueTextImage.setVisibility(View.INVISIBLE);
                                    prologueTextImage.setImageResource(R.drawable.prologue_text_01);
                                    prologueTextImage.startAnimation(animInSet);

                                    animRun = new Runnable() {
                                        @Override
                                        public void run() {
                                            checkAnim = true;
                                        }
                                    };

                                    animHandler = new Handler();
                                    animHandler.postDelayed(animRun, 600);
                                }
                            };

                            fadeInHandler.postDelayed(fadeInRun, 1000);

                            break;

                        case 1:
                            //postDelayed로 인한 callBack 을 취소함
                            destroyHandler();
                            checkAnim = false;
                            prologueTextImage.clearAnimation();
                            animationFlag = 4;

                            prologueTextImage.startAnimation(animTouchOutSet);

                            animRun = new Runnable() {
                                @Override
                                public void run() {
                                    checkAnim = true;
                                }
                            };

                            fadeInHandler = new Handler();
                            fadeInRun = new Runnable() {
                                @Override
                                public void run() {
                                    checkAnim = false;
                                    animationFlag = 2;
                                    prologueTextImage.setVisibility(View.INVISIBLE);
                                    prologueTextImage.setImageResource(R.drawable.prologue_text_02);

                                    prologueTextImage.startAnimation(animInSet);

                                    animHandler = new Handler();
                                    animHandler.postDelayed(animRun, 600);
                                }
                            };

                            fadeInHandler.postDelayed(fadeInRun, 1000);

                            break;

                        case 2:
                            //postDelayed로 인한 callBack 을 취소함
                            destroyHandler();
                            checkAnim = false;
                            prologueTextImage.clearAnimation();
                            animationFlag = 4;
                            prologueTextImage.startAnimation(animTouchOutSet);

                            fadeInHandler = new Handler();

                            fadeInRun = new Runnable() {
                                @Override
                                public void run() {
                                    checkAnim = false;
                                    animationFlag = 3;
                                    prologueTextImage.setVisibility(View.INVISIBLE);
                                    prologueTextImage.setImageResource(R.drawable.prologue_text_03);
                                    prologueTextImage.startAnimation(animInSet);

                                    animRun = new Runnable() {
                                        @Override
                                        public void run() {
                                            checkAnim = true;
                                        }
                                    };

                                    animHandler = new Handler();
                                    animHandler.postDelayed(animRun, 600);

                                }
                            };

                            fadeInHandler.postDelayed(fadeInRun, 1000);

                            break;

                    }
                }
            }
        }) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

//                Log.d("syncArrayLength", syncArray.length + "");

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
    public void onPause() {
        super.onPause();

        try {
            if (musicPlayer != null && musicPlayer.isPlaying()) {
                musicPlayer.pause();
                musicPlayer.release();
                musicPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                subtitleImageVIew.setVisibility(View.VISIBLE);
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

        if (musicPlayer != null && musicPlayer.isPlaying()) {
            musicPlayer.pause();
            musicPlayer.release();
            musicPlayer = null;
        }
        subtitleImageVIew.setVisibility(View.GONE);
        musicPlayer = new MediaPlayer();
        musicPlayer = MediaPlayer.create(getContext(), R.raw.prologue);
        musicPlayer.setLooping(false);
        musicPlayer.start();

        musicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                vp.setCurrentItem(vp.getCurrentItem() + 1, true);
            }
        });


        checkedAnimation = true;

        prologueTextImage.setVisibility(View.INVISIBLE);
        prologueTextImage.setImageResource(R.drawable.prologue_text_01);

        storyFlag = 0;
        animationFlag = 1;
        checkAnim = false;
//
//        fadeIn = new AlphaAnimation(0, 1);
//        fadeIn.setDuration(1000);
//        fadeIn.setStartOffset(1000);
//        fadeIn.setFillAfter(true);
//        fadeIn.setAnimationListener(new MyAnimationListener());

        prologueTextImage.post(new Runnable() {
            @Override
            public void run() {

                checkAnim = false;

                fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setDuration(500);
                fadeIn.setStartOffset(0);
                fadeIn.setFillAfter(true);
                fadeIn.setAnimationListener(new MyAnimationListener());

                fadeOut = new AlphaAnimation(1, 0);
                fadeOut.setStartOffset(2000);
                fadeOut.setDuration(3000);
                fadeOut.setFillAfter(true);
                fadeOut.setAnimationListener(new MyAnimationListener());

                fadeTouchOut = new AlphaAnimation(1, 0);
                fadeTouchOut.setStartOffset(0);
                fadeTouchOut.setDuration(1000);
                fadeTouchOut.setFillAfter(true);
                fadeTouchOut.setAnimationListener(new MyAnimationListener());

                transIn = new TranslateAnimation(0, 0, prologueTextImage.getHeight(), 0);
                transIn.setDuration(500);
                transIn.setFillAfter(true);
                transIn.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                transOut = new TranslateAnimation(0, 0, 0, -prologueTextImage.getHeight() / 5);
                transOut.setDuration(5000);
                transOut.setFillAfter(true);
                transOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                transTouchOut = new TranslateAnimation(0, 0, 0, -prologueTextImage.getHeight() / 5);
                transTouchOut.setDuration(1000);
                transTouchOut.setFillAfter(true);
                transTouchOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                animInSet = new AnimationSet(false);
                animInSet.addAnimation(fadeIn);
                animInSet.addAnimation(transIn);
                animInSet.setFillAfter(true);

                animOutSet = new AnimationSet(false);
                animOutSet.addAnimation(fadeOut);
                animOutSet.addAnimation(transOut);
                animOutSet.setFillAfter(true);


                animTouchOutSet = new AnimationSet(false);
                animTouchOutSet.addAnimation(fadeTouchOut);
                animTouchOutSet.addAnimation(transTouchOut);
                animTouchOutSet.setFillAfter(true);

                prologueTextImage.setAnimation(animInSet);
                prologueTextImage.startAnimation(animInSet);

                animRun = new Runnable() {
                    @Override
                    public void run() {
                        checkAnim = true;
                    }
                };

                animHandler = new Handler();
                animHandler.postDelayed(animRun, 600);

            }
        });

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

        if (animHandler != null && animRun != null) {
            animHandler.removeCallbacks(animRun);
            animHandler = null;
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

                            animationFlag = 4;
                            prologueTextImage.startAnimation(animOutSet);

                            fadeInHandler = new Handler();
                            fadeInRun = new Runnable() {
                                @Override
                                public void run() {
                                    storyFlag = 1;
                                    animationFlag = 2;
                                    prologueTextImage.setVisibility(View.INVISIBLE);
                                    prologueTextImage.setImageResource(R.drawable.prologue_text_02);

                                    prologueTextImage.startAnimation(animInSet);
                                }
                            };

                            fadeInHandler.postDelayed(fadeInRun, 5000);
                        }
                    };

                    fadeOutHandler.postDelayed(fadeOutRun, 18700);

                    break;

                case 2:

                    storyFlag = 1;

                    fadeOutHandler = new Handler();
                    fadeOutRun = new Runnable() {
                        @Override
                        public void run() {

                            destroyHandler();

                            animationFlag = 4;
                            prologueTextImage.startAnimation(animOutSet);

                            fadeInHandler = new Handler();
                            fadeInRun = new Runnable() {
                                @Override
                                public void run() {
                                    animationFlag = 3;
                                    storyFlag = 2;
                                    prologueTextImage.setVisibility(View.INVISIBLE);
                                    prologueTextImage.setImageResource(R.drawable.prologue_text_03);
                                    prologueTextImage.startAnimation(animInSet);
                                }
                            };

                            fadeInHandler.postDelayed(fadeInRun, 5000);
                        }
                    };

                    fadeOutHandler.postDelayed(fadeOutRun, 23500);

                    break;

                case 3:

                    animationFlag = 4;

                    fadeInHandler = new Handler();
                    fadeOutHandler = new Handler();


                    fadeInRun = new Runnable() {
                        @Override
                        public void run() {
                            checkAnim = true;
                        }
                    };

                    fadeOutRun = new Runnable() {
                        @Override
                        public void run() {
                            checkAnim = false;
                            prologueTextImage.startAnimation(animOutSet);
                            fadeInHandler.postDelayed(fadeInRun, 5000);
                        }
                    };

                    fadeOutHandler.postDelayed(fadeOutRun, 15000);
                    break;

                case 4:
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