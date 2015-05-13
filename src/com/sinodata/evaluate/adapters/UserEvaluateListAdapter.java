package com.sinodata.evaluate.adapters;

import java.util.ArrayList;
import java.util.List;

import com.sinodata.evaluate.R;
import com.sinodata.evaluate.adapters.HistoryAdapter.Holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UserEvaluateListAdapter extends BaseAdapter{

	private Context context;
	private List<String> datas = new ArrayList<String>();

	public void setDatas(List<String> datas) {
		this.datas = datas;
	}
	
	public UserEvaluateListAdapter(Context context, List<String> datas) {
		super();
		this.datas = datas;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder tag = new Holder();
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.userevaluatelist_item, null);
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
