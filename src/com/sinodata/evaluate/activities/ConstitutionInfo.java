package com.sinodata.evaluate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.sinodata.evaluate.BaseActivity;
import com.sinodata.evaluate.R;

public class ConstitutionInfo extends BaseActivity implements OnClickListener{

	private static final String typeAURL = "file:///android_asset/html/ATYPE.html";
	private static final String typeBURL = "file:///android_asset/html/BTYPE.html";
	private static final String typeCURL = "file:///android_asset/html/CTYPE.html";
	private static final String typeDURL = "file:///android_asset/html/DTYPE.html";
	private static final String typeEURL = "file:///android_asset/html/ETYPE.html";
	private static final String typeFURL = "file:///android_asset/html/FTYPE.html";
	private static final String typeGURL = "file:///android_asset/html/GTYPE.html";
	private static final String typeHURL = "file:///android_asset/html/HTYPE.html";
	private static final String typeIURL = "file:///android_asset/html/ITYPE.html";
	private ImageView iv_back;
	private ImageView iv_type_a;
	private ImageView iv_type_b;
	private ImageView iv_type_c;
	private ImageView iv_type_d;
	private ImageView iv_type_e;
	private ImageView iv_type_f;
	private ImageView iv_type_g;
	private ImageView iv_type_h;
	private ImageView iv_type_i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_constitutioninfo);
		initView();
		initEvent();
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		iv_back = (ImageView) findViewById(R.id.iv_type_back);
		iv_type_a = (ImageView) findViewById(R.id.iv_type_a);
		iv_type_b = (ImageView) findViewById(R.id.iv_type_b);
		iv_type_c = (ImageView) findViewById(R.id.iv_type_c);
		iv_type_d = (ImageView) findViewById(R.id.iv_type_d);
		iv_type_e = (ImageView) findViewById(R.id.iv_type_e);
		iv_type_f = (ImageView) findViewById(R.id.iv_type_f);
		iv_type_g = (ImageView) findViewById(R.id.iv_type_g);
		iv_type_h = (ImageView) findViewById(R.id.iv_type_h);
		iv_type_i = (ImageView) findViewById(R.id.iv_type_i);
		
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		iv_back.setOnClickListener(this);
		iv_type_a.setOnClickListener(this);
		iv_type_b.setOnClickListener(this);
		iv_type_c.setOnClickListener(this);
		iv_type_d.setOnClickListener(this);
		iv_type_e.setOnClickListener(this);
		iv_type_f.setOnClickListener(this);
		iv_type_g.setOnClickListener(this);
		iv_type_h.setOnClickListener(this);
		iv_type_i.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.iv_type_a :
			Intent intenta = new Intent(this,WebViewActivity.class);
			intenta.setAction(typeAURL);
			startActivity(intenta);
			break;
		case R.id.iv_type_b :
			Intent intentb = new Intent(this,WebViewActivity.class);
			intentb.setAction(typeBURL);
			startActivity(intentb);
			break;
		case R.id.iv_type_c :
			Intent intentc = new Intent(this,WebViewActivity.class);
			intentc.setAction(typeCURL);
			startActivity(intentc);
			break;
		case R.id.iv_type_d :
			Intent intentd = new Intent(this,WebViewActivity.class);
			intentd.setAction(typeDURL);
			startActivity(intentd);
			break;
		case R.id.iv_type_e :
			Intent intente = new Intent(this,WebViewActivity.class);
			intente.setAction(typeEURL);
			startActivity(intente);
			break;
		case R.id.iv_type_f :
			Intent intentf = new Intent(this,WebViewActivity.class);
			intentf.setAction(typeFURL);
			startActivity(intentf);
			break;
		case R.id.iv_type_g :
			Intent intentg = new Intent(this,WebViewActivity.class);
			intentg.setAction(typeGURL);
			startActivity(intentg);
			break;
		case R.id.iv_type_h :
			Intent intenth = new Intent(this,WebViewActivity.class);
			intenth.setAction(typeHURL);
			startActivity(intenth);
			break;
		case R.id.iv_type_i :
			Intent intenti = new Intent(this,WebViewActivity.class);
			intenti.setAction(typeIURL);
			startActivity(intenti);
			break;
		case R.id.iv_type_back :
			finish();
			default :
		}
	}

}
