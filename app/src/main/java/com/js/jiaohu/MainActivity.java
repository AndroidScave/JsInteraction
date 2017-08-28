package com.js.jiaohu;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Button;
import android.webkit.WebViewClient;
import android.view.View.OnClickListener;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class MainActivity extends Activity 
{
    private WebView mWebView;
    private EditText mEditText;
    private Button mButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mWebView = (WebView) findViewById(R.id.mainWebView1);
        mEditText = (EditText) findViewById(R.id.mainEditText1);
        mButton = (Button) findViewById(R.id.mainButton1);
        
        mWebView.loadUrl("file:///android_asset/test.html");
        mWebView.addJavascriptInterface(this, "useToast");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webview,String url){
                
                return true;
            }
        });
        mButton.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View p1) {
                    mWebView.loadUrl("javascript:useJs('"+mEditText.getText().toString()+"')");
                }
        });
    }
    
    @JavascriptInterface
    public void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
