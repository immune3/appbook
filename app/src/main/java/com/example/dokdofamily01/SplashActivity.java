package com.example.dokdofamily01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.dokdofamily01.DB.LocalDB;

/**
 * Created by heronation on 2017-12-01.
 */

public class SplashActivity extends Activity {

    LocalDB db;
    String[][] isFirstArray;
    int isFirst;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        bindViews();
        setValues();
        setAnimation();
        setUpEvents();

    }

    public void setUpEvents() {

        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isFirst == 1) {
                    db.query("update Prologue set isFirst = 0");

                    Intent intent = new Intent(getApplicationContext(), PrologueActivity.class);
                    startActivity(intent);
                    finish();
                } else finish();
//                finish();
            }
        }, 3000); // 3초 후 이미지를 닫습니다

    }

    public void setAnimation() {

    }



    public void setValues() {

        db = new LocalDB(BaseActivity.context);
        isFirstArray = db.selectQuery("select isFirst from Prologue;");
        if(isFirstArray == null) {
            db.query("insert into Prologue values(1);");
        }

        isFirstArray = db.selectQuery("select isFirst from Prologue;");
        System.out.println("isFirstArray : " + isFirstArray);
        Log.d("isFirstArray[0][0]", isFirstArray[0][0]);
        isFirst = Integer.parseInt(isFirstArray[0][0]);
        isFirstArray = null; // 메모리 비어줌
        db = null;
    }

    public void bindViews() {

    }
}
