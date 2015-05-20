package com.sinodata.evaluate.utils;

import com.sinodata.evaluate.MyApplication;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class VersionManage {

	
	
	/**
	 * 得到当前应用程序的版本号
	 */

	public static String getAppVersion() {
		PackageManager pm = MyApplication.getContext().getPackageManager();
		try {
			// 功能清单文件的信息
			PackageInfo info = pm.getPackageInfo(MyApplication.getContext().getPackageName(), 0);
			return info.versionName;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
			// 不可能发生
			return null;
		}

	}
	
}
