package com.example.dokdofamily01;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ssomai.android.scalablelayout.ScalableLayout;

/**
 * Created by mapl0 on 2017-12-04.
 */

public class CopyVariable extends Activity {

    private android.widget.Button waveBtn;
    private android.widget.Button fatherBtn;
    private android.widget.Button flowerBtn;
    private android.widget.Button birdBtn;
    private android.widget.Button manBtn;
    private android.widget.Button momBtn;
    private android.widget.Button postBtn;
    private android.widget.Button seagullBtn;
    private android.widget.Button byulBtn;
    private android.widget.Button squidBtn;
    private android.widget.Button treeBtn;
    private android.view.View mask;
    private android.widget.ImageView wave;
    private android.widget.ImageView waveText;
    private android.widget.ImageView introByul;
    private android.widget.ImageView introByulText;
    private android.widget.ImageView introFather;
    private android.widget.ImageView introFatherText;
    private android.widget.ImageView introBird;
    private android.widget.ImageView introBirdText;
    private android.widget.ImageView introFlower;
    private android.widget.ImageView introFlowerText;
    private android.widget.ImageView introMan;
    private android.widget.ImageView introManText;
    private android.widget.ImageView introMom;
    private android.widget.ImageView introMomText;
    private android.widget.ImageView introPost;
    private android.widget.ImageView introPostText;
    private android.widget.ImageView introSeagull;
    private android.widget.ImageView introSeagullText;
    private android.widget.ImageView introSquid;
    private android.widget.ImageView introSquidText;
    private android.widget.ImageView introTree;
    private android.widget.ImageView introTreeText;
    private com.ssomai.android.scalablelayout.ScalableLayout sl;
    private CustomScrollView sv;
    private android.widget.RelativeLayout rl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        this.rl = (RelativeLayout) findViewById(R.id.rl);
        this.sv = (CustomScrollView) findViewById(R.id.sv);
        this.sl = (ScalableLayout) findViewById(R.id.sl);
        this.introTreeText = (ImageView) findViewById(R.id.introTreeText);
        this.introTree = (ImageView) findViewById(R.id.introTree);
        this.introSquidText = (ImageView) findViewById(R.id.introSquidText);
        this.introSquid = (ImageView) findViewById(R.id.introSquid);
        this.introSeagullText = (ImageView) findViewById(R.id.introSeagullText);
        this.introSeagull = (ImageView) findViewById(R.id.introSeagull);
        this.introPostText = (ImageView) findViewById(R.id.introPostText);
        this.introPost = (ImageView) findViewById(R.id.introPost);
        this.introMomText = (ImageView) findViewById(R.id.introMomText);
        this.introMom = (ImageView) findViewById(R.id.introMom);
        this.introManText = (ImageView) findViewById(R.id.introManText);
        this.introMan = (ImageView) findViewById(R.id.introMan);
        this.introFlowerText = (ImageView) findViewById(R.id.introFlowerText);
        this.introFlower = (ImageView) findViewById(R.id.introFlower);
        this.introBirdText = (ImageView) findViewById(R.id.introBirdText);
        this.introBird = (ImageView) findViewById(R.id.introBird);
        this.introFatherText = (ImageView) findViewById(R.id.introFatherText);
        this.introFather = (ImageView) findViewById(R.id.introFather);
        this.introByulText = (ImageView) findViewById(R.id.introByulText);
        this.introByul = (ImageView) findViewById(R.id.introByul);
        this.waveText = (ImageView) findViewById(R.id.waveText);
        this.wave = (ImageView) findViewById(R.id.wave);
        this.mask = (View) findViewById(R.id.mask);
        this.treeBtn = (Button) findViewById(R.id.treeBtn);
        this.squidBtn = (Button) findViewById(R.id.squidBtn);
        this.byulBtn = (Button) findViewById(R.id.byulBtn);
        this.seagullBtn = (Button) findViewById(R.id.seagullBtn);
        this.postBtn = (Button) findViewById(R.id.postBtn);
        this.momBtn = (Button) findViewById(R.id.momBtn);
        this.manBtn = (Button) findViewById(R.id.manBtn);
        this.birdBtn = (Button) findViewById(R.id.birdBtn);
        this.flowerBtn = (Button) findViewById(R.id.flowerBtn);
        this.fatherBtn = (Button) findViewById(R.id.fatherBtn);
        this.waveBtn = (Button) findViewById(R.id.waveBtn);



    }
}
