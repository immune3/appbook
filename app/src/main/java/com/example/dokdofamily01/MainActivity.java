package com.example.dokdofamily01;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button taleBtn;
    static Context context;
    boolean splashFlag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
