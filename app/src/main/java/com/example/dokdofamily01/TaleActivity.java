package com.example.dokdofamily01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class TaleActivity extends AppCompatActivity{
    public CustomViewPager vp;
    Spinner goPage;
    LinearLayout menuContainer;
    boolean showFlag;
    TranslateAnimation ani_menu_up;
    TranslateAnimation ani_menu_down;
    TranslateAnimation ani_menuContainer_up;
    TranslateAnimation ani_menuContainer_down;
    Button showMenu;
    Button menuBtn;
    private float x1,x2;
    static final int MIN_DISTANCE = 150;

    static boolean checkedAnimation = true;

    static public TextView subtitleTextView;

    static int height;
    static int width;
    int showMenuHeight;

    /* 추가 필드 */
    BroadcastReceiver screenOffReceiver;
    IntentFilter screenOffFilter;
    static public boolean homeKeyFlag = false;
    private int currentVpPos = -1;
    static public boolean screenFlag = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        menuContainer = (LinearLayout)findViewById(R.id.menuContainer);

        ImageView goFront = (ImageView)findViewById(R.id.goFront);
        ImageView goBack = (ImageView)findViewById(R.id.goBack);
        ImageView goHome = (ImageView)findViewById(R.id.goHome);
        ImageView showPage = (ImageView)findViewById(R.id.showPage);
        showMenu = (Button)findViewById(R.id.showMenu);
        menuBtn = (Button)findViewById(R.id.menuBtn);
        goPage = (Spinner)findViewById(R.id.goPage);

        subtitleTextView = (CustomTextView) findViewById(R.id.CustomTextView);
        showFlag = true;

        showMenu.post(new Runnable() {
            @Override
            public void run() {
                int showMenuWidth = showMenu.getWidth();
                showMenuHeight = showMenu.getHeight()/2;
                menuContainer.setTranslationY(showMenuHeight);
                menuBtn.setTranslationY(showMenuHeight);
                showMenu.setTranslationY(showMenuHeight);
                subtitleTextView.setTranslationY(-showMenuHeight);
                setAnimation();
            }
        });

        vp = (CustomViewPager) findViewById(R.id.vp);
        vp.setOffscreenPageLimit(0);
        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);
        vp.setPageScrollEnabled(false);
        vp.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                float normalizedposition = Math.abs( 1 - Math.abs(position) );

//                page.setAlpha(normalizedposition);  //View의 투명도 조절

//                page.setScaleX(normalizedposition/2 + 0.5f); //View의 x축 크기조절

//                page.setScaleY(normalizedposition/2 + 0.5f); //View의 y축 크기조절

//                page.setRotationY(position * 80);   //View의 Y축(세로축) 회전 각도
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(positionOffsetPixels==0) goPage.setSelection(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        vp.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch(motionEvent.getAction())
//                {
//                    case MotionEvent.ACTION_DOWN:
//                        x1 = motionEvent.getX();
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        x2 = motionEvent.getX();
//                        float deltaX = x2 - x1;
//                        if (Math.abs(deltaX) > MIN_DISTANCE)
//                        {
//                            if(x2>x1) {
//                                vp.setCurrentItem(vp.getCurrentItem()-1);
//                            }
//                            else if(x2<x1) {
//                                vp.setCurrentItem(vp.getCurrentItem()+1);
//                            }
//                        }
//                        else
//                        {
//                            Log.i("position", "short");
//                        }
//                        break;
//                }
//                return true;
//            }
//
//        });


        goFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = vp.getCurrentItem()-1;
                if(checkedAnimation) vp.setCurrentItem(position, true);
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = vp.getCurrentItem()+1;
                if(checkedAnimation) vp.setCurrentItem(position,true);
            }
        });
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.context, MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });
        showPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        goPage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vp.setCurrentItem(i,false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showFlag) {
                    showMenu.setTranslationY(0);
                    showMenu.clearAnimation();
                    showMenu.startAnimation(ani_menu_up);
                    menuContainer.setTranslationY(0);
                    menuContainer.clearAnimation();
                    menuContainer.startAnimation(ani_menuContainer_up);
                }else{
                    showMenu.setTranslationY(0);
                    showMenu.clearAnimation();
                    showMenu.startAnimation(ani_menu_down);
                    menuContainer.setTranslationY(0);
                    menuContainer.clearAnimation();
                    menuContainer.startAnimation(ani_menuContainer_down);
                }
            }
        });
