package com.sinodata.evaluate.activities;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.sinodata.evaluate.BaseActivity;
import com.sinodata.evaluate.R;
import com.sinodata.evaluate.adapters.UserEvaluateListAdapter;
import com.sinodata.evaluate.views.MyListView;
import com.sinodata.evaluate.views.MyListView.OnRefreshListener;

public class UserEvaluateListActivity extends BaseActivity {

	private ImageView iv_back;
	private MyListView listView;
	private List<String> list;
	private UserEvaluateListAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userevaluatelist);
		initData();
		initView();
		initEvent();
	}

	private void initData() {
		// TODO Auto-generated method stub
		list = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			list.add("会员" + i);
		}
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		iv_back = (ImageView) findViewById(R.id.iv_userevaluatelist_back);
		listView = (MyListView) findViewById(R.id.freelook_listview);
		mAdapter = new UserEvaluateListAdapter(this,list);
		
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		listView.setAdapter(new UserEvaluateListAdapter(this, list));
		iv_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		// 设置刷新监听
		listView.setonRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				list.add("新增会员");
				mAdapter.notifyDataSetChanged();
				listView.onRefreshComplete();
			}
		});
	}
}
