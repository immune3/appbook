package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.ssomai.android.scalablelayout.ScalableLayout;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale15 extends BaseFragment {

    private android.widget.ImageView ivCave15;
    private android.widget.ImageView manImage1;
    private android.widget.ImageView manImage2;
    private android.widget.ImageView manImage3;
    private android.widget.ImageView manImage4;
    private android.widget.ImageView ivByul15;
    private android.widget.ImageView seaweadImage;
    private android.widget.ImageView fish;
    private com.ssomai.android.scalablelayout.ScalableLayout sl;
    private ImageView ivLand15;
    Boolean isAuto;

    int animationFlag = 0;

    AlphaAnimation fadeIn;
    AlphaAnimation fadeOut;
    AlphaAnimation blink;
    TranslateAnimation landAnimation, byulAnimation, caveAnimation;

    SoundPool clickFishSp, moveManSp, appearManSp;
    int clickFish;
    int moveMan;
    int appearMan;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale15;
        subtitleTextView.setText(null);


        return super.onCreateView(inflater, container, savedInstanceState);
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
        fish.setOnTouchListener(new BlockObjListener());
    }

    @Override
    public void blockAnimFunc() {
        if ((animationFlag == 0 || animationFlag ==4) && checkedAnimation) {
            animationClear();
            checkedAnimation = false;
            animationFlag = 1;
            fish.clearAnimation();
            fish.setVisibility(View.VISIBLE);
            //man1 사라지고 man2나온다.
            clickFish = clickFishSp.load(getContext(), R.raw.effect_15_fish, 1);
            manImage4.setVisibility(View.INVISIBLE);
            manImage1.startAnimation(fadeIn);
            moveMan = moveManSp.load(getContext(), R.raw.effect_05_move_letters, 2);
        }

        super.blockAnimFunc();
    }

    private class MyAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            switch (animationFlag) {
                case 0:

                case 1:

                    fish.clearAnimation();
//                    fish.setVisibility(View.GONE);
                    break;
                case 2:
                    moveMan = moveManSp.load(getContext(), R.raw.effect_05_move_letters, 2);
                    break;
                case 3:
                    moveMan = moveManSp.load(getContext(), R.raw.effect_05_move_letters, 2);
                    break;
                case 4:
                    appearMan = appearManSp.load(getContext(), R.raw.effect_15_man, 3);
                    manImage4.setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag) {
                case 0:
                    checkedAnimation = true;
                    break;
                case 1:
                    Log.d("Case1", "AnimationFlag" + animationFlag);
                    animationFlag = 2;
                    manImage1.clearAnimation();
                    manImage2.clearAnimation();
                    manImage1.startAnimation(fadeOut);
                    manImage2.startAnimation(fadeIn);
                    break;
                case 2:
                    animationFlag = 3;
                    manImage1.clearAnimation();
                    manImage2.clearAnimation();
                    manImage2.startAnimation(fadeOut);
                    manImage3.startAnimation(fadeIn);
                    break;
                case 3:
                    animationFlag = 4;
                    manImage2.clearAnimation();
                    manImage3.clearAnimation();
                    manImage3.startAnimation(fadeOut);
                    manImage4.startAnimation(fadeIn);
                    break;
                case 4:

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            manImage3.clearAnimation();
                            manImage4.clearAnimation();

                            fish.setVisibility(View.VISIBLE);
                            fish.startAnimation(blink);

                            checkedAnimation = true;
                        }
                    }, 1000);


                    break;
            }
