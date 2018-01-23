package com.example.dokdofamily01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
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
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by heronation on 2017-11-06.
 */

public class TaleActivity extends AppCompatActivity {
    // 화면 꺼짐 제어용 변수
    PowerManager.WakeLock m_sleep_lock = null;

    public CustomViewPager vp;
    Spinner goPage;
    LinearLayout menuContainer;
    boolean showFlag, animFlag;
    TranslateAnimation ani_menu_up;
    TranslateAnimation ani_menu_down;
    TranslateAnimation ani_menuContainer_up;
    TranslateAnimation ani_menuContainer_down;
    Button showMenu;
    Button menuBtn;
    private float x1, x2;
    static final int MIN_DISTANCE = 150;

    static boolean checkedAnimation = false;
//    static boolean checkEndOfPrologue = false;

    static public TextView subtitleTextView;
    static public ImageView subtitleImageVIew;

    static int height;
    static int width;
    int showMenuHeight;

    boolean isOpen = false;

    /* 추가 필드 */
    BroadcastReceiver screenOffReceiver;
    IntentFilter screenOffFilter;
    static public boolean homeKeyFlag = false;
    private int currentVpPos = -1;
    static public boolean screenFlag = true;

    protected ImageView goFront;
    protected ImageView goBack;
    protected ImageView goHome;
    protected ImageView showPage;
    protected ImageView showPageBtn;

    Intent intent;
    public boolean isAutoRead = true;

    private ArrayList<String> indexItems = new ArrayList<>();
    private CustomSpinnerAdapter customSpinnerAdapter;
    private final int NUM_OF_INDEX = 20;

    private Handler closeMenuHandler;
    private Runnable closeMenuRunnable;

    Tale01 tale01;
    Tale02 tale02;
    Tale03 tale03;
    Tale04 tale04;
    Tale05 tale05;
    Tale06 tale06;
    Tale07 tale07;
    Tale08 tale08;
    Tale09 tale09;
    Tale10 tale10;
    Tale11 tale11;
    Tale12 tale12;
    Tale13 tale13;
    Tale14 tale14;
    Tale15 tale15;
    Tale16 tale16;
    Tale17 tale17;
    Tale18 tale18;
    Tale19 tale19;
    Tale20 tale20;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                for (View view : getWindowManagerViews()) {
                    try {
                        Class clazz = view.getClass();
                        Field outerField = clazz.getDeclaredField("this$0");
                        outerField.setAccessible(true);
                        Object popupWindow = outerField.get(view);

                        Field field = popupWindow.getClass().getDeclaredField("mTouchInterceptor");
                        field.setAccessible(true);
                        final View.OnTouchListener innerOnTouchListener = (View.OnTouchListener) field.get(popupWindow);
                        View.OnTouchListener outerOnTOuchListener = new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                Log.d(MainActivity.class.getSimpleName(), String.format("popupwindow event %s at %s-%s", motionEvent.getAction(), motionEvent.getX(), motionEvent.getY()));
                                autoCloseMenu(3000);
                                return innerOnTouchListener.onTouch(view, motionEvent);
                            }

                        };
                        field.set(popupWindow, outerOnTOuchListener);
                    } catch (Exception e) {
                        //e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        setValues();
        bindViews();
        setUpEvents();

    }

    public void setValues() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

    public void setUpEvents() {

        intent = getIntent();
        isAutoRead = intent.getBooleanExtra("isAutoRead", true);

        sendBundle();

        for (int iter = 1; iter <= NUM_OF_INDEX; iter++) {
            indexItems.add(String.valueOf(iter));
        }
        customSpinnerAdapter = new CustomSpinnerAdapter(this, indexItems);
        goPage.setAdapter(customSpinnerAdapter);

        showFlag = true;
        animFlag = false;

        showMenu.post(new Runnable() {
            @Override
            public void run() {
                int showMenuWidth = showMenu.getWidth();
                showMenuHeight = showMenu.getHeight() / 2;
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
                float normalizedposition = Math.abs(1 - Math.abs(position));

//                page.setAlpha(normalizedposition);  //View의 투명도 조절

//                page.setScaleX(normalizedposition/2 + 0.5f); //View의 x축 크기조절

//                page.setScaleY(normalizedposition/2 + 0.5f); //View의 y축 크기조절

//                page.setRotationY(position * 80);   //View의 Y축(세로축) 회전 각도
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (positionOffsetPixels == 0) goPage.setSelection(position);

            }

            @Override
            public void onPageSelected(int position) {
                goPage.setAdapter(customSpinnerAdapter);
                goPage.setSelection(position);
//                destroyMenuHandler();
//                autoCloseMenu(3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                autoCloseMenu(3000);
            }
        });

        vp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("뷰페이저 클릭", "click");
                autoCloseMenu(3000);
            }
        });

        goFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoCloseMenu(3000);

                if (vp.getCurrentItem() == 0) {
                    Toast.makeText(getApplicationContext(), "첫번째 페이지입니다.", Toast.LENGTH_SHORT).show();
                } else {

                    int position = vp.getCurrentItem() - 1;
                    if (checkedAnimation) {
                        destroyAllDelayedPaging();
                        vp.setCurrentItem(position, true);
                    }
                }
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoCloseMenu(3000);

                if (vp.getCurrentItem() == 19) {
                    Toast.makeText(getApplicationContext(), "마지막 페이지입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    int position = vp.getCurrentItem() + 1;
                    if (checkedAnimation) {
                        destroyAllDelayedPaging();
                        vp.setCurrentItem(position, true);
                    }

                }
            }
        });

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        showPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destroyMenuHandler();

                goPage.performClick();

