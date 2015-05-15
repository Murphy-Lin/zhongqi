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
		 //���ɶ�̬���飬����ת������  
	      ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();  
	      String[] str = {"��������WIFI","һ���ػ�","�û�ע��","�û���Ϣ��ѯ"};
	      for (String string : str) {
	    	  HashMap<String, Object> map = new HashMap<String, Object>();  
		      map.put("ItemImage", R.drawable.ic_launcher);//���ͼ����Դ��ID  
		      map.put("ItemText", string);//�������ItemText  
		      lstImageItem.add(map);  
		}
	      //������������ImageItem <====> ��̬�����Ԫ�أ�����һһ��Ӧ  
		SimpleAdapter saImageItems = new SimpleAdapter(this, // ûʲô����
				lstImageItem,// ������Դ
				R.layout.management_item,// night_item��XMLʵ��

				// ��̬������ImageItem��Ӧ������
				new String[] { "ItemImage", "ItemText" },

				// ImageItem��XML�ļ������һ��ImageView,����TextView ID
				new int[] { R.id.ItemImage, R.id.ItemText });
	      //��Ӳ�����ʾ  
	      gridView.setAdapter(saImageItems);  
	      //�����Ϣ����  
	      gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				@SuppressWarnings("unchecked")
				HashMap<String, Object> item = (HashMap<String, Object>) parent.getItemAtPosition(position);
				// ��ʾ��ѡItem��ItemText
				//setTitle((String) item.get("ItemText"));
				switch((String)item.get("ItemText")){
				case "��������WIFI":
					startActivity(new Intent(ManagementActivity.this, WifiListActivity.class));
					overridePendingTransition(android.R.anim.slide_in_left,
							android.R.anim.slide_out_right);
					break;
				case "һ���ػ�":
					new AlertDialog.Builder(ManagementActivity.this).setTitle("��ȷ���Ƿ�ػ�ô��").setPositiveButton("�ػ�", new DialogInterface.OnClickListener() {
				
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Process proc;
							try {
								proc = Runtime.getRuntime().exec(new String[]{"su","-c","reboot -p"});
								proc.waitFor();
								Toast.makeText(ManagementActivity.this, "�Զ��ػ�", Toast.LENGTH_SHORT).show();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}  
						}
					}).setNegativeButton("���ػ�", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					}).show();
				
					break;
				case "�û�ע��":
					lm.saveLicense("");
					MyApplication.exit();
					break;
				case "�û���Ϣ��ѯ":
					break;
					default:
				}
			}
		});
	  }  
}
