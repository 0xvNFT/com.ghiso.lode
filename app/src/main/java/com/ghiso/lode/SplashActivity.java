package com.ghiso.lode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.ghiso.lode.View.LoginApp;
import com.ghiso.lode.View.WebViewLink;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 900;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences save=getSharedPreferences("Ghiso", MODE_PRIVATE);

        String phone=save.getString("phone_number","");
        int ki=save.getInt("ki",0);

        new Handler().postDelayed(() -> {
            if(ki>0){
                if(phone.equals("0355555102")){
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }else {
                    startActivity(new Intent(SplashActivity.this, WebViewLink.class));
                }
            }else {
                Intent intent = new Intent(SplashActivity.this, LoginApp.class);
                startActivity(intent);
            }
                finish();

        }, SPLASH_TIMEOUT);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
