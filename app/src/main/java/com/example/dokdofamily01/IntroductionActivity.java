package com.example.dokdofamily01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ssomai.android.scalablelayout.ScalableLayout;

/**
 * Created by heronation on 2017-12-26.
 */

public class IntroductionActivity extends AppCompatActivity {
    CustomScrollView sv;
    ScalableLayout sl;
    Button waveBtn;
    ImageView wave;
    ImageView waveText;
    View mask;

    int deviceHeight;
    int deviceWidth;
    private ImageView introByul;
    private ImageView introByulText;
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
    private Button byulBtn;
    private Button fatherBtn;
    private Button birdBtn;
    private Button flowerBtn;
    private Button manBtn;
    private Button momBtn;
    private Button postBtn;
    private Button seagullBtn;
    private Button squidBtn;
    private Button treeBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        bindViews();

        deviceHeight = displayMetrics.heightPixels;
        deviceWidth = displayMetrics.widthPixels;
        sv = (CustomScrollView)findViewById(R.id.sv);
        sl = (ScalableLayout)findViewById(R.id.sl);
        sl.post(new Runnable() {
            @Override
            public void run() {
                int innerWidth = sl.getWidth();
                int innerHeight = sl.getHeight();
                Log.e("length", ""+innerHeight );
                Log.e("length", ""+deviceHeight );
                sv.scrollTo(0,(innerHeight-deviceHeight)/2);
//                if(innerWidth>deviceWidth){
//                    sv.scrollTo((innerWidth-deviceWidth)/2,0);
//                }else{
//                    sv.scrollTo(0,(innerHeight-deviceHeight)/2);
//                }
            }
        });
        sv.setScrolling(false);

        mask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkVisible();
            }
        });

        waveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    checkVisible();
                    mask.setVisibility(View.VISIBLE);
                    wave.setVisibility(View.VISIBLE);
                    waveText.setVisibility(View.VISIBLE);
            }
        });

        birdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introBird.setVisibility(View.VISIBLE);
                introBirdText.setVisibility(View.VISIBLE);
            }
        });

        byulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introByul.setVisibility(View.VISIBLE);
                introByulText.setVisibility(View.VISIBLE);
            }
        });

        fatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introFather.setVisibility(View.VISIBLE);
                introFatherText.setVisibility(View.VISIBLE);
            }
        });
        flowerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introFlower.setVisibility(View.VISIBLE);
                introFlowerText.setVisibility(View.VISIBLE);
            }
        });

        manBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introMan.setVisibility(View.VISIBLE);
                introManText.setVisibility(View.VISIBLE);
            }
        });

        momBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introMom.setVisibility(View.VISIBLE);
                introMomText.setVisibility(View.VISIBLE);
            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introPost.setVisibility(View.VISIBLE);
                introPostText.setVisibility(View.VISIBLE);
            }
        });

        seagullBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introSeagull.setVisibility(View.VISIBLE);
                introSeagullText.setVisibility(View.VISIBLE);
            }
        });

        squidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introSquid.setVisibility(View.VISIBLE);
                introSquidText.setVisibility(View.VISIBLE);
            }
        });

        treeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkVisible();
                mask.setVisibility(View.VISIBLE);
                introTree.setVisibility(View.VISIBLE);
                introTreeText.setVisibility(View.VISIBLE);
            }
        });

    }

    public void checkVisible() {

        wave.setVisibility(View.GONE);
        waveText.setVisibility(View.GONE);

        introBird.setVisibility(View.GONE);
        introBirdText.setVisibility(View.GONE);

        introByul.setVisibility(View.GONE);
        introByulText.setVisibility(View.GONE);

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

    public void bindViews() {

        mask = (View)findViewById(R.id.mask);
        waveBtn = (Button) findViewById(R.id.waveBtn);
        wave = (ImageView)findViewById(R.id.wave);
        waveText = (ImageView)findViewById(R.id.waveText);
        this.introTreeText = (ImageView) findViewById(R.id.introTreeText);
        this.treeBtn = (Button) findViewById(R.id.treeBtn);
        this.introTree = (ImageView) findViewById(R.id.introTree);
        this.introSquidText = (ImageView) findViewById(R.id.introSquidText);
        this.squidBtn = (Button) findViewById(R.id.squidBtn);
        this.introSquid = (ImageView) findViewById(R.id.introSquid);
        this.introSeagullText = (ImageView) findViewById(R.id.introSeagullText);
        this.seagullBtn = (Button) findViewById(R.id.seagullBtn);
        this.introSeagull = (ImageView) findViewById(R.id.introSeagull);
        this.introPostText = (ImageView) findViewById(R.id.introPostText);
        this.postBtn = (Button) findViewById(R.id.postBtn);
        this.introPost = (ImageView) findViewById(R.id.introPost);
        this.introMomText = (ImageView) findViewById(R.id.introMomText);
        this.momBtn = (Button) findViewById(R.id.momBtn);
        this.introMom = (ImageView) findViewById(R.id.introMom);
        this.introManText = (ImageView) findViewById(R.id.introManText);
        this.manBtn = (Button) findViewById(R.id.manBtn);
        this.introMan = (ImageView) findViewById(R.id.introMan);
        this.introFlowerText = (ImageView) findViewById(R.id.introFlowerText);
        this.flowerBtn = (Button) findViewById(R.id.flowerBtn);
        this.introFlower = (ImageView) findViewById(R.id.introFlower);
        this.introBirdText = (ImageView) findViewById(R.id.introBirdText);
        this.birdBtn = (Button) findViewById(R.id.birdBtn);
        this.introBird = (ImageView) findViewById(R.id.introBird);
        this.introFatherText = (ImageView) findViewById(R.id.introFatherText);
        this.fatherBtn = (Button) findViewById(R.id.fatherBtn);
        this.introFather = (ImageView) findViewById(R.id.introFather);
        this.introByulText = (ImageView) findViewById(R.id.introByulText);
        this.byulBtn = (Button) findViewById(R.id.byulBtn);
        this.introByul = (ImageView) findViewById(R.id.introByul);
    }
}
