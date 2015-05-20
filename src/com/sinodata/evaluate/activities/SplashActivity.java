package com.sinodata.evaluate.activities;

import android.os.Bundle;

import com.sinodata.evaluate.BaseActivity;
import com.sinodata.evaluate.utils.LicenseManager;

public class SplashActivity extends BaseActivity{

	private LicenseManager lm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		initView();
		initEvent();
		
	}
	
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		lm = new LicenseManager();
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		lm.checkLicense(this);
	}

	
	
}
