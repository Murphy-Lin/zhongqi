package com.sinodata.evaluate.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinodata.evaluate.R;

public class HistoryAdapter extends BaseAdapter {

	private Context context;
	private List<String> datas = new ArrayList<String>();

	public void setDatas(List<String> datas) {
		this.datas = datas;
	}

	public HistoryAdapter(Context context, List<String> datas) {
		super();
		this.datas = datas;
		this.context = context;
	}

	public HistoryAdapter() {
		super();
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder tag = new Holder();
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.history_item, null);
			tag.name = (TextView) convertView.findViewById(R.id.name);
			convertView.setTag(tag);
		}else{
			tag = (Holder) convertView.getTag();
		}
		tag.name.setText(datas.get(position));
		tag.name.setTextSize(36);
		return convertView;
	}

	public static class Holder {
		public TextView name;
	}

}
