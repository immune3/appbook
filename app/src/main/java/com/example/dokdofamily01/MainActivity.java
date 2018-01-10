package com.example.dokdofamily01;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dokdofamily01.DB.LocalDB;
import com.ssomai.android.scalablelayout.ScalableLayout;

public class MainActivity extends BaseActivity {
    static Context context;
    Button taleBtn, menualBtn;
    boolean splashFlag=true;
    private com.ssomai.android.scalablelayout.ScalableLayout sl;
    private CustomScrollView sv;
    RelativeLayout rl;
    CustomHorizontalScrollView hv;
    int innerWidth,innerHeight;
    private android.widget.Button prologueBtn;
    private int isFirst;
    private LocalDB db;
    private String[][] isFirstArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        if(splashFlag) {
            splashFlag=false;
            startActivity(new Intent(this,SplashActivity.class));
        }

        selectPrologue();
        bindViews();
        setValues();
        setUpEvents();

    }

    public void selectPrologue() {
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

    @Override
    public void setAnimation() {
        super.setAnimation();
    }

    @Override
    public void setUpEvents() {
        super.setUpEvents();
        taleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TaleActivity.class);
                intent.putExtra("isAutoRead", true);
                startActivity(intent);
            }
        });

        menualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TaleActivity.class);
                intent.putExtra("isAutoRead", false);
                startActivity(intent);
            }
        });

        prologueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PrologueActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {
        super.setValues();
        if(isFirst == 1) prologueBtn.setVisibility(View.GONE);
        else prologueBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void bindViews() {
        super.bindViews();
//        sv = (CustomScrollView) findViewById(R.id.sv);
//        rl = (RelativeLayout)findViewById(R.id.rl);
//        sl = (ScalableLayout)findViewById(R.id.sl);
//        hv = new CustomHorizontalScrollView(this);
//        HorizontalScrollView.LayoutParams lp = new HorizontalScrollView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        hv.setLayoutParams(lp);

        taleBtn = (Button)findViewById(R.id.taleBtn);
        menualBtn = (Button)findViewById(R.id.menualBtn);
        prologueBtn = (Button) findViewById(R.id.prologueBtn);

    }

    private long pressedTime;

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if(pressedTime == 0){
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();
        }else{
            int second = (int)(System.currentTimeMillis() - pressedTime);

            if(second > 2000){
                pressedTime = 0;
            }else{
                finish();
            }
        }


    }
}
