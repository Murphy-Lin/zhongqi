package com.sinodata.evaluate.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sinodata.evaluate.activities.PrepareActivity;

public class BootBroadcastReceiver extends BroadcastReceiver {
	
	/**
	 * ���������㲥
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent intent1=new Intent();
            intent1.setClass(context, PrepareActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
	}
}
