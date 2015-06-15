package com.sinodata.evaluate.activities;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sinodata.evaluate.BaseActivity;
import com.sinodata.evaluate.R;
import com.sinodata.evaluate.utils.HttpUtil;
import com.sinodata.evaluate.utils.LicenseManager;

public class PrepareActivity extends BaseActivity {

	protected static final int ENTER_HOME = 1;

	// public WifiConnectManager wifiConnectManager;
	private TextView tv_deviceID;
	private EditText et_license;
	private EditText et_username;
	private EditText et_protocol;
	private Button btn_register;
	private String urlString = "http://123.57.59.200:8080/api/MobileProduct/DeviceReg";
	private RequestParams params;
	// private SharedPreferences sp;
	private LicenseManager lm;

	private void enterHome() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prepare_view);

		initView();
		// lm.checkLicense(this);
		initEvent();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		// sp = getSharedPreferences("config", MODE_PRIVATE);
		// wifiConnectManager = new WifiConnectManager(this);
		params = new RequestParams();
		lm = new LicenseManager();
		tv_deviceID = (TextView) findViewById(R.id.tv_id);
		et_username = (EditText) findViewById(R.id.et_username);
		et_license = (EditText) findViewById(R.id.et_license);
		btn_register = (Button) findViewById(R.id.btn_register);
		et_protocol = (EditText) findViewById(R.id.et_protocol);
	}

	@Override
	public void initEvent() {
		final String serialNumber = android.os.Build.SERIAL;
		tv_deviceID.setText(serialNumber);
		et_protocol.setText(R.string.protocol_in);
		et_protocol.setOnTouchListener(new View.OnTouchListener() {  
            
            @Override  
            public boolean onTouch(View v, MotionEvent event) {  
                if (event.getAction() == MotionEvent.ACTION_DOWN) {  
                    v.getParent().requestDisallowInterceptTouchEvent(true);  
                } else if (event.getAction() == MotionEvent.ACTION_UP) {  
                    v.getParent().requestDisallowInterceptTouchEvent(false);  
                } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {  
                    v.getParent().requestDisallowInterceptTouchEvent(false);  
                }  
                return false;  
            }  
        });  
		
		btn_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String licenseNumber = et_license.getText().toString();
				final String username = et_username.getText().toString();
				
				if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(licenseNumber)) {
					//测试代码
//					lm.saveLicense(licenseNumber,username, serialNumber);
//					enterHome();
//					finish();
					params.put("TerminalNum", serialNumber);
					params.put("TerminaSN", licenseNumber);
					params.put("UserName", username);
					HttpUtil.post(urlString, params,new AsyncHttpResponseHandler() {
								@Override
								public void onSuccess(int statusCode,
										String content) {
									// TODO Auto-generated method stub
									super.onSuccess(statusCode, content);
									try {
										JSONObject obj = JSON.parseObject(content);
										if (obj.getBoolean("success") == true) {
											Toast.makeText(PrepareActivity.this,obj.getString("message"),Toast.LENGTH_LONG).show();
											lm.saveCode(obj.getString("code"));
											JSONArray array = obj.getJSONArray("data");
											JSONObject dataObj = array.getJSONObject(0);
											lm.saveCompany(dataObj.getString("CompanyName"));
											lm.saveLicense(licenseNumber,username, serialNumber);
											enterHome();
											finish();
										} else {
											Toast.makeText(PrepareActivity.this,obj.getString("message"),Toast.LENGTH_LONG).show();
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
								}

								@Override
								public void onFailure(Throwable error,
										String content) {
									// TODO Auto-generated method stub
									super.onFailure(error, content);
									Toast.makeText(PrepareActivity.this,"网络请求异常!", Toast.LENGTH_LONG).show();
								}
							});
				} else if (TextUtils.isEmpty(username) || TextUtils.isEmpty(licenseNumber)) {
					Toast.makeText(PrepareActivity.this, "您输入的激活码不能为空！请重新输入",Toast.LENGTH_LONG).show();
				}

			}
		});
	}
	// 安装APK
	protected void installApk(File file) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
		startActivity(intent);
	}
}
