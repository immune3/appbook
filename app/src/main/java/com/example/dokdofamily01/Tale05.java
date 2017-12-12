package com.example.dokdofamily01;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale05 extends BaseFragment {
    ImageView[] letter = new ImageView[6];
    Animation letterAppear;
    Animation letterDisappear;
    int animationFlag = 0;

    MediaPlayer mp = null;

    ArrayList<SubTitleData> subtitleList;

    SoundPool sp;
    int moveLetters;
    int clickLetter;



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
        letter[0] = (ImageView) layout.findViewById(R.id.letter0);
        letter[1] = (ImageView) layout.findViewById(R.id.letter1);
        letter[2] = (ImageView) layout.findViewById(R.id.letter2);
        letter[3] = (ImageView) layout.findViewById(R.id.letter3);
        letter[4] = (ImageView) layout.findViewById(R.id.letter4);
        letter[5] = (ImageView) layout.findViewById(R.id.letter5);

        sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        moveLetters = sp.load(getContext(), R.raw.effect_05_move_letters, 2);
        clickLetter = sp.load(getContext(), R.raw.effect_05_click_letter, 1);
    }

    @Override
    public void setValues() {
        super.setValues();
        letter[5].post(new Runnable() {
            @Override
            public void run() {
//                letterAnimation.setAnimationListener(new MyAnimationListener());
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();

        letterAppear = AnimationUtils.loadAnimation(getContext(), R.anim.anim_05_letter_appear);
        letterAppear.setFillAfter(true);
        letterAppear.setAnimationListener(new MyAnimationListener());


        letterDisappear = AnimationUtils.loadAnimation(getContext(), R.anim.anim_05_letter_disappear);
        letterDisappear.setFillAfter(true);

    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        letter[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (animationFlag == 0) {
                    animationFlag = 1;
                    sp.play(clickLetter, 1, 1, 0, 0, 1);
                    // letter[0] 사라지고 letter[1]나온다.
                    letter[5].setVisibility(View.INVISIBLE);
//                    letter[0].startAnimation(letterDisappear);
                    letter[1].startAnimation(letterAppear);
                } else {
                    sp.play(clickLetter, 1, 1, 0, 0, 1);
                }
            }
        });
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            switch (animationFlag) {
                case 1:
                    sp.play(moveLetters, 1, 1, 0, 0, 1);
                    letter[1].setVisibility(View.VISIBLE);
                    break;
                case 2:
                    letter[2].setVisibility(View.VISIBLE);
                    break;
                case 4:
                    letter[3].setVisibility(View.VISIBLE);
                    break;
                case 6:
                    letter[4].setVisibility(View.VISIBLE);
                    break;
                case 8:
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
                    animationFlag = 2;
//                    letter[0].setVisibility(View.INVISIBLE);
//                    letter[0].clearAnimation();
                    letter[1].clearAnimation();
                    break;
                case 2:
                    // letter[1] 사라지고 letter[2]나온다.
                    animationFlag = 3;
//                    letter[1].startAnimation(letterDisappear);
                    sp.play(moveLetters, 1, 1, 0, 0, 1);
                    letter[2].startAnimation(letterAppear);
                    break;
                case 3:
                    animationFlag = 4;
//                    letter[1].setVisibility(View.INVISIBLE);
//                    letter[1].clearAnimation();
                    letter[2].clearAnimation();
                    letter[2].setVisibility(View.VISIBLE);
                    break;
                case 4:
                    // letter[2] 사라지고 letter[3]나온다.
                    animationFlag = 5;
//                    letter[2].startAnimation(letterDisappear);
                    sp.play(moveLetters, 1, 1, 0, 0, 1);
                    letter[3].startAnimation(letterAppear);
                    break;
                case 5:
                    animationFlag = 6;
//                    letter[2].setVisibility(View.INVISIBLE);
//                    letter[2].clearAnimation();
                    letter[3].clearAnimation();
                    letter[3].setVisibility(View.VISIBLE);
                    break;
                case 6:
                    // letter[3] 사라지고 letter[4]나온다.
                    animationFlag = 7;
//                    letter[3].startAnimation(letterDisappear);
                    sp.play(moveLetters, 1, 1, 0, 0, 1);
                    letter[4].startAnimation(letterAppear);
                    break;
                case 7:
                    animationFlag = 8;
//                    letter[3].setVisibility(View.INVISIBLE);
//                    letter[3].clearAnimation();
                    letter[4].clearAnimation();
                    letter[4].setVisibility(View.VISIBLE);
                    break;
                case 8:
                    // letter[4] 사라지고 letter[5]나온다.
                    animationFlag = 9;
//                    letter[4].startAnimation(letterDisappear);
                    sp.play(moveLetters, 1, 1, 0, 0, 1);
                    letter[5].startAnimation(letterAppear);
                    break;
                case 9:
                    animationFlag = 10;
//                    letter[4].setVisibility(View.INVISIBLE);
//                    letter[4].clearAnimation();
                    letter[5].setVisibility(View.VISIBLE);
                    letter[5].clearAnimation();
                    break;
            }
        }


    }


    public void soundPlayFunc() {
        musicController = new MusicController(getActivity(), R.raw.scene_5);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"덜컹덜컹 쿵쿵쿵~ ", "2500"},
                new String[]{"언제나 상냥한 빨간 우체통 엄마가 호들갑스럽게 달려와 별이를 덥석 끌어안아요.", "11000"},
                new String[]{"어서 오렴~ 나는 편지를 아주 재미나게 읽어주는 빨간 우체통 엄마란다.", "17500"},
                new String[]{"별이의 편지도 내가 읽어주었지~ 호호호~ ", "21700"},
                new String[]{"독도를 생각하는 별이의 마음이 어찌나 예쁘던지... 쪽~ 쪽~ 쪼오옥~ ", "30000"},
                new String[]{"아줌마 너무 간지러워요. 까르르~", "40000"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();

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


