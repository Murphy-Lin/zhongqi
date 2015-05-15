package com.sinodata.evaluate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.sinodata.evaluate.activities.WifiListActivity;
import com.sinodata.evaluate.utils.WifiConnect;

public abstract class BaseActivity extends Activity{

	public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		((MyApplication)getApplication()).addActivity(this);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle("您还未连接WIFI，请先连接！").setPositiveButton("去搜搜附近的WIFI", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(BaseActivity.this,WifiListActivity.class);
				startActivity(intent);
				dialog.dismiss();
				
			}
		});
		
		AlertDialog dialog = builder.create();
		
		boolean iscon = WifiConnect.checkNetworkConnection(this);
		if(getClass().getSimpleName().equals("WifiListActivity")||getClass().getSimpleName().equals("SplashActivity")){
			if(dialog!=null){
				dialog.dismiss();
			}
		}else if(!iscon){
			dialog.show();
		}
		/*
		 * 隐藏运行Android 4.0以上系统的平板的屏幕下方的状态栏
		 */
//		try  
//	    {  
//	        String ProcID = "79";  
//	        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) ProcID = "42"; // ICS  
//	        // 需要root 权限  
//	        Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", "service call activity " + ProcID + " s16 com.android.systemui" }); // WAS  
//	        proc.waitFor();  
//	    }  
//	    catch (Exception e)  
//	    {  
//	        e.printStackTrace();
//	    }  
	}
	
	public abstract void initView();
	
	public abstract void initEvent();
	
	@Override
	protected void onDestroy() {
		((MyApplication)getApplication()).removeActivity(this);
				/*
				 * 恢复运行Android 4.0以上系统的平板的屏幕下方的状态栏
				 */
//				try  
//			    {  
//			        Process proc = Runtime.getRuntime().exec(new String[] { "am", "startservice", "-n", "com.android.systemui/.SystemUIService" });  
//			        proc.waitFor();  
//			    }  
//			    catch (Exception e)  
//			    {  
//			        e.printStackTrace();  
//			    }  
		
		super.onDestroy();
	}
}
