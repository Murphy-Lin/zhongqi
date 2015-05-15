package com.sinodata.evaluate.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.sinodata.evaluate.MyApplication;
import com.sinodata.evaluate.activities.MainActivity;
import com.sinodata.evaluate.activities.PrepareActivity;

public class LicenseManager {

	private Context context;
	private SharedPreferences sp;
	
	public LicenseManager() {
		context = MyApplication.getContext();
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
	}

	public void saveLicense(String license){
		Editor ed = sp.edit();
		ed.putString("License", license);
		ed.commit();
	}
	
	public void checkLicense(Activity a){
		if(sp.getString("License", "") != ""){
			Intent in = new Intent(context,MainActivity.class);
			in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(in);
			a.finish();
		}else{
			Intent in2 = new Intent(context,PrepareActivity.class);
			in2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(in2);
			a.finish();
		}
	}
	
	
}
