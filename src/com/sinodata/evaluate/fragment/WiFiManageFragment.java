package com.sinodata.evaluate.fragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.sinodata.evaluate.MyApplication;
import com.sinodata.evaluate.R;
import com.sinodata.evaluate.adapters.MyListViewAdapter;
import com.sinodata.evaluate.utils.WifiAdmin;
import com.sinodata.evaluate.utils.WifiConnect;
import com.sinodata.evaluate.views.MyListView;
import com.sinodata.evaluate.views.MyListView.OnRefreshListener;
import com.sinodata.evaluate.views.OnNetworkChangeListener;
import com.sinodata.evaluate.views.WifiConnDialog;
import com.sinodata.evaluate.views.WifiStatusDialog;

public class WiFiManageFragment extends Fragment {

	private static final int REFRESH_CONN = 100;

	private static final int REQ_SET_WIFI = 200;

	// Wifi管理类
	private WifiAdmin mWifiAdmin;
	private WifiManager mWifiManager;
	private WifiInfo connInfo;
	// 扫描结果列表
	private List<ScanResult> list = new ArrayList<ScanResult>();
	// 显示列表
	private MyListView listView;
	private ToggleButton tgbWifiSwitch;
	//private ImageView iv_back;
	private MyListViewAdapter mAdapter;

