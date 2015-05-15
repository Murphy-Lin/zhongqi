package com.sinodata.evaluate.activities;


import java.io.File;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
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
import com.sinodata.evaluate.utils.WifiConnectManager;

public class PrepareActivity extends BaseActivity {

	protected static final int ENTER_HOME = 1;

	protected static final int SHOW_UPDATE_DIALOG = 2;

	protected static final int URL_ERROR = 3;

	protected static final int NETWORK_ERROR = 4;

	protected static final int JSON_ERROR = 5;
	
	private TextView tv_splash_updateinfo;
	
	public WifiConnectManager wifiConnectManager;
	private EditText et_license;
	private Button btn_activition;
	private String apkurl;
	private String testUrl = "http://123.57.49.66/Assessment/crypto?serviceType=MBS0000050";
	private RequestParams params;
	private SharedPreferences sp;
	private LicenseManager lm;
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case ENTER_HOME:// ������ҳ��
				//sp = get
				break;
			case SHOW_UPDATE_DIALOG:// ��ʾ���¶Ի���
				//Log.i(TAG, "��ʾ���¶Ի���");
				showUpdateDialog();
				break;
			}
		}

	};
	private void enterHome() {
		Intent intent = new Intent(this, MainActivity.class);
		
		startActivity(intent);
		finish();
	}
	
	private void showUpdateDialog() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("��������");
		builder.setOnCancelListener(new OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				
				enterHome();
			}
		});
		builder.setPositiveButton("��������", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {

					// �������µ�APK apkurl
					FinalHttp http = new FinalHttp();
					http.download(apkurl, Environment
							.getExternalStorageDirectory().getAbsolutePath()
							+ "/update.apk", new AjaxCallBack<File>() {

						@Override
						public void onFailure(Throwable t, int errorNo,
								String strMsg) {
							super.onFailure(t, errorNo, strMsg);
							t.printStackTrace();
							Toast.makeText(PrepareActivity.this, "����ʧ��", Toast.LENGTH_LONG)
									.show();
						}

						@Override
						public void onLoading(long count, long current) {
							super.onLoading(count, current);
							int progress = (int) (current * 100 / count);
							tv_splash_updateinfo.setText("���ؽ��ȣ�" + progress
									+ "%");
						}

						@Override
						public void onSuccess(File t) {
							super.onSuccess(t);
							installAPK(t);
						}

						private void installAPK(File t) {
							Intent intent = new Intent();
							intent.setAction("android.intent.action.VIEW");
							intent.addCategory("android.intent.category.DEFAULT");
							intent.setDataAndType(Uri.fromFile(t),
									"application/vnd.android.package-archive");
							startActivity(intent);

						}

					});
				}else{
					Toast.makeText(PrepareActivity.this, "sd��������", Toast.LENGTH_LONG).show();
				}
			}
		});
		builder.setNegativeButton("�´���˵", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// ������ҳ��
				enterHome();
			}
		});
		builder.show();

	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prepare_view);
		
		initView();
		//lm.checkLicense(this);
		initEvent();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		sp = getSharedPreferences("config", MODE_PRIVATE);
		wifiConnectManager = new WifiConnectManager(this);
		params = new RequestParams();
		lm = new LicenseManager();
		tv_splash_updateinfo = (TextView) findViewById(R.id.tv_splash_updateinfo);
		et_license = (EditText) findViewById(R.id.et_license);
		btn_activition = (Button) findViewById(R.id.btn_activation);
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		wifiConnectManager.isConnect();
		
		String licenseNumber = et_license.getText().toString();
		
		params.put("license", licenseNumber);
		btn_activition.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				NetUtil.getClient().post(testUrl, params, new AsyncHttpResponseHandler(){
//
//					@Override
//					public void onSuccess(int statusCode, Header[] headers,
//							String content) {
//						// TODO Auto-generated method stub
//						super.onSuccess(statusCode, headers, content);
//						Log.d("onSuccess", content);
//						HashMap<String, Object> licenseMap = JsonFactory.getJsonFactory().paserLicense(content);
//						if(!(boolean) licenseMap.get("success")){
//							Toast.makeText(getApplicationContext(), "���ļ����벻��ȷ������ʧЧ�����������룡", Toast.LENGTH_LONG).show();
//						}else{
//							Toast.makeText(getApplicationContext(), "����ɹ���", Toast.LENGTH_LONG).show();
//							Intent intent = new Intent(PrepareActivity.this,MainActivity.class);
//							startActivity(intent);
//						}
//						
//					}
					
//				});
				if(et_license.getText().toString() != ""){
					lm.saveLicense(et_license.getText().toString());
					Intent intent = new Intent(PrepareActivity.this,MainActivity.class);
					startActivity(intent);
				}else{
					Toast.makeText(PrepareActivity.this, "������ļ����벻��ȷ�����������룡", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		
		
	}


	
	/**
	 * �õ���ǰӦ�ó���İ汾��
	 */

	public int getAppVersion() {
		PackageManager pm = getPackageManager();
		try {
			// �����嵥�ļ�����Ϣ
			PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
			return info.versionCode;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
			// �����ܷ���
			return 0;
		}

	}

	//��װapk   
	protected void installApk(File file) {  
	    Intent intent = new Intent();  
	    //ִ�ж���  
	    intent.setAction(Intent.ACTION_VIEW);  
	    //ִ�е���������  
	    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");  
	    startActivity(intent);  
	}  
	
}
