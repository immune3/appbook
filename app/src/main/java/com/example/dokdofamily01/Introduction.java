package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.ssomai.android.scalablelayout.ScalableLayout;

import java.util.Random;

import static android.view.View.GONE;
import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by mapl0 on 2017-12-28.
 */

public class Introduction extends BaseFragment {

    Button waveBtn;
    ImageView wave;
    ImageView waveText;
    View mask;

    private ImageView introBuyl;
    private ImageView introBuylText;
    private ImageView introFather;
    private ImageView introFatherText;
    private ImageView introBird;
    private ImageView introBirdText;
    private ImageView introFlower;
    private ImageView introFlowerText;
    private ImageView introMan;
    private ImageView introManText;
    private ImageView introMom;
    private ImageView introMomText;
    private ImageView introPost;
    private ImageView introPostText;
    private ImageView introSeagull;
    private ImageView introSeagullText;
    private ImageView introSquid;
    private ImageView introSquidText;
    private ImageView introTree;
    private ImageView introTreeText;
    private Button buylBtn;
    private Button fatherBtn;
    private Button birdBtn;
    private Button flowerBtn;
    private Button manBtn;
    private Button momBtn;
    private Button postBtn;
    private Button seagullBtn;
    private Button squidBtn;
    private Button treeBtn;
    private com.ssomai.android.scalablelayout.ScalableLayout sl;

    AlphaAnimation fadeIn;
    AlphaAnimation fadeOut;
    AlphaAnimation blink;

    Random random;

    MediaPlayer musicPlayer;

    int animationCaseFlag = 0;

    int animationFlag = 1;
    int randomN = 0;
    boolean isBlink = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.activity_introduction;
        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void setValues() {
        super.setValues();

        musicPlayer = new MediaPlayer();

    }

