package com.sinodata.evaluate.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinodata.evaluate.MyApplication;
import com.sinodata.evaluate.R;
import com.sinodata.evaluate.activities.ManageActivity;
import com.sinodata.evaluate.activities.PrepareActivity;
import com.sinodata.evaluate.utils.LicenseManager;

public class AccountManageFragment extends Fragment implements OnClickListener{

	private TextView tv_sn;
	private TextView tv_license;
	private TextView tv_username;
	private TextView tv_company;
	//private Button btn_logout;
	private LicenseManager lm;
	private SharedPreferences sp;
	private ManageActivity ma;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ma = new ManageActivity();
		lm = new LicenseManager();
		MyApplication.getContext();
		sp = MyApplication.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		View v = inflater.inflate(R.layout.fragment_accountmanage, container, false);
		tv_sn = (TextView) v.findViewById(R.id.tv_account_sn);
		tv_license = (TextView) v.findViewById(R.id.tv_account_license);
		tv_username = (TextView) v.findViewById(R.id.tv_account_username);
		tv_company = (TextView) v.findViewById(R.id.tv_account_company);
		//btn_logout = (Button) v.findViewById(R.id.btn_fragment_logout);
		
		//btn_logout.setOnClickListener(this);
		String company = sp.getString("Company", "");
		tv_company.setText(company);
		String username = sp.getString("Username", "");
		tv_username.setText(username);
		String license = sp.getString("License", "");
		tv_license.setText(license);
		String sn = sp.getString("DeviceID", "");
		tv_sn.setText(sn);
		return v;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		lm.saveLicense("", "", "");
		Intent in = new Intent(MyApplication.getContext(),PrepareActivity.class);
		startActivity(in);
		ma.finish();
	}
	
}
