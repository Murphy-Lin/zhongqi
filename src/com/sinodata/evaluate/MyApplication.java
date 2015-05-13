package com.sinodata.evaluate;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.Context;

import com.sinodata.evaluate.activities.SplashActivity;

public class MyApplication extends Application {

	private static List<BaseActivity> list = new ArrayList<BaseActivity>();
	private static Context context;
	
	@Override
	public void onCreate() {
		context = getApplicationContext();
	}
	
	public static Context getContext(){
		return context;
	}

	public void addActivity(BaseActivity activtiy) {
		list.add(activtiy);
	}

	public void removeActivity(BaseActivity activtiy) {
		list.remove(activtiy);
	}

	public static void exit() {
		for (BaseActivity activity : list) {
			if (activity != null) {
				if (!activity.getClass().getSimpleName().equals(SplashActivity.class.getSimpleName())) {
					activity.finish();
				}
			}
		}
	}
}
