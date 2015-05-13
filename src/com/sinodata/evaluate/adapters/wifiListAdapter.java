package com.sinodata.evaluate.adapters;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sinodata.evaluate.R;
import com.sinodata.evaluate.MyApplication;
import com.sinodata.evaluate.utils.WifiConnectManager;

public class wifiListAdapter extends BaseAdapter {

	private List<String> wifiList = new ArrayList<String>();
	private WifiConnectManager wifiConnectManager;
	private MyApplication myApplication;

	public wifiListAdapter() {
		super();
		// TODO Auto-generated constructor stub
		myApplication = new MyApplication();
		wifiConnectManager = new WifiConnectManager(
				myApplication.getApplicationContext());
		wifiList = wifiConnectManager.getAllNetWorkList();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return wifiList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return wifiList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	static class ViewHolder {
		TextView text;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(
					myApplication.getApplicationContext()).inflate(
					R.layout.list_wifi, null);
			holder = new ViewHolder();
			holder.text = (TextView) convertView.findViewById(R.id.tv_wifiname);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(wifiList.get(position));
		return convertView;
	}

}