//                getCurrentFocus().setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        autoCloseMenu(3000);
//                    }
//                });

            }
        });

        goPage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                autoCloseMenu(3000);
//                Log.d("setOnItemSelectedListener", "qwerqwerqwerqwer");
                vp.setCurrentItem(i, false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showFlag && !animFlag) {
                    openMenu();
                } else if (!showFlag && !animFlag) {
                    closeMenu();
                } else destroyMenuHandler();
            }
        });

        try {

            screenOffReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.d("SCREEN", "SCREEN OFF");
                    screenFlag = false;
                }
            };

            screenOffFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);


            registerReceiver(screenOffReceiver, screenOffFilter);

            // 컨텍스트의 전원에 관한 시스템 서비스를 얻는다.
            PowerManager power = (PowerManager) getSystemService(Context.POWER_SERVICE);
            // 화면이 꺼지 않도록 제어할 수 있는 권리를 얻는다.
            m_sleep_lock = power.newWakeLock(PowerManager.FULL_WAKE_LOCK, "TaleActivity");

            m_sleep_lock.acquire();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void sendBundle() {

        Bundle autoReadBundle = new Bundle();
        autoReadBundle.putBoolean("isAuto", isAutoRead);

        tale01.setArguments(autoReadBundle);
        tale02.setArguments(autoReadBundle);
        tale03.setArguments(autoReadBundle);
        tale04.setArguments(autoReadBundle);
        tale05.setArguments(autoReadBundle);
        tale06.setArguments(autoReadBundle);
        tale07.setArguments(autoReadBundle);
        tale08.setArguments(autoReadBundle);
        tale09.setArguments(autoReadBundle);
        tale10.setArguments(autoReadBundle);
        tale11.setArguments(autoReadBundle);
        tale12.setArguments(autoReadBundle);
        tale13.setArguments(autoReadBundle);
        tale14.setArguments(autoReadBundle);
        tale15.setArguments(autoReadBundle);
        tale16.setArguments(autoReadBundle);
        tale17.setArguments(autoReadBundle);
        tale18.setArguments(autoReadBundle);
        tale19.setArguments(autoReadBundle);
        tale20.setArguments(autoReadBundle);


    }

    public void bindViews() {

        menuContainer = (LinearLayout) findViewById(R.id.menuContainer);

        goFront = (ImageView) findViewById(R.id.goFront);
        goBack = (ImageView) findViewById(R.id.goBack);
        goHome = (ImageView) findViewById(R.id.goHome);
        showPage = (ImageView) findViewById(R.id.showPage);
        showPageBtn = (ImageView) findViewById(R.id.showPageBtn);

        showMenu = (Button) findViewById(R.id.showMenu);
        menuBtn = (Button) findViewById(R.id.menuBtn);
        goPage = (Spinner) findViewById(R.id.goPage);

        subtitleTextView = (CustomTextView) findViewById(R.id.CustomTextView);
        subtitleImageVIew = (ImageView) findViewById(R.id.subtitleImageView);

        tale01 = new Tale01();
        tale02 = new Tale02();
        tale03 = new Tale03();
        tale04 = new Tale04();
        tale05 = new Tale05();
        tale06 = new Tale06();
        tale07 = new Tale07();
        tale08 = new Tale08();
        tale09 = new Tale09();
        tale10 = new Tale10();
        tale11 = new Tale11();
        tale12 = new Tale12();
        tale13 = new Tale13();
        tale14 = new Tale14();
        tale15 = new Tale15();
        tale16 = new Tale16();
        tale17 = new Tale17();
        tale18 = new Tale18();
        tale19 = new Tale19();
        tale20 = new Tale20();

    }

    public void autoCloseMenu(int milsec) {

        if (isOpen) {
            destroyMenuHandler();
            closeMenuRunnable = new Runnable() {
                @Override
                public void run() {
                    closeMenu();
                }
            };

            closeMenuHandler = new Handler();
            closeMenuHandler.postDelayed(closeMenuRunnable, milsec);
        }

    }

    public void destroyAllDelayedPaging() {

        if (tale01.musicController != null) tale01.musicController.destroyPaging();
        if (tale02.musicController != null) tale02.musicController.destroyPaging();
        if (tale03.musicController != null) tale03.musicController.destroyPaging();
        if (tale04.musicController != null) tale04.musicController.destroyPaging();
        if (tale05.musicController != null) tale05.musicController.destroyPaging();
        if (tale06.musicController != null) tale06.musicController.destroyPaging();
        if (tale07.musicController != null) tale07.musicController.destroyPaging();
        if (tale08.musicController != null) tale08.musicController.destroyPaging();
        if (tale09.musicController != null) tale09.musicController.destroyPaging();
        if (tale10.musicController != null) tale10.musicController.destroyPaging();
        if (tale11.musicController != null) tale11.musicController.destroyPaging();
        if (tale12.musicController != null) tale12.musicController.destroyPaging();
        if (tale13.musicController != null) tale13.musicController.destroyPaging();
        if (tale14.musicController != null) tale14.musicController.destroyPaging();
        if (tale15.musicController != null) tale15.musicController.destroyPaging();
        if (tale16.musicController != null) tale16.musicController.destroyPaging();
        if (tale17.musicController != null) tale17.musicController.destroyPaging();
        if (tale18.musicController != null) tale18.musicController.destroyPaging();
        if (tale19.musicController != null) tale19.musicController.destroyPaging();
        if (tale20.musicController != null) tale20.musicController.destroyPaging();

    }

    public void openMenu() {

        isOpen = true;
        destroyMenuHandler();

        showMenu.setTranslationY(0);
        showMenu.clearAnimation();
        showMenu.startAnimation(ani_menu_up);
        menuContainer.setTranslationY(0);
        menuContainer.clearAnimation();
        menuContainer.startAnimation(ani_menuContainer_up);

        autoCloseMenu(3000);

    }

    public void destroyMenuHandler() {

        if (closeMenuHandler != null && closeMenuRunnable != null) {
            Log.d("핸들러 제거", "ㅎㅎ");
            closeMenuHandler.removeCallbacks(closeMenuRunnable);
            closeMenuHandler = null;
            closeMenuRunnable = null;
        }
    }

    public void closeMenu() {

        isOpen = false;
        destroyMenuHandler();

        showMenu.setTranslationY(0);
        showMenu.clearAnimation();
        showMenu.startAnimation(ani_menu_down);
        menuContainer.setTranslationY(0);
        menuContainer.clearAnimation();
        menuContainer.startAnimation(ani_menuContainer_down);
    }

    private class pagerAdapter extends FragmentStatePagerAdapter {

        public pagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return tale01;
                case 1:
                    return tale02;
                case 2:
                    return tale03;
                case 3:
                    return tale04;
                case 4:
                    return tale05;
                case 5:
                    return tale06;
                case 6:
                    return tale07;
                case 7:
                    return tale08;
                case 8:
                    return tale09;
                case 9:
                    return tale10;
                case 10:
                    return tale11;
                case 11:
                    return tale12;
                case 12:
                    return tale13;
                case 13:
                    return tale14;
                case 14:
                    return tale15;
                case 15:
                    return tale16;
                case 16:
                    return tale17;
                case 17:
                    return tale18;
                case 18:
                    return tale19;
                case 19:
                    return tale20;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
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
            if (showFlag) {
                menuBtn.setTranslationY(0);
            } else {
                menuBtn.setTranslationY(showMenuHeight);
            }

            animFlag = false;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            animFlag = true;
        }

    }

    private class MyAnimationListener1 implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            if (showFlag) {
                menuContainer.setTranslationY(0);
                showFlag = false;
            } else {
                menuContainer.setTranslationY(showMenuHeight);
                showFlag = true;
            }

            animFlag = false;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            animFlag = true;
        }

    }

    private void setAnimation() {

        ani_menu_up = new TranslateAnimation(0, 0, showMenuHeight, 0);
        ani_menu_up.setDuration(500);
        ani_menu_up.setFillAfter(true);
        ani_menu_up.setAnimationListener(new MyAnimationListener());

        ani_menu_down = new TranslateAnimation(0, 0, 0, showMenuHeight);
        ani_menu_down.setDuration(500);
        ani_menu_down.setFillAfter(true);
        ani_menu_down.setAnimationListener(new MyAnimationListener());

        ani_menuContainer_up = new TranslateAnimation(0, 0, showMenuHeight, 0);
        ani_menuContainer_up.setDuration(500);
        ani_menuContainer_up.setFillAfter(true);
        ani_menuContainer_up.setAnimationListener(new MyAnimationListener1());

        ani_menuContainer_down = new TranslateAnimation(0, 0, 0, showMenuHeight);
        ani_menuContainer_down.setDuration(500);
        ani_menuContainer_down.setFillAfter(true);
        ani_menuContainer_down.setAnimationListener(new MyAnimationListener1());

    }
    /* 추가 on 메소드 */

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            unregisterReceiver(screenOffReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (screenOffReceiver != null && screenFlag == false) {
            unregisterReceiver(screenOffReceiver);
            screenOffReceiver = null;
        }
        if (m_sleep_lock.isHeld()) {
            // 화면 제어를 해제한다.
            m_sleep_lock.release();
        }
    }

    @Override
    protected void onResume() {

        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        // 화면 제어가 동작중인 경우
        if (m_sleep_lock.isHeld()) {
            // 화면 제어를 해제한다.
            m_sleep_lock.release();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (screenOffReceiver != null && screenFlag == false) {
            unregisterReceiver(screenOffReceiver);
            screenOffReceiver = null;
        }
        // 화면 제어가 동작중인 경우
        if (m_sleep_lock.isHeld()) {
            // 화면 제어를 해제한다.
            m_sleep_lock.release();
        }

    }

    @Override
    protected void onStart() {
        System.out.println("Activity State : onStart");
        Log.d("homeKeyFlag", homeKeyFlag + "");
        Log.d("currentVpPos", currentVpPos + "");

        if (screenOffReceiver == null) {
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
        m_sleep_lock.acquire();
    }

    @Override
    protected void onUserLeaveHint() {
        homeKeyFlag = true;
        currentVpPos = vp.getCurrentItem();
        super.onUserLeaveHint();
    }

    public static List<View> getWindowManagerViews() {
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH &&
                    Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {

                // get the list from WindowManagerImpl.mViews
                Class wmiClass = Class.forName("android.view.WindowManagerImpl");
                Object wmiInstance = wmiClass.getMethod("getDefault").invoke(null);

                return viewsFromWM(wmiClass, wmiInstance);

            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {

                // get the list from WindowManagerGlobal.mViews
                Class wmgClass = Class.forName("android.view.WindowManagerGlobal");
                Object wmgInstance = wmgClass.getMethod("getInstance").invoke(null);

                return viewsFromWM(wmgClass, wmgInstance);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<View>();
    }

    private static List<View> viewsFromWM(Class wmClass, Object wmInstance) throws Exception {

        Field viewsField = wmClass.getDeclaredField("mViews");
        viewsField.setAccessible(true);
        Object views = viewsField.get(wmInstance);

        if (views instanceof List) {
            return (List<View>) viewsField.get(wmInstance);
        } else if (views instanceof View[]) {
            return Arrays.asList((View[]) viewsField.get(wmInstance));
        }

        return new ArrayList<View>();
    }


}
