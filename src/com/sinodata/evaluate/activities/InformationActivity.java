package com.sinodata.evaluate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.sinodata.evaluate.BaseActivity;
import com.sinodata.evaluate.R;

/**
 * 
 * @author 林杰
 *
 */
public class InformationActivity extends BaseActivity implements OnClickListener {

	private ImageView iv_information_constitution_types;
	private ImageView iv_information_prof_wangqi;
	private ImageView iv_information_constitution_conditioning;
	private ImageView iv_back;
	private static final String url = "file:///android_asset/html/WQJS.html";
	private static final String url2 = "file:///android_asset/html/JZTZ.html";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_information);
		
		initView();
		initEvent();
	}
	
	public void initView(){
		iv_information_constitution_types = (ImageView) findViewById(R.id.iv_information_constitution_types);
		iv_information_prof_wangqi = (ImageView) findViewById(R.id.iv_information_prof_wangqi);
		iv_information_constitution_conditioning = (ImageView) findViewById(R.id.iv_information_constitution_conditioning);
		iv_back = (ImageView) findViewById(R.id.iv_information_back);
	}
	
	public void initEvent(){
		iv_back.setOnClickListener(this);
		iv_information_prof_wangqi.setOnClickListener(this);
		iv_information_constitution_types.setOnClickListener(this);
		iv_information_constitution_conditioning.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_information_back:
			finish();
			break;
		case R.id.iv_information_prof_wangqi:
			Intent intent = new Intent(this,WebViewActivity.class);
			intent.setAction(url);
			startActivity(intent);
			break;
		case R.id.iv_information_constitution_types :
			Intent intent2 = new Intent(this,WebViewActivity.class);
			intent2.setAction(url2);
			startActivity(intent2);
			break;
		case R.id.iv_information_constitution_conditioning :
			Intent intent1 = new Intent(this,ConstitutionInfo.class);
			startActivity(intent1);
			break;
		default:
			break;
		}
	}
}
