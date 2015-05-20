package com.sinodata.evaluate.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinodata.evaluate.BaseActivity;
import com.sinodata.evaluate.R;
import com.sinodata.evaluate.fragment.AccountManageFragment;
import com.sinodata.evaluate.fragment.ShutDownFragment;
import com.sinodata.evaluate.fragment.VersionManageFragment;
import com.sinodata.evaluate.fragment.WiFiManageFragment;

public class ManageActivity extends BaseActivity implements OnClickListener{

	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private TextView tv4;
	private ImageView iv_management_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_activity_management);
		
		initView();
		initEvent();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (TextView) findViewById(R.id.tv2);
		tv3 = (TextView) findViewById(R.id.tv3);
		tv4 = (TextView) findViewById(R.id.tv4);
		iv_management_back = (ImageView) findViewById(R.id.iv_management_back1);
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		iv_management_back.setOnClickListener(this);
		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
		tv3.setOnClickListener(this);
		tv4.setOnClickListener(this);
		if(getIntent().getAction() !=null&&getIntent().getAction().equals("wifi")){
			FragmentManager fm3 = getSupportFragmentManager();
			FragmentTransaction beginTransaction3 = fm3.beginTransaction();
			beginTransaction3.replace(R.id.content, new WiFiManageFragment());
			beginTransaction3.commit();
		}else{
			FragmentManager fm1 = getSupportFragmentManager();
			FragmentTransaction beginTransaction1 = fm1.beginTransaction();
			beginTransaction1.replace(R.id.content, new VersionManageFragment());
			beginTransaction1.commit();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.tv1:
			FragmentManager fm1 = getSupportFragmentManager();
			FragmentTransaction beginTransaction1 = fm1.beginTransaction();
			beginTransaction1.replace(R.id.content, new VersionManageFragment());
			beginTransaction1.commit();
			break;
		case R.id.tv2:
			FragmentManager fm2 = getSupportFragmentManager();
			FragmentTransaction beginTransaction2 = fm2.beginTransaction();
			beginTransaction2.replace(R.id.content, new AccountManageFragment());
			beginTransaction2.commit();
			break;
		case R.id.tv3:
			FragmentManager fm3 = getSupportFragmentManager();
			FragmentTransaction beginTransaction3 = fm3.beginTransaction();
			beginTransaction3.replace(R.id.content, new WiFiManageFragment());
			beginTransaction3.commit();
			break;
		case R.id.tv4:
			FragmentManager fm4 = getSupportFragmentManager();
			FragmentTransaction beginTransaction4 = fm4.beginTransaction();
			beginTransaction4.replace(R.id.content, new ShutDownFragment());
			beginTransaction4.commit();
			break;
		case R.id.iv_management_back1:
			finish();
			break;
			default:
		}
	}

}
