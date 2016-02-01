package com.example.switchview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainFragmentActivity extends AppCompatActivity {

    private CommonViewFragment commonViewFragment;
    private WebViewFragment webViewFragment;
    private SfViewFragment sfViewFragment;
    private FragmentManager fragmentManager;
    protected ViewGroup layout;
    protected Button btn_switch;
    protected int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fag);
        initView();
        initViewsEvent();
    }

    private void initView(){
        layout = (ViewGroup) findViewById(R.id.layout_main);
        btn_switch = (Button) findViewById(R.id.btn_switch);
        fragmentManager = getSupportFragmentManager();
        commonViewFragment = new CommonViewFragment();
        webViewFragment = new WebViewFragment();
        sfViewFragment = new SfViewFragment();
        fragmentManager.beginTransaction().add(R.id.layout_main, webViewFragment).add(R.id.layout_main, sfViewFragment).add(R.id.layout_main, commonViewFragment).commitAllowingStateLoss();
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
                  fragmentManager.beginTransaction().show(commonViewFragment).hide(sfViewFragment).hide(webViewFragment).commitAllowingStateLoss();
                  break;
              case 1:
                  fragmentManager.beginTransaction().show(sfViewFragment).hide(commonViewFragment).hide(webViewFragment).commitAllowingStateLoss();
                  break;
              case 2:
                  fragmentManager.beginTransaction().show(webViewFragment).hide(sfViewFragment).hide(commonViewFragment).commitAllowingStateLoss();
                  break;
          }
    }





}
