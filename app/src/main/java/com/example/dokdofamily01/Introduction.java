package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dokdofamily01.Data.SubTitleDataTest;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by mapl0 on 2017-12-28.
 */

public class Introduction extends BaseFragment {

    Button waveBtn;
    ImageView wave;
    ImageView waveText;
    View mask;

    private ImageView introBuyl;
    private ImageView introBuylText;
    private ImageView introFather;
    private ImageView introFatherText;
    private ImageView introBird;
    private ImageView introBirdText;
    private ImageView introFlower;
    private ImageView introFlowerText;
    private ImageView introMan;
    private ImageView introManText;
    private ImageView introMom;
    private ImageView introMomText;
    private ImageView introPost;
    private ImageView introPostText;
    private ImageView introSeagull;
    private ImageView introSeagullText;
    private ImageView introSquid;
    private ImageView introSquidText;
    private ImageView introTree;
    private ImageView introTreeText;
    private Button buylBtn;
    private Button fatherBtn;
    private Button birdBtn;
    private Button flowerBtn;
    private Button manBtn;
    private Button momBtn;
    private Button postBtn;
    private Button seagullBtn;
    private Button squidBtn;
    private Button treeBtn;

    AlphaAnimation fadeIn;
    AlphaAnimation fadeOut;

    MediaPlayer musicPlayer;

    int animationCaseFlag = 0;

    ArrayList<SubTitleDataTest> subtitleList;
    MediaPlayer mp = null;

