package com.example.dokdofamily01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ssomai.android.scalablelayout.ScalableLayout;

import static android.content.ContentValues.TAG;
import static java.lang.Integer.parseInt;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale01 extends BaseFragment{
    int animationFlag=0;
    ImageView lamp;
    ImageView lampLight;
    ImageView bedLight;
    ImageView head;
    ImageView blanket;
    ImageView byul;
    ImageView hand;
    ImageView curtain;
    ImageView light;
    Animation fadeIn;
    Animation fadeOut;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale01;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        lamp = (ImageView)layout.findViewById(R.id.lamp);
        lampLight = (ImageView)layout.findViewById(R.id.lampLight);
        bedLight = (ImageView)layout.findViewById(R.id.bedLight);
        head = (ImageView)layout.findViewById(R.id.head);
        blanket = (ImageView)layout.findViewById(R.id.blanket);
        byul = (ImageView)layout.findViewById(R.id.byul);
        hand = (ImageView)layout.findViewById(R.id.hand);
        curtain = (ImageView)layout.findViewById(R.id.curtain);
        light = (ImageView)layout.findViewById(R.id.light);
    }

    @Override
    public void setValues() {
        super.setValues();
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        fadeIn = AnimationUtils.loadAnimation(getContext(),R.anim.anim_01_fadein);
        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new MyAnimationListener());

        fadeOut = AnimationUtils.loadAnimation(getContext(),R.anim.anim_01_fadeout);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new MyAnimationListener());
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        lamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationFlag = 1;
                lampLight.startAnimation(fadeIn);
                bedLight.startAnimation(fadeIn);
                head.setVisibility(View.VISIBLE);
                blanket.setVisibility(View.VISIBLE);
                curtain.setVisibility(View.VISIBLE);
                light.setVisibility(View.INVISIBLE);
                byul.setVisibility(View.INVISIBLE);
                hand.setVisibility(View.INVISIBLE);
                
            }
        });
    }
    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            switch (animationFlag){
                case 1:
                    animationFlag=2;
                    lampLight.clearAnimation();
                    bedLight.clearAnimation();
                    break;
                case 2:
                    animationFlag=3;
                    bedLight.startAnimation(fadeOut);
                    head.startAnimation(fadeOut);
                    blanket.startAnimation(fadeOut);
                    break;
                case 3:
                    animationFlag=4;
                    bedLight.setVisibility(View.INVISIBLE);
                    head.setVisibility(View.INVISIBLE);
                    blanket.setVisibility(View.INVISIBLE);
                    bedLight.clearAnimation();
                    head.clearAnimation();
                    blanket.clearAnimation();
                    break;
                case 4:
                    animationFlag=5;
                    byul.startAnimation(fadeIn);
                    break;
                case 5:
                    animationFlag=6;
                    byul.clearAnimation();
                    break;
                case 6:
                    animationFlag=7;
                    hand.startAnimation(fadeIn);
                    curtain.startAnimation(fadeOut);
                    light.startAnimation(fadeIn);
                    break;
                case 7:
                    animationFlag=8;
                    curtain.setVisibility(View.INVISIBLE);
                    hand.clearAnimation();
                    curtain.clearAnimation();
                    light.clearAnimation();
                    break;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            if(animationFlag==1){
                lampLight.setVisibility(View.VISIBLE);
                bedLight.setVisibility(View.VISIBLE);
            } else if (animationFlag==5) {
                byul.setVisibility(View.VISIBLE);
            }else if(animationFlag==7){
                hand.setVisibility(View.VISIBLE);
                light.setVisibility(View.VISIBLE);
            }
        }

    }
}
