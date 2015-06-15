package com.sinodata.evaluate.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sinodata.evaluate.R;
import com.sinodata.evaluate.beans.User;

public class UserEvaluateListAdapter extends BaseAdapter{

	private Context context;
	private List<User> datas = new ArrayList<User>();

	public void setDatas(List<User> datas) {
		this.datas = datas;
	}
	
	public UserEvaluateListAdapter(Context context, List<User> datas) {
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
			tag.name = (TextView) convertView.findViewById(R.id.Name);
			tag.sex = (TextView) convertView.findViewById(R.id.Sex);
			tag.IDcard = (TextView) convertView.findViewById(R.id.IDCard);
			tag.phone = (TextView) convertView.findViewById(R.id.Phone);
			convertView.setTag(tag);
		}else{
			tag = (Holder) convertView.getTag();
		}
		tag.name.setText(datas.get(position).getUsername());
		String gender;
		if(datas.get(position).getGender().equals("0")){
			gender = "男";
			tag.sex.setText(gender);
		}else if(datas.get(position).getGender().equals("1")){
			gender = "女";
			tag.sex.setText(gender);
		}
		tag.IDcard.setText(datas.get(position).getIDNumbr());
		tag.phone.setText(datas.get(position).getPhone());
		//tag.name.setTextSize(30);
		return convertView;
	}

	public static class Holder {
		public TextView name;
		public TextView sex;
		public TextView IDcard;
		public TextView phone;
	}
}
