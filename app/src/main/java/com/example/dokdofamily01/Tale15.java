package com.example.dokdofamily01;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;
import com.ssomai.android.scalablelayout.ScalableLayout;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.homeKeyFlag;
import static com.example.dokdofamily01.TaleActivity.screenFlag;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale15 extends BaseFragment {

    boolean isAttached = false;
    boolean isHint;
    MediaPlayer mp = null;
    MusicController musicController;

    ArrayList<SubTitleData> subtitleList;

    private android.widget.ImageView ivCave15;
    private android.widget.ImageView manImage1;
    private android.widget.ImageView manImage2;
    private android.widget.ImageView manImage3;
    private android.widget.ImageView manImage4;
    private android.widget.ImageView ivByul15;
    private android.widget.ImageView seaweadImage;
    private android.widget.ImageView fish;
    private com.ssomai.android.scalablelayout.ScalableLayout sl;
    private CustomScrollView sv;
    private ImageView ivLand15;

    int animationFlag = 0;
    int appearFlag=0;

    Animation fadeIn;
    Animation fadeOut;
    AlphaAnimation blink;
    TranslateAnimation landAnimation, byulAnimation, caveAnimation;

    SoundPool sp;
    int clickFish;
    int moveMan;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isAttached = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        isHint = isVisibleToUser;
        super.setUserVisibleHint(isVisibleToUser);
        if(isAttached ){
            if (isVisibleToUser) {
                System.out.println("PlayByHint");
                soundPlayFunc();
            } else {
                if (musicController != null) {
                    musicController.getMp().release();
                }
            }
        }
    }

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
    public void onResume() {
        if (isHint && !homeKeyFlag && screenFlag) {
            soundPlayFunc();
        }
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (musicController != null) {
            musicController.getMp().release();
            musicController = null;
        }
    }

    @Override
    public void setValues() {
        super.setValues();
        sl.post(new Runnable() {
            @Override
            public void run() {

                landAnimation = new TranslateAnimation(0, 0, ivLand15.getHeight(), 0);
                landAnimation.setDuration(2000);
                landAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                caveAnimation = new TranslateAnimation(ivCave15.getWidth(), 0, 0, 0);
                caveAnimation.setStartOffset(500);
                caveAnimation.setDuration(2500);
                caveAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

                byulAnimation = new TranslateAnimation(-ivByul15.getWidth()*1.5f, 0, 0, 0);
                byulAnimation.setStartOffset(1000);
                byulAnimation.setDuration(2500);
                byulAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                byulAnimation.setAnimationListener(new MyAnimationListener());

                fadeIn = AnimationUtils.loadAnimation(getContext(),R.anim.anim_15_fadein);
                fadeIn.setFillAfter(true);
                fadeIn.setAnimationListener(new MyAnimationListener());

                fadeOut = AnimationUtils.loadAnimation(getContext(),R.anim.anim_15_fadeout);
                fadeOut.setFillAfter(true);

                blink = new AlphaAnimation(0.3f, 1);
                blink.setDuration(500);
                blink.setRepeatCount(Animation.INFINITE);
                blink.setRepeatMode(Animation.REVERSE);

                if(landAnimation!=null) {
                    appearFlag=1;
                    animationClear();
                    ivLand15.startAnimation(landAnimation);
                    seaweadImage.startAnimation(landAnimation);
                    ivCave15.startAnimation(caveAnimation);
                    ivByul15.startAnimation(byulAnimation);
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
        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(animationFlag == 0 && appearFlag==0){
                    animationFlag = 1;
                    fish.clearAnimation();
                    fish.setVisibility(View.VISIBLE);
                    //man1 사라지고 man2나온다.
                    sp.play(clickFish,1,1,0,0,1);
                    manImage4.setVisibility(View.INVISIBLE);
                    manImage1.startAnimation(fadeIn);
                }
            }
        });
    }

    private class MyAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            switch (animationFlag){
                case 1 :
                    manImage1.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    manImage2.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    manImage3.setVisibility(View.VISIBLE);
                    break;
                case 7:
                    manImage4.setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag){
                case 1:
                    // man2 사라지고 man3나온다.
                    animationFlag=2;
                    manImage1.clearAnimation();
                    break;
                case 2:
                    animationFlag=3;
                    manImage1.startAnimation(fadeOut);
                    manImage2.startAnimation(fadeIn);
                    break;
                case 3 :
                    // man3 사라지고 man4 나온다.
                    animationFlag=4;
                    manImage1.setVisibility(View.INVISIBLE);
                    manImage1.clearAnimation();
                    manImage2.clearAnimation();
                    break;
                case 4:
                    animationFlag=5;
                    manImage2.startAnimation(fadeOut);
                    manImage3.startAnimation(fadeIn);
                    break;
                case 5:
                    animationFlag=6;
                    manImage2.setVisibility(View.INVISIBLE);
                    manImage2.clearAnimation();
                    manImage3.clearAnimation();
                    break;
                case 6:
                    animationFlag=7;
                    sp.play(moveMan,1,1,0,0,1);
                    manImage3.startAnimation(fadeOut);
                    manImage4.startAnimation(fadeIn);
                    break;
                case 7:
                    animationFlag=0;
                    manImage3.setVisibility(View.INVISIBLE);
                    manImage3.clearAnimation();
                    manImage4.clearAnimation();
                    break;
            }
            if(appearFlag==1){
                appearFlag=0;
                fish.setVisibility(View.VISIBLE);
                fish.startAnimation(blink);
            }

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
        sp = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        clickFish = sp.load(getContext(),R.raw.effect_15_fish,1);
        moveMan = sp.load(getContext(),R.raw.effect_15_man,2);

    }

    public void animationClear(){
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

    public void soundPlayFunc(){
        musicController = new MusicController(getActivity(), R.raw.scene_15);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"딸깍~ 대문이 열리고 ", "2500"},
                new String[]{"이마에 왕혹을 매달은 언제나 위풍당당한 \n" +
                        "혹돔 삼촌이 성큼성큼 나와요.", "10500"},
                new String[]{"별아 깊은 바다 속 우리 집까지 와줘서 진짜 고마워!", "16500"},
                new String[]{"혹돔 삼촌 목소리가 얼마나 우렁찬지 \n" +
                        "깊고 깊은 동해바다 맨 아래까지 쩌렁쩌렁 울려요.", "27000"},
                new String[]{"깜깜한 밤인데 혹돔 삼촌 집은 왜 불을 안 켜요? ", "34000"},
                new String[]{"그건 깜깜해야 소리를 잘 들을 수 있기 때문이지!", "39000"},
                new String[]{"어떤 소리요?", "41000"},
                new String[]{"그야 우리 보물섬 독도 가족들의 소리지~", "46500"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();
        if(landAnimation!=null) {
            animationClear();
            ivLand15.startAnimation(landAnimation);
            ivCave15.startAnimation(caveAnimation);
            ivByul15.startAnimation(byulAnimation);
            seaweadImage.startAnimation(landAnimation);
        }
    }

}
