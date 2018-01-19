package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.ssomai.android.scalablelayout.ScalableLayout;

import java.util.Random;

import static android.view.View.GONE;
import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleImageVIew;

/**
 * Created by mapl0 on 2018-01-19.
 */

public class IntroActivity extends BaseActivity {

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        setValues();
        setAnimation();
        setUpEvents();

    }

    @Override
    public void setAnimation() {
        super.setAnimation();

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

    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();

        stopMusic();

        mask.setOnTouchListener(new CustomTouchListener(new CustomTouchListener.AsyncResponse() {
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

        waveBtn.setOnTouchListener(new CustomTouchListener(new CustomTouchListener.AsyncResponse() {
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


        birdBtn.setOnTouchListener(new CustomTouchListener(new CustomTouchListener.AsyncResponse() {
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

        buylBtn.setOnTouchListener(new CustomTouchListener(new CustomTouchListener.AsyncResponse() {
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

        fatherBtn.setOnTouchListener(new CustomTouchListener(new CustomTouchListener.AsyncResponse() {
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

        flowerBtn.setOnTouchListener(new CustomTouchListener(new CustomTouchListener.AsyncResponse() {
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

        manBtn.setOnTouchListener(new CustomTouchListener(new CustomTouchListener.AsyncResponse() {
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

        momBtn.setOnTouchListener(new CustomTouchListener(new CustomTouchListener.AsyncResponse() {
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

        postBtn.setOnTouchListener(new CustomTouchListener(new CustomTouchListener.AsyncResponse() {
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

        seagullBtn.setOnTouchListener(new CustomTouchListener(new CustomTouchListener.AsyncResponse() {
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

        squidBtn.setOnTouchListener(new CustomTouchListener(new CustomTouchListener.AsyncResponse() {
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

        treeBtn.setOnTouchListener(new CustomTouchListener(new CustomTouchListener.AsyncResponse() {
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
    public void setValues() {
        super.setValues();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initVisible();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

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

    @Override
    public void bindViews() {
        super.bindViews();

        mask = (View) findViewById(R.id.mask);
        waveBtn = (Button) findViewById(R.id.waveBtn);
        wave = (ImageView) findViewById(R.id.wave);
        waveText = (ImageView) findViewById(R.id.waveText);
        this.introTreeText = (ImageView) findViewById(R.id.introTreeText);
        this.treeBtn = (Button) findViewById(R.id.treeBtn);
        this.introTree = (ImageView) findViewById(R.id.introTree);
        this.introSquidText = (ImageView) findViewById(R.id.introSquidText);
        this.squidBtn = (Button) findViewById(R.id.squidBtn);
        this.introSquid = (ImageView) findViewById(R.id.introSquid);
        this.introSeagullText = (ImageView) findViewById(R.id.introSeagullText);
        this.seagullBtn = (Button) findViewById(R.id.seagullBtn);
        this.introSeagull = (ImageView) findViewById(R.id.introSeagull);
        this.introPostText = (ImageView) findViewById(R.id.introPostText);
        this.postBtn = (Button) findViewById(R.id.postBtn);
        this.introPost = (ImageView) findViewById(R.id.introPost);
        this.introMomText = (ImageView) findViewById(R.id.introMomText);
        this.momBtn = (Button) findViewById(R.id.momBtn);
        this.introMom = (ImageView) findViewById(R.id.introMom);
        this.introManText = (ImageView) findViewById(R.id.introManText);
        this.manBtn = (Button) findViewById(R.id.manBtn);
        this.introMan = (ImageView) findViewById(R.id.introMan);
        this.introFlowerText = (ImageView) findViewById(R.id.introFlowerText);
        this.flowerBtn = (Button) findViewById(R.id.flowerBtn);
        this.introFlower = (ImageView) findViewById(R.id.introFlower);
        this.introBirdText = (ImageView) findViewById(R.id.introBirdText);
        this.birdBtn = (Button) findViewById(R.id.birdBtn);
        this.introBird = (ImageView) findViewById(R.id.introBird);
        this.introFatherText = (ImageView) findViewById(R.id.introFatherText);
        this.fatherBtn = (Button) findViewById(R.id.fatherBtn);
        this.introFather = (ImageView) findViewById(R.id.introFather);
        this.introBuylText = (ImageView) findViewById(R.id.introByulText);
        this.buylBtn = (Button) findViewById(R.id.byulBtn);
        this.introBuyl = (ImageView) findViewById(R.id.introByul);
        this.sl = (ScalableLayout) findViewById(R.id.sl);
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


}
