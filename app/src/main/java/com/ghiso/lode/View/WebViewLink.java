package com.ghiso.lode.View;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.ghiso.lode.R;


public class WebViewLink extends AppCompatActivity {
    ProgressBar prosec;
    WebView web1, web2, web3;
    int index_web=1;
    String link1,link2,link3,link4;
    SharedPreferences save;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_app);
        web1=findViewById(R.id.web1);
        web2=findViewById(R.id.web2);
        web3=findViewById(R.id.web3);

        prosec=findViewById(R.id.prosec);
        //set prosecbar

        prosec.setMax(100);
        setupWebView();

        //get link
        save=getSharedPreferences("KingFunApp",MODE_PRIVATE);
        link1=save.getString("link1","");
        link2=save.getString("link2","");
        link3=save.getString("link3","");
        link4=save.getString("link4","");
        Log.d("AAAA =",link1+" && "+link2+" && "+ link3+" && "+link4);
        web1.loadUrl(link1);


    }
    private void setupWebView() {
        web1.getSettings().setJavaScriptEnabled(true);
        this.web1.setWebViewClient(new WebViewClient());
        this.web1.getSettings().setDomStorageEnabled(true);
        this.web1.getSettings().setLoadWithOverviewMode(true);
        this.web1.getSettings().setJavaScriptEnabled(true);
        this.web1.getSettings().setSaveFormData(true);
        this.web1.setWebChromeClient(
                new WebChromeClient(){
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        prosec.setProgress(newProgress);
                        if (newProgress == 100) {
                            prosec.setVisibility(View.INVISIBLE);
                        } else {
                            prosec.setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                        WebView newWebView = new WebView(WebViewLink.this);
                        newWebView.getSettings().setJavaScriptEnabled(true);
                        newWebView.getSettings().setSupportZoom(true);
                        newWebView.getSettings().setBuiltInZoomControls(true);
                        newWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
                        newWebView.getSettings().setSupportMultipleWindows(true);
                        newWebView.getSettings().setDomStorageEnabled(true);
                        newWebView.getSettings().setLoadWithOverviewMode(true);
                        newWebView.getSettings().setJavaScriptEnabled(true);
                        newWebView.getSettings().setSaveFormData(true);
                        newWebView.getSettings().setAllowFileAccess(true);
                        newWebView.getSettings().setAllowFileAccess(true);
                        newWebView.getSettings().setAllowContentAccess(true);
                        newWebView.getSettings().setSupportMultipleWindows(true);
                        newWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                        newWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
                        newWebView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36");
                        view.addView(newWebView);
                        WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                        transport.setWebView(newWebView);
                        resultMsg.sendToTarget();
                        newWebView.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                Log.d("Load URL: ",url);
                                web2.setVisibility(View.VISIBLE);
                                web1.setVisibility(View.GONE);
                                web2.loadUrl(url);
                                index_web=2;
                                return true;
                            }
                        });
                        return true;
                    }
                });

        this.web1.getSettings().setAllowFileAccess(true);

        this.web1.getSettings().setAllowFileAccess(true);
        this.web1.getSettings().setAllowContentAccess(true);
        this.web1.getSettings().setSupportMultipleWindows(true);
        this.web1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.web1.getSettings().setJavaScriptEnabled(true);
        this.web1.getSettings().setSupportMultipleWindows(true);
        this.web1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.web1.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        this.web1.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36");
        //webview 2
        web2.getSettings().setJavaScriptEnabled(true);
        this.web2.setWebViewClient(new WebViewClient());
        this.web2.getSettings().setDomStorageEnabled(true);
        this.web2.getSettings().setLoadWithOverviewMode(true);
        this.web2.getSettings().setJavaScriptEnabled(true);
        this.web2.getSettings().setSaveFormData(true);
        this.web2.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                prosec.setProgress(newProgress);
                if (newProgress == 100) {
                    prosec.setVisibility(View.INVISIBLE);
                } else {
                    prosec.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {

                WebView newWebView = new WebView(WebViewLink.this);
                newWebView.getSettings().setJavaScriptEnabled(true);
                newWebView.getSettings().setSupportZoom(true);
                newWebView.getSettings().setBuiltInZoomControls(true);
                newWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
                newWebView.getSettings().setSupportMultipleWindows(true);
                newWebView.getSettings().setDomStorageEnabled(true);
                newWebView.getSettings().setLoadWithOverviewMode(true);
                newWebView.getSettings().setJavaScriptEnabled(true);
                newWebView.getSettings().setSaveFormData(true);
                newWebView.getSettings().setAllowFileAccess(true);
                newWebView.getSettings().setAllowFileAccess(true);
                newWebView.getSettings().setAllowContentAccess(true);
                newWebView.getSettings().setSupportMultipleWindows(true);
                newWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                newWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
                newWebView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36");
                view.addView(newWebView);
                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                transport.setWebView(newWebView);
                resultMsg.sendToTarget();

                newWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        //view.loadUrl(url);
                        Log.d("Open URL", url);
                        web3.loadUrl(url);
                        web3.setVisibility(View.VISIBLE);
                        web2.setVisibility(View.GONE);
                        web1.setVisibility(View.GONE);
                        index_web=3;
                        return true;
                    }
                });
                return true;
            }
        });
        this.web2.getSettings().setAllowFileAccess(true);
        this.web2.getSettings().setAllowFileAccess(true);
        this.web2.getSettings().setAllowContentAccess(true);
        this.web2.getSettings().setSupportMultipleWindows(true);
        this.web2.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.web2.getSettings().setJavaScriptEnabled(true);
        this.web2.getSettings().setSupportMultipleWindows(true);
        this.web2.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.web2.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        this.web2.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36");

        //webview 3
        web3.getSettings().setJavaScriptEnabled(true);
        this.web3.setWebViewClient(new WebViewClient());
        this.web3.getSettings().setDomStorageEnabled(true);
        this.web3.getSettings().setLoadWithOverviewMode(true);
        this.web3.getSettings().setJavaScriptEnabled(true);
        this.web3.getSettings().setSaveFormData(true);
        this.web3.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                prosec.setProgress(newProgress);
                if (newProgress == 100) {
                    prosec.setVisibility(View.INVISIBLE);
                } else {
                    prosec.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {

                WebView newWebView = new WebView(WebViewLink.this);
                newWebView.getSettings().setJavaScriptEnabled(true);
                newWebView.getSettings().setSupportZoom(true);
                newWebView.getSettings().setBuiltInZoomControls(true);
                newWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
                newWebView.getSettings().setSupportMultipleWindows(true);
                newWebView.getSettings().setDomStorageEnabled(true);
                newWebView.getSettings().setLoadWithOverviewMode(true);
                newWebView.getSettings().setJavaScriptEnabled(true);
                newWebView.getSettings().setSaveFormData(true);
                newWebView.getSettings().setAllowFileAccess(true);
                newWebView.getSettings().setAllowFileAccess(true);
                newWebView.getSettings().setAllowContentAccess(true);
                newWebView.getSettings().setSupportMultipleWindows(true);
                newWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                newWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
                newWebView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36");

                view.addView(newWebView);
                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                transport.setWebView(newWebView);
                resultMsg.sendToTarget();

                newWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        //view.loadUrl(url);
                        Log.d("Open URL", url);
                        web3.loadUrl(url);
                        return true;
                    }
                });

                return true;


            }
        });
        this.web3.getSettings().setAllowFileAccess(true);
        this.web3.getSettings().setAllowFileAccess(true);
        this.web3.getSettings().setAllowContentAccess(true);
        this.web3.getSettings().setSupportMultipleWindows(true);
        this.web3.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.web3.getSettings().setJavaScriptEnabled(true);
        this.web3.getSettings().setSupportMultipleWindows(true);
        this.web3.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.web3.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        this.web3.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36");



        SharedPreferences sharedPreferences = getSharedPreferences("sysdata",MODE_PRIVATE);
        String AccessTime = sharedPreferences.getString("AccessTime", "0");


        web1.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                prosec.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                prosec.setVisibility(View.GONE);
                int AccessTimeC = Integer.parseInt(AccessTime);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("AccessTime", String.valueOf(AccessTimeC+1));
                if(AccessTimeC<1==true && url.contains("introduce")){
                    editor.putString("his", "introduce");
                }
                editor.commit();
                web1.evaluateJavascript("(function(){  document.getElementsByClassName('service__rain draggable vdr')[0].innerHTML=\"<a class='service__link' href='https://tk88game.com/cskh/index.html'></a>\";document.getElementsByClassName('user-links')[0].innerHTML=`<a data-v-61fc9f0a='' href='#/login' class='nav-link font-link' data-v-f3f7587a=''>Đăng Nhập</a> | <a data-v-61fc9f0a='' href='"+link4+"' class='nav-link font-link' data-v-f3f7587a=''>Đăng Ký</a>`})();", paRes -> {
                    if (paRes != null && !paRes.isEmpty() && !paRes.equals("null")) {
                        // TODO: You will use get value
                    }
                });
                web1.evaluateJavascript("(function(){setInterval(()=>{document.getElementsByClassName('row options')[0].innerHTML=`<style>body { display: flex; justify-content: center; align-items: center; } body { width: 100%; height: 90vh; } .button { display: block; width: 320px; max-width: 100%; margin: 0 auto; margin-bottom: 0; overflow: hidden; position: relative; transform: translatez(0); text-decoration: none; box-sizing: border-box; font-size: 24px; font-weight: normal; box-shadow: 0 9px 18px rgba(0,0,0,0.2); } .instagram { text-align: center; border-radius: 50px; padding: 26px; color: white; background: #BD3381; transition: all 0.2s ease-out 0s; } .gradient { display: block; position: absolute; top: 0; right: 0; width: 100%; height: 100%; bottom: auto; margin: auto; z-index: -1; background: radial-gradient(90px circle at top center, rgba(238,88,63,.8) 30%, rgba(255,255,255,0)); transition: all 0s ease-out 0s; transform: translatex(-140px); animation: 18s linear 0s infinite move; } @keyframes move { 0% { transform: translatex(-140px); } 25% { transform: translatex(140px); opacity: 0.3; } 50% { transform: translatex(140px); opacity: 1; background: radial-gradient(90px circle at bottom center, rgba(238,88,63,.5) 30%, rgba(255,255,255,0)); } 75% { transform: translatex(-140px); opacity: 0.3; } 100% { opacity: 1; transform: translatex(-140px); background: radial-gradient(90px circle at top center, rgba(238,88,63,.5) 30%, rgba(255,255,255,0)); } }</style><a href='"+link4+"' class='button instagram'><span class='gradient'></span>Đăng Ký</a>`;},1000)})();", paRes -> {
                    if (paRes != null && !paRes.isEmpty() && !paRes.equals("null")) {
                        // TODO: You will use get value
                    }
                });
            }
        });

        web2.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                prosec.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                prosec.setVisibility(View.GONE);

            }
        });

        web3.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                prosec.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                prosec.setVisibility(View.GONE);

            }
        });

        prosec.setProgress(0);
    }

    public void closeGame(){

        AlertDialog.Builder builder = new AlertDialog.Builder(WebViewLink.this);
        builder.setCancelable(true);
        builder.setTitle("Đóng sảnh Game");
        builder.setMessage("Bạn chắc chắn muốn thoát?");
        builder.setPositiveButton("Thoát",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if( index_web==2){
                            web1.setVisibility(View.INVISIBLE);

                            String baseUrl = "https://example.com/";
                            web2.loadDataWithBaseURL(baseUrl, "", "text/html", null, baseUrl);
                            web1.setVisibility(View.VISIBLE);
                            index_web=1;
                        }
                        else if(index_web==3){
                            web3.setVisibility(View.INVISIBLE);
                            String baseUrl = "https://example.com/";
                            web3.loadDataWithBaseURL(baseUrl, "", "text/html", null, baseUrl);
                            web2.setVisibility(View.VISIBLE);
                            index_web=2;
                        }else{
                        }
                    }
                });
        builder.setNegativeButton("Không Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void onBackPressed() {
        if( index_web==2){
            closeGame();
        }else  if(index_web==3){
            closeGame();
        }else{
            if(web1.canGoBack()) {
                web1.goBack();
            } else {
            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState )
    {
        super.onSaveInstanceState(outState);
        web1.saveState(outState);
        web2.saveState(outState);
        web3.saveState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        web1.restoreState(savedInstanceState);
        web2.restoreState(savedInstanceState);
        web3.restoreState(savedInstanceState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //This is used to hide/show 'Status Bar' & 'System Bar'. Swip bar to get it as visible.
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    public  void HOME(View view){
        web1.loadUrl(link1);
    }
    public  void CHANGE(View view){
        web1.loadUrl(link2);
    }
    public  void CONTACT(View view){
        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(link3)));
    }


}