package com.sinodata.evaluate.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sinodata.evaluate.BaseActivity;
import com.sinodata.evaluate.R;
import com.sinodata.evaluate.adapters.UserEvaluateListAdapter;
import com.sinodata.evaluate.beans.User;
import com.sinodata.evaluate.utils.HttpUtil;

public class UserEvaluateListActivity extends BaseActivity {

	private ImageView iv_back;
	private ListView listView;
	private List<User> list;
	//private UserEvaluateListAdapter mAdapter;
	
	private EditText et_username;
	private EditText et_IDcard;
	private Button btn_search;
	private SharedPreferences sp;
	private static final String evasearchUrl = "http://123.57.59.200:8080/api/MobilePadAccount/GetPadAccounts";
	public static final String evaurl = "http://123.57.59.200:8080/Habitus/Question";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userevaluatelist);
		
		initView();
		initEvent();
	}



	@Override
	public void initView() {
		// TODO Auto-generated method stub
		iv_back = (ImageView) findViewById(R.id.iv_userevaluatelist_back);
		listView = (ListView) findViewById(R.id.freelook_listview);
		list = new ArrayList<User>();
		//mAdapter = new UserEvaluateListAdapter(this,list);
		et_username = (EditText) findViewById(R.id.et_search_username);
		et_IDcard = (EditText) findViewById(R.id.et_search_idcard);
		btn_search = (Button) findViewById(R.id.btn_search_select);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		btn_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String username = et_username.getText().toString().trim();
				String idcard = et_IDcard.getText().toString().trim();
				RequestParams params = new RequestParams();
				params.put("TNo", sp.getString("DeviceID",""));
				params.put("TSn", sp.getString("License",""));
				if(!TextUtils.isEmpty(username)){
					params.put("RealName", username);
				}
				if(!TextUtils.isEmpty(idcard)){
					params.put("IDCard", idcard);
				}
				HttpUtil.post(evasearchUrl, params, new AsyncHttpResponseHandler(){
					@Override
					public void onSuccess(int statusCode, String content) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, content);
						if(statusCode == 200){
							JSONObject obj = JSON.parseObject(content);
							if(obj.getString("success").equals("true")){
								list.clear();
								User u = new User();
								//list = FastJsonTools.getBeans(obj.getString("data"), User.class);
								
								JSONArray array = obj.getJSONArray("data");
								for (int i = 0; i < array.size(); i++) {
									u.setUsername(array.getJSONObject(i).getString("RealName"));
									u.setAccountID(array.getJSONObject(i).getString("AccountID"));
									u.setAge(array.getJSONObject(i).getString("Birthday"));
									u.setGender(array.getJSONObject(i).getString("Sex"));
									u.setIDNumbr(array.getJSONObject(i).getString("IDCard"));
									u.setMail(array.getJSONObject(i).getString("Email"));
									u.setPhone(array.getJSONObject(i).getString("Phone"));
									list.add(u);
								}
								listView.setAdapter(new UserEvaluateListAdapter(UserEvaluateListActivity.this, list));
								//list.removeAll(list);
							}else{
								Toast.makeText(UserEvaluateListActivity.this, obj.getString("message"), Toast.LENGTH_LONG).show();
							}
						}
						
					}
					
					@Override
					public void onFailure(Throwable error, String content) {
						// TODO Auto-generated method stub
						super.onFailure(error, content);
						Toast.makeText(UserEvaluateListActivity.this, R.string.neterror, Toast.LENGTH_LONG).show();
					}
				});
				
			}
		});
		iv_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		// 设置刷新监听
//		listView.setonRefreshListener(new OnRefreshListener() {
//			@Override
//			public void onRefresh() {
//				//list.add("新增会员");
//				mAdapter.notifyDataSetChanged();
//				listView.onRefreshComplete();
//			}
//		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(UserEvaluateListActivity.this,WebViewActivity2.class);
				intent.setAction(evaurl+"?tno="+sp.getString("DeviceID", "")+"&tsn="+sp.getString("License", "")+"&uid="+list.get(position-1).getAccountID());
				startActivity(intent);
				finish();
			}
		});
		
	}
}
