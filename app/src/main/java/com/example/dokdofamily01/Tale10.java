package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale10 extends BaseFragment {

    ImageView birds;
    ImageView mountain;
    ImageView rock;
    ImageView seagull;
    ImageView byulHead;
    ImageView byulBody;
    ImageView byulHand;
    ImageView blinkBird;
    ImageView bird[];
    Boolean isAuto;

    TranslateAnimation mountainAppear;
    TranslateAnimation rockAppear;
    TranslateAnimation birdsAppear;
    RotateAnimation byulHeadRotate;
    RotateAnimation byulHandRotate;
    AlphaAnimation fadeIn;
    AlphaAnimation blink;
    AlphaAnimation repeat;
    int animationFlag = 0;
    int repeatFlag = 0;

    private SoundPool tweetSoundPool;
    private int tweetSound;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale10;
        subtitleTextView.setText(null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();

        bird = new ImageView[3];

        birds = (ImageView) layout.findViewById(R.id.birds);
        mountain = (ImageView) layout.findViewById(R.id.mountain);
        rock = (ImageView) layout.findViewById(R.id.rock);
        seagull = (ImageView) layout.findViewById(R.id.seagull);
        byulHead = (ImageView) layout.findViewById(R.id.byulHead);
        byulBody = (ImageView) layout.findViewById(R.id.byulBody);
        byulHand = (ImageView) layout.findViewById(R.id.byulHand);
        blinkBird = (ImageView) layout.findViewById(R.id.blinkBird);
        bird[0] = (ImageView) layout.findViewById(R.id.bird00);
        bird[1] = (ImageView) layout.findViewById(R.id.bird10);
        bird[2] = (ImageView) layout.findViewById(R.id.bird20);
    }

    @Override
    public void setValues() {
        super.setValues();

    }

    @Override
    public void setAnimation() {
        super.setAnimation();
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        blinkBird.setOnTouchListener(new BlockObjListener());
    }

    @Override
    public void blockAnimFunc() {
//        tweetTouchSound= tweetTouchSoundPool.load(getContext(), R.raw.effect_10_tweet_touch, 1);
        checkedAnimation = false;
        blinkBird.clearAnimation();
        blinkBird.startAnimation(repeat);
        byulHead.startAnimation(byulHeadRotate);
        byulHand.startAnimation(byulHandRotate);

        super.blockAnimFunc();
    }

    private class MyAnimationListener extends com.example.dokdofamily01.MyAnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            super.onAnimationStart(animation);
            switch (animationFlag) {
                case 1:
                    byulHead.setVisibility(View.INVISIBLE);
                    byulHand.setVisibility(View.INVISIBLE);
                    byulBody.setVisibility(View.INVISIBLE);
                    seagull.setVisibility(View.INVISIBLE);
                    mountain.setVisibility(View.VISIBLE);
                    rock.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    byulHead.setVisibility(View.VISIBLE);
                    byulHand.setVisibility(View.VISIBLE);
                    byulBody.setVisibility(View.VISIBLE);
                    seagull.setVisibility(View.VISIBLE);
                    birds.setVisibility(View.VISIBLE);
                    bird[0].setVisibility(View.VISIBLE);
                    bird[1].setVisibility(View.VISIBLE);
                    bird[2].setVisibility(View.VISIBLE);
                    break;
                case 3:
                    animationFlag = 4;
                    blinkBird.setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            switch (animationFlag) {
                case 1:
                    animationFlag = 2;
                    mountain.clearAnimation();
                    rock.clearAnimation();
                    birds.startAnimation(birdsAppear);
                    bird[0].startAnimation(birdsAppear);
                    bird[1].startAnimation(birdsAppear);
                    bird[2].startAnimation(birdsAppear);
                    byulBody.startAnimation(fadeIn);
                    byulHand.startAnimation(fadeIn);
                    byulHead.startAnimation(fadeIn);
                    seagull.startAnimation(fadeIn);
                    break;
                case 2:
                    animationFlag = 3;
                    birds.clearAnimation();
                    byulBody.clearAnimation();
                    byulHand.clearAnimation();
                    byulHead.clearAnimation();
                    seagull.clearAnimation();
                    blinkBird.startAnimation(blink);
                    checkedAnimation = true;
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            super.onAnimationRepeat(animation);
            switch (repeatFlag) {
                case 0:
                    repeatFlag = 1;
                    bird[0].setImageResource(R.drawable.img_10_bird_04);
                    bird[1].setImageResource(R.drawable.img_10_bird_03);
                    bird[2].setImageResource(R.drawable.img_10_bird_02);
                    break;
                case 1:
                    repeatFlag = 2;
                    bird[0].setImageResource(R.drawable.img_10_bird_03);
                    bird[1].setImageResource(R.drawable.img_10_bird_02);
                    bird[2].setImageResource(R.drawable.img_10_bird_01);
                    break;
                case 2:
                    repeatFlag = 3;
                    bird[0].setImageResource(R.drawable.img_10_bird_02);
                    bird[1].setImageResource(R.drawable.img_10_bird_01);
                    bird[2].setImageResource(R.drawable.img_10_bird_05);
                    break;
                case 3:
                    repeatFlag = 4;
                    bird[0].setImageResource(R.drawable.img_10_bird_01);
                    bird[1].setImageResource(R.drawable.img_10_bird_05);
                    bird[2].setImageResource(R.drawable.img_10_bird_04);
                    break;
                case 4:
                    repeatFlag = 0;
                    bird[0].setImageResource(R.drawable.img_10_bird_05);
                    bird[1].setImageResource(R.drawable.img_10_bird_04);
                    bird[2].setImageResource(R.drawable.img_10_bird_03);
                    break;
            }
        }
    }

    @Override
    public void soundPlayFunc() {
        this.isAuto = getArguments().getBoolean("isAuto");

        checkedAnimation = false;

        if(isAuto) {
            musicController = new MusicController(((TaleActivity)getActivity()), R.raw.scene_10, vp,
                    new int[]{R.drawable.sub_10_01, 4000},
                    new int[]{R.drawable.sub_10_02, 7400},
                    new int[]{R.drawable.sub_10_03, 12400},
                    new int[]{R.drawable.sub_10_04, 15400},
                    new int[]{R.drawable.sub_10_05, 18600},
                    new int[]{R.drawable.sub_10_06, 23600},
                    new int[]{R.drawable.sub_10_07, 32100},
                    new int[]{R.drawable.sub_10_08, 35000},
                    new int[]{R.drawable.sub_10_09, 37600},
                    new int[]{R.drawable.sub_10_10, 40400},
                    new int[]{R.drawable.sub_10_11, 99999});
        } else {
            subtitleController = new SubtitleController(((TaleActivity)getActivity()), vp,
                    R.drawable.sub_10_01,
                    R.drawable.sub_10_02,
                    R.drawable.sub_10_03,
                    R.drawable.sub_10_04,
                    R.drawable.sub_10_05,
                    R.drawable.sub_10_06,
                    R.drawable.sub_10_07,
                    R.drawable.sub_10_08,
                    R.drawable.sub_10_09,
                    R.drawable.sub_10_10,
                    R.drawable.sub_10_11);
        }
        mountain.post(new Runnable() {
            @Override
            public void run() {
                birds.setVisibility(View.INVISIBLE);
                bird[0].setVisibility(View.INVISIBLE);
                bird[1].setVisibility(View.INVISIBLE);
                bird[2].setVisibility(View.INVISIBLE);

                //        fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
                fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setDuration(1000);
                fadeIn.setAnimationListener(new MyAnimationListener());

                blink = new AlphaAnimation(0.3f, 1);
                blink.setDuration(500);
                blink.setRepeatCount(Animation.INFINITE);
                blink.setRepeatMode(Animation.REVERSE);
//        blink.setAnimationListener(new MyAnimationListener());

                repeat = new AlphaAnimation(1, 1);
                repeat.setDuration(500);
                repeat.setRepeatCount(16);
//        repeat.setRepeatCount(Animation.INFINITE);
                repeat.setRepeatMode(Animation.REVERSE);
                repeat.setAnimationListener(new MyAnimationListener());

                tweetSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
                tweetSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        tweetSoundPool.play(tweetSound, 0.2f, 0.2f, 0, 0, 1);
                    }
                });
                mountainAppear = new TranslateAnimation(0, 0, mountain.getHeight(), 0);
                mountainAppear.setDuration(2000);
                mountainAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                mountainAppear.setAnimationListener(new MyAnimationListener());

                birdsAppear = new TranslateAnimation(0, 0, birds.getHeight(), 0);
                birdsAppear.setDuration(1000);
                birdsAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                birdsAppear.setAnimationListener(new MyAnimationListener());

                rockAppear = new TranslateAnimation(-rock.getWidth(), 0, 0, 0);
                rockAppear.setStartOffset(500);
                rockAppear.setDuration(1500);
                rockAppear.setInterpolator(new AccelerateDecelerateInterpolator());

                byulHeadRotate = new RotateAnimation(0, -20, byulHead.getWidth() / 2, byulHead.getHeight() / 2);
                byulHeadRotate.setDuration(2000);
                byulHeadRotate.setInterpolator(new AccelerateDecelerateInterpolator());
                byulHeadRotate.setRepeatCount(3);
                byulHeadRotate.setRepeatMode(Animation.REVERSE);
                byulHeadRotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        tweetSound = tweetSoundPool.load(getContext(), R.raw.effect_10_tweet, 0);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        blinkBird.startAnimation(blink);
                        checkedAnimation = true;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        tweetSound = tweetSoundPool.load(getContext(), R.raw.effect_10_tweet, 0);
                    }
                });

                byulHandRotate = new RotateAnimation(0, 8, 0, byulHand.getHeight());
                byulHandRotate.setDuration(2000);
                byulHandRotate.setInterpolator(new AccelerateDecelerateInterpolator());
                byulHandRotate.setRepeatCount(3);
                byulHandRotate.setRepeatMode(Animation.REVERSE);

                blinkBird.setVisibility(View.INVISIBLE);
                blinkBird.clearAnimation();
                animationFlag = 1;
                mountain.startAnimation(mountainAppear);
                rock.startAnimation(rockAppear);

            }
        });
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            if(tweetSoundPool != null) {
                tweetSoundPool.release();
            }
        }
    }

    private void returnMemory() {

        birds = null;
        mountain = null;
        rock = null;
        seagull = null;
        byulHead = null;
        byulBody = null;
        byulHand = null;
        blinkBird = null;
        bird = null;

        if(mountainAppear != null) mountainAppear.cancel();
        if(rockAppear != null) rockAppear.cancel();
        if(birdsAppear != null) birdsAppear.cancel();
        if(byulHeadRotate != null) byulHeadRotate.cancel();
        if(byulHandRotate != null) byulHandRotate.cancel();
        if(fadeIn != null) fadeIn.cancel();
        if(blink != null) blink.cancel();
        if(repeat != null) repeat.cancel();

        mountainAppear = null;
        rockAppear = null;
        birdsAppear = null;
        byulHeadRotate = null;
        byulHandRotate = null;
        fadeIn = null;
        blink = null;
        repeat = null;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        returnMemory();

    }
}
