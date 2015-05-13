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

	// ����һ��WifiManager����
	private WifiManager mWifiManager;
	// ����һ��WifiInfo����
	private WifiInfo mWifiInfo;
	// ɨ��������������б�
	private List<ScanResult> mWifiList;
	// ���������б�
	private List<WifiConfiguration> mWifiConfigurations;
	private WifiLock mWifiLock;
	private DialogShow dialogShow;
	private List<String> wifiList = new ArrayList<String>();
	// private TextView allNetWork;
	// ɨ�����б�
	private ScanResult mScanResult;

	// private StringBuffer sb = new StringBuffer();

	public WifiConnectManager(Context context) {
		// ȡ��WifiManager����
		mWifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		// ȡ��WifiInfo����
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
			Toast.makeText(MyApplication.getContext(), "wifiδ������", Toast.LENGTH_LONG).show();
			openWifi();
			new AlertDialog.Builder(
					MyApplication.getContext())
					.setMessage("��ǰ����δ���ӣ���������WIFI��")
					.setNegativeButton("��������WIFI", dialogShow).show();
		}
	}

	// ��wifi
	public void openWifi() {
		if (!mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(true);
		}
	}

	// �ر�wifi
	public void closeWifi() {
		if (mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(false);
		}
	}

	// ��鵱ǰwifi״̬
	public int checkState() {
		return mWifiManager.getWifiState();
	}

	// ����wifiLock
	public void acquireWifiLock() {
		mWifiLock.acquire();
	}

	// ����wifiLock
	public void releaseWifiLock() {
		// �ж��Ƿ�����
		if (mWifiLock.isHeld()) {
			mWifiLock.acquire();
		}
	}

	// ����һ��wifiLock
	public void createWifiLock() {
		mWifiLock = mWifiManager.createWifiLock("test");
	}

	// �õ����úõ�����
	public List<WifiConfiguration> getConfiguration() {
		return mWifiConfigurations;
	}

	// ָ�����úõ������������
	public void connetionConfiguration(int index) {
		if (index > mWifiConfigurations.size()) {
			return;
		}
		// �������ú�ָ��ID������
		mWifiManager.enableNetwork(mWifiConfigurations.get(index).networkId,
				true);
	}

	public void startScan() {
		mWifiManager.startScan();
		// �õ�ɨ����
		mWifiList = mWifiManager.getScanResults();
		// �õ����úõ���������
		mWifiConfigurations = mWifiManager.getConfiguredNetworks();
	}

	// �õ������б�
	public List<ScanResult> getWifiList() {
		return mWifiList;
	}

	// �鿴ɨ����
	@SuppressLint("UseValueOf")
	public StringBuffer lookUpScan() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mWifiList.size(); i++) {
			sb.append("Index_" + new Integer(i + 1).toString() + ":");
			// ��ScanResult��Ϣת����һ���ַ�����
			// ���аѰ�����BSSID��SSID��capabilities��frequency��level
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

	// �õ����ӵ�ID
	public int getNetWordId() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
	}

	// �õ�wifiInfo��������Ϣ
	public String getWifiInfo() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
	}

	// ���һ�����粢����
	public void addNetWork(WifiConfiguration configuration) {
		int wcgId = mWifiManager.addNetwork(configuration);
		mWifiManager.enableNetwork(wcgId, true);
	}

	// �Ͽ�ָ��ID������
	public void disConnectionWifi(int netId) {
		mWifiManager.disableNetwork(netId);
		mWifiManager.disconnect();
	}

	public List<String> getAllNetWorkList() {
		// ÿ�ε��ɨ��֮ǰ�����һ�ε�ɨ����
		if (wifiList != null) {
			wifiList.removeAll(wifiList);
		}
		// ��ʼɨ������
		startScan();
		mWifiList = getWifiList();
		if (mWifiList != null) {
			for (int i = 0; i < mWifiList.size(); i++) {
				// �õ�ɨ����
				mScanResult = mWifiList.get(i);
				wifiList.add(mScanResult.SSID);
				// sb = sb.append(mScanResult.BSSID + "  ")
				// sb = sb.append(mScanResult.SSID + "   ")
				// .append(mScanResult.capabilities + "   ")
				// .append(mScanResult.frequency + "   ")
				// .append(mScanResult.level + "\n\n");
			}
			// allNetWork.setText("ɨ�赽��wifi���磺\n" + sb.toString());
		}
		return wifiList;
	}

}
