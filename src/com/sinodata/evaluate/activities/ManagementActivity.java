package com.sinodata.evaluate.activities;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.sinodata.evaluate.BaseActivity;
import com.sinodata.evaluate.MyApplication;
import com.sinodata.evaluate.R;
import com.sinodata.evaluate.utils.LicenseManager;

public class ManagementActivity extends BaseActivity {

	private ImageView iv_management_back;
	private GridView gridView;
	private LicenseManager lm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_management);
		
		
		initView();
		initEvent();
	}
	
	public void initView(){
		iv_management_back = (ImageView) findViewById(R.id.iv_management_back1);
		gridView = (GridView) findViewById(R.id.gridview); 
		lm = new LicenseManager();
	}
	public void initEvent(){
		
		iv_management_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		 //生成动态数组，并且转入数据  
	      ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();  
	      String[] str = {"搜索附近WIFI","一键关机","用户注销","用户信息查询"};
	      for (String string : str) {
	    	  HashMap<String, Object> map = new HashMap<String, Object>();  
		      map.put("ItemImage", R.drawable.ic_launcher);//添加图像资源的ID  
		      map.put("ItemText", string);//按序号做ItemText  
		      lstImageItem.add(map);  
		}
	      //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应  
		SimpleAdapter saImageItems = new SimpleAdapter(this, // 没什么解释
				lstImageItem,// 数据来源
				R.layout.management_item,// night_item的XML实现

				// 动态数组与ImageItem对应的子项
				new String[] { "ItemImage", "ItemText" },

				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.ItemImage, R.id.ItemText });
	      //添加并且显示  
	      gridView.setAdapter(saImageItems);  
	      //添加消息处理  
	      gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				@SuppressWarnings("unchecked")
				HashMap<String, Object> item = (HashMap<String, Object>) parent.getItemAtPosition(position);
				// 显示所选Item的ItemText
				//setTitle((String) item.get("ItemText"));
				switch((String)item.get("ItemText")){
				case "搜索附近WIFI":
					startActivity(new Intent(ManagementActivity.this, WifiListActivity.class));
					overridePendingTransition(android.R.anim.slide_in_left,
							android.R.anim.slide_out_right);
					break;
				case "一键关机":
					new AlertDialog.Builder(ManagementActivity.this).setTitle("您确认是否关机么？").setPositiveButton("关机", new DialogInterface.OnClickListener() {
				
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Process proc;
							try {
								proc = Runtime.getRuntime().exec(new String[]{"su","-c","reboot -p"});
								proc.waitFor();
								Toast.makeText(ManagementActivity.this, "自动关机", Toast.LENGTH_SHORT).show();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}  
						}
					}).setNegativeButton("不关机", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					}).show();
				
					break;
				case "用户注销":
					lm.saveLicense("","","");
					MyApplication.exit();
					break;
				case "用户信息查询":
					break;
					default:
				}
			}
		});
	  }  
}
