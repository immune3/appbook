package com.example.dokdofamily01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ssomai.android.scalablelayout.ScalableLayout;

import java.util.concurrent.locks.ReentrantReadWriteLock;

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
    static public int firstFlag = 0;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = (RelativeLayout) inflater.inflate(xml,container, false);
        vp = ((TaleActivity) getActivity()).vp;
        hv = new CustomHorizontalScrollView(getContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        hv.setLayoutParams(lp);
//        hv.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
//        hv.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;

        rl = (RelativeLayout)layout.findViewById(R.id.rl) ;
        sv = (CustomScrollView)layout.findViewById(R.id.sv);
        sl = (ScalableLayout)layout.findViewById(R.id.sl);
        sl.post(new Runnable() {
            @Override
            public void run() {
                int deviceWidth = TaleActivity.width;
                int deviceHeight = TaleActivity.height;
                innerHeight = sl.getHeight();

                float ratio = (float)deviceWidth/(float)deviceHeight;
                Log.e("ratio", ""+ratio);
                if(ratio<=1.66){
                    sv.removeView(sl);
                    rl.removeView(sv);
                    hv.addView(sl);
                    rl.addView(hv);

                    innerWidth = hv.getWidth();
                    Log.e("innerWidth", ""+innerWidth);
                    hv.scrollTo((innerWidth-deviceWidth)/2,0);
                    hv.setScrolling(false);

                }else{
                    sv.scrollTo(0,(innerHeight-deviceHeight)/2);
                    sv.setScrolling(false);


                }

//                if(innerWidth>deviceWidth){
//
//                    sv.scrollTo((innerWidth-deviceWidth)/2,0);
//                }else{
//                }

            }
        });
        bindViews();
        setValues();
        setAnimation();
        setupEvents();
        return layout;
    }

    public void bindViews(){

    }

    public void setValues(){

    }

    public void setAnimation(){

    }

    public void setupEvents(){

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
