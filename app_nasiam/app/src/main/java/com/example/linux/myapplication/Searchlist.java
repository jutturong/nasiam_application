package com.example.linux.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * Created by linux on 21/9/2018 AD.
 */

public class Searchlist extends Activity {

    TextView txtforword;//ถัดไป
    TextView txtback; //ย้อนกลับ

    TextView imglistprice; //รายการพระเครื่อง

    ImageView imgsearch; //ค้นหาพระเครื่อง

    ViewSwitcher Vs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.loginpage);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //fix ด้านตั้ง
        setContentView(R.layout.layout_search);

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


//txtforword
        txtforword=(TextView)findViewById(R.id.txtforword);
        txtforword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Searchlist.this,Detaillist.class);
                startActivity(i);
                finish();

            }
        });

        txtback=(TextView)findViewById(R.id.txtback);
        txtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Searchlist.this, SplashScreen.class);
                startActivity(i);
                finish();
            }
        });

        //imagesearch=(ImageView)findViewById(R.id.imagesearch);

        imgsearch=(ImageView)findViewById(R.id.imgsearch);
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Searchlist.this,Detaillist.class);
                startActivity(i);
                finish();
            }
        });

        /*
        imglistprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Searchlist.this, Detaillist.class);
                startActivity(i);
                finish();
            }
        });
        */



    }

}
