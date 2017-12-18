package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.MediaPlayer;

import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import android.view.MotionEvent;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;

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
    Animation fadeIn;
    AnimationSet leafAniSet = new AnimationSet(false);
    AnimationSet eyeBlackAniSet = new AnimationSet(false);
    int animationFlag = 0;

    MediaPlayer mp = null;


    ArrayList<SubTitleData> subtitleList;

    SoundPool laughingSoundPool, eyeSoundPool;
    int laughingSound;
    int eyeSound;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale08;

        subtitleList = new ArrayList<>();

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

        laughingSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        eyeSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);

        laughingSound = laughingSoundPool.load(getContext(), R.raw.effect_08_laughing, 0);
        eyeSound = eyeSoundPool.load(getContext(), R.raw.effect_08_eyesound, 0);
    }

    @Override
    public void setValues() {
        super.setValues();

    }

    @Override
    public void setAnimation() {
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
        afterLeafFadeout.setStartOffset(1500);
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

        super.setAnimation();
        blink = new AlphaAnimation(1, 0.3f);
        blink.setDuration(500);
        blink.setInterpolator(new LinearInterpolator());
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        byul.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                CustomViewPager.isPageScrollEnabled = true;

                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        if (animationFlag == 0) {
                            animationFlag = 1;
//                    eyeBlack.clearAnimation();
                            checkedAnimation = false;
                            byul.clearAnimation();
                            treeHand.setVisibility(View.VISIBLE);
                            leaves.setVisibility(View.VISIBLE);
                            treeHand.startAnimation(treeHandRotate);
                            leaves.startAnimation(leafAniSet);
                            eyeBlack.startAnimation(treeEyeToByul);
                            smile.startAnimation(fadeIn);
                            laughingSoundPool.play(laughingSound, 1, 1, 1, 0, 1);
                            eyeSoundPool.play(eyeSound, 1, 1, 1, 0, 1);
                        }
                        break;

                    case MotionEvent.ACTION_MOVE:
                        break;
                }

                return false;

            }
        });
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
        musicController = new MusicController(getActivity(), R.raw.scene_8);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"별이랑 언제나 든든한 사철나무 아빠가 도란도란 얘기해요.", "6000"},
                new String[]{"사철나무 아빠는 왜 자꾸 두리번두리번 해요?", "12000"},
                new String[]{"나는 가족을 지키는 아빠니까 이렇게 잘 살펴봐야 한단다. ", "18500"},
                new String[]{"뾰족한 절벽에서 살면 엉덩이가 안 아파요?", "24500"},
                new String[]{"100년도 넘어서 이제 아무렇지도 않단다.", "29000"},
                new String[]{"100년? 그럼 사철나무 할아버지예요?", "35000"},
                new String[]{"하하하~ 우리 독도에는 아~주아~주 먼 옛날에 \n" +
                        "태어나신 서도할아버지가 계신 걸~ ", "43500"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();

        land.post(new Runnable() {
            @Override
            public void run() {
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
                landAnimation.setAnimationListener(new Animation.AnimationListener(){
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


                byulAnimation = new TranslateAnimation(byul.getWidth()*1.5f, 0, -byul.getHeight(), 0);
                byulAnimation.setStartOffset(1000);
                byulAnimation.setDuration(700);
                byulAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                byulAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {

                        if(animationFlag == 1) {
//                            Log.d("ababab", "aaaa");
                            byul.startAnimation(blink);
                            eyeBlack.startAnimation(treeEyeRotate);
                            animationFlag = 0;
                            checkedAnimation = true;

                        }
                        else{

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


                treeAnimation = new TranslateAnimation((int) (treeBody.getWidth() * 0.3), 0, treeBody.getHeight()*1.5f, 0);
                treeAnimation.setStartOffset(800);
                treeAnimation.setDuration(800);
                treeAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

//                treeAnimation2 = new TranslateAnimation((int) (treeBody.getWidth() * 0.3), 0, treeBody.getHeight()*1.5f, 0);
//                treeAnimation2.setStartOffset(800);
//                treeAnimation2.setDuration(800);
//                treeAnimation2.setInterpolator(new AccelerateDecelerateInterpolator());
//                treeAnimation2.setAnimationListener(new Animation.AnimationListener(){
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
////                        eyeBlack.setVisibility(View.INVISIBLE);
////                        eyeBlack.startAnimation(treeEyeRotate);
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//                    }
//
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//                        eyeBlack.clearAnimation();
////                        eyeBlack.setVisibility(View.INVISIBLE);
//                    }
//                });

                eyeBlackAniSet.addAnimation(treeAnimation);
                eyeBlackAniSet.addAnimation(treeEyeRotate);

                eyeBlackAniSet.setAnimationListener(new AnimationSet.AnimationListener(){
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

                treeHandRotate = new RotateAnimation(-100, 0, 0, treeHand.getHeight());
                treeHandRotate.setStartOffset(1000);
                treeHandRotate.setDuration(1000);
//                treeHandRotate.setInterpolator(new AccelerateDecelerateInterpolator());
//                treeHandRotate.setInterpolator(new AnticipateOvershootInterpolator());
                treeHandRotate.setInterpolator(new BounceInterpolator());

                leafTranslateAni = new TranslateAnimation(0, 0, 0, leaves.getHeight() * 0.2f);
                leafTranslateAni.setStartOffset(1200);
                leafTranslateAni.setDuration(3000);
//                leafTranslateAni.setInterpolator(new AccelerateDecelerateInterpolator());
                leafTranslateAni.setInterpolator(new AnticipateInterpolator());

                leafAniSet.addAnimation(leafTranslateAni);
                leafAniSet.addAnimation(leafFadein);
                leafAniSet.addAnimation(leafFadeout);
//                leafAniSet.addAnimation(afterLeafFadeout);

                fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
                fadeIn.setFillAfter(true);

                fadeout = new AlphaAnimation(1, 0);
                fadeout.setStartOffset(1000);
                fadeout.setDuration(1000);
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
//        animationClear();
//        if (plantAnimation != null) {
////            treeEyeRotate.cancel();
//            checkedAnimation = false;
//            byul.clearAnimation();
//            eyeBlack.clearAnimation();
//            eyeBlack.setVisibility(View.INVISIBLE);
//            animationFlag = 1;
//            plant.startAnimation(plantAnimation);
//            dokdo.startAnimation(dokdoAnimation);
//            seagull.startAnimation(seagullAnimation);
//            land.startAnimation(landAnimation);
//            byul.startAnimation(byulAnimation);
//            treeBody.startAnimation(treeAnimation);
//            eyeBlack.startAnimation(treeAnimation);
//            eyeWhite.startAnimation(treeAnimation);
//        }
//        else{
//            setValues();
////            treeEyeRotate.cancel();
////            byulAnimation.cancel();
//        }
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
