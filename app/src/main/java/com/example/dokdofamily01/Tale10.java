package com.example.dokdofamily01;

import android.media.MediaPlayer;
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

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.homeKeyFlag;
import static com.example.dokdofamily01.TaleActivity.screenFlag;
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
    ImageView bird[][] = new ImageView[3][5];

    TranslateAnimation mountainAppear;
    TranslateAnimation rockAppear;
    TranslateAnimation birdsAppear;
    Animation fadeIn;
    AlphaAnimation blink;
    AlphaAnimation repeat;
    int animationFlag=0;
    int repeatFlag=0;

    boolean isAttached = false;
    boolean isHint;
    MediaPlayer mp = null;
    private MusicController musicController;

    ArrayList<SubTitleData> subtitleList;



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
        xml = R.layout.tale10;
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
    public void bindViews() {
        super.bindViews();
        birds = (ImageView)layout.findViewById(R.id.birds);
        mountain = (ImageView)layout.findViewById(R.id.mountain);
        rock = (ImageView)layout.findViewById(R.id.rock);
        seagull = (ImageView)layout.findViewById(R.id.seagull);
        byulHead = (ImageView)layout.findViewById(R.id.byulHead);
        byulBody = (ImageView)layout.findViewById(R.id.byulBody);
        byulHand = (ImageView)layout.findViewById(R.id.byulHand);
        blinkBird = (ImageView)layout.findViewById(R.id.blinkBird);
        bird[0][0] = (ImageView)layout.findViewById(R.id.bird00);
        bird[0][1] = (ImageView)layout.findViewById(R.id.bird01);
        bird[0][2] = (ImageView)layout.findViewById(R.id.bird02);
        bird[0][3] = (ImageView)layout.findViewById(R.id.bird03);
        bird[0][4] = (ImageView)layout.findViewById(R.id.bird04);
        bird[1][0] = (ImageView)layout.findViewById(R.id.bird10);
        bird[1][1] = (ImageView)layout.findViewById(R.id.bird11);
        bird[1][2] = (ImageView)layout.findViewById(R.id.bird12);
        bird[1][3] = (ImageView)layout.findViewById(R.id.bird13);
        bird[1][4] = (ImageView)layout.findViewById(R.id.bird14);
        bird[2][0] = (ImageView)layout.findViewById(R.id.bird20);
        bird[2][1] = (ImageView)layout.findViewById(R.id.bird21);
        bird[2][2] = (ImageView)layout.findViewById(R.id.bird22);
        bird[2][3] = (ImageView)layout.findViewById(R.id.bird23);
        bird[2][4] = (ImageView)layout.findViewById(R.id.bird24);
//        for(int i=0; i<3; i++){
//            for (int j=0; j<5; j++){
//                int aa = "R.id.bird"+i+""+j;
//                bird[i][j] = (ImageView)layout.findViewById(aa);
//            }
//        }
    }

    @Override
    public void setValues() {
        super.setValues();
        mountain.post(new Runnable() {
            @Override
            public void run() {
                mountainAppear = new TranslateAnimation(0,0,mountain.getHeight(),0);
                mountainAppear.setDuration(2000);
                mountainAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                mountainAppear.setAnimationListener(new MyAnimationListener());

                birdsAppear = new TranslateAnimation(0,0,birds.getHeight(),0);
                birdsAppear.setDuration(1000);
                birdsAppear.setInterpolator(new AccelerateDecelerateInterpolator());
                birdsAppear.setAnimationListener(new MyAnimationListener());

                rockAppear = new TranslateAnimation(-rock.getWidth(),0,0,0);
                rockAppear.setStartOffset(500);
                rockAppear.setDuration(1500);
                rockAppear.setInterpolator(new AccelerateDecelerateInterpolator());

                if(animationFlag==0){
                    animationFlag=1;
                    mountain.startAnimation(mountainAppear);
                    rock.startAnimation(rockAppear);
                }
            }
        });
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeIn.setDuration(1000);
        fadeIn.setAnimationListener(new MyAnimationListener());

        blink = new AlphaAnimation(0.3f,1);
        blink.setDuration(500);
        blink.setRepeatCount(Animation.INFINITE);
        blink.setRepeatMode(Animation.REVERSE);
//        blink.setAnimationListener(new MyAnimationListener());

        repeat= new AlphaAnimation(1,1);
        repeat.setDuration(500);
        repeat.setRepeatCount(Animation.INFINITE);
        repeat.setRepeatMode(Animation.REVERSE);
        repeat.setAnimationListener(new MyAnimationListener());
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        blinkBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blinkBird.clearAnimation();
                blinkBird.startAnimation(repeat);
            }
        });
    }

    private class MyAnimationListener extends com.example.dokdofamily01.MyAnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {
            super.onAnimationStart(animation);
            switch (animationFlag){
                case 1:
                    byulHead.setVisibility(View.INVISIBLE);
                    byulHand.setVisibility(View.INVISIBLE);
                    byulBody.setVisibility(View.INVISIBLE);
                    seagull.setVisibility(View.INVISIBLE);
                    birds.setVisibility(View.INVISIBLE);
                    bird[0][0].setVisibility(View.INVISIBLE);
                    bird[1][0].setVisibility(View.INVISIBLE);
                    bird[2][0].setVisibility(View.INVISIBLE);
                    mountain.setVisibility(View.VISIBLE);
                    rock.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    byulHead.setVisibility(View.VISIBLE);
                    byulHand.setVisibility(View.VISIBLE);
                    byulBody.setVisibility(View.VISIBLE);
                    seagull.setVisibility(View.VISIBLE);
                    birds.setVisibility(View.VISIBLE);
                    bird[0][0].setVisibility(View.VISIBLE);
                    bird[1][0].setVisibility(View.VISIBLE);
                    bird[2][0].setVisibility(View.VISIBLE);
                    break;
                case 3:
                    animationFlag=4;
                    blinkBird.setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            switch (animationFlag){
                case 1:
                    animationFlag=2;
                    mountain.clearAnimation();
                    rock.clearAnimation();
                    birds.startAnimation(birdsAppear);
                    bird[0][0].startAnimation(birdsAppear);
                    bird[1][0].startAnimation(birdsAppear);
                    bird[2][0].startAnimation(birdsAppear);
                    byulBody.startAnimation(fadeIn);
                    byulHand.startAnimation(fadeIn);
                    byulHead.startAnimation(fadeIn);
                    seagull.startAnimation(fadeIn);
                    break;
                case 2:
                    animationFlag=3;
                    birds.clearAnimation();
                    byulBody.clearAnimation();
                    byulHand.clearAnimation();
                    byulHead.clearAnimation();
                    seagull.clearAnimation();
                    blinkBird.startAnimation(blink);
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            super.onAnimationRepeat(animation);
            switch (repeatFlag){
                case 0:
                    repeatFlag=1;
//                    bird[0][0].setVisibility(View.INVISIBLE);
//                    bird[0][1].setVisibility(View.VISIBLE);
//                    bird[0][2].setVisibility(View.INVISIBLE);
//                    bird[0][3].setVisibility(View.INVISIBLE);
//                    bird[0][4].setVisibility(View.INVISIBLE);
                    bird[0][0].setImageResource(R.drawable.img_10_bird_04);
                    bird[1][0].setImageResource(R.drawable.img_10_bird_03);
                    bird[2][0].setImageResource(R.drawable.img_10_bird_02);
                    break;
                case 1:
                    repeatFlag=2;
//                    bird[0][0].setVisibility(View.INVISIBLE);
//                    bird[0][1].setVisibility(View.INVISIBLE);
//                    bird[0][2].setVisibility(View.VISIBLE);
//                    bird[0][3].setVisibility(View.INVISIBLE);
//                    bird[0][4].setVisibility(View.INVISIBLE);
                    bird[0][0].setImageResource(R.drawable.img_10_bird_03);
                    bird[1][0].setImageResource(R.drawable.img_10_bird_02);
                    bird[2][0].setImageResource(R.drawable.img_10_bird_01);
                    break;
                case 2:
                    repeatFlag=3;
//                    bird[0][0].setVisibility(View.INVISIBLE);
//                    bird[0][1].setVisibility(View.INVISIBLE);
//                    bird[0][2].setVisibility(View.INVISIBLE);
//                    bird[0][3].setVisibility(View.VISIBLE);
//                    bird[0][4].setVisibility(View.INVISIBLE);
                    bird[0][0].setImageResource(R.drawable.img_10_bird_02);
                    bird[1][0].setImageResource(R.drawable.img_10_bird_01);
                    bird[2][0].setImageResource(R.drawable.img_10_bird_05);
                    break;
                case 3:
                    repeatFlag=4;
//                    bird[0][0].setVisibility(View.INVISIBLE);
//                    bird[0][1].setVisibility(View.INVISIBLE);
//                    bird[0][2].setVisibility(View.INVISIBLE);
//                    bird[0][3].setVisibility(View.INVISIBLE);
//                    bird[0][4].setVisibility(View.VISIBLE);
                    bird[0][0].setImageResource(R.drawable.img_10_bird_01);
                    bird[1][0].setImageResource(R.drawable.img_10_bird_05);
                    bird[2][0].setImageResource(R.drawable.img_10_bird_04);
                    break;
                case 4:
                    repeatFlag=0;
//                    bird[0][0].setVisibility(View.VISIBLE);
//                    bird[0][1].setVisibility(View.INVISIBLE);
//                    bird[0][2].setVisibility(View.INVISIBLE);
//                    bird[0][3].setVisibility(View.INVISIBLE);
//                    bird[0][4].setVisibility(View.INVISIBLE);
                    bird[0][0].setImageResource(R.drawable.img_10_bird_05);
                    bird[1][0].setImageResource(R.drawable.img_10_bird_04);
                    bird[2][0].setImageResource(R.drawable.img_10_bird_03);
                    break;
            }
        }
    }

    public void soundPlayFunc(){
        musicController = new MusicController(getActivity(), R.raw.scene_10);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"보물들은 다 어디에 있을까?","4000"},
                new String[]{"풀죽은 별이가 쪼그리고 앉아요.", "7500"},
                new String[]{"언제나 사이좋은 바다제비들이 조잘대기 시작해요.","12500"},
                new String[]{"별이가 아직 보물을 못 찾았대~ ","15500"},
                new String[]{"... 어떡해? ... \n" +
                        "걱정 마. 금방 찾을 거야~ ","18700"},
                new String[]{"... 맞아! 하늘이랑 땅이랑 바다랑 다 이렇게 맑고 푸른 걸~ ","23700"},
                new String[]{"... 오래 날아 날개가 막 아파도 밥 먹고 코~ 자고 나면 \n" +
                        "다시 힘이 펄펄 솟는 섬이잖아~ ","32300"},
                new String[]{"... 그럼 또 씩씩하게 날 수 있지! ","35200"},
                new String[]{"...우리들은 언제나 튼튼하지! ","37700"},
                new String[]{"... 별이가 빨리 보물을 찾았으면~ ","40500"},
                new String[]{"... 우리가 알려줄까?","43000"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();
        if(mountainAppear!=null){
            animationFlag=1;
            mountain.startAnimation(mountainAppear);
            rock.startAnimation(rockAppear);
        }
    }
}