//            if (appearFlag == 1) {
//                appearFlag = 0;
//                fish.setVisibility(View.VISIBLE);
//                fish.startAnimation(blink);
//                checkedAnimation = true;
//            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    @Override
    public void bindViews() {
        super.bindViews();

        this.ivLand15 = (ImageView) layout.findViewById(R.id.ivLand15);
        this.sv = (CustomScrollView) layout.findViewById(R.id.sv);
        this.sl = (ScalableLayout) layout.findViewById(R.id.sl);
        this.fish = (ImageView) layout.findViewById(R.id.fish);
        this.seaweadImage = (ImageView) layout.findViewById(R.id.seaweadImage);
        this.manImage4 = (ImageView) layout.findViewById(R.id.manImage4);
        this.manImage3 = (ImageView) layout.findViewById(R.id.manImage3);
        this.manImage2 = (ImageView) layout.findViewById(R.id.manImage2);
        this.manImage1 = (ImageView) layout.findViewById(R.id.manImage1);
        this.ivCave15 = (ImageView) layout.findViewById(R.id.ivCave15);
        this.ivByul15 = (ImageView) layout.findViewById(R.id.ivByul15);

    }

    public void animationClear() {
        animationFlag=0;
        fish.setVisibility(View.INVISIBLE);
        manImage1.setVisibility(View.INVISIBLE);
        manImage2.setVisibility(View.INVISIBLE);
        manImage3.setVisibility(View.INVISIBLE);
        manImage4.setVisibility(View.INVISIBLE);

        fish.clearAnimation();
        manImage1.clearAnimation();
        manImage1.clearAnimation();
        manImage2.clearAnimation();
        manImage3.clearAnimation();
        manImage4.clearAnimation();
        ivByul15.clearAnimation();
        ivCave15.clearAnimation();
        ivLand15.clearAnimation();
        seaweadImage.clearAnimation();
    }

    @Override
    public void soundPlayFunc() {
        this.isAuto = getArguments().getBoolean("isAuto");

        checkedAnimation = false;

        if(isAuto) {
            musicController = new MusicController(((TaleActivity)getActivity()), R.raw.scene_15, vp,
                    new int[]{R.drawable.sub_15_01, 2500},
                    new int[]{R.drawable.sub_15_02, 10500},
                    new int[]{R.drawable.sub_15_03, 16500},
                    new int[]{R.drawable.sub_15_04, 27000},
                    new int[]{R.drawable.sub_15_05, 34000},
                    new int[]{R.drawable.sub_15_06, 39000},
                    new int[]{R.drawable.sub_15_07, 41000},
                    new int[]{R.drawable.sub_15_08, 99999});
        } else {
            subtitleController = new SubtitleController(((TaleActivity)getActivity()), vp,
                    R.drawable.sub_15_01,
                    R.drawable.sub_15_02,
                    R.drawable.sub_15_03,
                    R.drawable.sub_15_04,
                    R.drawable.sub_15_05,
                    R.drawable.sub_15_06,
                    R.drawable.sub_15_07,
                    R.drawable.sub_15_08);
        }

        sl.post(new Runnable() {
            @Override
            public void run() {
                clickFishSp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
                moveManSp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
                appearManSp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

                clickFishSp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        clickFishSp.play(clickFish, 1, 1, 0, 0, 2);
                    }
                });

                moveManSp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        moveManSp.play(moveMan, 1, 1, 0, 0, 1);
                    }
                });

                appearManSp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        appearManSp.play(appearMan, 1, 1, 0, 0, 1);
                    }
                });

                landAnimation = new TranslateAnimation(0, 0, ivLand15.getHeight(), 0);
                landAnimation.setDuration(2000);
                landAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                caveAnimation = new TranslateAnimation(ivCave15.getWidth(), 0, 0, 0);
                caveAnimation.setStartOffset(500);
                caveAnimation.setDuration(2500);
                caveAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                byulAnimation = new TranslateAnimation(-ivByul15.getWidth() * 1.5f, 0, 0, 0);
                byulAnimation.setStartOffset(1000);
                byulAnimation.setDuration(2500);
                byulAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                byulAnimation.setAnimationListener(new MyAnimationListener());

//                fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.anim_15_fadein);

                fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setAnimationListener(new MyAnimationListener());
                fadeIn.setDuration(1000);
//                fadeIn.setFillAfter(true);

                fadeOut = new AlphaAnimation(1, 0);
                fadeOut.setDuration(1000);
//                fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.anim_15_fadeout);
                fadeOut.setFillAfter(true);


                blink = new AlphaAnimation(0.3f, 1);
                blink.setDuration(500);
                blink.setRepeatCount(Animation.INFINITE);
                blink.setRepeatMode(Animation.REVERSE);

                fish.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fish.setVisibility(View.VISIBLE);
                        fish.startAnimation(blink);
                    }
                }, 3000);

                if (landAnimation != null) {
                    animationClear();
                    checkedAnimation = false;
                    ivLand15.startAnimation(landAnimation);
                    seaweadImage.startAnimation(landAnimation);
                    ivCave15.startAnimation(caveAnimation);
                    ivByul15.startAnimation(byulAnimation);
                }
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            if(clickFishSp != null) {
                clickFishSp.release();
            }
            if(appearManSp != null) {
                appearManSp.release();
            }
            if(moveManSp != null) {
                moveManSp.release();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void returnMemory() {

        ivCave15 = null;
        manImage1 = null;
        manImage2 = null;
        manImage3 = null;
        manImage4 = null;
        ivByul15 = null;
        seaweadImage = null;
        fish = null;
        sl = null;
        ivLand15 = null;

        if(fadeIn != null) fadeIn.cancel();
        if(fadeOut != null) fadeOut.cancel();
        if(blink != null) blink.cancel();
        if(landAnimation != null) landAnimation.cancel();
        if(byulAnimation != null) byulAnimation.cancel();
        if(caveAnimation != null) caveAnimation.cancel();

        fadeIn = null;
        fadeOut = null;
        blink = null;
        landAnimation = null;
        byulAnimation = null;
        caveAnimation = null;


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        returnMemory();
    }
}
