package com.sinodata.evaluate.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.sinodata.evaluate.BaseActivity;
import com.sinodata.evaluate.R;

/**
 * 此界面为体质辨识选择界面，根据新老用户跳转
 * @author 林杰
 *
 */
public class EvaluateChooseActivity extends BaseActivity implements OnClickListener {

	private ImageView iv_new_user;
	private ImageView iv_old_user;
	private ImageView iv_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.evaluate_choose);
		
		initView();
		initEvent();
	}
	
	public void initView() {
		iv_new_user = (ImageView) findViewById(R.id.iv_new_user);
		iv_old_user = (ImageView) findViewById(R.id.iv_old_user);
		iv_back = (ImageView) findViewById(R.id.iv_evaluate_back);
	}
	
	public void initEvent() {
		iv_new_user.setOnClickListener(this);
		iv_old_user.setOnClickListener(this);
		iv_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.iv_evaluate_back :
			finish();
			break;
		case R.id.iv_new_user :
			Intent intent = new Intent(EvaluateChooseActivity.this, UserInformationRegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.iv_old_user :
			Intent intent1 = new Intent(EvaluateChooseActivity.this,UserEvaluateListActivity.class);
			startActivity(intent1);
			break;
		}
	}
}