//        showMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        screenOffReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("SCREEN", "SCREEN OFF");
                screenFlag = false;
            }
        };

        screenOffFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);

        registerReceiver(screenOffReceiver, screenOffFilter);


}

    private class pagerAdapter extends FragmentStatePagerAdapter
    {
//        Fragment[] fragments = new Fragment[20];

        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new Tale01();
                case 1:
                    return new Tale02();
                case 2:
                    return new Tale03();
                case 3:
                    return new Tale04();
                case 4:
                    return new Tale05();
                case 5:
                    return new Tale06();
                case 6:
                    return new Tale07();
                case 7:
                    return new Tale08();
                case 8:
                    return new Tale09();
                case 9:
                    return new Tale10();
                case 10:
                    return new Tale11();
                case 11:
                    return new Tale12();
                case 12:
                    return new Tale13();
                case 13:
                    return new Tale14();
                case 14:
                    return new Tale15();
                case 15:
                    return new Tale16();
                case 16:
                    return new Tale17();
                case 17:
                    return new Tale18();
                case 18:
                    return new Tale19();
                case 19:
                    return new Tale20();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 20;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

    }
    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            if(showFlag) {
                menuBtn.setTranslationY(0);
            }else{
                menuBtn.setTranslationY(showMenuHeight);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

    }
    private class MyAnimationListener1 implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            if(showFlag) {
                menuContainer.setTranslationY(0);
                showFlag = false;
            }else{
                menuContainer.setTranslationY(showMenuHeight);
                showFlag = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

    }

    private void setAnimation(){

        ani_menu_up = new TranslateAnimation(0,0,showMenuHeight,0);
        ani_menu_up.setDuration(500);
        ani_menu_up.setFillAfter(true);
        ani_menu_up.setAnimationListener(new MyAnimationListener());

        ani_menu_down = new TranslateAnimation(0,0,0,showMenuHeight);
        ani_menu_down.setDuration(500);
        ani_menu_down.setFillAfter(true);
        ani_menu_down.setAnimationListener(new MyAnimationListener());

        ani_menuContainer_up = new TranslateAnimation(0,0,showMenuHeight,0);
        ani_menuContainer_up.setDuration(500);
        ani_menuContainer_up.setFillAfter(true);
        ani_menuContainer_up.setAnimationListener(new MyAnimationListener1());

        ani_menuContainer_down = new TranslateAnimation(0,0,0,showMenuHeight);
        ani_menuContainer_down.setDuration(500);
        ani_menuContainer_down.setFillAfter(true);
        ani_menuContainer_down.setAnimationListener(new MyAnimationListener1());

    }
    /* 추가 on 메소드 */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(screenOffReceiver!=null && screenFlag == false){
            unregisterReceiver(screenOffReceiver);
            screenOffReceiver = null;
        }
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(screenOffReceiver!=null && screenFlag == false){
            unregisterReceiver(screenOffReceiver);
            screenOffReceiver = null;
        }

    }

    @Override
    protected void onStart() {
        System.out.println("Activity State : onStart");
        Log.d("homeKeyFlag", homeKeyFlag + "");
        Log.d("currentVpPos", currentVpPos + "");

        if(screenOffReceiver == null ){
            screenOffReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.d("SCREEN", "SCREEN OFF");
                    screenFlag = false;
                }
            };

            screenOffFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);

            registerReceiver(screenOffReceiver, screenOffFilter);
        }


        screenFlag = true;
        if (homeKeyFlag) {
            Log.d("home", "key");
            vp.setCurrentItem(currentVpPos, false);
            homeKeyFlag = false;
        }

        super.onStart();
    }

    @Override
    protected void onUserLeaveHint() {
        homeKeyFlag = true;
        currentVpPos = vp.getCurrentItem();
        super.onUserLeaveHint();
    }
}
