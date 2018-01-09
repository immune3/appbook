package com.example.dokdofamily01;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dokdofamily01.DB.LocalDB;
import com.ssomai.android.scalablelayout.ScalableLayout;

public class MainActivity extends BaseActivity {
    static Context context;
    Button taleBtn, menualBtn;
    boolean splashFlag=true;
    private com.ssomai.android.scalablelayout.ScalableLayout sl;
    private CustomScrollView sv;
    private android.widget.Button prologueBtn;
    private int isFirst;
    private LocalDB db;
    private String[][] isFirstArray;

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

        selectPrologue();

        taleBtn = (Button)findViewById(R.id.taleBtn);
        taleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TaleActivity.class);
                intent.putExtra("isAutoRead", true);
                startActivity(intent);
//                finish();
            }
        });

        menualBtn = (Button)findViewById(R.id.menualBtn);
        menualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TaleActivity.class);
                intent.putExtra("isAutoRead", false);
                startActivity(intent);
            }
        });

        this.prologueBtn = (Button) findViewById(R.id.prologueBtn);
        prologueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PrologueActivity.class);
                startActivity(intent);
            }
        });

        if(isFirst == 1) prologueBtn.setVisibility(View.GONE);
        else prologueBtn.setVisibility(View.VISIBLE);

        BaseFragment.firstFlag = 0;
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
    }
}
