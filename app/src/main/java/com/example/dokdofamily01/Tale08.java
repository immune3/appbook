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
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;


/**
 * Created by heronation on 2017-11-06.
 */

public class Tale08 extends BaseFragment {
    ImageView treeBody;
    ImageView treeHand;
    ImageView leaves;
    ImageView smile;
    ImageView eyeBlack;
    ImageView eyeWhite;
    ImageView plant;
    ImageView dokdo;
    ImageView land;
    ImageView seagull;
    ImageView byul;
    TranslateAnimation plantAnimation;
    TranslateAnimation dokdoAnimation;
    TranslateAnimation landAnimation;
    TranslateAnimation seagullAnimation;
    TranslateAnimation byulAnimation;
    TranslateAnimation treeAnimation;
    TranslateAnimation treeAnimation2;
    TranslateAnimation leafTranslateAni;
    TranslateAnimation treeEyeToByul;
    RotateAnimation treeEyeRotate;
    RotateAnimation treeHandRotate;
    AlphaAnimation blink;
    AlphaAnimation leafFadein;
    AlphaAnimation leafFadeout;
    AlphaAnimation afterLeafFadeout;
    AlphaAnimation fadeout;
    AlphaAnimation fadeIn;
    AnimationSet leafAniSet;
    AnimationSet eyeBlackAniSet;
    int animationFlag = 0;
    Boolean isAuto;

    SoundPool laughingSoundPool, eyeSoundPool, treeHandSoundPool;
    int laughingSound, eyeSound, treeHandSound;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale08;

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void bindViews() {
        super.bindViews();
        plant = (ImageView) layout.findViewById(R.id.plant);
        dokdo = (ImageView) layout.findViewById(R.id.dokdo);
        land = (ImageView) layout.findViewById(R.id.land);
        seagull = (ImageView) layout.findViewById(R.id.seagull);
        byul = (ImageView) layout.findViewById(R.id.byul);
        treeBody = (ImageView) layout.findViewById(R.id.treeBody);
        treeHand = (ImageView) layout.findViewById(R.id.treeHand);
        leaves = (ImageView) layout.findViewById(R.id.leaves);
        smile = (ImageView) layout.findViewById(R.id.smile);
        eyeBlack = (ImageView) layout.findViewById(R.id.eyeBlack);
        eyeWhite = (ImageView) layout.findViewById(R.id.eyeWhite);
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

        byul.setOnTouchListener(new BlockObjListener());

    }

    @Override
    public void blockAnimFunc() {
        if (animationFlag == 0) {
            animationFlag = 1;
//                    eyeBlack.clearAnimation();
            checkedAnimation = false;
            byul.clearAnimation();
            leaves.setVisibility(View.VISIBLE);
            leaves.startAnimation(leafAniSet);
            eyeBlack.startAnimation(treeEyeToByul);
            smile.startAnimation(fadeIn);
            laughingSound = laughingSoundPool.load(getContext(), R.raw.effect_08_laughing, 0);
            eyeSound = eyeSoundPool.load(getContext(), R.raw.effect_08_eyesound, 0);
        }

        super.blockAnimFunc();
    }

    private void animationClear() {
        checkedAnimation = false;
        eyeBlack.setVisibility(View.INVISIBLE);
        byul.setVisibility(View.INVISIBLE);
        treeHand.setVisibility(View.INVISIBLE);
        leaves.setVisibility(View.INVISIBLE);
        treeBody.setVisibility(View.INVISIBLE);
        animationFlag = 0;
        treeBody.clearAnimation();
        treeHand.clearAnimation();
        leaves.clearAnimation();
        smile.clearAnimation();
//        eyeBlack.clearAnimation();
        eyeWhite.clearAnimation();
        plant.clearAnimation();
        dokdo.clearAnimation();
        land.clearAnimation();
        seagull.clearAnimation();
        byul.clearAnimation();
    }

