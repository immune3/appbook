package com.example.dokdofamily01;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ssomai.android.scalablelayout.ScalableLayout;

public class MainActivity extends AppCompatActivity {
    Button taleBtn;
    static Context context;
    boolean splashFlag=true;
    private com.ssomai.android.scalablelayout.ScalableLayout sl;
    private CustomScrollView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.sv = (CustomScrollView) findViewById(R.id.sv);
        this.sl = (ScalableLayout) findViewById(R.id.sl);

        if(splashFlag) {
            splashFlag=false;
            startActivity(new Intent(this,SplashActivity.class));
        }

        context = getApplicationContext();
        taleBtn = (Button)findViewById(R.id.taleBtn);

        taleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TaleActivity.class);
                startActivity(intent);
//                finish();
            }
        });

        BaseFragment.firstFlag = 0;
    }
}
