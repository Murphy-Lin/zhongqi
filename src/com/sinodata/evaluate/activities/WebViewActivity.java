package com.sinodata.evaluate.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.sinodata.evaluate.R;
import com.sinodata.evaluate.BaseActivity;

public class WebViewActivity extends BaseActivity {

	private ImageView iv_back;
	private String url;
	private WebView webView;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		
		url = getIntent().getAction();
		
		initView();
		initEvent();
	}
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		iv_back = (ImageView) findViewById(R.id.iv_webview_back);
		webView = (WebView) findViewById(R.id.webview);
		
	}
	
	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		iv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
			
		});
		
	}
	@Override 
    //设置回退  
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {  
            webView.goBack(); //goBack()表示返回WebView的上一页面  
            return true;  
        }  
        return super.onKeyDown(keyCode, event);
    }  
}
