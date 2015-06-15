package com.sinodata.evaluate.utils;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.util.Log;
import android.widget.Toast;

import com.sinodata.evaluate.MyApplication;

public class WifiConnectManager {

	// 定义一个WifiManager对象
	private WifiManager mWifiManager;
	// 定义一个WifiInfo对象
	private WifiInfo mWifiInfo;
	// 扫描出的网络连接列表
	private List<ScanResult> mWifiList;
	// 网络连接列表
	private List<WifiConfiguration> mWifiConfigurations;
	private WifiLock mWifiLock;
	private DialogShow dialogShow;
	private List<String> wifiList = new ArrayList<String>();
	// private TextView allNetWork;
	// 扫描结果列表
	private ScanResult mScanResult;

	// private StringBuffer sb = new StringBuffer();

	public WifiConnectManager(Context context) {
		// 取得WifiManager对象
		mWifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		// 取得WifiInfo对象
		mWifiInfo = mWifiManager.getConnectionInfo();
	}

	public class DialogShow implements
			android.content.DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface paramDialogInterface, int paramInt) {
			// TODO Auto-generated method stub
			// paramDialogInterface.dismiss();
			if (paramInt == 0) {
				wifiList = getAllNetWorkList();
				paramDialogInterface.dismiss();
				
			}
		}
	}

	public void isConnect() {
		if (checkState() == 0) {
			Log.i("wifistate", String.valueOf(checkState()));
			Toast.makeText(MyApplication.getContext(), "wifi未开启！", Toast.LENGTH_LONG).show();
			openWifi();
			new AlertDialog.Builder(
					MyApplication.getContext())
					.setMessage("当前网络未连接，请先连接WIFI！")
					.setNegativeButton("搜索附近WIFI", dialogShow).show();
		}
	}

	// 打开wifi
	public void openWifi() {
		if (!mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(true);
		}
	}

	// 关闭wifi
	public void closeWifi() {
		if (mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(false);
		}
	}

	// 检查当前wifi状态
	public int checkState() {
		return mWifiManager.getWifiState();
	}

	// 锁定wifiLock
	public void acquireWifiLock() {
		mWifiLock.acquire();
	}

	// 解锁wifiLock
	public void releaseWifiLock() {
		// 判断是否锁定
		if (mWifiLock.isHeld()) {
			mWifiLock.acquire();
		}
	}

	// 创建一个wifiLock
	public void createWifiLock() {
		mWifiLock = mWifiManager.createWifiLock("test");
	}

	// 得到配置好的网络
	public List<WifiConfiguration> getConfiguration() {
		return mWifiConfigurations;
	}

	// 指定配置好的网络进行连接
	public void connetionConfiguration(int index) {
		if (index > mWifiConfigurations.size()) {
			return;
		}
		// 连接配置好指定ID的网络
		mWifiManager.enableNetwork(mWifiConfigurations.get(index).networkId,
				true);
	}

	public void startScan() {
		mWifiManager.startScan();
		// 得到扫描结果
		mWifiList = mWifiManager.getScanResults();
		// 得到配置好的网络连接
		mWifiConfigurations = mWifiManager.getConfiguredNetworks();
	}

	// 得到网络列表
	public List<ScanResult> getWifiList() {
		return mWifiList;
	}

	// 查看扫描结果
	@SuppressLint("UseValueOf")
	public StringBuffer lookUpScan() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mWifiList.size(); i++) {
			sb.append("Index_" + new Integer(i + 1).toString() + ":");
			// 将ScanResult信息转换成一个字符串包
			// 其中把包括：BSSID、SSID、capabilities、frequency、level
			sb.append((mWifiList.get(i)).toString()).append("\n");
		}
		return sb;
	}

	public String getMacAddress() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
	}

	public String getBSSID() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
	}

	public int getIpAddress() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();
	}

	// 得到连接的ID
	public int getNetWordId() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
	}

	// 得到wifiInfo的所有信息
	public String getWifiInfo() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
	}

	// 添加一个网络并连接
	public void addNetWork(WifiConfiguration configuration) {
		int wcgId = mWifiManager.addNetwork(configuration);
		mWifiManager.enableNetwork(wcgId, true);
	}

	// 断开指定ID的网络
	public void disConnectionWifi(int netId) {
		mWifiManager.disableNetwork(netId);
		mWifiManager.disconnect();
	}

	public List<String> getAllNetWorkList() {
		// 每次点击扫描之前清空上一次的扫描结果
		if (wifiList != null) {
			wifiList.removeAll(wifiList);
		}
		// 开始扫描网络
		startScan();
		mWifiList = getWifiList();
		if (mWifiList != null) {
			for (int i = 0; i < mWifiList.size(); i++) {
				// 得到扫描结果
				mScanResult = mWifiList.get(i);
				wifiList.add(mScanResult.SSID);
				// sb = sb.append(mScanResult.BSSID + "  ")
				// sb = sb.append(mScanResult.SSID + "   ")
				// .append(mScanResult.capabilities + "   ")
				// .append(mScanResult.frequency + "   ")
				// .append(mScanResult.level + "\n\n");
			}
			// allNetWork.setText("扫描到的wifi网络：\n" + sb.toString());
		}
		return wifiList;
	}

}
