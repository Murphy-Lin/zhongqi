package com.sinodata.evaluate.activities;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.sinodata.evaluate.BaseActivity;
import com.sinodata.evaluate.R;
import com.sinodata.evaluate.utils.LicenseManager;

public class PrepareActivity extends BaseActivity {

	protected static final int ENTER_HOME = 1;

	//public WifiConnectManager wifiConnectManager;
	private TextView tv_deviceID;
	private EditText et_license;
	private EditText et_company;
	private EditText et_username;
	private EditText et_protocol;
	private Button btn_register;
	//private String testUrl = "http://123.57.49.66/Assessment/crypto?serviceType=MBS0000050";
	private RequestParams params;
	//private SharedPreferences sp;
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
		//sp = getSharedPreferences("config", MODE_PRIVATE);
		//wifiConnectManager = new WifiConnectManager(this);
		params = new RequestParams();
		lm = new LicenseManager();
		tv_deviceID = (TextView) findViewById(R.id.tv_id);
		et_company = (EditText) findViewById(R.id.et_company);
		et_username = (EditText) findViewById(R.id.et_username);
		et_license = (EditText) findViewById(R.id.et_license);
		btn_register = (Button) findViewById(R.id.btn_register);
		et_protocol = (EditText) findViewById(R.id.et_protocol);
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		String licenseNumber = et_license.getText().toString();
		final String serialNumber = android.os.Build.SERIAL;
		tv_deviceID.setText(serialNumber);
		String company = et_company.getText().toString();
		String username = et_username.getText().toString();
		et_protocol.setText(R.string.spe);
		params.put("license", licenseNumber);
		btn_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// NetUtil.getClient().post(testUrl, params, new
				// AsyncHttpResponseHandler(){
				//
				// @Override
				// public void onSuccess(int statusCode, Header[] headers,
				// String content) {
				// // TODO Auto-generated method stub
				// super.onSuccess(statusCode, headers, content);
				// Log.d("onSuccess", content);
				// HashMap<String, Object> licenseMap =
				// JsonFactory.getJsonFactory().paserLicense(content);
				// if(!(boolean) licenseMap.get("success")){
				// Toast.makeText(getApplicationContext(),
				// "您的激活码不正确或者已失效，请重新输入！", Toast.LENGTH_LONG).show();
				// }else{
				// Toast.makeText(getApplicationContext(), "激活成功！",
				// Toast.LENGTH_LONG).show();
				// Intent intent = new
				// Intent(PrepareActivity.this,MainActivity.class);
				// startActivity(intent);
				// }
				//
				// }

				// });
				if (et_license.getText().toString() != "" && et_company.getText().toString() !="" && et_username.getText().toString() != "") {
					lm.saveLicense(et_license.getText().toString(),et_company.getText().toString(),et_username.getText().toString(),serialNumber);
					enterHome();
					finish();
				} else {
					Toast.makeText(PrepareActivity.this, "您输入的激活码不正确，请重新输入！",
							Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	

	// 安装apk
	protected void installApk(File file) {
		Intent intent = new Intent();
		// 执行动作
		intent.setAction(Intent.ACTION_VIEW);
		// 执行的数据类型
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		startActivity(intent);
	}

}
