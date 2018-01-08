package com.example.dokdofamily01;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.ssomai.android.scalablelayout.ScalableLayout;

/**
 * Created by mapl0 on 2017-12-04.
 */

public class CopyVariable extends Activity {


    private android.widget.ImageView prologueTextImage;
    private com.ssomai.android.scalablelayout.ScalableLayout sl;
    private CustomScrollView sv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prologue);
        this.sv = (CustomScrollView) findViewById(R.id.sv);
        this.sl = (ScalableLayout) findViewById(R.id.sl);
        this.prologueTextImage = (ImageView) findViewById(R.id.prologueTextImage);


    }
}
