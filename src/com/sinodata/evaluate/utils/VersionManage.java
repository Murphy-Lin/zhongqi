package com.sinodata.evaluate.utils;

import com.sinodata.evaluate.MyApplication;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class VersionManage {

	
	
	/**
	 * �õ���ǰӦ�ó���İ汾��
	 */

	public static String getAppVersion() {
		PackageManager pm = MyApplication.getContext().getPackageManager();
		try {
			// �����嵥�ļ�����Ϣ
			PackageInfo info = pm.getPackageInfo(MyApplication.getContext().getPackageName(), 0);
			return info.versionName;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
			// �����ܷ���
			return null;
		}

	}
	
}
