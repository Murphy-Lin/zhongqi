package com.sinodata.evaluate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.sinodata.evaluate.BaseActivity;
import com.sinodata.evaluate.R;

public class MainActivity extends BaseActivity implements OnClickListener{

	private ImageView iv_evaluate;
	private ImageView iv_history;
	private ImageView iv_information;
	private ImageView iv_management;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		initView();
		initEvent();
	}

	public void initEvent() {
		// TODO Auto-generated method stub
		iv_evaluate.setOnClickListener(this);
		iv_history.setOnClickListener(this);
		iv_information.setOnClickListener(this);
		iv_management.setOnClickListener(this);
	}
				
	public void initView() {
		// TODO Auto-generated method stub
		iv_evaluate = (ImageView) findViewById(R.id.iv_evaluate);
		iv_history = (ImageView) findViewById(R.id.iv_history);
		iv_information = (ImageView) findViewById(R.id.iv_information);
		iv_management = (ImageView) findViewById(R.id.iv_management);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch(v.getId()){
		case R.id.iv_evaluate:
			intent = new Intent(this,EvaluateChooseActivity.class);
			startActivity(intent);
			break;
		case R.id.iv_history:
			intent = new Intent(this,EvaluateHistoryActivity.class);
			startActivity(intent);
			break;
		case R.id.iv_information:
			intent = new Intent(this,InformationActivity.class);
			startActivity(intent);
			break;
		case R.id.iv_management:
			intent = new Intent(this,ManagementActivity.class);
			startActivity(intent);
			break;
			default:
		}
		
			
	}
}