	private OnNetworkChangeListener mOnNetworkChangeListener = new OnNetworkChangeListener() {

		@Override
		public void onNetWorkDisConnect() {
			getWifiListInfo();
			mAdapter.setDatas(list);
			mAdapter.notifyDataSetChanged();
		}

		@Override
		public void onNetWorkConnect() {
			getWifiListInfo();
			mAdapter.setDatas(list);
			mAdapter.notifyDataSetChanged();
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View v = inflater.inflate(R.layout.activity_wifi_list, container, false);
		initData();
		tgbWifiSwitch = (ToggleButton) v.findViewById(R.id.tgb_wifi_switch);
		listView = (MyListView) v.findViewById(R.id.freelook_listview);
		initEvent();
		setListener();
		
		refreshWifiStatusOnTime();

		return v;
	}
	
	private void initData() {
		mWifiAdmin = new WifiAdmin(MyApplication.getContext());
		mWifiManager = (WifiManager) MyApplication.getContext().getSystemService(Context.WIFI_SERVICE);
		connInfo = mWifiManager.getConnectionInfo();
		// 获得Wifi列表信息
		getWifiListInfo();
	}
	
	public void initEvent(){
		mAdapter = new MyListViewAdapter(getActivity(), list);
		listView.setAdapter(mAdapter);
		int wifiState = mWifiAdmin.checkState();
		if (wifiState == WifiManager.WIFI_STATE_DISABLED
				|| wifiState == WifiManager.WIFI_STATE_DISABLING
				|| wifiState == WifiManager.WIFI_STATE_UNKNOWN) {
			tgbWifiSwitch.setChecked(false);
		} else {
			tgbWifiSwitch.setChecked(true);
		}
		
		if(!WifiConnect.checkNetworkConnection(getActivity())){
			Toast.makeText(getActivity(), "您还未连接WIFI，请先连接！", Toast.LENGTH_LONG).show();
		}else if(mWifiAdmin.checkState() <3){
			Toast.makeText(getActivity(), "您的网卡未开启，请按上面的开关先开启网卡！", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(getActivity(), "已连接至"+connInfo.getSSID(), Toast.LENGTH_LONG).show();
		}
		
//		iv_back.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				finish();
//			}
//		});
		
		// 设置刷新监听
		listView.setonRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {

				new AsyncTask<Void, Void, Void>() {
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							e.printStackTrace();
						}
						getWifiListInfo();
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						mAdapter.setDatas(list);
						mAdapter.notifyDataSetChanged();
						listView.onRefreshComplete();
					}

				}.execute();
			}
		});
	}

	public void initView() {
		//iv_back = (ImageView) findViewById(R.id.iv_back);
		
		
		
	}

	/**
	 * 监听顶部网卡开关按钮
	 */
	private void setListener() {

		tgbWifiSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					//Log.w(TAG, "======== open wifi ========");
					// 打开Wifi
					mWifiAdmin.openWifi();
				} else {
				//	Log.w(TAG, "======== close wifi ========");
					// 关闭Wifi
					boolean res = mWifiAdmin.closeWifi();
					Toast.makeText(getActivity(), "您的网卡未开启，请按上面的开关先开启网卡！", Toast.LENGTH_LONG).show();
					if (!res) {
						gotoSysCloseWifi();
					}
				}
			}
		});

		

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				int position = pos - 1;
				// String wifiName = list.get(position).SSID;
				// String singlStrength = "" + list.get(position).frequency;
				// String secuString = list.get(position).capabilities;

				ScanResult scanResult = list.get(position);

				if (mWifiAdmin.isConnect(scanResult)) {
					// 已连接，显示连接状态对话框
					WifiStatusDialog mStatusDialog = new WifiStatusDialog(
							getActivity(), R.style.PopDialog,
							scanResult, mOnNetworkChangeListener);
					mStatusDialog.show();
				} else {
					// 未连接显示连接输入对话框
					WifiConnDialog mDialog = new WifiConnDialog(
							getActivity(), R.style.PopDialog,
							scanResult, mOnNetworkChangeListener);
					// WifiConnDialog mDialog = new WifiConnDialog(
					// WifiListActivity.this, R.style.PopDialog, wifiName,
					// singlStrength, secuString);
					mDialog.show();
				}
			}
		});
	}

	public void getWifiListInfo() {
		System.out.println("WifiListActivity#getWifiListInfo");
		mWifiAdmin.startScan();
		List<ScanResult> tmpList = mWifiAdmin.getWifiList();
		if (tmpList == null) {
			list.clear();
		} else {
			list = tmpList;
		}
	}

	private Handler mHandler = new MyHandler(this);

	protected boolean isUpdate = true;

	private static class MyHandler extends Handler {

		private WeakReference<WiFiManageFragment> reference;

		public MyHandler(WiFiManageFragment wififragment) {
			this.reference = new WeakReference<WiFiManageFragment>(wififragment);
		}

		@Override
		public void handleMessage(Message msg) {

			WiFiManageFragment wififragment = reference.get();

			switch (msg.what) {
			case REFRESH_CONN:
				wififragment.getWifiListInfo();
				wififragment.mAdapter.setDatas(wififragment.list);
				wififragment.mAdapter.notifyDataSetChanged();
				break;

			default:
				break;
			}

			super.handleMessage(msg);
		}
	}

	/**
	 * Function:定时刷新Wifi列表信息<br>
	 * 
	 * @author Murphy_Lin DateTime 2014-5-15 上午9:14:34<br>
	 * <br>
	 */
	private void refreshWifiStatusOnTime() {
		new Thread() {
			public void run() {
				while (isUpdate) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					mHandler.sendEmptyMessage(REFRESH_CONN);
				}
			}
		}.start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		isUpdate = false;
	}

	/**
	 * Function:到系统中设置wifi，如果用户手动关闭失败，跳转到系统中进行关闭Wifi<br>
	 * 
	 * @author Murphy_Lin DateTime 2014-5-15 上午10:03:15<br>
	 * <br>
	 */
	private void gotoSysCloseWifi() {
		// 05-15 09:57:56.351: I/ActivityManager(397): START
		// {act=android.settings.WIFI_SETTINGS flg=0x14000000
		// cmp=com.android.settings/.Settings$WifiSettingsActivity} from pid 572

		Intent intent = new Intent("android.settings.WIFI_SETTINGS");
		intent.setComponent(new ComponentName("com.android.settings",
				"com.android.settings.Settings$WifiSettingsActivity"));
		startActivityForResult(intent, REQ_SET_WIFI);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQ_SET_WIFI:
			// 处理改变wifi状态结果
			//
			int wifiState = mWifiAdmin.checkState();
			if (wifiState == WifiManager.WIFI_STATE_DISABLED
					|| wifiState == WifiManager.WIFI_STATE_DISABLING
					|| wifiState == WifiManager.WIFI_STATE_UNKNOWN) {
				tgbWifiSwitch.setChecked(false);
			} else {
				tgbWifiSwitch.setChecked(true);
			}
			break;

		default:
			break;
		}
	}
}