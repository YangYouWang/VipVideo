package com.yangyouwang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.yangyouwang.utils.ProperTies;

import java.util.Properties;


public class MainActivity extends AppCompatActivity {

    private boolean isQuit = false;
    private String url = null;
    private String data = null;
    private Spinner spinner;
    private EditText et_url;
    private WebView webView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();
        InitWebView();
    }
    //初始化
    private void InitView() {
        et_url = findViewById(R.id.et_url);
        spinner = findViewById(R.id.spinner_text);
        webView = findViewById(R.id.webView);
    }
    private void InitWebView(){
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(false);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
    }
    public void click(View v) {
        if(TextUtils.isEmpty(et_url.getText())) {
            Toast.makeText(MainActivity.this,"播放视频地址为空!", Toast.LENGTH_LONG).show();
        }else if(!Patterns.WEB_URL.matcher(et_url.getText()).matches()){
            Toast.makeText(MainActivity.this,"播放视频地址错误,请重新输入!", Toast.LENGTH_LONG).show();
        }else{
            Properties proper = ProperTies.getProperties(getApplicationContext());
            switch ((String)spinner.getSelectedItem()){
                case "接口一":
                    data = proper.getProperty("url1");
                    break;
                case "接口二":
                    data = proper.getProperty("url2");
                    break;
                case "接口三":
                    data = proper.getProperty("url3");
                    break;
                case "接口四":
                    data = proper.getProperty("url4");
                    break;
                case "接口五":
                    data = proper.getProperty("url5");
                    break;
                case "接口六":
                    data = proper.getProperty("url6");
                    break;
                case "接口七":
                    data = proper.getProperty("url7");
                    break;
                case "接口八":
                    data = proper.getProperty("url8");
                    break;
                case "接口九":
                    data = proper.getProperty("url9");
                    break;
                case "接口十":
                    data = proper.getProperty("url10");
                    break;
            }
            url =data + et_url.getText().toString();
            webView.loadUrl(url);
        }
    }
    @Override
    public void onBackPressed() {
        if (!isQuit) {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            isQuit = true;
            //这段代码意思是,在两秒钟之后isQuit会变成false
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        isQuit = false;
                    }
                }
            }).start();
        } else {
            System.exit(0);
        }
    }
}