    int animationFlag = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.activity_introduction;
        subtitleTextView.setText(null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void setValues() {
        super.setValues();
        checkedAnimation = true;
        musicPlayer = new MediaPlayer();

    }

    @Override
    public void setAnimation() {
        super.setAnimation();

        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(1000);
        fadeIn.setStartOffset(0);
        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new MyAnimationListener());

        fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setStartOffset(0);
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new MyAnimationListener());
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        mask.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                animationCaseFlag = 0;
                checkedAnimation = true;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    try {
                        if (musicPlayer.isPlaying()) {
                            musicPlayer.pause();
                            musicPlayer.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                return super.onTouch(view, motionEvent);
            }
        });

        waveBtn.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                animationCaseFlag = 1;
                checkedAnimation = true;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (musicPlayer.isPlaying()) {
                            musicPlayer.pause();
                            musicPlayer.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                return super.onTouch(view, motionEvent);
            }
        });


        birdBtn.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                animationCaseFlag = 2;
                checkedAnimation = true;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (musicPlayer.isPlaying()) {
                            musicPlayer.pause();
                            musicPlayer.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                return super.onTouch(view, motionEvent);
            }
        });

        buylBtn.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                animationCaseFlag = 3;
                checkedAnimation = true;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (musicPlayer.isPlaying()) {
                            musicPlayer.pause();
                            musicPlayer.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                return super.onTouch(view, motionEvent);
            }
        });

        fatherBtn.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                animationCaseFlag = 4;
                checkedAnimation = true;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (musicPlayer.isPlaying()) {
                            musicPlayer.pause();
                            musicPlayer.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                return super.onTouch(view, motionEvent);
            }
        });

        flowerBtn.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                animationCaseFlag = 5;
                checkedAnimation = true;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (musicPlayer.isPlaying()) {
                            musicPlayer.pause();
                            musicPlayer.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                return super.onTouch(view, motionEvent);
            }
        });

        manBtn.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                animationCaseFlag = 6;
                checkedAnimation = true;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (musicPlayer.isPlaying()) {
                            musicPlayer.pause();
                            musicPlayer.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                return super.onTouch(view, motionEvent);
            }
        });

        momBtn.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                animationCaseFlag = 7;
                checkedAnimation = true;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (musicPlayer.isPlaying()) {
                            musicPlayer.pause();
                            musicPlayer.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                return super.onTouch(view, motionEvent);
            }
        });

        postBtn.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                animationCaseFlag = 8;
                checkedAnimation = true;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (musicPlayer.isPlaying()) {
                            musicPlayer.pause();
                            musicPlayer.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                return super.onTouch(view, motionEvent);
            }
        });

        seagullBtn.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                animationCaseFlag = 9;
                checkedAnimation = true;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (musicPlayer.isPlaying()) {
                            musicPlayer.pause();
                            musicPlayer.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                return super.onTouch(view, motionEvent);
            }
        });

        squidBtn.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                animationCaseFlag = 10;
                checkedAnimation = true;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (musicPlayer.isPlaying()) {
                            musicPlayer.pause();
                            musicPlayer.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                return super.onTouch(view, motionEvent);
            }
        });

        treeBtn.setOnTouchListener(new BlockObjListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                animationCaseFlag = 11;
                checkedAnimation = true;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (musicPlayer.isPlaying()) {
                            musicPlayer.pause();
                            musicPlayer.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                return super.onTouch(view, motionEvent);
            }
        });

        vp = ((TaleActivity) getActivity()).vp;
        vp.setOnTouchListener(new CustomTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                customViewPager = vp;
                checkedAnimation = true;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (musicPlayer.isPlaying()) {
                            musicPlayer.pause();
                            musicPlayer.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                return super.onTouch(view, motionEvent);
            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        isHint = isVisibleToUser;
        Log.d("isHint", isHint + "");
        super.setUserVisibleHint(isVisibleToUser);
        if (isAttached) {
            if (isVisibleToUser) {
                System.out.println("PlayByHint");
                initVisible();
                soundPlayFunc();

                vp.setOnTouchListener(null);
                vp.setOnTouchListener(new MyChangeListener());

            } else {
                CheckMP checkMP = new CheckMP(musicController);
                checkMP.execute();
            }
        }
    }

    @Override
    public void soundPlayFunc() {
        super.soundPlayFunc();

//        musicController = new MusicController(getActivity(), R.raw.scene_1);
//        subtitleList = new ArrayList<>();
//        subtitleList = musicController.makeSubTitleList(
//
//        );
//        musicController.excuteAsync();
//        mp = musicController.getMp();
        checkedAnimation = true;
    }


    @Override
    public void blockAnimFunc() {
        switch (animationCaseFlag) {
            case 0:

                checkVisible();
                break;

            case 1:
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                wave.setVisibility(View.VISIBLE);
                wave.startAnimation(fadeIn);
                waveText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_wave);
                musicPlayer.start();
                musicPlayer.setLooping(false);

                break;

            case 2:
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introBird.setVisibility(View.VISIBLE);
                introBird.startAnimation(fadeIn);
                introBirdText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_bird);
                musicPlayer.start();
                musicPlayer.setLooping(false);

                break;

            case 3:
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introBuyl.setVisibility(View.VISIBLE);
                introBuyl.startAnimation(fadeIn);
                introBuylText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_buyl);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

            case 4:
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introFather.setVisibility(View.VISIBLE);
                introFather.startAnimation(fadeIn);
                introFatherText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_father);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

            case 5:
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introFlower.setVisibility(View.VISIBLE);
                introFlower.startAnimation(fadeIn);
                introFlowerText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_flower);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

            case 6:
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introMan.setVisibility(View.VISIBLE);
                introMan.startAnimation(fadeIn);
                introManText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_man);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

            case 7:
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introMom.setVisibility(View.VISIBLE);
                introMom.startAnimation(fadeIn);
                introMomText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_mom);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

            case 8:
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introPost.setVisibility(View.VISIBLE);
                introPost.startAnimation(fadeIn);
                introPostText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_post);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;
            case 9:
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introSeagull.setVisibility(View.VISIBLE);
                introSeagull.startAnimation(fadeIn);
                introSeagullText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_seagull);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

            case 10:
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introSquid.setVisibility(View.VISIBLE);
                introSquid.startAnimation(fadeIn);
                introSquidText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_squid);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

            case 11:
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introTree.setVisibility(View.VISIBLE);
                introTree.startAnimation(fadeIn);
                introTreeText.setVisibility(View.VISIBLE);

                musicPlayer = MediaPlayer.create(getContext(), R.raw.cast_tree);
                musicPlayer.start();
                musicPlayer.setLooping(false);
                break;

        }
    }

    @Override
    public void onResume() {
        initVisible();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        try {
            if (musicPlayer != null && musicPlayer.isPlaying()) {
                musicPlayer.pause();
                musicPlayer.release();
            }
            musicPlayer = null;

        } catch(Exception e) {
            e.printStackTrace();
        }
        initVisible();
    }

    public void initVisible() {
        wave.clearAnimation();
        waveBtn.setVisibility(View.VISIBLE);

        introBird.clearAnimation();
        birdBtn.setVisibility(View.VISIBLE);

        introBuyl.clearAnimation();
        buylBtn.setVisibility(View.VISIBLE);

        introFather.clearAnimation();
        fatherBtn.setVisibility(View.VISIBLE);

        introFlower.clearAnimation();
        flowerBtn.setVisibility(View.VISIBLE);

        introMan.clearAnimation();
        manBtn.setVisibility(View.VISIBLE);

        introMom.clearAnimation();
        momBtn.setVisibility(View.VISIBLE);

        introPost.clearAnimation();
        postBtn.setVisibility(View.VISIBLE);

        introSeagull.clearAnimation();
        seagullBtn.setVisibility(View.VISIBLE);

        introSquid.clearAnimation();
        squidBtn.setVisibility(View.VISIBLE);

        introTree.clearAnimation();
        treeBtn.setVisibility(View.VISIBLE);

        wave.setVisibility(View.GONE);
        waveText.setVisibility(View.GONE);

        introBird.setVisibility(View.GONE);
        introBirdText.setVisibility(View.GONE);

        introBuyl.setVisibility(View.GONE);
        introBuylText.setVisibility(View.GONE);

        introFather.setVisibility(View.GONE);
        introFatherText.setVisibility(View.GONE);

        introFlower.setVisibility(View.GONE);
        introFlowerText.setVisibility(View.GONE);

        introMan.setVisibility(View.GONE);
        introManText.setVisibility(View.GONE);

        introMom.setVisibility(View.GONE);
        introMomText.setVisibility(View.GONE);

        introPost.setVisibility(View.GONE);
        introPostText.setVisibility(View.GONE);

        introSeagull.setVisibility(View.GONE);
        introSeagullText.setVisibility(View.GONE);

        introSquid.setVisibility(View.GONE);
        introSquidText.setVisibility(View.GONE);

        introTree.setVisibility(View.GONE);
        introTreeText.setVisibility(View.GONE);

        mask.setVisibility(View.GONE);
    }

    public void checkVisible() {

        if (mask.getVisibility() == View.VISIBLE) {

            wave.clearAnimation();
            waveBtn.setVisibility(View.VISIBLE);

            introBird.clearAnimation();
            birdBtn.setVisibility(View.VISIBLE);

            introBuyl.clearAnimation();
            buylBtn.setVisibility(View.VISIBLE);

            introFather.clearAnimation();
            fatherBtn.setVisibility(View.VISIBLE);

            introFlower.clearAnimation();
            flowerBtn.setVisibility(View.VISIBLE);

            introMan.clearAnimation();
            manBtn.setVisibility(View.VISIBLE);

            introMom.clearAnimation();
            momBtn.setVisibility(View.VISIBLE);

            introPost.clearAnimation();
            postBtn.setVisibility(View.VISIBLE);

            introSeagull.clearAnimation();
            seagullBtn.setVisibility(View.VISIBLE);

            introSquid.clearAnimation();
            squidBtn.setVisibility(View.VISIBLE);

            introTree.clearAnimation();
            treeBtn.setVisibility(View.VISIBLE);

            mask.setVisibility(View.GONE);

            try {
                if (musicPlayer.isPlaying()) {
                    musicPlayer.pause();
                    musicPlayer.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {

            waveBtn.setVisibility(View.GONE);
            birdBtn.setVisibility(View.GONE);
            buylBtn.setVisibility(View.GONE);
            fatherBtn.setVisibility(View.GONE);
            flowerBtn.setVisibility(View.GONE);
            manBtn.setVisibility(View.GONE);
            momBtn.setVisibility(View.GONE);
            postBtn.setVisibility(View.GONE);
            seagullBtn.setVisibility(View.GONE);
            squidBtn.setVisibility(View.GONE);
            treeBtn.setVisibility(View.GONE);

            mask.setVisibility(View.GONE);
        }

        wave.setVisibility(View.GONE);
        waveText.setVisibility(View.GONE);

        introBird.setVisibility(View.GONE);
        introBirdText.setVisibility(View.GONE);

        introBuyl.setVisibility(View.GONE);
        introBuylText.setVisibility(View.GONE);

        introFather.setVisibility(View.GONE);
        introFatherText.setVisibility(View.GONE);

        introFlower.setVisibility(View.GONE);
        introFlowerText.setVisibility(View.GONE);

        introMan.setVisibility(View.GONE);
        introManText.setVisibility(View.GONE);

        introMom.setVisibility(View.GONE);
        introMomText.setVisibility(View.GONE);

        introPost.setVisibility(View.GONE);
        introPostText.setVisibility(View.GONE);

        introSeagull.setVisibility(View.GONE);
        introSeagullText.setVisibility(View.GONE);

        introSquid.setVisibility(View.GONE);
        introSquidText.setVisibility(View.GONE);

        introTree.setVisibility(View.GONE);
        introTreeText.setVisibility(View.GONE);

    }

    @Override
    public void bindViews() {
        super.bindViews();

        mask = (View) layout.findViewById(R.id.mask);
        waveBtn = (Button) layout.findViewById(R.id.waveBtn);
        wave = (ImageView) layout.findViewById(R.id.wave);
        waveText = (ImageView) layout.findViewById(R.id.waveText);
        this.introTreeText = (ImageView) layout.findViewById(R.id.introTreeText);
        this.treeBtn = (Button) layout.findViewById(R.id.treeBtn);
        this.introTree = (ImageView) layout.findViewById(R.id.introTree);
        this.introSquidText = (ImageView) layout.findViewById(R.id.introSquidText);
        this.squidBtn = (Button) layout.findViewById(R.id.squidBtn);
        this.introSquid = (ImageView) layout.findViewById(R.id.introSquid);
        this.introSeagullText = (ImageView) layout.findViewById(R.id.introSeagullText);
        this.seagullBtn = (Button) layout.findViewById(R.id.seagullBtn);
        this.introSeagull = (ImageView) layout.findViewById(R.id.introSeagull);
        this.introPostText = (ImageView) layout.findViewById(R.id.introPostText);
        this.postBtn = (Button) layout.findViewById(R.id.postBtn);
        this.introPost = (ImageView) layout.findViewById(R.id.introPost);
        this.introMomText = (ImageView) layout.findViewById(R.id.introMomText);
        this.momBtn = (Button) layout.findViewById(R.id.momBtn);
        this.introMom = (ImageView) layout.findViewById(R.id.introMom);
        this.introManText = (ImageView) layout.findViewById(R.id.introManText);
        this.manBtn = (Button) layout.findViewById(R.id.manBtn);
        this.introMan = (ImageView) layout.findViewById(R.id.introMan);
        this.introFlowerText = (ImageView) layout.findViewById(R.id.introFlowerText);
        this.flowerBtn = (Button) layout.findViewById(R.id.flowerBtn);
        this.introFlower = (ImageView) layout.findViewById(R.id.introFlower);
        this.introBirdText = (ImageView) layout.findViewById(R.id.introBirdText);
        this.birdBtn = (Button) layout.findViewById(R.id.birdBtn);
        this.introBird = (ImageView) layout.findViewById(R.id.introBird);
        this.introFatherText = (ImageView) layout.findViewById(R.id.introFatherText);
        this.fatherBtn = (Button) layout.findViewById(R.id.fatherBtn);
        this.introFather = (ImageView) layout.findViewById(R.id.introFather);
        this.introBuylText = (ImageView) layout.findViewById(R.id.introByulText);
        this.buylBtn = (Button) layout.findViewById(R.id.byulBtn);
        this.introBuyl = (ImageView) layout.findViewById(R.id.introByul);
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag) {
                case 1:

                    break;
                case 2:

                    break;

            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }
    }
}
