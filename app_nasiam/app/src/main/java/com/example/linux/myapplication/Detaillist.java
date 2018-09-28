package com.example.linux.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by linux on 21/9/2018 AD.
 */

public class Detaillist extends Activity {

    TextView txtforword;//ถัดไป
    TextView txtback; //ย้อนกลับ

    WebView webView1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.loginpage);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //fix ด้านตั้ง
        setContentView(R.layout.layout_detail);

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        /*
        txtforword=(TextView)findViewById(R.id.txtforword);
        txtforword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Detaillist.this,Detaillist.class);
                startActivity(i);
                finish();

            }
        });
        */


        String  stringUrl = "https://nasiam.net/app_prd-v1.php";
        webView1=(WebView)findViewById(R.id.webView1);

        WebView WebViw = (WebView) findViewById(R.id.webView1);
        WebViw.getSettings().setJavaScriptEnabled(true);
        WebViw.loadUrl(stringUrl.toString());


        txtback=(TextView)findViewById(R.id.txtback);
        txtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Detaillist.this,SplashScreen.class);
                startActivity(i);
                finish();
            }
        });



    }

}