    @Override
    public void soundPlayFunc() {
        this.isAuto = getArguments().getBoolean("isAuto");

        checkedAnimation = false;

        if (isAuto) {
            musicController = new MusicController(((TaleActivity) getActivity()), R.raw.scene_8, vp,
                    new int[]{R.drawable.sub_08_01, 6000},
                    new int[]{R.drawable.sub_08_02, 12000},
                    new int[]{R.drawable.sub_08_03, 18500},
                    new int[]{R.drawable.sub_08_04, 24500},
                    new int[]{R.drawable.sub_08_05, 29000},
                    new int[]{R.drawable.sub_08_06, 35000},
                    new int[]{R.drawable.sub_08_07, 99999});

        } else {
            subtitleController = new SubtitleController(((TaleActivity) getActivity()), vp,
                    R.drawable.sub_08_01,
                    R.drawable.sub_08_02,
                    R.drawable.sub_08_03,
                    R.drawable.sub_08_04,
                    R.drawable.sub_08_05,
                    R.drawable.sub_08_06,
                    R.drawable.sub_08_07);
        }

        land.post(new Runnable() {
            @Override
            public void run() {
                laughingSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
                eyeSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
                treeHandSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
                laughingSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int
                            i1) {
                        laughingSoundPool.play(laughingSound, 1, 1, 1, 0, 1);
                    }
                });

                eyeSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                        eyeSoundPool.play(eyeSound, 1, 1, 1, 0, 1);
                    }
                });
                treeHandSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                        treeHandSoundPool.play(treeHandSound, 0.8f, 0.8f, 1, 0, 1);
                    }
                });

                leafFadeout = new AlphaAnimation(1, 0);
                leafFadeout.setStartOffset(3000);
                leafFadeout.setDuration(1500);
                leafFadeout.setInterpolator(new AccelerateDecelerateInterpolator());
                leafFadeout.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        leaves.setVisibility(View.INVISIBLE);
                        smile.startAnimation(afterLeafFadeout);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                    }
                });

                afterLeafFadeout = new AlphaAnimation(1, 0);
                afterLeafFadeout.setStartOffset(500);
                afterLeafFadeout.setDuration(500);
                afterLeafFadeout.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animationFlag = 0;
//                treeHand.startAnimation(fadeout);
                        checkedAnimation = true;
                        byul.startAnimation(blink);
                        eyeBlack.startAnimation(treeEyeRotate);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        treeHand.startAnimation(fadeout);
                        byul.clearAnimation();
                    }
                });

                leafFadein = new AlphaAnimation(0, 1);
                leafFadein.setStartOffset(1200);
                leafFadein.setDuration(500);
                leafFadein.setInterpolator(new AccelerateDecelerateInterpolator());

                blink = new AlphaAnimation(1, 0.3f);
                blink.setDuration(500);
                blink.setInterpolator(new LinearInterpolator());
                blink.setRepeatCount(Animation.INFINITE);
                blink.setRepeatMode(Animation.REVERSE);

                plantAnimation = new TranslateAnimation(-plant.getWidth(), 0, 0, 0);
                plantAnimation.setDuration(1200);
                plantAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                dokdoAnimation = new TranslateAnimation(-dokdo.getWidth(), 0, 0, 0);
                dokdoAnimation.setDuration(1000);
                dokdoAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                landAnimation = new TranslateAnimation(0, 0, land.getHeight(), 0);
                landAnimation.setStartOffset(200);
                landAnimation.setDuration(1000);
                landAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                landAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
//                        byul.clearAnimation();
//                        treeBody.clearAnimation();
//                        eyeBlack.clearAnimation();
//                        eyeWhite.clearAnimation();
                        byul.setVisibility(View.INVISIBLE);
                        treeBody.setVisibility(View.INVISIBLE);
                        eyeBlack.setVisibility(View.INVISIBLE);
                        eyeWhite.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        byul.setVisibility(View.VISIBLE);
                        treeBody.setVisibility(View.VISIBLE);
                        eyeBlack.setVisibility(View.VISIBLE);
                        eyeWhite.setVisibility(View.VISIBLE);
//                        byul.startAnimation(byulAnimation);
//                        treeBody.startAnimation(treeAnimation);
//                        eyeBlack.startAnimation(treeAnimation);
//                        eyeWhite.startAnimation(treeAnimation);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }


                });

                seagullAnimation = new TranslateAnimation(seagull.getWidth(), 0, -seagull.getHeight(), 0);
                seagullAnimation.setStartOffset(500);
                seagullAnimation.setDuration(800);
                seagullAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                treeEyeRotate = new RotateAnimation(-2, 1.7f, -(eyeBlack.getWidth()), -(treeBody.getHeight()));
                treeEyeRotate.setDuration(2000);
                treeEyeRotate.setInterpolator(new AccelerateDecelerateInterpolator());
                treeEyeRotate.setRepeatCount(Animation.INFINITE);
                treeEyeRotate.setRepeatMode(Animation.REVERSE);


                byulAnimation = new TranslateAnimation(byul.getWidth() * 1.5f, 0, -byul.getHeight(), 0);
                byulAnimation.setStartOffset(1000);
                byulAnimation.setDuration(700);
                byulAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                byulAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {

                        if (animationFlag == 1) {
//                            Log.d("ababab", "aaaa");
                            byul.startAnimation(blink);
                            eyeBlack.startAnimation(treeEyeRotate);
                            animationFlag = 0;
                            checkedAnimation = true;

                        } else {

//                            Log.d("ababab", "bbbb");
//                            treeEyeRotate.reset();
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                    }
                });


                treeAnimation = new TranslateAnimation((int) (treeBody.getWidth() * 0.3), 0, treeBody.getHeight() * 1.5f, 0);
                treeAnimation.setStartOffset(800);
                treeAnimation.setDuration(800);
                treeAnimation.setInterpolator(new AccelerateDecelerateInterpolator());


                eyeBlackAniSet = new AnimationSet(false);
                eyeBlackAniSet.addAnimation(treeAnimation);
                eyeBlackAniSet.addAnimation(treeEyeRotate);

                eyeBlackAniSet.setAnimationListener(new AnimationSet.AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        eyeBlack.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        eyeBlack.setVisibility(View.VISIBLE);
                    }
                });


                treeEyeToByul = new TranslateAnimation(0, eyeBlack.getWidth() / 15, 0, 0);
                treeEyeToByul.setDuration(300);
                treeEyeToByul.setInterpolator(new AccelerateDecelerateInterpolator());
                treeEyeToByul.setFillAfter(true);
                treeEyeToByul.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        treeHand.startAnimation(treeHandRotate);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                treeHandRotate = new RotateAnimation(-100, 0, 0, treeHand.getHeight());
