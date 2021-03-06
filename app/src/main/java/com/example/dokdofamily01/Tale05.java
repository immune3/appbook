package com.example.dokdofamily01;


import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale05 extends BaseFragment {
    ImageView[] letter;
    AlphaAnimation letterAppear;
    //    Animation letterDisappear;
    int animationFlag = 0;

    SoundPool sp;
    int soundID;
    Boolean isAuto;

    AlphaAnimation blink;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale05;

        subtitleTextView.setText(null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void bindViews() {
        super.bindViews();

        letter = new ImageView[6];

        letter[0] = (ImageView) layout.findViewById(R.id.letter0);
        letter[1] = (ImageView) layout.findViewById(R.id.letter1);
        letter[2] = (ImageView) layout.findViewById(R.id.letter2);
        letter[3] = (ImageView) layout.findViewById(R.id.letter3);
        letter[4] = (ImageView) layout.findViewById(R.id.letter4);
        letter[5] = (ImageView) layout.findViewById(R.id.letter5);
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

        letter[0].setOnTouchListener(new BlockObjListener());
    }

    @Override
    public void blockAnimFunc() {
        if (animationFlag == 0) {
            animationFlag = 1;
            checkedAnimation = false;
            soundID = sp.load(getContext(), R.raw.effect_05_click_letter, 1);
            // letter[0] 사라지고 letter[1]나온다.
            letter[5].setVisibility(View.INVISIBLE);
//                    letter[0].startAnimation(letterDisappear);
            letter[1].startAnimation(letterAppear);
        } else {
            soundID = sp.load(getContext(), R.raw.effect_05_click_letter, 1);
        }

        super.blockAnimFunc();
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            switch (animationFlag) {
                case 1:
//                    soundsID = soundsPool.load(getContext(), R.raw.effect_05_move_letters, 1);
                    letter[1].setVisibility(View.VISIBLE);
                    break;
                case 2:
                    letter[2].setVisibility(View.VISIBLE);
                    break;
                case 3:
                    letter[3].setVisibility(View.VISIBLE);
                    break;
                case 4:
                    letter[4].setVisibility(View.VISIBLE);
                    break;
                case 5:
                    letter[5].setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }


        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag) {
                case 0:
                    break;
                case 1:
                    letter[0].clearAnimation();
                    letter[1].clearAnimation();
                    animationFlag = 2;
                    soundID = sp.load(getContext(), R.raw.effect_05_move_letters, 2);
                    letter[2].startAnimation(letterAppear);
                    break;
                case 2:
                    letter[2].clearAnimation();
                    animationFlag = 3;
                    soundID = sp.load(getContext(), R.raw.effect_05_move_letters, 2);
                    letter[3].startAnimation(letterAppear);
                    break;
                case 3:
                    letter[3].clearAnimation();
                    animationFlag = 4;
                    soundID = sp.load(getContext(), R.raw.effect_05_move_letters, 2);
                    letter[4].startAnimation(letterAppear);
                    break;
                case 4:
                    letter[4].clearAnimation();
                    animationFlag = 5;
                    soundID = sp.load(getContext(), R.raw.effect_05_move_letters, 2);
                    letter[5].startAnimation(letterAppear);
                    break;
                case 5:
                    letter[5].clearAnimation();
                    animationFlag = 10;
                    soundID = sp.load(getContext(), R.raw.effect_05_move_letters, 2);
                    checkedAnimation = true;
                    break;

            }
        }

    }


    public void soundPlayFunc() {
        this.isAuto = getArguments().getBoolean("isAuto");

        checkedAnimation = false;

        if (isAuto) {
            musicController = new MusicController(((TaleActivity) getActivity()), R.raw.scene_5, vp,
                    new int[]{R.drawable.sub_05_01, 2500},
                    new int[]{R.drawable.sub_05_02, 11000},
                    new int[]{R.drawable.sub_05_03, 17500},
                    new int[]{R.drawable.sub_05_04, 21700},
                    new int[]{R.drawable.sub_05_05, 30000},
                    new int[]{R.drawable.sub_05_06, 99999});

        } else {
            subtitleController = new SubtitleController(((TaleActivity) getActivity()), vp,
                    R.drawable.sub_05_01,
                    R.drawable.sub_05_02,
                    R.drawable.sub_05_03,
                    R.drawable.sub_05_04,
                    R.drawable.sub_05_05,
                    R.drawable.sub_05_06);
        }

        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                sp.play(soundID, 1, 1, 1, 0, 1);
            }
        });
        animationFlag = 0;
        letter[1].setVisibility(View.INVISIBLE);
        letter[2].setVisibility(View.INVISIBLE);
        letter[3].setVisibility(View.INVISIBLE);
        letter[4].setVisibility(View.INVISIBLE);
        letter[5].setVisibility(View.INVISIBLE);
        letter[1].clearAnimation();
        letter[2].clearAnimation();
        letter[3].clearAnimation();
        letter[4].clearAnimation();
        letter[5].clearAnimation();

        letter[0].clearAnimation();

        letter[0].post(new Runnable() {
            @Override
            public void run() {
                letterAppear = new AlphaAnimation(0, 1);
                letterAppear.setDuration(700);
                letterAppear.setFillAfter(true);
                letterAppear.setAnimationListener(new MyAnimationListener());

                blink = new AlphaAnimation(1, 0.3f);
                blink.setDuration(500);
                blink.setRepeatCount(Animation.INFINITE);
                blink.setRepeatMode(Animation.REVERSE);

                letter[0].startAnimation(blink);
                checkedAnimation = true;
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
            if (sp != null) {
                sp.release();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void returnMemory() {
        letter[0].clearAnimation();
        letter[1].clearAnimation();
        letter[2].clearAnimation();
        letter[3].clearAnimation();
        letter[4].clearAnimation();
        letter[5].clearAnimation();

        letter = null;

        if (blink != null) blink.cancel();
        if (letterAppear != null) letterAppear.cancel();

        blink = null;
        letterAppear = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        returnMemory();
    }
}


