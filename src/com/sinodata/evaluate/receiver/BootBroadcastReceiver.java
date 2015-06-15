package com.sinodata.evaluate.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sinodata.evaluate.activities.PrepareActivity;
import com.sinodata.evaluate.activities.SplashActivity;

public class BootBroadcastReceiver extends BroadcastReceiver {
	
	/**
	 * 开机启动广播
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent intent1=new Intent();
            intent1.setClass(context, SplashActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
	}
}
