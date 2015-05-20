package com.sinodata.evaluate.activities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sinodata.evaluate.BaseActivity;
import com.sinodata.evaluate.MyApplication;
import com.sinodata.evaluate.R;

public class UserInformationRegisterActivity extends BaseActivity {

	private ImageView iv_back;
	private Button btn_submit;
	
	private EditText et_username;
	private EditText et_IDCard;
	private EditText et_tel;
	private EditText et_age;
	private RadioGroup radioGroup;
	//private User user;
	private String url = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.userinformationregister);
		//user = new User();
		
		initView();
		initEvent();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		iv_back = (ImageView) findViewById(R.id.iv_register_back);
		btn_submit = (Button) findViewById(R.id.btn_submit);
		et_username = (EditText) findViewById(R.id.et_name);
		et_IDCard = (EditText) findViewById(R.id.et_identity_card);
		et_tel = (EditText) findViewById(R.id.et_tel);
		et_age = (EditText) findViewById(R.id.et_birth);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
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
		btn_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						String username = et_username.getText().toString();
						String idnumber = et_IDCard.getText().toString();
						String tel = et_tel.getText().toString();
						String age = et_age.getText().toString();
						int btnid = radioGroup.getCheckedRadioButtonId();
						RadioButton rb = (RadioButton) UserInformationRegisterActivity.this.findViewById(btnid);
						String gender = (String) rb.getText();
//						user.setUsername(username);
//						user.setAge(age);
//						user.setGender(gender);
//						user.setIDNumbr(idnumber);
//						user.setPhone(tel);
						
						RequestParams params = new RequestParams();
						params.put("username", username);
						params.put("idnumber", idnumber);
						params.put("tel", tel);
						params.put("age", age);
						params.put("gender", gender);
						
						new AsyncHttpClient(){
							
						}.get(MyApplication.getContext(), url, params, new AsyncHttpResponseHandler(){
							@Override
							public void onSuccess(int statusCode, String content) {
								// TODO Auto-generated method stub
								super.onSuccess(statusCode, content);
								
							}
							@Override
							public void onFailure(Throwable error,
									String content) {
								// TODO Auto-generated method stub
								super.onFailure(error, content);
								
							}
						});
//					   	 将User对象转换成一个json类型的字符串对象
					 	//String str = JSON.toJSONString(user);
				        //JSONObject result = JSON.parseObject(str);
				        
					}
				};
			}
		});
		
		
		
	}
}
