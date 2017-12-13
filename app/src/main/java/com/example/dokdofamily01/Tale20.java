package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale20 extends BaseFragment {
    private CustomViewPager vp;

    ImageView man;
    ImageView dokdo_father;
    ImageView sqeed;
    ImageView dokdo_mom;
    ImageView wave;
    ImageView cutain;

    TranslateAnimation manAppearAnimation;
    TranslateAnimation dokdoFatherAppearAnimation;
    TranslateAnimation sqeedAppearAnimation;
    TranslateAnimation dokdoMomAppearAnimation;
    TranslateAnimation waveAppearAnimation;
    TranslateAnimation cutainDownAnimation1;
    TranslateAnimation cutainDownAnimation2;

    AnimationSet cutainDownAniSet = new AnimationSet(false);
    int animationFlag = 0;

    MediaPlayer mp = null;

    ArrayList<SubTitleData> subtitleList;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale20;
        subtitleTextView.setText(null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public void bindViews() {
        super.bindViews();
        man = (ImageView) layout.findViewById(R.id.man);
        dokdo_father = (ImageView) layout.findViewById(R.id.dokdo_father);
        sqeed = (ImageView) layout.findViewById(R.id.sqeed);
        dokdo_mom = (ImageView) layout.findViewById(R.id.dokdo_mom);
        wave = (ImageView) layout.findViewById(R.id.wave);
        cutain = (ImageView) layout.findViewById(R.id.cutain);
    }

    @Override
    public void setValues() {
        super.setValues();
        wave.post(new Runnable() {
            @Override
            public void run() {
                manAppearAnimation = new TranslateAnimation(0, 0, man.getHeight(), 0);
                manAppearAnimation.setDuration(1000);
                manAppearAnimation.setStartOffset(400);
                manAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                manAppearAnimation.setFillAfter(true);

                dokdoFatherAppearAnimation = new TranslateAnimation(0, 0, dokdo_father.getHeight(), 0);
                dokdoFatherAppearAnimation.setDuration(1000);
                dokdoFatherAppearAnimation.setStartOffset(700);
                dokdoFatherAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                dokdoFatherAppearAnimation.setFillAfter(true);

                sqeedAppearAnimation = new TranslateAnimation(0, 0, sqeed.getHeight(), 0);
                sqeedAppearAnimation.setDuration(1000);
                sqeedAppearAnimation.setStartOffset(200);
                sqeedAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                sqeedAppearAnimation.setFillAfter(true);

                dokdoMomAppearAnimation = new TranslateAnimation(0, 0, dokdo_mom.getHeight(), 0);
                dokdoMomAppearAnimation.setDuration(1000);
                dokdoMomAppearAnimation.setStartOffset(800);
                dokdoMomAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                dokdoMomAppearAnimation.setFillAfter(true);
                dokdoMomAppearAnimation.setAnimationListener(new MyAnimationListener());

                waveAppearAnimation = new TranslateAnimation(0, 0, wave.getHeight(), 0);
                waveAppearAnimation.setDuration(1000);
                waveAppearAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                waveAppearAnimation.setFillAfter(true);

                cutainDownAnimation1 = new TranslateAnimation(0, 0, 0, cutain.getHeight() * 0.02f);
                cutainDownAnimation1.setDuration(1000);
                cutainDownAnimation1.setInterpolator(new AnticipateInterpolator());
//                cutainDownAnimation1.setFillAfter(true);

                cutainDownAnimation2 = new TranslateAnimation(0, 0, 0, cutain.getHeight() * 0.4f);
                cutainDownAnimation2.setStartOffset(1000);
                cutainDownAnimation2.setDuration(20000);
                cutainDownAnimation2.setInterpolator(new DecelerateInterpolator());

//                cutainDownAnimation2.setFillAfter(true);

                cutainDownAniSet.addAnimation(cutainDownAnimation1);
                cutainDownAniSet.addAnimation(cutainDownAnimation2);
                cutainDownAniSet.setFillAfter(true);

                if (animationFlag == 0) {
                    animationFlag = 1;
                    cutain.clearAnimation();
                    man.startAnimation(manAppearAnimation);
                    dokdo_father.startAnimation(dokdoFatherAppearAnimation);
                    sqeed.startAnimation(sqeedAppearAnimation);
                    dokdo_mom.startAnimation(dokdoMomAppearAnimation);
                    wave.startAnimation(waveAppearAnimation);
                }
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        cutain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cutain.startAnimation(cutainDownAniSet);
            }
        });
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            animationFlag = 0;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

    }
    @Override
    public void soundPlayFunc() {
        musicController = new MusicController(getActivity(), R.raw.scene_20);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"별이를 태운 언제나 용감한 갈매기가 ", "3000"},
                new String[]{"달님이 꾸벅꾸벅 조는 \n" +
                        "새벽하늘을 씩씩하게 날아요. ", "8500"},
                new String[]{"갈매기야 나는 지금 보물섬 독도의 \n" +
                        "보물들을 만나러 가는 거야!", "15000"},
                new String[]{"별아 보물들을 찾은 거야?", "18000"},
                new String[]{"응! 찾은 것 같아!", "21000"},
                new String[]{"상상해보세요!", "24000"},
                new String[]{"별이는 보물섬 독도에서 \n" +
                        "어떤 보물들을 찾아냈을까요?", "29500"},
                new String[]{"반짝반짝~ 보물섬 독도에서 펼쳐지는 \n" +
                        "별이의 신나는 보물찾기가 ", "36500"},
                new String[]{"지금부터 신나게 시작됩니다...!", "40500"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();
        if (manAppearAnimation != null) {
            animationFlag = 1;
            cutain.clearAnimation();
            man.clearAnimation();
            dokdo_father.clearAnimation();
            dokdo_mom.clearAnimation();
            sqeed.clearAnimation();
            wave.clearAnimation();
            man.startAnimation(manAppearAnimation);
            dokdo_father.startAnimation(dokdoFatherAppearAnimation);
            sqeed.startAnimation(sqeedAppearAnimation);
            dokdo_mom.startAnimation(dokdoMomAppearAnimation);
            wave.startAnimation(waveAppearAnimation);
        }
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

