package com.example.switchview;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;

/**
 * Created by Administrator on 2016/1/28.
 */
public class WebViewFragment extends Fragment {

    public final static int MINIMUMFONTSIZE = 8;
    public final static int MINIMUMLOGICALFONTSIZE = 8;
    public final static int DEFAULTFIXEDFONTSIZE = 13;
    public final static int DEFAULTFONTSIZE = 16;
    private WebView webview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fag_webview, null);
        webview = (WebView) view.findViewById(R.id.webview);
        initEngine();
        return view;

    }


    protected void initEngine(){
        WebViewClient webviewClient = new WebViewClient();
        WebChromeClient webviewChromeClient = new WebChromeClient();
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(webviewClient);
        webview.setWebChromeClient(webviewChromeClient);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1){
            webview.getSettings().setDisplayZoomControls(false);
            webview.removeJavascriptInterface("accessibility");
            webview.removeJavascriptInterface("ccessibilityaversal");
            webview.removeJavascriptInterface("searchBoxJavaBridge_");
        }
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN){
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
        String lightAppPath = getActivity().getApplicationContext().getDir("zybrowser", Context.MODE_PRIVATE).getPath();
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
