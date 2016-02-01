package com.example.switchview;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    public final static int MINIMUMFONTSIZE = 8;
    public final static int MINIMUMLOGICALFONTSIZE = 8;
    public final static int DEFAULTFIXEDFONTSIZE = 13;
    public final static int DEFAULTFONTSIZE = 16;
    private WebView webview;
    private WebViewClient webviewClient;
    private WebChromeClient webviewChromeClient;
    private SfView sfView;
    private CommonView commonView;
    protected int index = 0;
    protected Button btn_switch;
    protected ViewGroup layout;
    private RelativeLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        initView();
        initViewsEvent();
    }

    private void initWebView(){
        webview = new WebView(this);
        webview.setLayoutParams(params);
        initEngine();
    }

    private void initSfView(){
        sfView = new SfView(this);
        sfView.setLayoutParams(params);
    }


    private void initView(){
        layout = (ViewGroup) findViewById(R.id.layout_main);
        commonView = (CommonView) findViewById(R.id.commonview);
        btn_switch = (Button) findViewById(R.id.btn_switch);
        params = (RelativeLayout.LayoutParams) commonView.getLayoutParams();
        params.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        initWebView();
        initSfView();
    }



    private void initViewsEvent() {
        btn_switch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                index++;
                if (index == 3) {
                    index = 0;
                }
                switchView(index);
            }
        });
    }


    private void switchView(int index){
        switch (index){
              case 0:
                  layout.addView(commonView);
                  layout.removeView(webview);
                  break;
              case 1:
                  layout.addView(sfView);
                  layout.removeView(commonView);
                  break;
              case 2:
                  layout.addView(webview);
                  layout.removeView(sfView);
                  break;
          }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void loadAni(){
        LayoutTransition transition = new LayoutTransition();
        layout.setLayoutTransition(transition);

//        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "TranslationX", 720f,0f).setDuration(transition.getDuration(LayoutTransition.APPEARING));
//        transition.setAnimator(LayoutTransition.APPEARING, animIn);

//        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "TranslationX",0f,-720f).setDuration(transition.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
//        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, animOut);

    }


    protected void initEngine(){
        webviewClient = new WebViewClient();
        webviewChromeClient = new WebChromeClient();
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(webviewClient);
        webview.setWebChromeClient(webviewChromeClient);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.setBackgroundColor(Color.RED);
        webview.getSettings().setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
        if(VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1){
            webview.getSettings().setDisplayZoomControls(false);
            webview.removeJavascriptInterface("accessibility");
            webview.removeJavascriptInterface("ccessibilityaversal");
            webview.removeJavascriptInterface("searchBoxJavaBridge_");
        }
        if(VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN){
            try {
                webview.getSettings().setMediaPlaybackRequiresUserGesture(true);
            } catch (NoSuchMethodError e) {

            }

        }
        WebSettings webSettings = webview.getSettings();
        webSettings.setMinimumFontSize(MINIMUMFONTSIZE);
        webSettings.setMinimumLogicalFontSize(MINIMUMLOGICALFONTSIZE);
        webSettings.setDefaultFontSize(DEFAULTFONTSIZE);
        webSettings.setDefaultFixedFontSize(DEFAULTFIXEDFONTSIZE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 10);
        String lightAppPath = getApplicationContext().getDir("zybrowser", Context.MODE_PRIVATE).getPath();
        String appCachePath = lightAppPath + File.separator + "cache";
        webSettings.setAppCachePath(appCachePath);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDatabaseEnabled(true);
        String databasePath = lightAppPath + File.separator + "database";
        webSettings.setGeolocationEnabled(true);
        webSettings.setGeolocationDatabasePath(databasePath);
        webview.loadUrl("https://www.baidu.com/");
    }






}
