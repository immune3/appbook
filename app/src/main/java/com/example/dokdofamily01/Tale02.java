package com.example.dokdofamily01;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.ssomai.android.scalablelayout.ScalableLayout;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale02 extends BaseFragment {
    ImageView byulhead;
    ImageView seagullHand;
    ImageView seagullBody;
    ImageView star;
    TranslateAnimation ani;
    Animation fadeIn;
    Animation fadeOut;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale02;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        byulhead = (ImageView)layout.findViewById(R.id.byulhead);
        seagullHand = (ImageView)layout.findViewById(R.id.seagullHand);
        seagullBody = (ImageView) layout.findViewById(R.id.seagullBody);
        star = (ImageView)layout.findViewById(R.id.star);
    }

    @Override
    public void setValues() {
        super.setValues();
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
        ani = new TranslateAnimation(0,0,100,0);
        ani.setDuration(1000);
        ani.setFillAfter(true);
        ani.setAnimationListener(new MyAnimationListener());
        fadeIn = AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
        fadeIn.setAnimationListener(new MyAnimationListener());
        fadeOut = AnimationUtils.loadAnimation(getContext(),R.anim.fade_out);

    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        byulhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byulhead.startAnimation(ani);
                seagullBody.startAnimation(fadeIn);
                seagullHand.startAnimation(fadeIn);

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
        }

    }
}
