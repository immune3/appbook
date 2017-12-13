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
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale04 extends BaseFragment {
    ImageView dokdo;
    ImageView sun;
    ImageView sunLight;
    TranslateAnimation sunRiseAni;
    Animation sunLightAppear;
    int animationFlag = 0;

    int[] sunLightLocation = new int[2];


    MediaPlayer mp = null;

    ArrayList<SubTitleData> subtitleList;

    SoundPool sp;
    int soundID;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale04;

        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }




    @Override
    public void bindViews() {
        super.bindViews();
        dokdo = (ImageView) layout.findViewById(R.id.dokdo);
        sun = (ImageView) layout.findViewById(R.id.sun);
        sunLight = (ImageView) layout.findViewById(R.id.sunLight);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sp.load(getContext(), R.raw.effect_04_sun, 1);
    }

    @Override
    public void setValues() {
        super.setValues();
        dokdo.post(new Runnable() {
            @Override
            public void run() {
                sunLight.getLocationOnScreen(sunLightLocation);

                sun.setY(sunLightLocation[1]);
//                Log.d("SunLightLocation:", "LocationX"+sunLightLocation[0]);
//                Log.d("SunLightLocation:", "LocationY"+sunLightLocation[1]);

                sunRiseAni = new TranslateAnimation(0, 0, 0, -(sun.getHeight() / 2));
                sunRiseAni.setDuration(3000);
                sunRiseAni.setFillAfter(true);
                sunRiseAni.setAnimationListener(new MyAnimationListener());

                animationFlag=0;
                sun.clearAnimation();
                sunLight.clearAnimation();
                sunLight.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        sunLightAppear = AnimationUtils.loadAnimation(getContext(), R.anim.anim_04_sunlight_appear);
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        dokdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(animationFlag==0){
                    sp.play(soundID, 1, 1, 0, 0, 1);
                    sun.startAnimation(sunRiseAni);
                }
            }
        });
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            if (animationFlag == 0) {
                animationFlag = 1;
                sunLight.setVisibility(View.VISIBLE);
                sunLight.startAnimation(sunLightAppear);
            }
        }

    }

    @Override
    public void soundPlayFunc() {
        musicController = new MusicController(getActivity(), R.raw.scene_4);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"저기~ 해님이 앉아있는 섬이 보물섬 독도야.", "5000"},
                new String[]{"정답게 마주앉은 동도할머니와 서도할아버지 사이에서", "10500"},
                new String[]{"잘 자고 일어난 뽀얀 얼굴의 해님이 쭉쭉 기지개를 켜요. ", "16000"},
                new String[]{"쌩~ 지나가던 바람이 되돌아와 아주아주 큰소리로 외쳐요", "22500"},
                new String[]{"별이가 왔어요! 별이가 왔다구요!", "26500"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();

        animationFlag=0;
        sun.clearAnimation();
        sunLight.clearAnimation();
        sunLight.setVisibility(View.INVISIBLE);
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





