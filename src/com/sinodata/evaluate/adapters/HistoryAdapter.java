package com.sinodata.evaluate.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sinodata.evaluate.R;
import com.sinodata.evaluate.activities.WebViewActivity2;
import com.sinodata.evaluate.beans.History;

public class HistoryAdapter extends BaseAdapter {

	private Context context;
	private List<History> datas = new ArrayList<History>();
	private static final String  resultUrl = "http://123.57.59.200:8080/Habitus/Detail/";
	
	
	public void setDatas(List<History> datas) {
		this.datas = datas;
	}

	public HistoryAdapter(Context context, List<History> datas) {
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
		final int p = position;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.history_item, null);
			tag.createTime = (TextView) convertView.findViewById(R.id.create_time);
			tag.mainHabitus = (TextView) convertView.findViewById(R.id.main_habitus);
			tag.otherHabitus = (TextView) convertView.findViewById(R.id.other_habitus);
			tag.gotoResult = (TextView) convertView.findViewById(R.id.goto_result);
			convertView.setTag(tag);
		}else{
			tag = (Holder) convertView.getTag();
		}
		tag.createTime.setText(datas.get(position).getCreateTime());
		tag.mainHabitus.setText(datas.get(position).getMainHabitus());
		tag.otherHabitus.setText(datas.get(position).getOtherHabitus());
		tag.gotoResult.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//接口。。。。。
				Intent intent = new Intent(context,WebViewActivity2.class);
				intent.setAction(resultUrl+datas.get(p).getResultID());
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		});
		return convertView;
	}

	public static class Holder {
		public TextView createTime;
		public TextView mainHabitus;
		public TextView otherHabitus;
		public TextView gotoResult;
	}

}
