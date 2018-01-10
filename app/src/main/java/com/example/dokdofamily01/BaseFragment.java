package com.example.dokdofamily01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

import com.ssomai.android.scalablelayout.ScalableLayout;

import static com.example.dokdofamily01.TaleActivity.homeKeyFlag;
import static com.example.dokdofamily01.TaleActivity.screenFlag;

/**
 * Created by heronation on 2017-11-17.
 */

public class BaseFragment extends Fragment{
    CustomScrollView sv;
    ScalableLayout sl;
    RelativeLayout rl;
    public RelativeLayout layout;
    public int xml = 0;
    public MusicController musicController;
    public SubtitleController subtitleController;
    boolean isHint;
    boolean isAttached = false;
    public CustomViewPager vp;
    MyChangeListener change ;

    CustomHorizontalScrollView hv;
    int innerWidth,innerHeight;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = (RelativeLayout) inflater.inflate(xml,container, false);

        bindViews();
        setValues();
        setAnimation();

        return layout;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void bindViews(){
        rl = (RelativeLayout)layout.findViewById(R.id.rl) ;
        sv = (CustomScrollView)layout.findViewById(R.id.sv);
        sl = (ScalableLayout)layout.findViewById(R.id.sl);
    }

    public void setValues(){

    }

    public void setAnimation(){

    }

    public void setupEvents(){

        vp = ((TaleActivity) getActivity()).vp;
        hv = new CustomHorizontalScrollView(getContext());
        HorizontalScrollView.LayoutParams lp = new HorizontalScrollView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        hv.setLayoutParams(lp);
//        hv.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
//        hv.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;

        sl.post(new Runnable() {
            @Override
            public void run() {
                final int deviceWidth = TaleActivity.width;
                int deviceHeight = TaleActivity.height;
                innerHeight = sl.getHeight();
                Log.d("slWidth", sl.getWidth() + "");

                float ratio = (float)deviceWidth/(float)deviceHeight;
                Log.e("ratio", ""+ratio);
                if(ratio<=1.66){

                    sv.removeView(sl);
                    rl.removeView(sv);
                    hv.addView(sl);
                    rl.addView(hv);

                    hv.post(new Runnable() {
                        @Override
                        public void run() {

                            innerWidth = hv.getChildAt(0).getWidth();
                            Log.e("innerWidth", ""+innerWidth);
                            hv.scrollTo((innerWidth-deviceWidth)/2,0);
                            hv.setScrolling(false);

                        }
                    });

                }else{
                    sv.scrollTo(0,(innerHeight-deviceHeight)/2);
                    Log.e("innerHeight", innerHeight + "");
                    sv.setScrolling(false);

                }
            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isAttached = true;

        Log.d("VP",vp+"");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        isHint = isVisibleToUser;
        Log.d("isHint", isHint + "");
        super.setUserVisibleHint(isVisibleToUser);
        if (isAttached) {
            if (isVisibleToUser) {
                System.out.println("PlayByHint");
                soundPlayFunc();

                vp.setOnTouchListener(null);
                vp.setOnTouchListener(new MyChangeListener());

            } else {
                CheckMP checkMP = new CheckMP(musicController);
                checkMP.execute();
            }
        }
    }

    public void soundPlayFunc() {

    }

    class BlockObjListener extends CustomTouchListener{

        public BlockObjListener(AsyncResponse asyncResponse) {
            super.delegate = asyncResponse;
        }

        public BlockObjListener() {
            super.delegate = new AsyncResponse() {
                @Override
                public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {
//                    if(motionEvent.getAction() == MotionEvent.ACTION_UP && (checkDistanceX == 0 && checkDistanceY == 0) && musicController != null) {
//                        musicController.nextPart();
//                    }
//
//                    else if(motionEvent.getAction() == MotionEvent.ACTION_UP && (checkDistanceX == 0 && checkDistanceY == 0) && subtitleController != null) {
//                        subtitleController.nextInActionUp();
//                    }
                }
            };
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            customViewPager = vp;
            return super.onTouch(view, motionEvent);
        }

        @Override
        public void decreaseFunc() {
            Log.d("Start","DESC");
            if(musicController != null){
                Log.d("Start","DESC");

                if(musicController.previousPart()){

                }else{
                    super.decreaseFunc();
                }
            } else if(subtitleController != null) {
                subtitleController.front();
            }
        }

        @Override
        public void increaseFunc() {
            Log.d("Start","ASC");
            if(musicController!=null){
                Log.d("Start","ASC2");
                if(musicController.nextPart()){
                    Log.d("nextPart","if");
                }else{
                    Log.d("nextPart","else");
                    super.increaseFunc();
                }
            } else if(subtitleController != null) {
                subtitleController.next();
            }
        }

        @Override
        public void animationFunc() {
            super.animationFunc();
            blockAnimFunc();
        }

    }

    public void blockAnimFunc(){

    }

    class MyChangeListener extends CustomTouchListener{

        public MyChangeListener(AsyncResponse asyncResponse) {
            super.delegate = asyncResponse;
        }

        public MyChangeListener() {
            super.delegate = new AsyncResponse() {
                @Override
                public void onAction(MotionEvent motionEvent, int checkDistanceX, int checkDistanceY, float diff) {
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP && (checkDistanceX == 0 && checkDistanceY == 0) && musicController != null) {
                        musicController.nextPart();
                    } else if(motionEvent.getAction() == MotionEvent.ACTION_UP && (checkDistanceX == 0 && checkDistanceY == 0) && subtitleController != null) {
                        subtitleController.nextInActionUp();
                    }
                }
            };
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Log.d("Touch","On");
            customViewPager = vp;
            return super.onTouch(view, motionEvent);
        }

        @Override
        public void decreaseFunc() {
            Log.d("Start","DESC");
            if(musicController != null){
                Log.d("Start","DESC");

                if(musicController.previousPart()){
                    Log.d("DESC", "previous");
                }else{
                    Log.d("DESC", "else");
                    super.decreaseFunc();
                }
            } else if(subtitleController != null) {
                subtitleController.front();
            }
        }

//        @Override
//        public void increaseFunc() {
//            Log.d("Start","ASC");
//            if(musicController!=null){
//                Log.d("Start","ASC2");
//                if(musicController.nextPart()){
//                    Log.d("nextPart","if");
//                }else{
//                    Log.d("nextPart","else");
//                    super.increaseFunc();
//                }
//            } else super.increaseFunc();
//        }


        @Override
        public void increaseFunc() {
            Log.d("Start","ASC");
            if(musicController!=null){
                Log.d("Start","ASC2");
                if(musicController.nextPart()){
                    Log.d("ASC", "next");
                }else{
                    Log.d("ASC", "nextPart");
                    super.increaseFunc();
                }

            } else if(subtitleController != null) {
                subtitleController.next();
            }
        }

    }




    @Override
    public void onResume() {
        if (isHint && !homeKeyFlag && screenFlag) {
            soundPlayFunc();
            vp.setOnTouchListener(null);
            vp.setOnTouchListener(new MyChangeListener());
        }
        super.onResume();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (musicController != null) {
            CheckMP checkMP = new CheckMP(musicController);
            checkMP.execute();
        }
    }

}
