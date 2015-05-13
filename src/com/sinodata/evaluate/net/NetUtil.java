package com.sinodata.evaluate.net;

import com.loopj.android.http.AsyncHttpClient;

public class NetUtil {

	private static AsyncHttpClient client = null;
	private static String evaluateUrl = "http://123.57.49.66/DoctorQuestion/aboutIndex/aboutIndex.jsp/";
	private static String url_login = "checkMob.c";// �û�����
	private static String url_regist = "addMobUser.c";// �û�ע��
	private static String url_getUserInfo = "loadOpAll.c";// �û���ѯ 
	private static String url_uploadError = "catchExLog.c";// �쳣����
	private static String url_resetPassWord = "UserChangePwd.c"; // ��������

	public static AsyncHttpClient getClient() {
		if (client == null) {
			client = new AsyncHttpClient();
		}
		return client;
	}

	public static String getBaseUrl() {
		return evaluateUrl;
	}

	public static String getLoginUrl() {
		return evaluateUrl + url_login;
	}

	public static String getRegistUrl() {
		return evaluateUrl + url_regist;
	}

	public static String getUpLoadError() {
		return evaluateUrl + url_uploadError;
	}

	public static String getReSetPassWord() {
		return evaluateUrl + url_resetPassWord;
	}
}