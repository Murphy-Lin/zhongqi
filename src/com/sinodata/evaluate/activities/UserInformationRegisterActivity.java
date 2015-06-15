package com.sinodata.evaluate.activities;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sinodata.evaluate.BaseActivity;
import com.sinodata.evaluate.R;
import com.sinodata.evaluate.utils.HttpUtil;

public class UserInformationRegisterActivity extends BaseActivity {

	private ImageView iv_back;
	private Button btn_submit;
	private EditText et_username;
	private EditText et_IDCard;
	private EditText et_tel;
	private EditText et_age;
	//private TextView tv_age;
	private Button btn_birth;
	private EditText et_mail;
	private RadioGroup radioGroup;
	private SharedPreferences sp;
	private String result = "";
	//日期控件相关
	private static final int DATE_DIALOG_ID = 1;
	private static final int SHOW_DATAPICK = 0;
	private int mYear;
	private int mMonth;
	private int mDay;

	public static final String evaurl = "http://123.57.59.200:8080/Habitus/Question";
	// private User user;
	private String url = "http://123.57.59.200:8080/api/MobilePadAccount/CreateAccount";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.userinformationregister);

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
		//tv_age = (TextView) findViewById(R.id.tv_birth);
		btn_birth = (Button) findViewById(R.id.btn_date);
		et_mail = (EditText) findViewById(R.id.et_mail);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		btn_birth.setOnClickListener(new DateButtonOnClickListener());
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		setDateTime();
		
		et_age.addTextChangedListener(new MytextWatcher());
		
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

				String username = et_username.getText().toString().trim();
				String idnumber = et_IDCard.getText().toString().trim();
				String tel = et_tel.getText().toString().trim();
				//String age = et_age.getText().toString().trim();
				String age = et_age.getText().toString().trim();
				int btnid = radioGroup.getCheckedRadioButtonId();
				RadioButton rb = (RadioButton) UserInformationRegisterActivity.this
						.findViewById(btnid);
				String gender = rb.getText().toString().trim();
				if (gender.equals("男性")) {
					gender = "0";
				} else if (gender.equals("女性")) {
					gender = "1";
				}
				String mail = et_mail.getText().toString().trim();
				RequestParams params = new RequestParams();
				params.put("TerminalNum", sp.getString("DeviceID", ""));
				params.put("TerminaSN", sp.getString("License", ""));
				params.put("phone", tel);
				params.put("IDCard", idnumber);
				params.put("Email", mail);
				params.put("RealName", username);
				params.put("Sex", gender);
				params.put("Birthday", age);
				if (!TextUtils.isEmpty(username)
						&& !TextUtils.isEmpty(idnumber)
						&& !TextUtils.isEmpty(tel) && !TextUtils.isEmpty(age)
						&& !TextUtils.isEmpty(mail)) {
					HttpUtil.post(url, params, new AsyncHttpResponseHandler() {
						@Override
						public void onSuccess(int statusCode, String content) {
							// TODO Auto-generated method stub
							super.onSuccess(statusCode, content);
							if (statusCode == 200) {
								JSONObject obj = JSON.parseObject(content);
								if (obj.getString("success").equals("true")) {
								JSONArray array = obj.getJSONArray("data");
								JSONObject dataObj = array.getJSONObject(0);
									Intent in = new Intent(
											UserInformationRegisterActivity.this,
											WebViewActivity2.class);
									in.setAction(evaurl+"?tno="+sp.getString("DeviceID", "")+"&tsn="+sp.getString("License", "")+"&uid="+dataObj.getString("AccountID"));
									startActivity(in);
									finish();
								}else {
									Toast.makeText(
											UserInformationRegisterActivity.this,
											obj.getString("message"), Toast.LENGTH_LONG).show();
								}
							}
						}

						@Override
						public void onFailure(Throwable error, String content) {
							// TODO Auto-generated method stub
							super.onFailure(error, content);
							Log.i("TAG",error.getMessage());
							Log.i("TAG", content);
							Toast.makeText(
									UserInformationRegisterActivity.this,
									"网络请求异常！", Toast.LENGTH_LONG).show();
						}
					});
				} else {
					Toast.makeText(UserInformationRegisterActivity.this,
							"录入资料不能有为空项，请仔细重写填写注册信息！", Toast.LENGTH_LONG)
							.show();
				}
			}
		});

	}
	
	private void setDateTime() {

		final Calendar c = Calendar.getInstance();

		mYear = c.get(Calendar.YEAR);

		mMonth = c.get(Calendar.MONTH);

		mDay = c.get(Calendar.DAY_OF_MONTH);

		//updateDisplay();

	}
	
	/**
	 * 
	 * 更新日期
	 */

	private void updateDisplay() {

		et_age.setText(new StringBuilder().append(mYear+"-").append(

		(mMonth + 1) < 10 ? "0" + (mMonth + 1)+"-" : (mMonth + 1)+"-").append(

		(mDay < 10) ? "0" + mDay : mDay));

	}

	/**
	 * 
	 * 日期控件的事件
	 */

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,

		int dayOfMonth) {

			mYear = year;

			mMonth = monthOfYear;

			mDay = dayOfMonth;

			updateDisplay();

		}

	};

	/**
	 * 
	 * 选择日期Button的事件处理
	 * 
	 * 
	 * 
	 * @author Raul
	 * 
	 * 
	 */

	class DateButtonOnClickListener implements

	android.view.View.OnClickListener {

		@Override
		public void onClick(View v) {

			Message msg = new Message();

			if (btn_birth.equals((Button) v)) {

				msg.what = UserInformationRegisterActivity.SHOW_DATAPICK;

			}

			UserInformationRegisterActivity.this.saleHandler.sendMessage(msg);

		}

	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {

		case DATE_DIALOG_ID:

			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,

			mDay);

		}

		return null;

	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {

		switch (id) {

		case DATE_DIALOG_ID:

			((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);

			break;

		}

	}

	/**
	 * 
	 * 处理日期控件的Handler
	 */

	Handler saleHandler = new Handler() {

		@SuppressWarnings("deprecation")
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {

			case UserInformationRegisterActivity.SHOW_DATAPICK:
				
				showDialog(DATE_DIALOG_ID);

				break;

			}

		}

	};
	
	class MytextWatcher implements TextWatcher {
		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			if (arg0.length() == 4) {
				result = arg0 + "-";
			}
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {

		}

		@Override
		public void afterTextChanged(Editable arg0) {
			if(result.length()==5){
				et_age.removeTextChangedListener(this);
				et_age.setText(result);
				et_age.setSelection(5);
				et_age.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						if(s.length() == 7){
							result = s + "-";
						}
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						if(result.length()==8){
							et_age.removeTextChangedListener(this);
							et_age.setText(result);
							et_age.setSelection(8);
						}
					}
				});
			}
		}
	}

}
