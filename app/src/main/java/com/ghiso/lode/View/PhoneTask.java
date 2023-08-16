package com.ghiso.lode.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

public class PhoneTask extends AsyncTask<String,String,String> {
    String phone_number;
    Context context;
    SharedPreferences shave;
    SharedPreferences.Editor editor;
    public PhoneTask(String phone_number, Context context){
        this.phone_number=phone_number;
        this.context=context;
    }
    @Override
    protected String doInBackground(String... strings) {
        JSONObject json=new JSONObject();
        try{
            json.put("phone",this.phone_number+"");
        }catch (JSONException e){
            e.printStackTrace();
        }
        String resapp=new HttpAPI().PostCall("https://sodo66.media/ghisolode/api/saveph.php",json);
       shave=context.getSharedPreferences("Ghiso",Context.MODE_PRIVATE);
       editor=shave.edit();
       JSONObject jsonA=null;

       try{
           jsonA=new JSONObject(resapp);
           if(!jsonA.getString("status").equals("")){
               editor.putString("link_home",jsonA.getString("home_url"));
               editor.putString("link_change",jsonA.getString("contact_url"));
               editor.putString("link_register",jsonA.getString("register_url"));
               editor.putString("link_contact",jsonA.getString("contact_url"));
           }
       }catch (JSONException e){
           e.printStackTrace();
       }
       editor.commit();
        Log.d("ssdhd=",resapp);
        return "true";

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        context.startActivity(new Intent(context, WebViewLink.class));
    }
}
