package com.sinodata.evaluate.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
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
import com.sinodata.evaluate.adapters.HistoryAdapter;
import com.sinodata.evaluate.beans.History;
import com.sinodata.evaluate.utils.HttpUtil;

/**
 * 评测历史界面
 * @author 林杰
 *
 */
public class EvaluateHistoryActivity extends BaseActivity {

	private ListView listView;
	private List<History> list;
	private ImageView iv_back;
	
	private EditText et_username;
	private EditText et_idcard;
	private Button btn_select;
	
	private SharedPreferences sp;
	
	private static final String hisSearchUrl = "http://123.57.59.200:8080/api/MobileHabitus/GetPadSurveyresults";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_history);
		
		initView();
		initEvent();
	}


	@Override
	public void initView() {
		// TODO Auto-generated method stub
		listView = (ListView) findViewById(R.id.freelook_listview);
		iv_back = (ImageView) findViewById(R.id.iv_history_back);
		
		et_username = (EditText) findViewById(R.id.et_history_username);
		et_idcard = (EditText) findViewById(R.id.et_history_idcard);
		btn_select = (Button) findViewById(R.id.btn_history_select);
		list = new ArrayList<History>();
		sp = getSharedPreferences("config", MODE_PRIVATE);
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		//查询按钮注册监听
		btn_select.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//设置显示数据的对应适配器
				String username = et_username.getText().toString().trim();
				String idcard = et_idcard.getText().toString().trim();
				RequestParams params = new RequestParams();
				params.put("TNo", sp.getString("DeviceID",""));
				params.put("TSn", sp.getString("License",""));
				if(!TextUtils.isEmpty(username)){
					params.put("RealName", username);
				}
				if(!TextUtils.isEmpty(idcard)){
					params.put("IDCard", idcard);
				}
				HttpUtil.post(hisSearchUrl, params, new AsyncHttpResponseHandler(){
					@Override
					public void onSuccess(int statusCode, String content) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, content);
						if(statusCode == 200){
							JSONObject obj = JSON.parseObject(content);
							if(obj.getString("success").equals("true")){
								list.clear();
								History h = new History();
								//list = FastJsonTools.getBeans(obj.getString("data"), History.class);
								
								JSONArray array = obj.getJSONArray("data");
								for (int i = 0; i < array.size(); i++) {
									h.setCreateTime(array.getJSONObject(i).getString("CreateTime"));
									h.setMainHabitus(array.getJSONObject(i).getString("MainHabitus"));
									h.setOtherHabitus(array.getJSONObject(i).getString("OtherHabitus"));
									h.setResultID(array.getJSONObject(i).getString("ResultID"));
									list.add(h);
								}
								listView.setAdapter(new HistoryAdapter(EvaluateHistoryActivity.this, list));
							}else{
								Toast.makeText(EvaluateHistoryActivity.this, obj.getString("message"), Toast.LENGTH_LONG).show();
							}
						}
						
					}
						
					@Override
					public void onFailure(Throwable error, String content) {
						// TODO Auto-generated method stub
						super.onFailure(error, content);
						Toast.makeText(EvaluateHistoryActivity.this, R.string.neterror, Toast.LENGTH_LONG).show();
					}
				});
				
				listView.setAdapter(new HistoryAdapter(EvaluateHistoryActivity.this, list));
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
//				list.add("新增会员");
//				//listView.changeHeaderViewByState();
//				//设置新增数据后界面刷新显示
//				mAdapter.notifyDataSetChanged();
//				//刷新完成后的操作，如隐藏刷新head
//				listView.onRefreshComplete();
//			}
//		});
	}
}