    @Override
    public void setAnimation() {
        super.setAnimation();

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
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        mask.setOnTouchListener(new BlockObjListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {

                if(motionEvent.getAction() == MotionEvent.ACTION_UP && checkDistanceX == 0) {
                    animationClear();
                    animationCaseFlag = 0;
                    checkedAnimation = true;

                }

            }
        }) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return super.onTouch(view, motionEvent);
            }
        });

        waveBtn.setOnTouchListener(new BlockObjListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {

                if(motionEvent.getAction() == MotionEvent.ACTION_UP && checkDistanceX == 0) {
                    animationClear();
                    animationCaseFlag = 1;
                    checkedAnimation = true;
                }

            }
        }) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return super.onTouch(view, motionEvent);
            }
        });


        birdBtn.setOnTouchListener(new BlockObjListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {

                if(motionEvent.getAction() == MotionEvent.ACTION_UP && checkDistanceX == 0) {
                    animationClear();
                    animationCaseFlag = 2;
                    checkedAnimation = true;
                }

            }
        }) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                return super.onTouch(view, motionEvent);
            }
        });

        buylBtn.setOnTouchListener(new BlockObjListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {

                if(motionEvent.getAction() == MotionEvent.ACTION_UP && checkDistanceX == 0) {
                    animationClear();
                    animationCaseFlag = 3;
                    checkedAnimation = true;
                }

            }
        }) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {



                return super.onTouch(view, motionEvent);
            }
        });

        fatherBtn.setOnTouchListener(new BlockObjListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {

                if(motionEvent.getAction() == MotionEvent.ACTION_UP && checkDistanceX == 0) {
                    animationClear();
                    animationCaseFlag = 4;
                    checkedAnimation = true;
                }

            }
        }) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return super.onTouch(view, motionEvent);
            }
        });

        flowerBtn.setOnTouchListener(new BlockObjListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {

                if(motionEvent.getAction() == MotionEvent.ACTION_UP && checkDistanceX == 0) {
                    animationClear();
                    animationCaseFlag = 5;
                    checkedAnimation = true;
                }

            }
        }) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return super.onTouch(view, motionEvent);
            }
        });

        manBtn.setOnTouchListener(new BlockObjListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {

                if(motionEvent.getAction() == MotionEvent.ACTION_UP && checkDistanceX == 0) {
                    animationClear();
                    animationCaseFlag = 6;
                    checkedAnimation = true;
                }

            }
        }) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return super.onTouch(view, motionEvent);
            }
        });

        momBtn.setOnTouchListener(new BlockObjListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {

                if(motionEvent.getAction() == MotionEvent.ACTION_UP && checkDistanceX == 0) {
                    animationClear();
                    animationCaseFlag = 7;
                    checkedAnimation = true;
                }

            }
        }) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return super.onTouch(view, motionEvent);
            }
        });

        postBtn.setOnTouchListener(new BlockObjListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {

                if(motionEvent.getAction() == MotionEvent.ACTION_UP && checkDistanceX == 0) {
                    animationClear();
                    animationCaseFlag = 8;
                    checkedAnimation = true;
                }

            }
        }) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return super.onTouch(view, motionEvent);
            }
        });

        seagullBtn.setOnTouchListener(new BlockObjListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {

                if(motionEvent.getAction() == MotionEvent.ACTION_UP && checkDistanceX == 0) {
                    animationClear();
                    animationCaseFlag = 9;
                    checkedAnimation = true;
                }

            }
        }) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return super.onTouch(view, motionEvent);
            }
        });

        squidBtn.setOnTouchListener(new BlockObjListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {

                if(motionEvent.getAction() == MotionEvent.ACTION_UP && checkDistanceX == 0) {
                    animationClear();
                    animationCaseFlag = 10;
                    checkedAnimation = true;
                }

            }
        }) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return super.onTouch(view, motionEvent);
            }
        });

        treeBtn.setOnTouchListener(new BlockObjListener(new CustomTouchListener.AsyncResponse() {
            @Override
            public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {

                if(motionEvent.getAction() == MotionEvent.ACTION_UP && checkDistanceX == 0) {
                    animationClear();
                    animationCaseFlag = 11;
                    checkedAnimation = true;
                }

            }
        }) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                return super.onTouch(view, motionEvent);
            }


        });

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
                initVisible();
                soundPlayFunc();

                vp.setOnTouchListener(null);
                vp.setOnTouchListener(new MyChangeListener());

            } else {
                animationClear();
//                subtitleImageVIew.setVisibility(View.VISIBLE);
                stopMusic();

            }
        }
    }

    @Override
    public void soundPlayFunc() {
        super.soundPlayFunc();

        stopMusic();

//        subtitleImageVIew.setVisibility(GONE);

        introFather.post(new Runnable() {
            @Override
            public void run() {
                animationClear();
                isBlink = true;
                blink = new AlphaAnimation(0, 1);
                blink.setDuration(500);
                blink.setRepeatCount(5);
                blink.setRepeatMode(Animation.REVERSE);
                blink.setFillAfter(true);
                blink.setAnimationListener(new MyBlinkListener());
                wave.setVisibility(View.VISIBLE);
                wave.startAnimation(blink);
                random = new Random();
            }
        });

        checkedAnimation = true;
    }

    public void moveFragment(MotionEvent motionEvent, int checkDistance) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP && checkDistance == 1) {

            vp.setCurrentItem(vp.getCurrentItem() + 1, true);
            stopMusic();

        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP && checkDistance == -1) {

            vp.setCurrentItem(vp.getCurrentItem() - 1, true);
            stopMusic();
        }

    }


    @Override
    public void blockAnimFunc() {

    }

    @Override
    public void delegateFunc(MotionEvent motionEvent, int checkDistance) {
        super.delegateFunc(motionEvent, checkDistance);

        switch (animationCaseFlag) {
            case 0:

                maskOff();
                stopMusic();

                break;

            case 1:
                maskOn();
                stopMusic();
                wave.setVisibility(View.VISIBLE);
                wave.startAnimation(fadeIn);
                waveText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_wave);
                musicPlayer.start();
                musicPlayer.setLooping(false);

                break;

            case 2:
                maskOn();
                stopMusic();
                introBird.setVisibility(View.VISIBLE);
                introBird.startAnimation(fadeIn);
                introBirdText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_bird);
                musicPlayer.start();
                musicPlayer.setLooping(false);

                break;

            case 3:
                maskOn();
                stopMusic();
                introBuyl.setVisibility(View.VISIBLE);
                introBuyl.startAnimation(fadeIn);
                introBuylText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_byul);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

            case 4:
                maskOn();
                stopMusic();
                introFather.setVisibility(View.VISIBLE);
                introFather.startAnimation(fadeIn);
                introFatherText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_father);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

            case 5:
                maskOn();
                stopMusic();
                introFlower.setVisibility(View.VISIBLE);
                introFlower.startAnimation(fadeIn);
                introFlowerText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_flower);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

            case 6:
                maskOn();
                stopMusic();
                introMan.setVisibility(View.VISIBLE);
                introMan.startAnimation(fadeIn);
                introManText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_man);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

            case 7:
                maskOn();
                stopMusic();
                introMom.setVisibility(View.VISIBLE);
                introMom.startAnimation(fadeIn);
                introMomText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_mom);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

            case 8:
                maskOn();
                stopMusic();
                introPost.setVisibility(View.VISIBLE);
                introPost.startAnimation(fadeIn);
                introPostText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_post);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;
            case 9:
                maskOn();
                stopMusic();
                introSeagull.setVisibility(View.VISIBLE);
                introSeagull.startAnimation(fadeIn);
                introSeagullText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_seagull);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

            case 10:
                maskOn();
                stopMusic();
                introSquid.setVisibility(View.VISIBLE);
                introSquid.startAnimation(fadeIn);
                introSquidText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_squid);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

            case 11:
                maskOn();
                stopMusic();
                introTree.setVisibility(View.VISIBLE);
                introTree.startAnimation(fadeIn);
                introTreeText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_tree);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

        }

        moveFragment(motionEvent, checkDistance);
    }

    @Override
    public void onResume() {
        initVisible();
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        stopMusic();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        try {
            if (musicPlayer != null && musicPlayer.isPlaying()) {
                musicPlayer.pause();
                musicPlayer.release();
                musicPlayer = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        initVisible();
    }

    public void initVisible() {
        wave.clearAnimation();
        waveBtn.setVisibility(View.VISIBLE);

        introBird.clearAnimation();
        birdBtn.setVisibility(View.VISIBLE);

        introBuyl.clearAnimation();
        buylBtn.setVisibility(View.VISIBLE);

        introFather.clearAnimation();
        fatherBtn.setVisibility(View.VISIBLE);

        introFlower.clearAnimation();
        flowerBtn.setVisibility(View.VISIBLE);

        introMan.clearAnimation();
        manBtn.setVisibility(View.VISIBLE);

        introMom.clearAnimation();
        momBtn.setVisibility(View.VISIBLE);

        introPost.clearAnimation();
        postBtn.setVisibility(View.VISIBLE);

        introSeagull.clearAnimation();
        seagullBtn.setVisibility(View.VISIBLE);

        introSquid.clearAnimation();
        squidBtn.setVisibility(View.VISIBLE);

        introTree.clearAnimation();
        treeBtn.setVisibility(View.VISIBLE);

        wave.setVisibility(GONE);
        waveText.setVisibility(GONE);

        introBird.setVisibility(GONE);
        introBirdText.setVisibility(GONE);

        introBuyl.setVisibility(GONE);
        introBuylText.setVisibility(GONE);

        introFather.setVisibility(GONE);
        introFatherText.setVisibility(GONE);

        introFlower.setVisibility(GONE);
        introFlowerText.setVisibility(GONE);

        introMan.setVisibility(GONE);
        introManText.setVisibility(GONE);

        introMom.setVisibility(GONE);
        introMomText.setVisibility(GONE);

        introPost.setVisibility(GONE);
        introPostText.setVisibility(GONE);

        introSeagull.setVisibility(GONE);
        introSeagullText.setVisibility(GONE);

        introSquid.setVisibility(GONE);
        introSquidText.setVisibility(GONE);

        introTree.setVisibility(GONE);
        introTreeText.setVisibility(GONE);

        mask.setVisibility(GONE);
    }

    public void stopMusic() {
        try {
            if (musicPlayer != null && musicPlayer.isPlaying()) {
                musicPlayer.pause();
                musicPlayer.release();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void maskOff() {

        mask.setVisibility(GONE);

        wave.setVisibility(GONE);
        waveText.setVisibility(GONE);
        waveBtn.setVisibility(View.VISIBLE);

        introBird.setVisibility(GONE);
        introBirdText.setVisibility(GONE);
        birdBtn.setVisibility(View.VISIBLE);

        introBuyl.setVisibility(GONE);
        introBuylText.setVisibility(GONE);
        buylBtn.setVisibility(View.VISIBLE);

        introFather.setVisibility(GONE);
        introFatherText.setVisibility(GONE);
        fatherBtn.setVisibility(View.VISIBLE);

        introFlower.setVisibility(GONE);
        introFlowerText.setVisibility(GONE);
        flowerBtn.setVisibility(View.VISIBLE);

        introMan.setVisibility(GONE);
        introManText.setVisibility(GONE);
        manBtn.setVisibility(View.VISIBLE);

        introMom.setVisibility(GONE);
        introMomText.setVisibility(GONE);
        momBtn.setVisibility(View.VISIBLE);


        introPost.setVisibility(GONE);
        introPostText.setVisibility(GONE);
        postBtn.setVisibility(View.VISIBLE);

        introSeagull.setVisibility(GONE);
        introSeagullText.setVisibility(GONE);
        seagullBtn.setVisibility(View.VISIBLE);

        introSquid.setVisibility(GONE);
        introSquidText.setVisibility(GONE);
        squidBtn.setVisibility(View.VISIBLE);

        introTree.setVisibility(GONE);
        introTreeText.setVisibility(GONE);
        treeBtn.setVisibility(View.VISIBLE);

        isBlink = true;
        wave.setVisibility(View.VISIBLE);
        wave.startAnimation(blink);
        randomN = 0;

        Log.d("MaskOff", "마스크 꺼짐");

    }

    public void maskOn() {

        mask.setVisibility(View.VISIBLE);

        wave.setVisibility(GONE);
        waveText.setVisibility(GONE);
        waveBtn.setVisibility(View.GONE);

        introBird.setVisibility(GONE);
        introBirdText.setVisibility(GONE);
        birdBtn.setVisibility(View.GONE);

        introBuyl.setVisibility(GONE);
        introBirdText.setVisibility(GONE);
        buylBtn.setVisibility(View.GONE);

        introFather.setVisibility(GONE);
        introFatherText.setVisibility(GONE);
        fatherBtn.setVisibility(View.GONE);

        introFlower.setVisibility(GONE);
        introFlowerText.setVisibility(GONE);
        flowerBtn.setVisibility(View.GONE);

        introMan.setVisibility(GONE);
        introManText.setVisibility(GONE);
        manBtn.setVisibility(View.GONE);

        introMom.setVisibility(GONE);
        introMomText.setVisibility(GONE);
        momBtn.setVisibility(View.GONE);


        introPost.setVisibility(GONE);
        introPostText.setVisibility(GONE);
        postBtn.setVisibility(View.GONE);

        introSeagull.setVisibility(GONE);
        introSeagullText.setVisibility(GONE);
        seagullBtn.setVisibility(View.GONE);

        introSquid.setVisibility(GONE);
        introSquidText.setVisibility(GONE);
        squidBtn.setVisibility(View.GONE);

        introTree.setVisibility(GONE);
        introTreeText.setVisibility(GONE);
        treeBtn.setVisibility(View.GONE);

        Log.d("MaskOn", "마스크 켜짐");

        animationClear();
        isBlink = false;
    }

    @Override
    public void bindViews() {
        super.bindViews();

        mask = (View) layout.findViewById(R.id.mask);
        waveBtn = (Button) layout.findViewById(R.id.waveBtn);
        wave = (ImageView) layout.findViewById(R.id.wave);
        waveText = (ImageView) layout.findViewById(R.id.waveText);
        this.introTreeText = (ImageView) layout.findViewById(R.id.introTreeText);
        this.treeBtn = (Button) layout.findViewById(R.id.treeBtn);
        this.introTree = (ImageView) layout.findViewById(R.id.introTree);
        this.introSquidText = (ImageView) layout.findViewById(R.id.introSquidText);
        this.squidBtn = (Button) layout.findViewById(R.id.squidBtn);
        this.introSquid = (ImageView) layout.findViewById(R.id.introSquid);
        this.introSeagullText = (ImageView) layout.findViewById(R.id.introSeagullText);
        this.seagullBtn = (Button) layout.findViewById(R.id.seagullBtn);
        this.introSeagull = (ImageView) layout.findViewById(R.id.introSeagull);
        this.introPostText = (ImageView) layout.findViewById(R.id.introPostText);
        this.postBtn = (Button) layout.findViewById(R.id.postBtn);
        this.introPost = (ImageView) layout.findViewById(R.id.introPost);
        this.introMomText = (ImageView) layout.findViewById(R.id.introMomText);
        this.momBtn = (Button) layout.findViewById(R.id.momBtn);
        this.introMom = (ImageView) layout.findViewById(R.id.introMom);
        this.introManText = (ImageView) layout.findViewById(R.id.introManText);
        this.manBtn = (Button) layout.findViewById(R.id.manBtn);
        this.introMan = (ImageView) layout.findViewById(R.id.introMan);
        this.introFlowerText = (ImageView) layout.findViewById(R.id.introFlowerText);
        this.flowerBtn = (Button) layout.findViewById(R.id.flowerBtn);
        this.introFlower = (ImageView) layout.findViewById(R.id.introFlower);
        this.introBirdText = (ImageView) layout.findViewById(R.id.introBirdText);
        this.birdBtn = (Button) layout.findViewById(R.id.birdBtn);
        this.introBird = (ImageView) layout.findViewById(R.id.introBird);
        this.introFatherText = (ImageView) layout.findViewById(R.id.introFatherText);
        this.fatherBtn = (Button) layout.findViewById(R.id.fatherBtn);
        this.introFather = (ImageView) layout.findViewById(R.id.introFather);
        this.introBuylText = (ImageView) layout.findViewById(R.id.introByulText);
        this.buylBtn = (Button) layout.findViewById(R.id.byulBtn);
        this.introBuyl = (ImageView) layout.findViewById(R.id.introByul);
        this.sl = (ScalableLayout) layout.findViewById(R.id.sl);
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag) {
                case 1:

                    break;
                case 2:

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

    private class MyBlinkListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isBlink) {
                setVisibleGone();
                Log.e("ramdom", "" + randomN);
                switch (randomN) {
                    case 1:
                        wave.clearAnimation();
                        introBird.clearAnimation();
                        introFather.clearAnimation();
                        introFlower.clearAnimation();
                        introMan.clearAnimation();
                        introMom.clearAnimation();
                        introPost.clearAnimation();
                        introSeagull.clearAnimation();
                        introSquid.clearAnimation();
                        introTree.clearAnimation();
                        introBuyl.startAnimation(blink);
                        Log.e("ramdom", "" + randomN);
                        break;
                    case 2:
                        wave.clearAnimation();
                        introBird.clearAnimation();
                        introBuyl.clearAnimation();
                        introFather.clearAnimation();
                        introFlower.clearAnimation();
                        introMan.clearAnimation();
                        introMom.clearAnimation();
                        introPost.clearAnimation();
                        introSquid.clearAnimation();
                        introTree.clearAnimation();
                        introSeagull.startAnimation(blink);
                        Log.e("ramdom", "" + randomN);
                        break;
                    case 3:
                        wave.clearAnimation();
                        introBird.clearAnimation();
                        introBuyl.clearAnimation();
                        introFather.clearAnimation();
                        introFlower.clearAnimation();
                        introMan.clearAnimation();
                        introMom.clearAnimation();
                        introPost.clearAnimation();
                        introSeagull.clearAnimation();
                        introSquid.clearAnimation();
                        introTree.startAnimation(blink);
                        Log.e("ramdom", "" + randomN);
                        break;
                    case 4:
                        introBird.clearAnimation();
                        introBuyl.clearAnimation();
                        introFather.clearAnimation();
                        introFlower.clearAnimation();
                        introMan.clearAnimation();
                        introMom.clearAnimation();
                        introPost.clearAnimation();
                        introSeagull.clearAnimation();
                        introSquid.clearAnimation();
                        introTree.clearAnimation();
                        wave.startAnimation(blink);
                        Log.e("ramdom", "" + randomN);
                        break;
                    case 5:
                        wave.clearAnimation();
                        introBird.clearAnimation();
                        introBuyl.clearAnimation();
                        introFather.clearAnimation();
                        introFlower.clearAnimation();
                        introMom.clearAnimation();
                        introPost.clearAnimation();
                        introSeagull.clearAnimation();
                        introSquid.clearAnimation();
                        introTree.clearAnimation();
                        introMan.startAnimation(blink);
                        Log.e("ramdom", "" + randomN);
                        break;
                    case 6:
                        wave.clearAnimation();
                        introBird.clearAnimation();
                        introBuyl.clearAnimation();
                        introFather.clearAnimation();
                        introFlower.clearAnimation();
                        introMan.clearAnimation();
                        introPost.clearAnimation();
                        introSeagull.clearAnimation();
                        introSquid.clearAnimation();
                        introTree.clearAnimation();
                        introMom.startAnimation(blink);
                        Log.e("ramdom", "" + randomN);
                        break;
                    case 7:
                        wave.clearAnimation();
                        introBird.clearAnimation();
                        introBuyl.clearAnimation();
                        introFather.clearAnimation();
                        introFlower.clearAnimation();
                        introMan.clearAnimation();
                        introMom.clearAnimation();
                        introPost.clearAnimation();
                        introSeagull.clearAnimation();
                        introTree.clearAnimation();
                        introSquid.startAnimation(blink);
                        Log.e("ramdom", "" + randomN);
                        break;
                    case 8:
                        wave.clearAnimation();
                        introBuyl.clearAnimation();
                        introFather.clearAnimation();
                        introFlower.clearAnimation();
                        introMan.clearAnimation();
                        introMom.clearAnimation();
                        introPost.clearAnimation();
                        introSeagull.clearAnimation();
                        introSquid.clearAnimation();
                        introTree.clearAnimation();
                        introBird.startAnimation(blink);
                        Log.e("ramdom", "" + randomN);
                        break;
                    case 9:
                        wave.clearAnimation();
                        introBird.clearAnimation();
                        introBuyl.clearAnimation();
                        introFlower.clearAnimation();
                        introMan.clearAnimation();
                        introMom.clearAnimation();
                        introPost.clearAnimation();
                        introSeagull.clearAnimation();
                        introSquid.clearAnimation();
                        introTree.clearAnimation();
                        introFather.startAnimation(blink);
                        Log.e("ramdom", "" + randomN);
                        break;
                    case 10:
                        wave.clearAnimation();
                        introBird.clearAnimation();
                        introBuyl.clearAnimation();
                        introFather.clearAnimation();
                        introMan.clearAnimation();
                        introMom.clearAnimation();
                        introPost.clearAnimation();
                        introSeagull.clearAnimation();
                        introSquid.clearAnimation();
                        introTree.clearAnimation();
                        introFlower.startAnimation(blink);
                        Log.e("ramdom", "" + randomN);
                        break;
                    case 11:
                        wave.clearAnimation();
                        introBird.clearAnimation();
                        introBuyl.clearAnimation();
                        introFather.clearAnimation();
                        introFlower.clearAnimation();
                        introMan.clearAnimation();
                        introMom.clearAnimation();
                        introSeagull.clearAnimation();
                        introSquid.clearAnimation();
                        introTree.clearAnimation();
                        introPost.startAnimation(blink);
                        Log.e("ramdom", "" + randomN);
                        break;
                }
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            switch (randomN) {
                case 1:
                    introBuyl.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    introSeagull.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    introTree.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    wave.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    introMan.setVisibility(View.VISIBLE);
                    break;
                case 6:
                    introMom.setVisibility(View.VISIBLE);
                    break;
                case 7:
                    introSquid.setVisibility(View.VISIBLE);
                    break;
                case 8:
                    introBird.setVisibility(View.VISIBLE);
                    break;
                case 9:
                    introFather.setVisibility(View.VISIBLE);
                    break;
                case 10:
                    introFlower.setVisibility(View.VISIBLE);
                    break;
                case 11:
                    introPost.setVisibility(View.VISIBLE);
                    break;

            }
        }

    }

    private void setVisibleGone() {
        randomN = random.nextInt(11) + 1;

        introBuyl.setVisibility(GONE);
        introSeagull.setVisibility(GONE);
        introTree.setVisibility(GONE);
        wave.setVisibility(GONE);
        introMan.setVisibility(GONE);
        introMom.setVisibility(GONE);
        introSquid.setVisibility(GONE);
        introBird.setVisibility(GONE);
        introFather.setVisibility(GONE);
        introFlower.setVisibility(GONE);
        introPost.setVisibility(GONE);
    }

    private void animationClear() {

        isBlink = false;
        wave.clearAnimation();
        introBird.clearAnimation();
        introBuyl.clearAnimation();
        introFather.clearAnimation();
        introFlower.clearAnimation();
        introMan.clearAnimation();
        introMom.clearAnimation();
        introSeagull.clearAnimation();
        introSquid.clearAnimation();
        introTree.clearAnimation();
        introPost.clearAnimation();

        introBuyl.setVisibility(GONE);
        introSeagull.setVisibility(GONE);
        introTree.setVisibility(GONE);
        wave.setVisibility(GONE);
        introMan.setVisibility(GONE);
        introMom.setVisibility(GONE);
        introSquid.setVisibility(GONE);
        introBird.setVisibility(GONE);
        introFather.setVisibility(GONE);
        introFlower.setVisibility(GONE);
        introPost.setVisibility(GONE);
    }
}
