package com.onwards.aihealthcareconsole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    // private String URL = "http://onwards.iptime.org:50003/console";
    // private String URL = "http://lifelogop.ghealth.or.kr/console";
    private String URL = "http://221.168.252.145:10002/console";
    //private String URL = "https://lifelogop.ghealth.or.kr/console";
    //private String URL = "https://gslifelogop.ghealth.or.kr/console";
    //private String URL = "https://www.youtube.com/";

    private WebView mwv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        플로팅 홈버튼
//        FloatingActionButton fb = findViewById(R.id.fab);
//        fb.setBackgroundColor(Color.parseColor("#FFFFFF"));
//        fb.setOnClickListener(view -> finish());

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // 가로 고정
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 가로 고정

        mwv = (WebView) findViewById(R.id.webView);

        WebSettings mws = mwv.getSettings(); // Mobile Web Setting
        mws.setBuiltInZoomControls(false);   // 확대 축소
        mws.setSupportZoom(false);           // 확대 축소 지원
        mws.setJavaScriptEnabled(true);      // 자바스크립트 허용
        mws.setUseWideViewPort(true);
        mws.setLoadWithOverviewMode(false);  // 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정

        mwv.setVerticalScrollBarEnabled(false);   //세로 스크롤

        // 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정
        mwv.setInitialScale(100);

        // SSL Certificate 허용
        mwv.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        mwv.setWebChromeClient(new WebChromeClient());
        mwv.loadUrl(URL);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mwv.canGoBack()) {
                mwv.goBack();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}