//                treeHandRotate.setStartOffset(1000);
                treeHandRotate.setDuration(1000);
                treeHandRotate.setInterpolator(new BounceInterpolator());
                treeHandRotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        treeHand.setVisibility(View.VISIBLE);
                        treeHandSound = treeHandSoundPool.load(getContext(), R.raw.effect_03_clouds, 0);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                leafTranslateAni = new TranslateAnimation(0, 0, 0, leaves.getHeight() * 0.2f);
                leafTranslateAni.setStartOffset(1200);
                leafTranslateAni.setDuration(3000);
//                leafTranslateAni.setInterpolator(new AccelerateDecelerateInterpolator());
                leafTranslateAni.setInterpolator(new AnticipateInterpolator());

                leafAniSet = new AnimationSet(false);
                leafAniSet.addAnimation(leafTranslateAni);
                leafAniSet.addAnimation(leafFadein);
                leafAniSet.addAnimation(leafFadeout);
//                leafAniSet.addAnimation(afterLeafFadeout);

                fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setDuration(1000);
                fadeIn.setFillAfter(true);

                fadeout = new AlphaAnimation(1, 0);
                fadeout.setStartOffset(100);
                fadeout.setDuration(500);
                fadeout.setFillAfter(true);

                animationClear();
                if (animationFlag == 0) {
//                    treeEyeRotate.cancel();
                    checkedAnimation = false;
                    byul.clearAnimation();
                    eyeBlack.clearAnimation();
                    eyeBlack.setVisibility(View.INVISIBLE);
                    animationFlag = 1;
                    plant.startAnimation(plantAnimation);
                    dokdo.startAnimation(dokdoAnimation);
                    seagull.startAnimation(seagullAnimation);
                    land.startAnimation(landAnimation);
                    byul.startAnimation(byulAnimation);
                    treeBody.startAnimation(treeAnimation);
                    eyeBlack.startAnimation(treeAnimation);
                    eyeWhite.startAnimation(treeAnimation);

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
            if (laughingSoundPool != null) {
                laughingSoundPool.release();
            }
            if (eyeSoundPool != null) {
                eyeSoundPool.release();
            }
            if(treeHandSoundPool != null) {
                treeHandSoundPool.release();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void returnMemory() {

        treeBody = null;
        treeHand = null;
        leaves = null;
        smile = null;
        eyeBlack = null;
        eyeWhite = null;
        plant = null;
        dokdo = null;
        land = null;
        seagull = null;
        byul = null;

        if(plantAnimation != null) plantAnimation.cancel();
        if(dokdoAnimation != null) dokdoAnimation.cancel();
        if(landAnimation != null) landAnimation.cancel();
        if(seagullAnimation != null) seagullAnimation.cancel();
        if(byulAnimation != null) byulAnimation.cancel();
        if(treeAnimation != null) treeAnimation.cancel();
        if(treeAnimation2 != null) treeAnimation2.cancel();
        if(leafTranslateAni != null) leafTranslateAni.cancel();
        if(treeEyeToByul != null) treeEyeToByul.cancel();
        if(treeEyeRotate != null) treeEyeRotate.cancel();
        if(treeHandRotate != null) treeHandRotate.cancel();
        if(blink != null) blink.cancel();
        if(leafFadein != null) leafFadein.cancel();
        if(leafFadeout != null) leafFadeout.cancel();
        if(afterLeafFadeout != null) afterLeafFadeout.cancel();
        if(fadeout != null) fadeout.cancel();
        if(fadeIn != null) fadeIn.cancel();
        if(leafAniSet != null) leafAniSet.cancel();
        if(eyeBlackAniSet != null) eyeBlackAniSet.cancel();

        plantAnimation = null;
        dokdoAnimation = null;
        landAnimation = null;
        seagullAnimation = null;
        byulAnimation = null;
        treeAnimation = null;
        treeAnimation2 = null;
        leafTranslateAni = null;
        treeEyeToByul = null;
        treeEyeRotate = null;
        treeHandRotate = null;
        blink = null;
        leafFadein = null;
        leafFadeout = null;
        afterLeafFadeout = null;
        fadeout = null;
        fadeIn = null;
        leafAniSet = null;
        eyeBlackAniSet = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        returnMemory();
    }
}
