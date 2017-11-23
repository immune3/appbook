package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale15 extends BaseFragment {

    boolean isAttached = false;
    MediaPlayer mp = null;

    ArrayList<SubTitleData> subtitleList;

    ImageView manImage1;
    ImageView manImage2;
    ImageView manImage3;
    ImageView manImage4;
    ImageView fishImg;

    int animationFlag = 0;

    Animation fadeIn;
    Animation fadeOut;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isAttached = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isAttached) {
            if (isVisibleToUser) {
//                System.out.println(32+"Visible");
                if (mp == null) {
                    mp = MediaPlayer.create(getActivity(), R.raw.scene_15);
                }

                mp.start();

                Timer timer = new Timer();
                timer.schedule(new MyThread(), 0, 500);

            } else {
//                System.out.println(2+"notVisible");
                if (mp != null && mp.isPlaying()) {
                    mp.pause();
                    mp.stop();
                    mp.release();
                    mp = null;
                }

            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        System.out.println(2+"onDestroyView");
        if (mp != null && mp.isPlaying()) {
            mp.pause();
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    private ArrayList<SubTitleData> makeSubTitleList(String[]... params) {
        ArrayList<SubTitleData> list = new ArrayList<>();

        for (String[] s : params) {
            SubTitleData subTitleData = new SubTitleData(
                    s[0], Integer.parseInt(s[1])
            );
            list.add(subTitleData);
        }

        return list;
    }


    class MyThread extends TimerTask {
        int finishTime = 0;
        int subtitleIndex = 0;

        @Override
        public void run() {
            if (mp != null && mp.isPlaying()) {

                int playingTime = mp.getCurrentPosition();
                Message msg = new Message();

                finishTime = subtitleList.get(subtitleIndex).getFinishTime();

                if (playingTime <= finishTime) {
                    msg.what = subtitleIndex;
                } else {
                    increaseIndex();
                    if (playingTime > finishTime) {
                        increaseIndex();
                    } else {
                        msg.what = subtitleIndex;
                    }
                }
                mHandler.sendMessage(msg);

            } else {
                Message msg = new Message();
                msg.what = -1;
                mHandler.sendMessage(msg);
                cancel();
            }
        }

        private void increaseIndex() {
            subtitleIndex++;
            try {
                finishTime = subtitleList.get(subtitleIndex).getFinishTime();
            } catch (IndexOutOfBoundsException e) {
//                finishTime = subtitleList.get(subtitleIndex-1).getFinishTime();
            }

        }
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            if (msg.what >= 0)
                subtitleTextView.setText(subtitleList.get(msg.what).getSubTitle());
            else
                subtitleTextView.setText(null);


        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale15;

        subtitleList = new ArrayList<>();
        subtitleList = makeSubTitleList(
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

        subtitleTextView.setText(null);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();

        manImage1 = (ImageView) layout.findViewById(R.id.manImage1);
        manImage2 = (ImageView) layout.findViewById(R.id.manImage2);
        manImage3 = (ImageView) layout.findViewById(R.id.manImage3);
        manImage4 = (ImageView) layout.findViewById(R.id.manImage4);
        fishImg = (ImageView) layout.findViewById(R.id.fish);

    }

    @Override
    public void setValues() {
        super.setValues();
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadeIn = AnimationUtils.loadAnimation(getContext(),R.anim.anim_15_fadein);
        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new MyAnimationListener());

        fadeOut = AnimationUtils.loadAnimation(getContext(),R.anim.anim_15_fadeout);
        fadeOut.setFillAfter(true);
//        fadeOut.setAnimationListener(new MyAnimationListener());
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        fishImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(animationFlag == 0){
                    animationFlag = 1;
                    //man1 사라지고 man2나온다.
                    manImage4.setVisibility(View.INVISIBLE);
                    manImage1.startAnimation(fadeOut);
                    manImage2.startAnimation(fadeIn);

                }else{
                    Toast.makeText(getContext(),"TEST",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private class MyAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            switch (animationFlag){
                case 1 :
                    manImage2.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    manImage3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    manImage4.setVisibility(View.VISIBLE);
                    break;



            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag){
                case 0 :
                    break;
                case 1:
                    // man2 사라지고 man3나온다.
                    animationFlag=2;
                    manImage1.setVisibility(View.INVISIBLE);
                    manImage2.clearAnimation();
                    manImage1.clearAnimation();
                    break;
                case 2:
                    animationFlag=3;
                    manImage2.startAnimation(fadeOut);
                    manImage3.startAnimation(fadeIn);
                    break;
                case 3 :
                    // man3 사라지고 man4 나온다.
                    animationFlag=4;
                    manImage2.setVisibility(View.INVISIBLE);
                    manImage2.clearAnimation();
                    manImage3.clearAnimation();
                    break;
                case 4:
                    animationFlag=5;
                    manImage3.startAnimation(fadeOut);
                    manImage4.startAnimation(fadeIn);
                    break;
                case 5:
                    animationFlag=0;
                    manImage3.setVisibility(View.INVISIBLE);
                    manImage3.clearAnimation();
                    manImage4.setVisibility(View.VISIBLE);
                    manImage4.clearAnimation();
                    break;
            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
