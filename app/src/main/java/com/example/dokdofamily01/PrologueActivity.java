package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;
import com.ssomai.android.scalablelayout.ScalableLayout;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;

/**
 * Created by mapl0 on 2018-01-05.
 */

public class PrologueActivity extends BaseActivity {

    private android.widget.ImageView prologueTextImage;
    private com.ssomai.android.scalablelayout.ScalableLayout sl;
    private CustomScrollView sv;

    int deviceHeight;
    int deviceWidth;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prologue);

        bindViews();
        setValues();
        setAnimation();
        setUpEvents();

    }

    @Override
    public void setAnimation() {
        super.setAnimation();

        prologueTextImage.setImageResource(R.drawable.prologue_text_01);
        prologueTextImage.setVisibility(View.INVISIBLE);

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
                fadeIn.setDuration(1000);
                fadeIn.setStartOffset(0);
                fadeIn.setFillAfter(true);
                fadeIn.setAnimationListener(new MyAnimationListener());

                fadeOut = new AlphaAnimation(1, 0);
                fadeOut.setStartOffset(0);
                fadeOut.setDuration(1000);
                fadeOut.setFillAfter(true);
                fadeOut.setAnimationListener(new MyAnimationListener());

                fadeTouchOut = new AlphaAnimation(1, 0);
                fadeTouchOut.setStartOffset(0);
                fadeTouchOut.setDuration(1000);
                fadeTouchOut.setFillAfter(true);
                fadeTouchOut.setAnimationListener(new MyAnimationListener());

                transIn = new TranslateAnimation(0, 0, prologueTextImage.getHeight() / 5, 0);
                transIn.setDuration(1000);
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
                transOut.setDuration(1000);
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
                animHandler.postDelayed(animRun, 1000);

            }
        });

    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();

        destroyHandler();
        offScroll();

        CustomTouchListener mListener = new CustomTouchListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {
                // onAction 안에 CustomTouchListener onTouch() 이벤트가 끝난 후에 추가로 이벤트를 줄 수 있음.

                if (motionEvent.getAction() == MotionEvent.ACTION_UP && storyFlag < 3 && checkAnim && checkDistanceX == 0 && checkDistanceY == 0) {

                    Log.d("storyFlag", "plus");
                    storyFlag++;

                    if (storyFlag == 0) storyFlag++;

                    if (storyFlag != 3 && musicPlayer != null && musicPlayer.isPlaying())
                        musicPlayer.seekTo(syncArray[storyFlag]);
                    else finish();

//                    if(storyFlag == syncArray.length) checkedAnimation = true;
//                    else checkedAnimation = false;

                } else if(diff > 0) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP && storyFlag < 3 && checkDistanceX == 1 && checkAnim) {

                        Log.d("storyFlag", "plus");
                        storyFlag++;

                        if (storyFlag == 0) storyFlag++;

                        if (storyFlag != 3 && musicPlayer != null && musicPlayer.isPlaying())
                            musicPlayer.seekTo(syncArray[storyFlag]);
                        else finish();

//                    if(storyFlag == syncArray.length) checkedAnimation = true;
//                    else checkedAnimation = false;

                    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP && storyFlag >= 0 && checkDistanceX == -1 && checkAnim) {

                        Log.d("storyFlag", "minus");
                        storyFlag--;
                        if (storyFlag >= 0 && storyFlag < 2 && musicPlayer != null && musicPlayer.isPlaying())
                            musicPlayer.seekTo(syncArray[storyFlag]);
                        else if(storyFlag == 2) {
                            storyFlag = 1;
                            musicPlayer.seekTo(syncArray[storyFlag]);
                        }
                    }

                } else if(diff < 0) {

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP && storyFlag < 3 && checkDistanceY == 1 && checkAnim) {

                        Log.d("storyFlag", "plus");
                        storyFlag++;

                        if (storyFlag == 0) storyFlag++;

                        if (storyFlag != 3 && musicPlayer != null && musicPlayer.isPlaying())
                            musicPlayer.seekTo(syncArray[storyFlag]);
                        else finish();

//                    if(storyFlag == syncArray.length) checkedAnimation = true;
//                    else checkedAnimation = false;

                    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP && storyFlag >= 0 && checkDistanceY == -1 && checkAnim) {

                        Log.d("storyFlag", "minus");
                        storyFlag--;
                        if (storyFlag >= 0 && storyFlag < 2 && musicPlayer != null && musicPlayer.isPlaying())
                            musicPlayer.seekTo(syncArray[storyFlag]);
                        else if(storyFlag == 2) {
                            storyFlag = 1;
                            musicPlayer.seekTo(syncArray[storyFlag]);
                        }
                    }

                }
                Log.d("diff", diff + "");
                Log.d("StoryFlag", storyFlag + " " + checkDistanceX);

                if (motionEvent.getAction() == MotionEvent.ACTION_UP && checkAnim) {

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
                                    destroyHandler();
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
                                    animHandler.postDelayed(animRun, 1000);
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
                                    destroyHandler();
                                    checkAnim = false;
                                    animationFlag = 2;
                                    prologueTextImage.setVisibility(View.INVISIBLE);
                                    prologueTextImage.setImageResource(R.drawable.prologue_text_02);

                                    prologueTextImage.startAnimation(animInSet);

                                    animHandler = new Handler();
                                    animHandler.postDelayed(animRun, 1000);
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
                                    destroyHandler();
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
                                    animHandler.postDelayed(animRun, 1000);

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

        startMusic();
    }

    @Override
    public void setValues() {
        super.setValues();
        syncArray = new int[]{0, 24000, 53000};
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

    @Override
    public void bindViews() {
        super.bindViews();

        this.prologueTextImage = (ImageView) findViewById(R.id.prologueTextImage);
        this.sl = (ScalableLayout) findViewById(R.id.sl);
        this.sv = (CustomScrollView) findViewById(R.id.sv);
    }

    public void stopMusic() {

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

    public void startMusic() {
        musicPlayer = new MediaPlayer();
        musicPlayer = MediaPlayer.create(context, R.raw.prologue);
        musicPlayer.setLooping(false);
        musicPlayer.start();
    }

    public void offScroll() {
        sl.post(new Runnable() {
            @Override
            public void run() {

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                deviceHeight = displayMetrics.heightPixels;
                deviceWidth = displayMetrics.widthPixels;

                int innerWidth = sl.getWidth();
                int innerHeight = sl.getHeight();
                Log.e("length", "" + innerHeight);
                Log.e("length", "" + deviceHeight);
                sv.scrollTo(0, (innerHeight - deviceHeight) / 2);

            }
        });
        sv.setScrolling(false);
    }

    @Override
    protected void onPause() {
        super.onPause();

        stopMusic();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopMusic();

    }

    @Override
    protected void onStart() {
        super.onStart();

        checkedAnimation = false;
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

                            fadeInHandler.postDelayed(fadeInRun, 1000);
                        }
                    };

                    fadeOutHandler.postDelayed(fadeOutRun, 22700);

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

                            fadeInHandler.postDelayed(fadeInRun, 1000);
                        }
                    };

                    fadeOutHandler.postDelayed(fadeOutRun, 27500);

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
                            fadeInHandler.postDelayed(fadeInRun, 1000);
                        }
                    };

                    fadeOutHandler.postDelayed(fadeOutRun, 19000);
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
